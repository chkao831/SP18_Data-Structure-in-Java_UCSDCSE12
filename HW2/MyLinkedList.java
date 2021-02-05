import java.util.*;
/**
 * File: MyLinkedList.java
 *
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * PID: A92092372
 * Email: c4kao@ucsd.edu
 * Date: April 28, 2018
 */

/**
 * The class MyLinkedList extends the AbstractList
 */
public class MyLinkedList<E> extends AbstractList<E>  {

    //instance variables
    //the size of MyLinkedList
    private int size;
    //sentinel nodes
    private Node<E> head;
    private Node<E> tail;

    /**
     * The no-arg constructor for MyLinkedList
     * that creates an empty list
     * and initializes all the necessary variables to track the list
     */
    public MyLinkedList(){

        size = 0;
        head = new Node<E>();
        tail = new Node<E>();

        //construct an empty list with only sentinel nodes
        head.next = tail;
        tail.prev = head;
    }

    /**
     * This is an inner class, called Node, to represent a node in the linked
     * list. It is only usable within MyLinkedList. 
     */
    protected class Node<E> {
        E data;
        Node next;
        Node prev;

        /**
         * The no-arg constructor for the Node
         */
        public Node(){
            data = null;
            next = null;
            prev = null;
        }

        /**
         * Constructor that takes in the data of type E
         * @param theData: the data contained in the node
         */
        public Node(E theData){
            data = theData;
            next = null;
            prev = null;
        }
    }//end of Node inner class

    /**
     * This is an inner class, called MyListIterator. 
     * It has access to all of the MyLinkedList instance variables, including
     * private variables. 
     * To access the set() method of the MyLinkedList instance from within a
     * methodhere use
     *    MyLinkedList.this.set(i,e);
     */ 
    protected class MyListIterator implements ListIterator<E> {

        // instance variable
        Node<E> front;
        Node<E> back;
        int index;
        boolean nextcall;
        boolean prevcall;

        /**
         * no-arg constructor for MyListIterator
         */
        public MyListIterator(){

            front = new Node<E>();
            back = new Node<E>();
            index = 0;
            nextcall = false;
            prevcall = false;

            //firstly points to the beginning
            front = head;
            back = head.next;
        }

        /**
         * Method: add
         * Insert the given item into the list immediately before whatever
         * would have been returned by a call to next()
         * @param e the element to insert
         * @exception NullPointerException if e is null
         */
        @Override
        public void add(E e) throws NullPointerException
        {
            if(e == null){
                throw new NullPointerException("element is null");
            }

            //create a new node with element E
            Node<E> insert = new Node<E>(e);

            //reassigning the next pointer of the front node
            this.front.next = insert;
            //reassigning the prev pointer of the back node
            this.back.prev = insert;
            //reassigning the prev pointer of the new node
            insert.prev = this.front;
            //reassigning the next pointer of the new node
            insert.next = this.back;

            //reassigning iterator arrow
            this.back = insert;

            //update list size
            size++;

            //reset boolean
            nextcall = false;
            prevcall = false;

        }

        /**
         * Method: hasNext
         * Return true if there are more elements when going in the forward
         * direction
         * @return true if more element forward
         */
        @Override
        public boolean hasNext()
        {
            //if index is smaller than list size, return true
            //otherwise, return false
            return (this.index < size);
        }

        /**
         * Method: hasPrevious
         * Return true if there are more elements when going in the reverse
         * direction
         * @return true if more element backward
         */
        @Override
        public boolean hasPrevious()
        {
            //if index is bigger than zero, return true
            //otherwise, return false
            return (this.index > 0);
        }

        /**
         * Method: next
         * Return the next element in the list when going forward
         * @return the next element in the list
         * @exception NoSuchElementException if the iteration has no next
         * element
         */
        @Override
        public E next() throws NoSuchElementException
        {
            if(hasNext()){
                nextcall = true;
                prevcall = false;

                this.front = this.back;
                this.back = this.back.next;

                index++;

                return this.front.data;
            }
            else{
                throw new NoSuchElementException("No next element!");
            }
        }

        /**
         * Method: nextIndex
         * Return the index of the element that would be returned by a call to
         * next()
         * @return the index of the element that would be returned by a
         * subsequent call to next; or the list size if at the end of the list
         */
        @Override
        public int nextIndex()
        {
            //if there's next element
            if(hasNext()){
                //return the index of it
                return this.index ;
            }
            //otherwise, when reaching the end of the list (i.e. index = size)
            else {
                //return the size of the list
                return size;
            }
        }

