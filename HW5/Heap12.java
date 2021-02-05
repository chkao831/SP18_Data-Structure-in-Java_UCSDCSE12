import java.util.ArrayList;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
/** 
 * File: Heap12.java
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * PID: A92092372
 * Email: c4kao@ucsd.edu
 * Date: May 27, 2018
 * Source of Help: Zybook (textbook)
 */

/**
 * Heap12 class that implements an unbounded array-backed heap structure and is
 * an extension of the Java Collections AbstractQueue class 
 * <p>
 * The elements of the heap are ordered according to their natural 
 * ordering,  Heap12 does not permit null elements. 
 * The top of this heap is the minimal or maximal element (called min/max)  
 * with respect to the specified natural ordering.  
 * If multiple elements are tied for min/max value, the top is one of 
 * those elements -- ties are broken arbitrarily. 
 * The queue retrieval operations poll, remove, peek, and element 
 * access the element at the top of the heap.
 * <p>
 * A Heap12 is unbounded, but has an internal capacity governing the size of 
 * an array used to store the elements on the queue. It is always at least as 
 * large as the queue size. As elements are added to a Heap12, its capacity 
 * grows automatically. The details of the growth policy are not specified.
 * <p>
 * This class and its iterator implements the optional methods of the 
 * Iterator interface (including remove()). The Iterator provided in method 
 * iterator() is not guaranteed to traverse the elements of the Heap12 in 
 * any particular order. 
 * <p>
 * Note that this implementation is not synchronized. Multiple threads 
 * should not access a Heap12 instance concurrently if any of the 
 * threads modifies the Heap12. 
 */
