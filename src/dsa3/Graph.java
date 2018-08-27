package dsa3;

public class Graph {
	private final int MAX_LANDMARKS = 100;
	private final int INFINITY = 1000000;
	
	private Landmark list[];
	private int matrix[][];
	private int currentSize;
	private Stack<Integer> stack;
	
	private int currentLandmark;
	
	
	public Graph()
	{
		list = new Landmark[100];
		matrix = new int[MAX_LANDMARKS][MAX_LANDMARKS];
		currentSize = 0;
		
		for(int i = 0; i < MAX_LANDMARKS; i++)
			for(int j = 0; j < MAX_LANDMARKS; j++)
				matrix[i][j] = INFINITY;
		
		stack = new Stack<>();
	}
	
	public void addConnection(Landmark landmark1, Landmark landmark2, int distance)
	{
		int index1 = 0;
		int index2 = 0;
		
		for(int i = 0; i < list.length; i ++)
		{
			if(list[i].equals(landmark1))
			{
				index1 = i;
			}
			if(list[i].equals(landmark2))
			{
				index2 = i;
			}
		}
		
		matrix[index1][index2] = distance;
		matrix[index2][index1] = distance;
	}
	
	public void addLandmark(Landmark l)
	{
		list[currentSize++] = l;
		addConnection(l, l, 0);
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
	
	
}
