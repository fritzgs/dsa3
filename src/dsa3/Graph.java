package dsa3;

import java.util.ArrayList;
import java.util.Collections;

public class Graph {
	private final int MAX_LANDMARKS = 100;
	private final int INFINITY = 1000000;
	
	private Landmark list[];
	private int matrix[][];
	private int currentSize;
	private Stack<Integer> stack;
	public ArrayList<Connection> connections;
	
	private int currentLandmark;
	
	
	public Graph()
	{
		connections = new ArrayList<>();
		list = new Landmark[100];
		
		matrix = new int[MAX_LANDMARKS][MAX_LANDMARKS];
		currentSize = 0;
		
		for(int i = 0; i < MAX_LANDMARKS; i++)
			for(int j = 0; j < MAX_LANDMARKS; j++)
				matrix[i][j] = INFINITY;
		
		stack = new Stack<>();
	}
	
	public void addConnection(String landmark1, String landmark2, int distance, ArrayList<String> streets)
	{
		boolean land1 = false;
		boolean land2 = false;
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
		
		if(land1 == true && land2 == true)
		{
			int index1 = 0;
			int index2 = 0;
			
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
		
			Connection connect = new Connection(list[index1], list[index2]);
			Connection revConnect = new Connection(list[index2], list[index1]);
			
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
			
			matrix[index1][index2] = distance;
			matrix[index2][index1] = distance;
		}
		else
		{
			System.out.println("One or two of the landmark names are incorrect - does not exist in list");
		}
		
	}
	
	public void addLandmark(Landmark l)
	{
		list[currentSize++] = l;
		addConnection(l.getName(), l.getName(), 0, new ArrayList<String>());
		
	}

	public int getAdjacentUnvisitedTown(int l)
	{
		for(int i=0; i < currentSize; i++)
		{
			if(matrix[l][i] != INFINITY && list[i].getWasChecked()==false)
			{
				return i;
			}
		}
		
		return -1;
	}
	
	public Landmark[] getList()
	{
		return list;
	}
	
	public void getAllLandmarks()
	{
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
	
	public void getShortestPath(String start, String dest)
	{
		ArrayList<Integer> unvisited = new ArrayList<>();
		int startIndex = 0;
		int endIndex = 0;
		int prev[] = new int[currentSize];
		for(int i=0; i < currentSize; i++)
		{
			unvisited.add(i);
			prev[i] = -1;
			if(list[i].getName().toLowerCase().equals(start.toLowerCase()))
			{
				startIndex = i;
			}
			if(list[i].getName().toLowerCase().equals(dest.toLowerCase()))
			{
				endIndex = i;
			}

		}
		
		list[startIndex].setValue(0);

		
		while(list[endIndex].getWasChecked() == false)
		{
			currentLandmark = getMin(unvisited);
			ArrayList<Integer> adj = getAdjacents(currentLandmark);
			for (int lm : adj)
			{
				if(list[lm].getValue() > list[currentLandmark].getValue() + matrix[currentLandmark][lm])
				{
					list[lm].setValue(list[currentLandmark].getValue() + matrix[currentLandmark][lm]);
					prev[lm] = currentLandmark;
				}
			}
			
			list[currentLandmark].setWasChecked(true);
			unvisited.remove(unvisited.indexOf(currentLandmark));
		
		}
		
		
		

		while(prev[endIndex] != -1)
		{
			stack.addSize();
			stack.push(endIndex);
			endIndex = prev[endIndex];
		}

		stack.push(startIndex);

		
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
			
				if(k+1 != path.length)
				{
					if(once)
					{
						System.out.println(list[path[k]].getName() + " to " + list[path[path.length-1]].getName());
						System.out.println("via... \n");
						once = false;
					}
						
					if(list[path[k]].getName().equals(c.getLandmarkSrc().getName()) && list[path[k+1]].getName().equals(c.getLandmarkDest().getName()))
					{
						System.out.println(c.getStreets());
					}
				}
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
		
		System.out.println("\nDuration of walk: " + list[path[path.length-1]].getValue() + " minutes.");
	}

	
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
	
	public int getAdjacentLandmark(int t)
	{
		for(int i = 0; i < currentSize; i++)
			if(matrix[t][i] != INFINITY && !list[i].getWasChecked())
				return i;
		
		return -1;
	}
	
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