        /**
         * Method: previous
         * Return the next element in the list when going backwards
         * @return the previous element in the list
         * @exception NoSuchElementException if the iteration has no previous
         * element
         */
        @Override
        public E previous() throws NoSuchElementException
        {
            if(hasPrevious()){
                prevcall = true;
                nextcall = false;

                this.back = this.front;
                this.front = this.front.prev;

                index--;

                return this.back.data;
            }
            else{
                throw new NoSuchElementException("No previous element!");
            }
        }

        /**
         * Method: previousIndex
         * Return the index of the element that would be returned by a call to
         * previous()
         * @return the index of the element backward; or -1 if at the start of
         * the list
         */
        @Override
        public int previousIndex()
        {
            //if there's previous element
            if(hasPrevious()){
                //return the index of it
                return (this.index)-1;
            }
            //otherwise, when this is at the start of the list (i.e. index=0)
            else {
                //return -1
                return -1;
            }
        }

        /**
         * Method: remove
         * Remove the last element returned by the most recent call to either
         * next/previous
         */
        @Override
        public void remove() throws IllegalStateException
        {
            if(nextcall == false && prevcall == false){
                throw new IllegalStateException("no call");
            }
            else{
                //if most recent call was next
                if(nextcall == true){
                    //reassigning the next pointer of the front node
                    this.front.prev.next = this.back;
                    //reassigning the prev pointer of the next node
                    this.back.prev = this.front.prev;

                    //reassigning iterator arrow
                    this.front = this.front.prev;
                    //update index
                    this.index = this.index - 1;

                    //reset the boolean
                    nextcall = false;
                    //update list size
                    size--;
                } 
                //otherwise, if most recent call was previous
                else {
                    //reassigning the prev pointer of the next node
                    this.back.next.prev = this.front;
                    //reassigning the next pointer of the previous node
                    this.front.next = this.back.next;

                    //reassigning iterator arrow
                    this.back = this.back.next;

                    //reset the boolean
                    prevcall = false;
                    //update list size
                    size--;
                }
            }
        }

        /**
         * Method: set
         * Change the value in the node returned by the most recent
         * next/previous with the new value
         * @param e the element with which to replace the last element
         * returned by next or previous
         */
        @Override
        public void set(E e)
        {
            if(e == null){
                throw new NullPointerException("element is null");
            }
            if(nextcall == false && prevcall == false){
                throw new IllegalStateException("no call");
            }
            else{
                //if most recent call was next
                if(nextcall == true){
                    this.front.data = e;
                }
                //otherwise, if most recent call was previous
                else {
                    this.back.data = e;
                }
            }
        }

    }//end of MyListIterator inner class

    // Methods for the MyLinkedList Class 

    /**
     * Method: getNth
     * A method that returns the Node at a specified index, not the content
     * @param index: the index at which the Node is returned
     * @return Node at a specified index
     * @exception IndexOutOfBoundsException if the index is out of range 
     */
    private Node getNth(int index) throws IndexOutOfBoundsException{

        if(isEmpty() || index > this.size() -1 || index < 0){
            throw new IndexOutOfBoundsException("specified index out of range.");
        }
        else{
            //create new Node to keep track
            Node<E> curr = new Node<E>();
            //point to first element;
            curr = head.next;

            for(int i = 0; i<index; i++){
                curr = curr.next;
            }
            return curr;
        }
    }

    /**
     * Method: add
     * A boolean add method that will presumably always return true; optional
     * operation
     * @param data: the specified element to be appended to the end of the list
     * @return true, as specified by Collection.add(E)
     * @exception NullPointerException if the specified element is null and this
     * list does not permit null elements
     */
    @Override
    public boolean add(E data) throws NullPointerException{

        if(data==null){
            throw new NullPointerException("data is null.");
        } else {
            Node<E> newNode = new Node<E>(data);

            //create a new Node to keep track
            Node<E> pred = new Node<E>();
            //firstly points to head
            pred = head;

            //create a new Node to keep track
            Node<E> succ = new Node<E>();
            //firstly points to head.next
            succ = head.next;

            for(int i=0; i<size; i++){
                pred = succ;
                succ = succ.next;
            }
            newNode.next = succ;
            newNode.prev = pred;
            pred.next = newNode;
            succ.prev = newNode;
            size++;

            return true;
        }
    }

