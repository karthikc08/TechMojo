package datastructure;

import java.util.List;
/**
 * Data Object to store the tweet , hastag and number of tweets of hastag
 * @author Karthik_Vajja
 *
 */
public class DataDO {
	private int counter;
	private String hastag;
	private List<String> tweet;
	
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public String getHastag() {
		return hastag;
	}
	public void setHastag(String hastag) {
		this.hastag = hastag;
	}
	public List<String> getTweet() {
		return tweet;
	}
	public void setTweet(List<String> tweet) {
		this.tweet = tweet;
	}
		
}
