package dsa3;

import java.util.ArrayList;

public class Connection {
	
	private Landmark landmark1;
	private Landmark landmark2;
	private ArrayList<String> streets;
	
	public Connection(Landmark landmarkSrc, Landmark landmarkDest)
	{
		this.landmark1 = landmarkSrc;
		this.landmark2 = landmarkDest;
		this.streets = new ArrayList<>();
	}
	
	public Landmark getLandmarkSrc()
	{
		return landmark1;
	}
	
	public Landmark getLandmarkDest()
	{
		return landmark2;
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
