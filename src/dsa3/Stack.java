/**
 * @author fritzgeraldsantos
 */
package dsa3;


import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<T> implements StackInterface<T>{

	private T[] stack;
	private int top;
	private int size;
	
	@SuppressWarnings("unchecked")
	public Stack()
	{
		top=-1;
		size= 0;
		stack = (T[]) new Object[size];
	}
	
	@Override
	public boolean isEmpty() {
		if (top==-1)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public T peek() {
		T result = null;
		if(isEmpty())
		{
			System.out.println("Stack is empty");
			throw new EmptyStackException();
		}
		else
			result = stack[top];
		
		return result;
	}

	@Override
	public T pop() {
		return stack[top--];
	}

	@Override
	public T push(T element) {
		if(!isFull())
		{
			top++;
			stack[top] = element;
		}
		else if(isFull())
		{
			addSize();
			top++;
			stack[top] = element;
		}
		
		return element;
	}

	@Override
	public int search(T element) {
		for(int i = 0; i < size(); i++)
		{
			T object = stack[i];
			if(object.equals(element))
			{
				return top - i;
			}
		}
		
		return -1;
	}
	
	public boolean isFull()
	{
		return (top == stack.length -1);
	}
	
	public int size()
	{
		return this.size;
	}
	
	public void addSize()
	{
		size ++;
		stack = Arrays.copyOf(stack, size);
	}
 
	public void setSize(int size)
	{
		this.size = size;
	}
}
