import java.util.*;
/**File: Deque12.java
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * ID: A92092372
 * Email: c4kao@ucsd.edu
 * Date: May 3, 2018
 */

/**
 * A java class that implements the BoundedDeque interface using a circular
 * array. This implementation must use a circular array to hold its elements. 
 */
public class Deque12<E> implements BoundedDeque<E>
{

    //instance variables
    //capacity specifies the maximum number of elements which can hold
    public int capa;
    //the size of the Deque12
    public int size;
    //current front
    public int front;
    //current back
    public int rear;
    //ArrayList object with equal capacity, and length [capacity+1]
    public ArrayList<E> list;

    /**
     * A public constructor with a single argument of type <tt>int</tt>,
     * which specifies the capacity of the BoundedDeque. 
     * The constructor should throw an IllegalArgumentException if the specified
     * capacity is negative. 
     * @param capacity specifies the capacity of the BoundedDeque
     */
    public Deque12(int capacity) throws IllegalArgumentException{

        //if negative, throws exception
        if(capacity < 0 ){
            throw new IllegalArgumentException("negative capacity");
        }
        //otherwise, set capacity
        capa = capacity;

        //initialize arraylist based on capacity
        list = new ArrayList<E>();
        //add capacity items to list such that no IndexOutOfBounds
        for(int i = 0; i<capacity+1; i++){
            list.add(i,null);
        }
    }

    /**Method: capacity
     * Returns the capacity of this BoundedDeque, the maximum number of elements
     * it can hold
     * <br>PRECONDITION: none
     * <br>POSTCONDITION: the BoundedDeque is unchanged
     * @return the capacity of this BoundedDeque
     */
    public int capacity(){
        return this.capa;
    }

    /**Method: size
     * Returns the number of elements in the BoundedDeque
     * <br>PRECONDITION: none
     * <br>POSTCONDITION: the BoundedDeque is unchanged
     * @return the number of elements in this BoundedDeque
     */
    public int size(){
        return this.size;
    }

    /**Method: addFront
     * Adds the specified element to the front of this BoundedDeque. 
     * <br>PRECONDITION: size is less than capacity
     * <br>POSTCONDITION: new element in the front, none of other elements 
     * are changed, and size increments by 1
     * @param e the element to add to the front of the list
     * @return true if the operation succeeded; else false
     * @exception NullPointerException if the specified element is null, and
     * size is less than capacity
     */
    public boolean addFront(E e) throws NullPointerException{

        //firstly throw exception if e is null but there's space to add more
        if(this.size < this.capa && e==null){
            throw new NullPointerException("nonempty deque with null ele");
        }

        //if full, or passing in null element, no operation succeeded
        else if(this.size >= this.capa || e==null){
            return false;
        }
        //if the deque is not full, and the element is valid
        else{

            //if empty, directly add at Front
            if(this.size == 0){
                front = 0;
                rear = 0;
                list.set(0,e);
                size++;
            }

            //if not empty
            else {
                //initialize local variables
                int addAt = 0;

                //use math to traverse to the front
                //capacity plus one yields the length of arraylist
                if(this.front-1 < 0){
                    addAt = (this.capa+1)-(Math.abs(this.front-1));
                }
                else{
                    addAt = (this.front-1)%(this.capa+1);
                }

                //replace the element at position [addAt] with e in the deque
                list.set(addAt,e);
                //assigning front to this element
                this.front = addAt;
                //increment size
                size++;
            }
            //successfully addFront, return true
            return true;
        }
    }

    /**Method: addBack
     * Adds the specified element to the back of this BoundedDeque
     * <br>PRECONDITION: size is less than capacity
     * <br>POSTCONDITION: new element in the back, none of other elements 
     * are changed, and size increments by 1
     * @param e the element to add to the back of the list
     * @return true if the element was added, else false. 
     * @exception NullPointerException if the specified element is null, and
     * size is less than capacity
     */
    public boolean addBack(E e){
        //firstly throw exception if e is null but there's space to add more
        if(this.size < this.capa && e==null){
            throw new NullPointerException("nonempty deque with null ele");
        }

        //if full, or passing in null element, no operation succeeded
        else if(this.size >= this.capa || e==null){
            return false;
        }

        //if deque is not full and element is valid
        else{
            //if empty
            if(this.size == 0){
                front = 0;
                rear = 0;
                list.set(0,e);
                size++;
            }

            //if not empty
            else{

                //note that capacity plus one is equivalent to length of
                //arraylist
                int addAt = (this.rear+1)%(this.capa+1);
                //replace the element at position [addAt] with e in the deque
                list.set(addAt,e);
                //assigning rear to this element
                this.rear = addAt;
                //increment size
                this.size++;
            }
            //successfully add back, return true
            return true;
        }
    }

    /**Method: removeFront
     * Removes the element at the front of this BoundedDeque. 
     * <br>PRECONDITION: the size is greater than zero
     * <br>POSTCONDITION: new element in the front, none of other elements 
     * are changed, and size decrements by 1
     * @return the element removed, or null if there was no such element
     */
    public E removeFront(){
        //if this is an empty deque
        if(this.size == 0){
            //there is not first element
            return null;
        }
        else{
            int deleteAt = this.front;
            //catch the return item
            E beReturned = list.get(front);
            //assigning the element to null
            E nullEle = null;
            list.set(deleteAt,nullEle);
            this.front = (this.front+1)%(this.capa+1);

            //decrement size
            this.size--;

            return beReturned;
        }
    }

    /**Method: removeBack
     * Removes the element at the back of the BoundedDeque.
     * <br>PRECONDITION: the size is greater than zero
     * <br>POSTCONDITION: new element in the back, none of other elements 
     * are changed, and size decrements by 1
     * @return the element removed, or null if there was no such element
     */
    public E removeBack(){
        //if this is an empty deque
        if(this.size == 0){
            //there is not first element
            return null;
        }
        else{
            int deleteAt = this.rear;
            E beReturned = list.get(rear);
            //assigning the element to null
            E nullEle = null;
            list.set(deleteAt,nullEle);

            //use math to find the former position
            if(this.rear-1 < 0){
                this.rear = (this.capa+1)-(Math.abs(this.rear-1));
            }
            else{
                this.rear = (this.rear-1)%(this.capa+1);
            }
            //decrement size
            this.size--;

            return beReturned;
        }
    }

    /**Method: peekFront
     * Returns the element at the front of this BoundedDeque, or null if there
     * was no such element
     * <br>PRECONDITION: the size is greater than zero
     * <br>POSTCONDITION: deque is unchanged
     * @return the element at the front, or null if the size was zero
     */
    public E peekFront(){
        //if this is an empty deque
        if(this.size == 0){
            //there is no first element
            return null;
        }
        //otherwise, if the deque is not empty
        else {
            //return the element at the front of this deque
            return list.get(front);
        }
    }

    /**Method: peekBack
     * Returns the element at the back of this BoundedDeque, or null if there
     * was no such element
     * <br>PRECONDITION: the size is greater than zero
     * <br>POSTCONDITION: deque is unchanged
     * @return the element at the back, or null if the size was zero
     */
    public E peekBack(){

        //if this is an empty deque
        if(this.size == 0){
            //there is no rear element
            return null;
        }
        //otherwise, if the deque is not empty
        else {
            //return the element at the rear of this deque
            return list.get(rear);
        }
    }
}
