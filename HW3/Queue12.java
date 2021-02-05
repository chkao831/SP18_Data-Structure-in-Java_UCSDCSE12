/**File: Queue12.java
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * ID: A92092372
 * Email: c4kao@ucsd.edu
 * Date: May 4, 2018
 */

/**
 * A java class that implements the BoundedQueue interface
 * The BoundedQueue interface is an interface that specifies the familiar queue
 * abstraction, but with limited capacity. 
 */
public class Queue12<E> implements BoundedQueue<E>{
    
    /**
     * In addition to the methods required in the definition of this interface,
     * a class implementing this interface should provide a public constructor
     * with a single argument of type <tt>int</tt>, which specifies the capacity
     * of the BoundedQueue. The constructor shoudl throw an
     * IllegalArgumentException if the specified capacity is negative.
     */

    //instance
    private BoundedDeque<E> theQueue;

    /**
     * one-arg constructor
     * @param capacity capacity of the queue
     */
    public Queue12(int capacity){
        theQueue = new Deque12<E>(capacity);
    }

    /**
     * Returns the capacity of the BoundedQueue, that is, 
     * the maximum number of elements it can hold
     * <br>PRECONDITION: none
     * <br>POSTCONDITION: the BoundedQueue is unchanged
     * @return the capacity fo this BoundedQueue
     */
    public int capacity(){
        return theQueue.capacity();
    }

    /**
     * Returns the number of elements in the BoundedQueue. 
     * <br>PRECONDITION: none
     * <br>POSTCONDITION: the BoundedQueue is unchanged
     * @return the number of elements in the BoundedQueue
     */
    public int size(){
        return theQueue.size();
    }

    /**
     * Returns the element at the head of this BoundedQueue, 
     * or <tt>null</tt> if there was no such element. 
     * <br>PRECONDITION: the BoundedQueue's size is greater than zero
     * <br>POSTCONDITION: the BoundedQueue is unchanged
     * @return the element at the head, or <tt>null</tt> if the size was zero
     */
    public E peek(){
        return theQueue.peekFront();
    }

    /**
     * Adds the specified element to the tail of this BoundedQueue. 
     * Returns true if the operation succeeded, else false.
     * <br>PRECONDITION: the BoundedQueue's size is less than capacity. 
     * <br>POSTCONDITION: the element is now the tail element in this 
     * BoundedQueue, none of the other elements have been changed, 
     * and the size is increased by 1.
     * @param e the element to add to the queue
     * @return <tt>true</tt> if the element was add, else <tt>false</tt>
     * @exception NullPointerException if the specified element is null, 
     * and the size is less than capacity
     */
    public boolean enqueue(E e){
        return theQueue.addBack( e );
    }

    /**
     * Removes the  element at the head of this BoundedQueue. 
     * Returns the element removed, or <tt>null</tt> if there was no such
     * element. 
     * <br>PRECONDITION: the BoundedQueue's size is greater than zero. 
     * <br>POSTCONDITION: the head element in this BoundedQueue has been
     * removed, none of the other elements have been changed, and the size is
     * decreased by 1.
     * @return the element removed, or <tt>null</tt> if the size was zero.
     */
    public E dequeue(){
        return theQueue.removeFront();
    }
}
