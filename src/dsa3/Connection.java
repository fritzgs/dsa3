package dsa3;

import java.util.ArrayList;

public class Connection {
	
	private int landmark1;
	private int landmark2;
	private int distance;
	private ArrayList<String> streets;
	
	public Connection(int landmarkSrc, int landmarkDest, int distance)
	{
		this.landmark1 = landmarkSrc;
		this.landmark2 = landmarkDest;
		this.distance = distance;
		this.streets = new ArrayList<>();
	}
	
	public int getLandmarkSrc()
	{
		return landmark1;
	}
	
	public int getLandmarkDest()
	{
		return landmark2;
	}
	
	public int getDistance()
	{
		return distance;
	}
	
	public void addStreet(String street)
	{
		streets.add(street);
	}
	
	public ArrayList<String> getStreets()
	{
		return streets;
	}
	
	

}
