package dsa3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GraphTest {
	
	private Graph g;
	private Landmark johnrsq;
	private Landmark watcry;
	private Landmark reginalds;
	private Landmark clocktow;

	void setUp()
	{
		g = new Graph();
		johnrsq = new Landmark("John Robert's Square");
		watcry = new Landmark("Waterford Crystal");
		reginalds = new Landmark("Reginald's Tower");
		clocktow = new Landmark("Clock Tower");
		
		
		g.addLandmark(johnrsq);
		g.addLandmark(watcry);
		g.addLandmark(reginalds);
		g.addLandmark(clocktow);
		
		
		ArrayList<String> regwatSt = new ArrayList<>();
		regwatSt.add("The Mall");
		g.addConnection(reginalds.getName(), watcry.getName(), 3, regwatSt);
		
		ArrayList<String> regclockSt = new ArrayList<>();
		regclockSt.add("Meagher's Quay");
		regclockSt.add("Parade Quay");
		g.addConnection(reginalds.getName(), clocktow.getName(), 10, regclockSt);
		
		ArrayList<String> watcryJRS = new ArrayList<>();
		watcryJRS.add("Collbeck Street");
		watcryJRS.add("Lady Lane");
		watcryJRS.add("Bakehouse Lane");
		watcryJRS.add("Broad Street");
		g.addConnection(watcry.getName(), johnrsq.getName(), 7, watcryJRS);
		
		ArrayList<String> reg2JohnRSq = new ArrayList<>();
		reg2JohnRSq.add("Parade Quay");
		reg2JohnRSq.add("Henrietta Street");
		watcryJRS.add("High Street");
		watcryJRS.add("Blackfriars");
		g.addConnection(reginalds.getName(), johnrsq.getName(), 7, reg2JohnRSq);
		
		ArrayList<String> johnrsq2clocktow = new ArrayList<>();
		johnrsq2clocktow.add("Barronstrand Street");
		g.addConnection(johnrsq.getName(), clocktow.getName(), 2, johnrsq2clocktow);
	}
	
	@Test
	void testAddLandmark() {
		setUp();
		assertEquals("Reginald's Tower", g.getList()[2].getName());
		assertFalse(g.getList()[0].getName() == "Waterford Crystal");
	}
	
	

	@Test
	void testShowAllLandmark()
	{
		setUp();
		g.getAllLandmarks();
	}
	
	@Test
	void testShortestPath()
	{
		setUp();
		g.getShortestPath("Waterford Crystal", "Clock Tower");
		g.getShortestPath("Waterford Crystal", "Waterford Crystal");

	}

}
