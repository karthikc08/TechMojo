package workpackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datastructure.DataDO;

public class Main {
	private static final String HASH = "#";
	/**
	 * Main method triggering the process to find # tags
	 * To exit console inputs enter exit in the command line console.
	 * @param args
	 */
	public static void main(String[] args) {
		String keyword = "exit";		
		Map<String, DataDO> registeredData = new HashMap<>(); 
		try (InputStreamReader in = new InputStreamReader(System.in);
				BufferedReader buffer = new BufferedReader(in)) {
			String line;	            
			while ((line = buffer.readLine()) != null) {
				if (line.equalsIgnoreCase(keyword)) {
					break;
				}	    
				findHashTag(line, registeredData);
			}
			printTopHashTags(registeredData);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Finding the Hash tag in the tweet and storing in data structure Map.
	 * managing the tweet having two hash tags as well. 
	 * @param line
	 * @param registeredData
	 */
	private static void findHashTag(String line, Map<String, DataDO> registeredData) {		
		String comment = line;
		boolean continueLoop = true;
		do {
			int endPos = 0;
			int hashLocation = comment.indexOf(HASH);
			if(hashLocation < 0) {
				continueLoop = false;
			}else {
				int nextSpaceIndex = comment.indexOf(' ',hashLocation);
				if (nextSpaceIndex < 0) {
					// Hash tag at the end of tweet
					endPos = comment.length();            
				} else {
					//finding the Hashtag in between the tweet
					endPos = nextSpaceIndex;	            
				}  
				String hashTag = comment.substring(hashLocation, endPos);
				DataDO value = registeredData.get(hashTag);
				if(value == null) {
					DataDO newValue = new DataDO();
					newValue.setCounter(1);
					newValue.setHastag(hashTag);
					List<String> tweet = new ArrayList<>();
					tweet.add(line);
					newValue.setTweet(tweet);
					registeredData.put(hashTag,newValue);
				}else {
					value.setCounter(value.getCounter()+1);
					value.getTweet().add(line);
				}    
				comment = comment.substring(endPos);	
			}
		}while(continueLoop);	       		
	}
	
	/**
	 * find top 10 hash tags registered and print them in console.
	 * Tags are sorted in descending order of counter value.
	 * @param registeredData
	 */
	private static void printTopHashTags(Map<String, DataDO> registeredData) {
		List<DataDO> dataSet = new ArrayList<>();
		//Adding the map data into list
		registeredData.forEach((key,value) -> dataSet.add(value));
		//Sorting in descending order based on the counter value of each hash tag
		dataSet.sort((o1,o2) -> o2.getCounter() - o1.getCounter());
		int loop = 0;
		for(DataDO data : dataSet) {
			loop++;
			System.out.println(data.getHastag());
			if(loop > 9)	break;		
		}
	}

}
