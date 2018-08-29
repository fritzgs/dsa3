package dsa3;

import java.util.ArrayList;

/**
 * @author fritz
 */
public class Connection {
	
	private Landmark landmark1;
	private Landmark landmark2;
	private ArrayList<String> streets;
	
	/**
	 * Constructor
	 * @param landmarkSrc
	 * @param landmarkDest
	 */
	public Connection(Landmark landmarkSrc, Landmark landmarkDest)
	{
		this.landmark1 = landmarkSrc;
		this.landmark2 = landmarkDest;
		this.streets = new ArrayList<>();
	}
	
	/**
	 * Get the source landmark
	 * @return
	 */
	public Landmark getLandmarkSrc()
	{
		return landmark1;
	}
	
	/**
	 * Get the destination landmark
	 * @return
	 */
	public Landmark getLandmarkDest()
	{
		return landmark2;
	}
	
	/**
	 * Add a street that will need to be walked through to get from a to b.
	 * @param street
	 */
	public void addStreet(String street)
	{
		streets.add(street);
	}

	public ArrayList<String> getStreets()
	{
		return streets;
	}
	
	

}
