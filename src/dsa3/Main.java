package dsa3;

import java.util.ArrayList;
import java.util.Scanner;

import dsa3.Graph;
import dsa3.Landmark;

public class Main
{
	
	private Scanner sc = new Scanner(System.in);
	private Graph graph;

	public static void main(String[] args) {
		Main main = new Main();
		main.graph = new Graph();
		main.demo();
		main.menu();
	}

	private void menu()
	{
		System.out.println("Select an input from 1-6.");
		System.out.println("1) Find route from Landmark A to Landmark B.");
		System.out.println("2) Display all the Landmarks listed.");
		System.out.println("3) Add new Landmark.");
		System.out.println("4) Add a connection between two Landmarks.");
		System.out.println("5) Exit.");
		System.out.println();
		System.out.println("Selection: ");

		String option = sc.next();
		
		switch(option)
		{
			case "1":
				optionOne();
				sc.next();
				menu();
				break;
			case "2":
				graph.getAllLandmarks();
				System.out.println();
				menu();
				break;
			case "3":
				System.out.println("Name of the town to add: ");
				String landmarkName = sc.next();
				Landmark landmark = new Landmark(landmarkName);
				graph.addLandmark(landmark);
				System.out.println();
				menu();
				break;
			case "4":
				optionFour();
				System.out.println();
				menu();
				break;
			case "5":
				System.out.println("Exiting...");
				System.exit(0);
	
			default:
				System.out.println("Not a valid input!");
				System.out.println();
				menu();
		}
	}
	
	private void optionOne()
	{
		System.out.println("Find route from Landmark A to Landmark B.");
		
		System.out.println("Enter the name of Landmark A: ");
		System.out.println();
		String town1 = sc.next();
		town1 += sc.nextLine();

		System.out.println();
		System.out.println("Enter the name of Lanmark B: ");
		String town2 = sc.nextLine();
		
		graph.getShortestPath(town1, town2);
		System.out.println();
		System.out.println();

	}
	
	//Adding two connections 
	//need to add landmark 
	//need to prompt streets.
	private void optionFour()
	{
		System.out.println("Name of the first landmark to add: ");
		String landmark1 = sc.next();
		System.out.println();
		System.out.println("Name of the second landmark to add: ");
		String landmark2 = sc.next();
		System.out.println();
		System.out.println("Distance between the two landmarks: ");
		int dist = sc.nextInt();	
		System.out.println();
		System.out.println("How many streets will be passed on the way to Landmark B?: ");
		int stNo = sc.nextInt();
		System.out.println();
		ArrayList<String> streets = new ArrayList<>();
		for(int i = 0; i < stNo; i++)
		{
			System.out.println("Street "+ Integer.valueOf(i+1) +": ");
			String street = sc.next();
			streets.add(street);
		}
		graph.addConnection(landmark1, landmark2, dist, streets);
	}
	
	
	private void demo()
	{
		Landmark[] landmarks = {
		new Landmark("Waterford Crystal"),					//0
		new Landmark("Medieval Museum"),					//1
		new Landmark("Reginald's Tower"),					//2
		new Landmark("Christ Church"),						//3
		new Landmark("Thomas Francis Meagher Statue"),		//4
		new Landmark("Apple Market"),						//5
		new Landmark("John Condon Memorial"),				//6
		new Landmark("Waterford Museum of Treasures"),		//7
		new Landmark("Clock Tower")							//8
		};
		
		for(Landmark l : landmarks)
		{
			graph.addLandmark(l);
		}
		
		ArrayList<String> streets = new ArrayList<>();
		streets.add("The Mall");
		graph.addConnection(landmarks[0].getName(), landmarks[2].getName(), 2, streets);
		streets.clear();
		
		streets.add("Parnell Street");
		streets.add("John's Avenue");
		graph.addConnection(landmarks[0].getName(), landmarks[5].getName(), 6, streets);
		streets.clear();
		
		graph.addConnection(landmarks[0].getName(), landmarks[7].getName(), 1, streets);
		
		streets.add("The Mall");
		graph.addConnection(landmarks[0].getName(), landmarks[4].getName(), 2, streets);
		streets.clear();
		
		graph.addConnection(landmarks[2].getName(), landmarks[4].getName(), 0, streets);
		
		streets.add("Bailey's New Street");
		streets.add("Cathedral Square");
		graph.addConnection(landmarks[2].getName(), landmarks[6].getName(), 2, streets);
		streets.clear();		
		
		streets.add("The Mall");
		graph.addConnection(landmarks[2].getName(), landmarks[7].getName(), 3, streets);
		streets.clear();
		
		streets.add("The Quay");
		graph.addConnection(landmarks[2].getName(), landmarks[8].getName(), 6, streets);
		streets.clear();
		
		streets.add("Cathedral Square");
		graph.addConnection(landmarks[1].getName(), landmarks[6].getName(), 1, streets);
		streets.clear();
		
		graph.addConnection(landmarks[1].getName(), landmarks[7].getName(), 1, streets);
		
		streets.add("Cathedral Square");
		streets.add("Collbeck Street");
		streets.add("Parnell Street");
		graph.addConnection(landmarks[2].getName(), landmarks[7].getName(), 3, streets);
		streets.clear();
		
		graph.addConnection(landmarks[3].getName(), landmarks[6].getName(), 0, streets);

		streets.add("Henrietta Street");
		streets.add("The Quay");
		graph.addConnection(landmarks[3].getName(), landmarks[8].getName(), 6, streets);
		streets.clear();
		
		
		streets.add("Michael Street");
		streets.add("Broad Street");
		streets.add("Barrondstrand Street");
		graph.addConnection(landmarks[5].getName(), landmarks[8].getName(), 5, streets);
		streets.clear();
		
		streets.add("Parnell Street");
		streets.add("John's Avenue");
		graph.addConnection(landmarks[5].getName(), landmarks[7].getName(), 5, streets);
		streets.clear();
		
		
	}
}
