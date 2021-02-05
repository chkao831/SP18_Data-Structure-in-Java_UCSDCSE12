import java.util.*;

/**
 * File: BST12Adapt.java
 * @author: Chih-Hsuan Kao <c4kao@ucsd.edu>
 * Login: cs12sgh
 * PID: A92092372
 * Date: Jun 2, 2018
 */

/**
 * BST12Adapt is the adapted class that adapts the TreeSet Class from the java
 * collections framework. It implements BinSearchTree12, which is defined as a
 * subset of the methods defined in the Java Collections Framework class
 * Treeset, with 2 additional methods: height() and numChildren(). 
 */
public class BST12Adapt<E extends Comparable<? super E>> implements BinSearchTree12<E>
{

    //instance
    private TreeSet<E> adapt;

    /**
     * no arg constructor
     * constructs a new, empty BST12Adapt object, sorted according to the
     * natural ordering of its elements.
     */
    public BST12Adapt(){
        adapt = new TreeSet();
    }

    /**
     * Constructor.
     * Constructs a new BST12Adapt object, containing the elements in the
     * specified collection, sorted according to the natural ordering of its
     * elements.
     */
    public BST12Adapt( Collection<? extends E> c ){
        adapt = new TreeSet(c);
    }

    /**
     * Adds the specified element to this set if ti is not already present. 
     * @param   e   element to be added to this set
     * @return  true    if this set did not already contain the specified
     * element. 
     * @exception   ClassCastException  if the specified object cannot be
     * compared with the elemtns currently in this set. 
     * @exception   NullPointerException    if the specified element is null
     * and this sets does not permit null elements. 
     */
    public boolean add(E e){
        return adapt.add(e);
    }

    /**
     * Adds all of the elements in the specified collection to this set. 
     * @param   c   collection containing elements to be added to this set. 
     * @return  true    if this set changed as a result of the call
     * @exception   ClassCastException  if the specified object cannot be
     * compared with the elemtns currently in this set. 
     * @exception   NullPointerException    if the specified element is null
     * and this sets does not permit null elements. 
     */
    public boolean addAll(Collection<? extends E> c){
        return adapt.addAll(c);
    }

    /**
     * removes all of the elements from this search tree. 
     */
    public void clear(){
        adapt.clear();
        return;
    }

    /**
     * Returns true if this tree contains the specified element. 
     * @param   o   object to be checked for containment in this set. 
     * @return  true    if this set contains the specified element
     * @exception   ClassCastException  if the specified object cannot be
     * compared with the elemtns currently in this set. 
     * @exception   NullPointerException    if the specified element is null
     * and this sets does not permit null elements. 
     */
    public boolean contains(E o){
        return adapt.contains(o);
    }

    /**
     * returns the first (lowest) element currently in this set. 
     * @return  the lowest element in this set
     * @exception   NoSuchElementException  if this set is empty
     */
    public E first(){
        return adapt.first();
    }

    /**
     * returns the last (highest) element currently in this set. 
     * @return  the highest element in this set
     * @exception   NoSuchElementException  if this set is empty
     */
    public E last(){
        return adapt.last();
    }

    /**
     * returns true if this set contains no element
     * @return  true if this set contains no element
     */
    public boolean isEmpty(){
        return adapt.isEmpty();
    }

    /**
     * Returns an iterator over the elements in this set in ascending order. 
     */
    @Override
    public Iterator<E> iterator(){

        //should redefine iterator so that it returns an instance of
        //BST12AdaptIterator
        return new BST12AdaptIterator();
    }

    /**
     * INNER CLASS
     */
    protected class BST12AdaptIterator implements Iterator<E>{
       
        Iterator<E> ite;

        /** constructor 
         * that uses Adapter Design Pattern on TreeSet
         */
        protected BST12AdaptIterator(){
            ite = adapt.iterator();    
        }
        
        /**
         * Remove method is undefined. 
         * Must throw UnsupportedOperation exception when it's called. 
         * @exception   UnsupportedOperationException   if the remove
         * operation is not supported by the iterator. 
         */ 
        @Override
        public void remove(){
            throw new UnsupportedOperationException("Invalid operation.");
        }

        /**
         * returns the next element in the iteration
         * @return  the next element in the iteration
         * @exception   NoSuchElementException  if the iteration has no more
         * elements
         */
        @Override
        public E next(){
            return this.ite.next();
        }

        /**
         * returns true if the iteration has more elements. 
         * @return  true    if the iteration has more elements. 
         */ 
        @Override
        public boolean hasNext(){
            return this.ite.hasNext();
        }
    }

    /**
     * removes the specified element from this search tree if it is present
     * @param   o   object to be removed from this set, if present
     * @return  true    if this set contained the specified element
     * @exception   ClassCastException  if the specified object cannot be
     * compared with the elemtns currently in this set. 
     * @exception   NullPointerException    if the specified element is null
     * and this sets does not permit null elements. 
     */
    public boolean remove(E o){
        return adapt.remove(o);
    }

    /**
     * returns the number of elements in the tree
     */
    public int size(){
        return adapt.size();
    }

    /**
     * Additional methods defined in BinSearchTree12 interface. 
     * returns the height of the tree. 
     * An empty tree returns 0. 
     * 1 for a tree with one node. 
     * size() for all other cases. 
     */
    public int height(){

        //return 0 for an empty tree
        if(this.isEmpty() == true){
            return 0;
        }
        //return 1 for a tree with one node
        if(this.size() == 1){
            return 1;
        }
        //return size() for all other cases
        else{
            return this.size();
        }
    }

    /**
     * Additional methods defined in BinSearchTree12 interface. 
     * Return -1 if the target node is in the tree. 
     * @exception   NoSuchElementException  if the target node is not in the
     * tree
     * @exception   IllegalArgumentException    for any other faults. 
     */
    public int numChildren(E target) {
        if(!(this.contains(target))){
            throw new NoSuchElementException();
        }
        try{
            return -1;
        }
        catch (NullPointerException npe){
            throw new IllegalArgumentException();
        }
        catch (ClassCastException cce){
            throw new IllegalArgumentException();
        }
        catch (Exception exc){
            throw new IllegalArgumentException();
        }
    }

    /**
     * toString method redefined for tester file usage. 
     * @return  a content of the tree using In-Order traversal. 
     */
    @Override
    public String toString(){
        if(adapt.isEmpty()){
            String bracket = new String("[]");
            return bracket;
        }
        Iterator<E> it = adapt.iterator();
        String str = new String();
        E ele;
        while(it.hasNext()){
            ele = it.next();
            str = str + ele.toString() + ", "; 
        }
        int length = str.length();
        str = str.substring(0, str.length() -2);
        str = "[" + str + "]";
        return str;
    }

}//end BST12Adapt class
