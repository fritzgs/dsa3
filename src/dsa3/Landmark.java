package dsa3;


/**
 * @author fritz
 */
public class Landmark {
	
	private String name;
	private int value;
	private boolean wasChecked;
	
	/**
	 * Constructor - takes in the name of the landmark.
	 * @param name
	 */
	public Landmark(String name)
	{
		this.name = name;
		this.value = 1000000;
		this.wasChecked = false;
	}
	
	/**
	 * Flag to indicate if the landmark has been checked when doing shortest path traversal.
	 * @return boolean
	 */
	public boolean getWasChecked()
	{
		return wasChecked;
	}
	
	/**
	 * Get the name of the landmark
	 * @return String - name of landmark.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * 
	 * @return int of landmark's value/distance from source landmark.
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * Change the flag.
	 * @param b
	 */
	public void setWasChecked(boolean b)
	{
		this.wasChecked = b;
	}
	
	/**
	 * Give another value to the amount/distance from source a.
	 * @param v
	 */
	public void setValue(int v)
	{
		this.value = v;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
}
