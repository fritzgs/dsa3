/**
 * @author fritz
 * 
 */
package dsa3;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author fritz
 */
public class Graph {
	private final int MAX_LANDMARKS = 100;
	private final int INFINITY = 1000000;
	
	private Landmark list[];
	private int matrix[][];
	private int currentSize;
	public ArrayList<Connection> connections;
	
	private int currentLandmark;
	
	/**
	 * Constructor
	 */
	public Graph()
	{
		connections = new ArrayList<>();
		list = new Landmark[100];
		
		matrix = new int[MAX_LANDMARKS][MAX_LANDMARKS];
		currentSize = 0;
		
		for(int i = 0; i < MAX_LANDMARKS; i++)
			for(int j = 0; j < MAX_LANDMARKS; j++)
				matrix[i][j] = INFINITY;
	}
	
	/**
	 * Connects two landmarks together.
	 * Takes in names of the two landmarks and the distance between them. It also takes in a string array of the streets in between the landmarks.
	 * @param landmark1
	 * @param landmark2
	 * @param distance
	 * @param streets
	 */
	public void addConnection(String landmark1, String landmark2, int distance, ArrayList<String> streets)
	{
		//used as checker if landmark exist or not.
		boolean land1 = false;
		boolean land2 = false;
		
		//Check if the landmarks exist
		for(int j = 0; j < list.length;j++)
		{
			if(list[j] != null)
			{
				if(list[j].getName().toLowerCase().equals(landmark1.toLowerCase()))
				{
					land1 = true;
				}
				if(list[j].getName().toLowerCase().equals(landmark2.toLowerCase()))
				{
					land2 = true;
				}
			}
		}
		
		//if both landmarks exist, continue...
		if(land1 == true && land2 == true)
		{
			int index1 = 0;
			int index2 = 0;
			
			//find the actual index of each landmarks.
			for(int i = 0; i < list.length; i ++)
			{
				if(list[i] != null)
				{
					if(list[i].getName().toLowerCase().equals(landmark1.toLowerCase()))
					{
						index1 = i;
					}
					if(list[i].getName().toLowerCase().equals(landmark2.toLowerCase()))
					{
						index2 = i;
					}
				}
			}
		
			//make a connection object out of the two landmarks - forward and backwards
			Connection connect = new Connection(list[index1], list[index2]);
			Connection revConnect = new Connection(list[index2], list[index1]);
			
			//add the streets to each connection
			if(streets.size() > 0)
			{
				for(String s : streets)
				{
					connect.addStreet(s);
				}
				
				for(int j = streets.size()-1; j >= 0; j--)
				{
					revConnect.addStreet(streets.get(j));
				}
				
				connections.add(connect);
				connections.add(revConnect);
			}
			
			//set the distance in the matrix.
			matrix[index1][index2] = distance;
			matrix[index2][index1] = distance;
		}
		//print this if one or two of the landmarks doesn't exist.
		else
		{
			System.out.println("One or two of the landmark names are incorrect - does not exist in list");
		}
		
	}
	
	/**
	 * Add landmark to the graph.
	 * @param l
	 */
	public void addLandmark(Landmark l)
	{
		list[currentSize++] = l;
		addConnection(l.getName(), l.getName(), 0, new ArrayList<String>());
		
	}

	
	public Landmark[] getList()
	{
		return list;
	}
	
	/**
	 * Get all the landmarks in the graph.
	 */
	public void getAllLandmarks()
	{
		Stack<Integer> stack = new Stack<>();

		list[0].setWasChecked(true);
		stack.push(0);
		System.out.println(list[0]);

		while(!stack.isEmpty())
		{
			int adj = getAdjacentLandmark(stack.peek());
			if(adj == -1)
			{
				stack.pop();
			}
			else
			{
				list[adj].setWasChecked(true);
				stack.push(adj);
				System.out.println(list[adj]);
			}
		}
		
		//reset the flag to false
		for(int i=0; i < currentSize; i++)
		{
			list[i].setWasChecked(false);
		}
	
	}
	
