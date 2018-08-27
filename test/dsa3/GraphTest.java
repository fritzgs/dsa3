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
		g.addConnection(reginalds, watcry, 3, regwatSt);
		
		ArrayList<String> regclockSt = new ArrayList<>();
		regclockSt.add("Meagher's Quay");
		regclockSt.add("Parade Quay");
		g.addConnection(reginalds, clocktow, 10, regclockSt);

	}
	
	@Test
	void testAddLandmark() {
		setUp();
		assertEquals("Reginald's Tower", g.getList()[2].getName());
		assertFalse(g.getList()[0].getName() == "Waterford Crystal");
	}

}
