package dsa3;

/**
 * @author fritzgeraldsantos
 */
public interface StackInterface<T>
{
	/**
	 * @return true if stack is empty, else returns false if stack contains elements
	 */
	public boolean isEmpty();
	
	
	/**
	 * @return element at the top of the stack but it does not remove it.
	 */
	public T peek();
	
	
	/**
	 * @return element at the top of the stack and removes it from the stack
	 */
	public T pop ();
	
	
	/**
	 * adds element to stack
	 * @param element
	 * @return element
	 */
	public T push(T element);
	
	
	/**
	 * searches for element in stack, if found, its index from the top of the stack is returned. Otherwise, 1 is returned.
	 * @param element
	 * @return element
	 */
	public int search(T element);
			
}