    /**
     * Method: add
     * A method that inserts the specified element at the specified position in
     * the list; optional operation
     * @param index: the specified position
     * @param data: the specified element to be added
     * @exception IndexOutOfBoundsException if the index is out of range
     * @exception NullPointerException if the specified element is null and this
     * list does not permit null elements
     */
    @Override
    public void add(int index, E data) {

        if(data==null){
            throw new NullPointerException("data is null.");
        }
        if(index > this.size() || index<0 ){
            throw new IndexOutOfBoundsException("specified index out of range");
        }

        //create a new Node to capture the data
        Node<E> newNode = new Node<E>(data); 

        //if the list is empty
        if(head.next == tail && tail.prev == head){
            //index should only be zero, add it as the first element

            head.next = newNode;
            tail.prev = newNode;
            newNode.prev = head;
            newNode.next = tail;
            size++;

        }//otherwise, if the list is not empty
        //general case of add
        else{

            //create a new Node to keep track
            Node<E> pred = new Node<E>();
            //firstly points to head
            pred = head;
            //create a new Node to keep track
            Node<E> succ = new Node<E>();
            //firstly points to head.next
            succ = head.next;

            for(int i=0; i<index; i++){
                pred = succ;
                succ = succ.next;
            }
            newNode.next = succ;
            newNode.prev = pred;
            pred.next = newNode;
            succ.prev = newNode;
            size++;
        }
    }

    /**
     * Method: get
     * A method that gets contents at position [index]
     * @param index: the index that specifies the position
     * @return the contents at the position
     * @exception IndexOutOfBoundsException if the index is out of range 
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException{
        {
            if(isEmpty() || index>this.size()-1 || index<0){
                throw new IndexOutOfBoundsException("specified index out of range");
            }
            else{
                //create new Node to keep track
                Node<E> curr = new Node<E>();
                //point to first valid element
                curr = head.next;

                for(int i = 0; i< index; i++){
                    curr = curr.next;
                }
                return curr.data;
            }
        }
    }
    /**
     * Method: set
     * A method that sets the value at index [index] to [element]
     * @param index: the position at which the value is set
     * @param element: the value to be set
     * @return the element previously at the specified position
     * @exception NullPointerException if the specified element is null and this
     * list does not permit null elements
     * @exception IndexOutOfBoundsException if the index is out of range
     */
    @Override
    public E set(int index, E element) {

        if(element==null){
            throw new NullPointerException("data is null.");
        }
        if(index > size-1 || index < 0){
            throw new IndexOutOfBoundsException("out of bound.");
        }
        Node<E> curr = new Node<E>();
        curr = head.next;
        for(int count=0; count< index; count++){
            curr = curr.next;
        }
        E preData = curr.data;
        curr.data = element;
        return preData;
    }

    /**
     * Method: remove
     * A method that removes the element from position [i] in the list
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @exception IndexOutOfBoundsException if index is out of range
     */
    @Override
    public E remove(int index){
        //check empty
        if(isEmpty()){
            throw new IndexOutOfBoundsException("empty list. cannot delete.");
        }
        //otherwise, if index is too big or too small
        else if(index > this.size()-1 || index < 0){
            throw new IndexOutOfBoundsException("out of bound.");
        }
        //now that the index given is valid
        //normal operation
        else{
            //create curr Node (points to the removed Node)
            Node<E> curr = new Node<E>();
            //points to first valid element
            curr = head.next;

            //create pred and succ in front of and behind curr
            Node<E> pred = new Node<E>();
            Node<E> succ = new Node<E>();
            pred = curr.prev;
            succ = curr.next;

            //traverse until index
            for(int i=0; i<index; i++){
                pred = curr; 
                curr = succ; 
                succ = succ.next;
            }
            //capture returned element
            E beReturned = curr.data;

            //remove curr by re-assigning succ and pred reference
            succ.prev = pred;
            pred.next = succ;
            size--;

            return beReturned;
        }
    }

    /**
     * Method: clear
     * A method that removes all of the elements from the list
     */
    @Override
    public void clear(){
        //call the remove function size times
        for(int count=size; count > 0; count--){
            remove(0);
            //System.out.println("removing...");
        }
    }

    /**
     * Method: isEmpty
     * A method that determines if the list is empty
     * @return true if empty
     *         false otherwise
     */
    @Override
    public boolean isEmpty(){
        if(this.size() == 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method: size
     * A method that returns the number of elements being stored
     */
    @Override
    public int size()
    {
        return this.size; 
    }


    public Iterator<E> QQQiterator()
    {
        return new MyListIterator();
    }
    public ListIterator<E> QQQlistIterator()
    {
        return new MyListIterator();
    }

    /*  UNCOMMENT the following when you believe your MyListIterator class is
        functioning correctly */

    public Iterator<E> iterator()
    {
        return new MyListIterator();
    }
    public ListIterator<E> listIterator()
    {
        return new MyListIterator();
    }


}//end of class

// vim:tw=78:ts=4:et:sw=4