	/**
	 * Dijkstra's Algorithm.
	 * Get the shortest path from source to destination.
	 * Uses string names of the landmarks.
	 * @param start
	 * @param dest
	 */
	public void getShortestPath(String start, String dest)
	{
		Stack<Integer> stack = new Stack<>();
		ArrayList<Integer> unvisited = new ArrayList<>();
		
		int startIndex = 0;
		int endIndex = 0;
		int prev[] = new int[currentSize]; //for reference of which landmarks are on route of shortest path.
		for(int i=0; i < currentSize; i++)
		{
			//add each landmark to unvisited array initially.
			unvisited.add(i);
			prev[i] = -1; //set each one of them as index -1.
			//get the actual index of the landmarks.
			if(list[i].getName().toLowerCase().equals(start.toLowerCase()))
			{
				startIndex = i;
			}
			if(list[i].getName().toLowerCase().equals(dest.toLowerCase()))
			{
				endIndex = i;
			}

		}
		
		//set the startIndex as having value of 0.
		list[startIndex].setValue(0);

		//loop until the destination has been checked.
		while(list[endIndex].getWasChecked() == false)
		{
			currentLandmark = getMin(unvisited); 
			ArrayList<Integer> adj = getAdjacents(currentLandmark);
			for (int lm : adj)
			{
				//change the value of landmark if the route from current is less than what has been previously stated.
				if(list[lm].getValue() > list[currentLandmark].getValue() + matrix[currentLandmark][lm])
				{
					list[lm].setValue(list[currentLandmark].getValue() + matrix[currentLandmark][lm]);
					prev[lm] = currentLandmark;
				}
			}
			
			list[currentLandmark].setWasChecked(true);
			unvisited.remove(unvisited.indexOf(currentLandmark));
		
		}

		//pushes all the landmarks that are in shortest path to stack.
		while(prev[endIndex] != -1)
		{
			stack.addSize();
			stack.push(endIndex);
			endIndex = prev[endIndex]; //assigns the next to check as the previous index in array.
		}

		stack.push(startIndex);

		//add the indexes into int array.
		int[] path = new int[stack.size()];
		for(int j =0; j < stack.size(); j++)
		{
			path[j] = stack.pop();
			
		}
				
		
		boolean once = true;
		for(int k=0; k < path.length;k++)
		{
			for(Connection c : connections)
			{
				//this will run only until before the length of path array.
				if(k+1 != path.length)
				{
					//Only print this once.
					if(once)
					{
						System.out.println(list[path[k]].getName() + " to " + list[path[path.length-1]].getName());
						System.out.println("via... \n");
						once = false;
					}
					
					//if the landmark names provided match the connection object in the loop -> print the streets.
					if(list[path[k]].getName().equals(c.getLandmarkSrc().getName()) && list[path[k+1]].getName().equals(c.getLandmarkDest().getName()))
					{
						for(String st : c.getStreets())
						{
							System.out.println(st);
						}
					}
				}
				//if the source and destination are the same - print this.
				if(matrix[path[0]][path[path.length-1]] == 0)
				{
					if(once)
					{
						System.out.println(list[path[0]] + " to " + list[path[path.length-1]]);
						System.out.println("\nYou are in already at the landmark!");
						once = false;
					}
					
				}
			}
			
			
		}
		
		//prints the total duration of the walk.
		System.out.println("\nDuration of walk: " + list[path[path.length-1]].getValue() + " minutes.");
		
		//resets the checked flag.
		for(Landmark l : list)
		{
			if(l != null)
			{
				l.setWasChecked(false);
				l.setValue(1000000);
			}
		}
	
	}

	/**
	 * Get the lowest value index out of the connected landmarks.
	 * @param arr
	 * @return index of the landmark with the lowest value
	 */
	private int getMin(ArrayList<Integer> arr)
	{
		int min = 0;
		ArrayList<Integer> values = new ArrayList<>();
		for (int i : arr)
		{
			values.add(list[i].getValue());
			
		}
		
		Collections.sort(values);
		
		for (int j : arr)
		{
			if(values.get(0) == list[j].getValue())
			{
				min = j;
			}
		}
		
		return min;
		
	}
	
	/**
	 * Get the first unchecked landmark that is adjacent to this.
	 * @param t
	 * @return index of landmark.
	 */
	public int getAdjacentLandmark(int t)
	{
		for(int i = 0; i < currentSize; i++)
			if(matrix[t][i] != INFINITY && !list[i].getWasChecked())
				return i;
		
		return -1;
	}
	
	/**
	 * Get the index of all the adjacent landmarks
	 * @param t
	 * @return an array list of int indexes of all the landmarks that are adjacent.
	 */
	public ArrayList<Integer> getAdjacents(int t)
	{
		ArrayList<Integer> adj = new ArrayList<>();
		for(int i = 0; i < currentSize; i++)
		{
			if(matrix[t][i] != INFINITY)
			{
				adj.add(i);
			}
		}
		
		return adj;
	}
	
	public int[][] getmatrix()
	{
		return matrix;
	}
}
