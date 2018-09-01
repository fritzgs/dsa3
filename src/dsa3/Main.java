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
		String town1 = sc.next();
		System.out.println();
		System.out.println("Enter the name of Lanmark B: ");
		String town2 = sc.next();
		
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
	
}
