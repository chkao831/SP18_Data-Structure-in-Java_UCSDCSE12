
public class Stack12<E> implements BoundedStack<E> {
	
	/** 
	 * In addition to the methods required in the definition of this interface,
	 * a class implementing this interface should provide a public constructor
	 * with a single argument of type <tt>int</tt>, which specifies the
	 * capacity of the BoundedStack.  The constructor should throw an
	 * IllegalArgumentException if the specified capacity is negative.
	 */
	private BoundedDeque<E> theStack;
	
	public Stack12( int capacity ) {
		theStack = new Deque12<E>( capacity );
	}

	  /**
	   * Returns the capacity of this BoundedStack, that is,
	   * the maximum number of elements it can hold.
	   * <br>PRECONDITION: none
	   * <br>POSTCONDITION: the BoundedStack is unchanged.
	   * @return the capacity of this BoundedStack
	   */
	  public int capacity() {
		  return theStack.capacity();
	  }

	  /**
	   * Returns the number of elements in this BoundedStack.
	   * <br>PRECONDITION: none
	   * <br>POSTCONDITION: the BoundedStack is unchanged.
	   * @return the number of elements in this BoundedStack
	   */
	  public int size()
	  {
		  return theStack.size();
	  }

	  /**
	   * Adds the specified element to the top of this BoundedStack.
	   * Returns true if the operation succeeded, else false.
	   * <br>PRECONDITION: the BoundedStack's size is less than its capacity.
	   * <br>POSTCONDITION: the element is now the top element in this
	   * BoundedStack, none of the other elements have been changed, and
	   * the size is increased by 1.
	   * @param e the element to add to the stack
	   * @return <tt>true</tt> if the element was added, else <tt>false</tt>.
	   * @throws NullPointerException if the specified element is null,
	   * and size is less than capacity
	   */
	  public boolean push(E e)
	  {
		  return theStack.addFront( e );
	  }

	  /**
	   * Removes the element at the top of this BoundedStack.
	   * Returns the element removed, or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedStack's size is greater than zero.
	   * <br>POSTCONDITION: the top element in this BoundedStack has been removed,
	   * none of the other elements have been changed, and
	   * the size is decreased by 1.
	   * @return  the element removed, or <tt>null</tt> if the size was zero.
	   */
	  public E pop()
	  {
		  return theStack.removeFront();
	  }

	  /**
	   * Returns the element at the top of this BoundedStack,
	   * or <tt>null</tt> if there was no such element.
	   * <br>PRECONDITION: the BoundedStack's size is greater than zero.
	   * <br>POSTCONDITION: The BoundedStack is unchanged.
	   * @return  the element at the top, or <tt>null</tt> if the size was zero.
	   */
	  public E peek()
	  {
		  return theStack.peekFront();
	  }

	  /**
	   * Compares the specified object with this BoundedStack for equality.
	   * Returns true if and only if the specified object is
	   * also a BoundedStack of the same type, both have the same size, and the
	   * corresponding pairs of elements in the two are
	   * equal. Two elements e1 and e2 are equal if e1.equals(e2).
	   * "Corresponding pairs" means: the pair of top elements,
	   * the pair of next-to-top elements, and so on through to
	   * the pair of bottom elements.
	   * @return true if the specified Object is equal to this BoundedStack
	   * @param  o the Object to compare to this BoundedStack for equality
	   */
	  public boolean equals(Object o)
	  {
		  return theStack.equals( o );
	  }
}
