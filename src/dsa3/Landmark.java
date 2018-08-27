package dsa3;

public class Landmark {
	
	private String name;
	private int value;
	private boolean wasChecked;
	
	public Landmark(String name)
	{
		this.name = name;
		this.value = 1000000;
		this.wasChecked = false;
	}
	
	public boolean getWasChecked()
	{
		return wasChecked;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getValue()
	{
		return value;
	}
	
	
	public void setWasChecked(boolean b)
	{
		this.wasChecked = b;
	}
	
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