public class Heap12<E extends Comparable <? super E>> extends 
AbstractQueue<E> 
{

    //capacity of 5 for no-arg Heap12 constructor
    public final int FIVE = 5;
    //impossible index
    public final int NEGATIVEINDEX = -1;

    /** instance variables */
    //the current capacity of the Heap
    private int capa;
    //the current size of the Heap
    private int size;
    //ArrayList object with specified capacity
    private ArrayList<E> heap;
    //boolean to indicate max or min
    private boolean isMax;
    //boolean to indicate if bubbledUp successfully
    private boolean bubbled;

    /** these instances are for iterator use in percolate(up/down)methods */
    //memorylist list is an auxiliary list that stores "Remember me" items
    private ArrayList<E> memorylist;
    //integer array that stores the remove index corresponding to memorylist
    private ArrayList<Integer> memorylistIndex;
    //integer that keeps track of what next() method goes through
    private int memoryIndex;

    /** 0-argument constructor. Creates an empty Heap12 with capacity of 5
     *  elements, and is a min-heap 
     */ 
    public Heap12()
    {
        capa = FIVE;
        size = 0;
        heap = new ArrayList<E>(capa);
        //add capacity items to heap to avoid IndexOutOfBounds 
        for(int i = 0; i < capa; i++){
            heap.add(i,null);
        }
        isMax = false;
        memorylistIndex = new ArrayList<Integer>();
    }

    /** 
     * Constructor to build a min or max heap
     * @param isMaxHeap 	if true, this is a max-heap, else a min-heap. Initial
     * capacity of the heap should be 5.
     */ 
    public Heap12( boolean isMaxHeap)
    {
        capa = FIVE;
        size = 0;
        heap = new ArrayList<E>(capa);
        //add capacity items to heap to avoid IndexOutOfBounds 
        for(int i = 0; i < capa; i++){
            heap.add(i,null);
        }
        isMax = isMaxHeap;
        memorylistIndex = new ArrayList<Integer>();
    }

    /** 
     * Constructor to build a heap with specified initial capacity 
     *  min or max heap
     * @param capacity  	initial capacity of the heap.	
     * @param isMaxHeap 	if true, this is a max-heap, else a min-heap 
     */ 
    public Heap12( int capacity, boolean isMaxHeap)
    {
        capa = capacity;
        size = 0;
        heap = new ArrayList<E>(capacity);
        //add capacity items to heap to avoid IndexOutOfBounds 
        for(int i = 0; i < capa; i++){
            heap.add(i,null);
        }
        isMax = isMaxHeap;
        memorylistIndex = new ArrayList<Integer>();
    }

    /** Copy constructor. Creates Heap12 with a deep copy of the argument
     * @param toCopy      the heap that should be copied
     */
    public Heap12 (Heap12<E> toCopy)
    {
        capa = toCopy.capacity();
        size = toCopy.size();
        heap = new ArrayList<E>(capa);
        //add capacity items to heap to avoid IndexOutOfBounds 
        for(int i = 0; i < capa; i++){
            heap.add(i,null);
        }
        for(int i = 0; i < toCopy.size(); i++){
            this.heap.set(i,toCopy.getE(i));
        }
        isMax = toCopy.isMax();
        memorylistIndex = new ArrayList<Integer>();
    }

    /* The following are defined "stub" methods that provide degenerate
     * implementations of methods defined as abstract in parent classes.
     * These need to be coded properly for Heap12 to function correctly
     */

    /** Size of the heap
     * @return the number of elements stored in the heap
     */
    public int size()
    {
        return this.size;
    }

    /** 
     * @return an Iterator for the heap 
     */
    public Iterator<E> iterator()
    {
        return new Heap12Iterator();
    }

    /** 
     * Retrieve, but not remove, the element at top of heap.
     * @return Element at top of heap. Do not remove 
     *  return <tt>null</tt> if the heap is empty
     */
    public E peek()
    {
        return this.getE(0);
    }

    /** 
     * remove and return the highest priority element
     * <br>PRECONDITION: size is greater than zero
     * <br>POSTCONDITION: size is decreased by 1, highest priority element is
     * removed.
     * @return Element at top of heap. And remove it from the heap. 
     * 	return <tt>null</tt> if the heap is empty
     */
    public E poll()
    {   
        if(this.size()==0){
            return (E)null;
        }
        //extract the top element from the heap and store it in preTop
        E preTop = this.getE(0);
        //move the rightmost leaf element into top
        this.heap.set(0,this.getE(this.size()-1));
        //remove the rightmost leaf element 
        this.heap.set(this.size()-1, null);
        //decrement size by 1
        this.size--;
        //fix the heap starting at the top
        trickleDown(0);


        return preTop;
    }

    /** 
     * insert an element in the heap
     * <br>PRECONDITION: element is comparable to other elements in the heap
     * <br>POSTCONDITION: size is increased by 1
     * @return true
     * @throws ClassCastException 
     * 	if the class of the element prevents it from being added
     * @throws NullPointerException 
     * 	if the specified element is null	
     * @throws IllegalArgumentException 
     * 	if some property of the element keeps it from being added. 
     */
    public boolean offer (E e)
    {
        //if the specified element is null, throw NullPointerException
        if( e == null ){
            throw new NullPointerException("specified element is null");
        }

        try{
            //if the current size of the Heap12 is equal to the length of its
            //backing store array, the length of its backing store array must
            //double
            if(this.size() == this.capacity()){
                ArrayList<E> largeArr = new ArrayList<E>(2*(this.capacity()));
                //add capacity items to heap to avoid IndexOutOfBounds 
                for(int i = 0; i < 2*(this.capacity()) ; i++){
                    largeArr.add(i,null);
                }
                //copy over all old elements
                for(int i = 0; i < this.size(); i++){
                    largeArr.set(i,this.getE(i));
                }
                this.heap = largeArr;
                this.capa *= 2;
            }
            //insert the new element as the new rightmost leaf on the bottom level
            this.heap.set(this.size(),e); 
            //increment size by 1
            this.size++;
            //bubbleUp(rightmost leaf)
            this.bubbleUp(this.size()-1);
            //return true when successfully added
            return true;

        } catch (ClassCastException exp){
            throw new ClassCastException("class cast exception occurs!");
        } catch (IllegalArgumentException exp){
            throw new IllegalArgumentException("illegal argument exception!!");
        }
    }

    /* ------ Private Helper Methods --- */

    /**
     * This method uses the percolate algorithm (recursion) to operate on an
     * arraylist to heapify it
     * @param index   the position at which bubble up is performed
     * @return the index that the element of the pass-in index ends up at
     */
    private int bubbleUp(int index){
        int returnValue = index;
        bubbled = false;

        //if index is root of heap, return
        if (index == 0){
            return 0;
        }
        E parentEle = this.getE(this.parent(index));
        //MaxHeap: if element at this index is smaller than or equal to 
        //parent's element, return
        if(this.isMax()){
            if (this.getE(index).compareTo(parentEle) <= 0){
                return index;
            }
        }
        //MinHeap: if element at this index is bigger than or equal to 
        //parent's element, return
        else{
            if (this.getE(index).compareTo(parentEle) >= 0){
                return index;
            }

        }
        //swap node and parent's element
        //set parent to child element
        this.heap.set(this.parent(index), this.getE(index));
        //set child to parent element
        this.heap.set(index, parentEle);


        //check whether the swap element is in the memory list 
        for(int i=0; i<memorylistIndex.size();i++){
            //change the index of it if inside the memory list
            //such that the new index of the element in tht memory list
            //is the right one to store (after swapping)
            if(this.parent(index) == memorylistIndex.get(i)){
                memorylistIndex.set(i, index);
            }
        }

        //bubbleUp(parent's index)
        returnValue = bubbleUp(this.parent(index));
        //reset boolean
        bubbled = true;

        return returnValue;
    }

    /**
     * This method uses the percolate algorithm (iterably) to operate on an
     * arraylist to heapify it
     * @param index   the position at which trickle down is performed
     */
    private void trickleDown (int index){
        //catch the child index of the passed-in index
        int leftChild = this.leftChild(index);
        //store the current element 
        E currentEle = this.getE(index);

        //as long as child is within the range
        while (leftChild < this.size()){
            E criticalValue = currentEle;
            int criticalIndex = NEGATIVEINDEX;
            //compare child from both sides
            //if it's MaxHeap
            if(this.isMax()){
                for(int i = 0; i < 2 && i + leftChild < this.size(); i++){
                    //if any child is bigger, catch it
                    if((this.getE(i+leftChild)).compareTo(criticalValue)>0){
                        criticalValue = this.getE(i+leftChild);
                        criticalIndex = i + leftChild;
                    }
                }
                //if no child is bigger, no change, return. 
                if(criticalValue.compareTo(currentEle)==0){
                    return;
                }
                //after comparison, perform swapping
                else {
                    this.heap.set(criticalIndex,this.getE(index));
                    this.heap.set(index, criticalValue);
                    index = criticalIndex;
                    leftChild = this.leftChild(index);
                }
                //otherwise, if it's MinHeap
            } else {
                for(int i = 0; i < 2 && i + leftChild < this.size(); i++){
                    //if any child is smaller, catch it
                    if((this.getE(i+leftChild)).compareTo(criticalValue)<0){
                        criticalValue = this.getE(i+leftChild);
                        criticalIndex = i + leftChild;
                    }
                }

                //if no child is smaller, no change, return. 
                if(criticalValue.compareTo(currentEle)==0){
                    return;
                }
                //after comparison, perform swapping
                else {
                    this.heap.set(criticalIndex,this.getE(index));
                    this.heap.set(index, criticalValue);
                    index = criticalIndex;
                    leftChild = this.leftChild(index);
                }
            }
        }
    }

    /**
     * This method returns the index of the parent of a specific child index
     * @param child     the specific child index
     * @return the parent's index
     */
    private int parent (int child){
        int parent = 0;
        parent = (child-1)/2;
        return parent;
    }

    /**
     * This method returns the index of the left child of a specific parent
     * index
     * @param parent    the specific parent index
     * @return the left child's index
     */
    private int leftChild (int parent){
        int leftChild = 0;
        leftChild = 2*parent +1;
        return leftChild;
    }

    /**
     * Getter method for capacity of the heap.
     * @return the capacity of the heap
     */
    private int capacity(){
        return this.capa;
    }

    /**
     * Getter method for isMax instance of the heap. 
     * @return true if the heap is a max Heap
     *  return false if the heap is a min Heap
     */
    private boolean isMax(){
        return this.isMax; 
    }

    /**
     * Getter method for element at specific index of the heap. 
     * @param index    the index at which the element is returned
     * @return the element at the specific index
     */
    private E getE(int index){
        return this.heap.get(index);
    }

    /**
     * Debug usage: print out
     * Commented out. 
     * This prints out what's inside the heap to the terminal. 
     public void printOut(){
     for (int i = 0; i < this.size(); i++){
     System.out.print(this.heap.get(i)+ " ");
     }
     System.out.println();
     }
     */

    /** Inner Class for an Iterator 
      This is a recommended class name. You may change it**/
    private class Heap12Iterator implements Iterator<E>
    {
        /** instance variables */
        //canRemove boolean to make sure if there's previous next call
        private boolean canRemove;
        //boolean to check if memory list is activated
        private boolean memorylistActivated;
        //boolean to check if we're currently into the memory list in remove()
        private boolean intoMemory;
        //index to indicate current location
        private int index;

        /* there are several ways to iterate through a heap, 
         * the simplest is breadth-first, which is just through
         * the indices
         */

        /**
         * no-arg constuctor of the Heap12Iterator
         */
        private Heap12Iterator()
        {
            canRemove = false;
            index = 0;
            memorylist = new ArrayList<E>();
        }

        /**
         * returns true if the iteration has more elements; false otherwise
         * @return true if the iteration has more elements
         *  return false if the iteration has no more element
         */
        public boolean hasNext()
        {
            return (this.index < size);
        }

        /**
         * returns the next element in the iteration
         * @return the element in the iteration
         * @throws NoSuchElementException   if iteration has no more elements
         */ 
        public E next() throws NoSuchElementException
        {
            //if there's next element in the heap
            //return the element at the next index
            if(hasNext()){
                canRemove = true;
                index++;
                return heap.get(index - 1);
            }
            //check if the memorylist list is activated in the process
            else if(memorylistActivated == true){
                canRemove = true;
                E element = memorylist.get(memoryIndex);
                intoMemory = true;
                //increment memoryIndex to start keeping track
                memoryIndex++;
                return element;
            }
            //otherwise, throw exception
            else {
                throw new NoSuchElementException("No next element");
            }
        }

        /**
         * removes from underlying collection the last element returned by
         * this iterator
         * @throws IllegalStateException   if the next method has not yet been
         * called, or the remove method has already been called after the last
         * call to the next method
         */
        public void remove() throws IllegalStateException
        {
            //if there's no next call
            if (canRemove == false){
                throw new IllegalStateException("no next call");
            }
            //remove the memory list element
            else if(intoMemory == true){

                //swap up the rightmost leaf element to the memory list
                //element
                heap.set(memorylistIndex.get(memoryIndex-1),heap.get(size-1));
                heap.set(size-1,null);
                size--;
                //reheapify the heap
                bubbleUp(memorylistIndex.get(memoryIndex-1));
                //if not bubble up then trickle down 
                if(bubbled == false){
                    trickleDown(memorylistIndex.get(memoryIndex-1));
                }
                canRemove = false;
                return;
            }

            //if it's last element, simply remove
            if((index - 1) == (size - 1)){
                heap.set((size-1),null);
                size--;
                canRemove = false;
                return;
            }

            //remove from middle case
            //store the temporary element
            E ele = heap.get(size - 1);
            //move the rightmost leaf element into next position
            heap.set((index - 1), ele);
            //remove the rightmost leaf element
            heap.set((size - 1), null);
            //decrement size
            size--;
            //fix the heap at this index
            //first call bubble up
            int bubbleIndex = bubbleUp(index - 1);

            //if bubble up succeeds,
            //add ele to memorylist list, will come back later
            //also add the index corresponding to the element of it
            if (bubbled == true){
                memorylistActivated = true;
                memorylist.add(ele);
                memorylistIndex.add(bubbleIndex);
            } 
            //if bubble up not success
            //then trickle down
            else {
                trickleDown(index - 1);
                //decrement index (come back to newly switched position)
                index --;
            }
            //reset boolean
            canRemove = false;
            bubbled = false;
        }	
    }
} 
// vim:ts=4:sw=4:tw=78:et
