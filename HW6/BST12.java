import java.util.*;

/**
 * File: BST12.java
 * @author: Chih-Hsuan Kao <c4kao@ucsd.edu>
 * Login: cs12sgh
 * PID: A92092372
 * Date: Jun 5, 2018
 */

/**
 * BST12 implements BinSearchTree12, which is defined as a subset of the
 * methods defined in the Java Collections Framework class Treeset, with 2
 * additional methods: height() and numChildren().
 */
public class BST12<E extends Comparable<? super E>> implements BinSearchTree12<E>
{
    /** instance variables */
    //the current size of the BST
    private int size;
    private BST12Node<E> root;
    //popped node
    private BST12Node<E> popped;
    private boolean duplication;

    /** no-arg constructor 
     * constructs a new, empty BST12Adapt object, sorted according to the
     * natural ordering of its elements.
     */
    public BST12(){
        size = 0;
    }

    /** constructor that constructs a new BST containing the elements in the
     * specified collection.
     * @param   c   the collection to be added 
     */
    public BST12(Collection<? extends E> c){
        addAll(c);    
    }

    /** public methods */

    /**
     * Adds the specified element to this set if it is not already present. 
     * @param   e   element to be added to this set
     * @return  true    if this set did not already contain the specified
     * element. 
     * @exception   ClassCastException  if the specified object cannot be
     * compared with the elements currently in this set. 
     * @exception   NullPointerException    if the specified element is null
     * and this sets does not permit null elements. 
     */
    public boolean add(E e){
        //if the passed-in element is null, throw exception
        if ( e == null){
            //throw new SearchTreeException();
            throw new NullPointerException();
        }
        try{
            //if empty, simply add it as node and return true
            if (isEmpty()){
                root = new BST12Node(e);
                size++;
                return true;
            }
            //if already there, stop adding, return false
            else if (contains(e)){
                return false;
            }
            //there's at least one node (not empty)
            else {
                //set root to be a reference to the node returned by the original
                //call to add()
                add(null, this.getRoot(), e);
                //increment size
                size++;
                return true;
            }
        } catch (ClassCastException excep){
            throw new ClassCastException();
        }
    }

    /** 
     * protected version that does the work for add(). 
     * Using recursion, this helper method returns a reference to the current
     * node to the caller in base case. 
     * @param   parent  the parent of the current BST12Node
     * @param   curr    the current BST12Node in the recursive call
     * @param   e   the element to be added
     * @return  a reference to the current node to the caller
     */
    protected BST12Node<E> add(BST12Node<E> parent, BST12Node<E> curr, E e){
        //base case: when the current node has no child when going down
        //let the new element be its child
        if(curr == null){
            //change the curr reference to new one
            curr = new BST12Node(e);
            curr.parent = parent;
        }
        //recursive case
        else{
            int compareResult;
            compareResult = e.compareTo(curr.element);
            //if the data passed-in is smaller than the data of current node
            //go to the left
            if(compareResult < 0){
                curr.leftChild = add(curr,curr.leftChild, e);
            }
            //if bigger, go to the right
            else {
                curr.rightChild = add(curr,curr.rightChild, e);
            }
        }
        //return a reference to the current node to the caller
        return curr;
    }

    /**
     * Adds all of the elements in the specified collection to this set.
     * @param   c   collection containing elements to be added to this set.
     * @return  true    if this set changed as a result of the call
     * @exception   ClassCastException   if the specified object cannot be 
     * compared with the elements currectly in this set
     * @exception   NullPointerException    if the specified element is null
     * and this does not permit null elements
     */
    public boolean addAll(Collection<? extends E> c){
        //create boolean instance to catch what add() returns
        boolean returned = false;
        //use iterator to loop through collection
        Iterator<? extends E> ite = c.iterator();
        //add element using add()
        while (ite.hasNext()){
            E element = ite.next();
            returned = this.add(element);
        }
        return returned;
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
        //if the element to be compared is null, then throw exception
        if ( o == null){
            //throw new NullPointerException
            throw new NullPointerException();
        }

        try{
            //calling helper method
            return contains(this.getRoot(), o);
        } catch (ClassCastException e){
            throw new ClassCastException();
        }
    }

    /** Protected version that does the work for contains(E o)
     * Has direct access to node's data fields.
     * This helper method uses recursion to perform comparison, stops at base
     * case where there is no more child. 
     * @param   node    a BST12Node to check element containment
     * @param   o   element to be checked
     * @return  true    if found containment; false otherwise
     */
    protected boolean contains(BST12Node<E> node, E o){
        //base case: failure
        if (node == null){
            return false;
        }   
        //if node is not null, compare
        int compareResult;
        compareResult = o.compareTo(node.element);

        //if target is smaller than the current node's element
        if(compareResult < 0){
            //recursively call on its left (smaller) child
            return contains(node.leftChild, o);
        }
        //if bigger
        else if(compareResult > 0){
            //recursively call on its right (bigger) child
            return contains(node.rightChild, o);
        }
        //if equal
        else {
            //THIS is what we're looking for: return true
            return true;
        }
    }

    /**
     * returns the first (lowest) element currently in this set. 
     * @return  the lowest element in this set
     * @exception   NoSuchElementException  if this set is empty
     */
    public E first(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        //if not empty
        else{
            BST12Node<E> follow = new BST12Node(null);
            //firstly points to root
            follow = this.root;
            //go down to find leftmost child
            while(follow.leftChild != null){
                follow = follow.leftChild;
            }
            return follow.element;
        }
    }

    /**
     * returns the last (highest) element currently in this set. 
     * @return  the highest element in this set
     * @exception   NoSuchElementException  if this set is empty
     */
    public E last(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        //if not empty
        else{
            BST12Node<E> follow = new BST12Node(null);
            //firstly points to root
            follow = this.root;
            //go down to find rightmost child
            while(follow.rightChild != null){
                follow = follow.rightChild;
            }
            return follow.element;
        }
    }

    /**
     * Returns an iterator over the elements in this set in ascending order. 
     */
    public Iterator<E> iterator(){
        return new BST12Iterator();
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
        //throw NullPointerException if element is null
        if(o==null){
            throw new NullPointerException(); 
        }
        try{
            //return false if cannot remove because isn't there
            if(!(contains(o))){
                return false;
            }
            //if the element is in the tree, firstly use iterator to find where
            //the node is
            Iterator<E> ite = this.iterator();
            BST12Node<E> tracker = new BST12Node(null);
            E nodeEle;
            //loop through the tree to get where the node is
            while(ite.hasNext()){
                nodeEle = ite.next();
                tracker = popped;
                //if found where the node is, 
                //the node referenced by tracker is what I look for, exit loop
                if( nodeEle.compareTo(o) == 0){
                    break; 
                }
            }
            //after locating the node, perform removal with different cases
            //case1: Internal node with 2 children
            if(tracker.leftChild != null && tracker.rightChild != null){
                //copy data from its successor to itself
                BST12Node<E> succNode = successor(tracker);
                //store the data of succNode
                E replace;
                replace = succNode.element;
                //recursively remove successor (which has 0 or 1 rightChild-case4)
                remove(succNode.element);
                tracker.element = replace;

                return true;
            }
            //case2: Root node (with 1 or 0 children)
            else if(tracker == this.getRoot()){
                //get if its child is left or right
                if(tracker.leftChild != null){
                    root = tracker.leftChild;
                }
                else{
                    root = tracker.rightChild;
                }
                //make sure has a null parent
                if(this.getRoot() != null){
                    this.root.parent = null;
                }
                size--;
                return true;
            }
            //case3: internal with left child only or leaf
            else if(tracker.leftChild != null){
                BST12Node<E> parent = tracker.parent;

                //know if the to-be-removed node is a left or right child
                BST12Node<E> newLeftChild = tracker.leftChild;
                if(parent.leftChild == tracker){
                    parent.leftChild =  newLeftChild;
                }
                else{
                    parent.rightChild = newLeftChild;
                }
                //reference the new child to the parent
                if(newLeftChild != null){
                    newLeftChild.parent = parent;
                }
                size--;
                return true;
            }
            //case4: internal with right child only or leaf
            else{
                BST12Node<E> parent = tracker.parent;
                BST12Node<E> newRightChild = tracker.rightChild;
                //know if the to-be-removed child is a left of right child
                if(parent.leftChild == tracker){
                    parent.leftChild =  newRightChild;
                }
                else{
                    parent.rightChild = newRightChild;
                }
                //reference the new child to the parent
                if(newRightChild != null){
                    newRightChild.parent = parent;
                }
                size--;
                return true;
            }
        }catch(ClassCastException exception){
            throw new ClassCastException();
        }
    }

    /**
     * removes all of the elements from this search tree. 
     */
    public void clear(){
        root = new BST12Node(null);
        size = 0;

    }

    /**
     * A private helper method whose function is to find a successor
     * (BST12Node) of a specified target (which is a BST12Node)
     * @param   target  the target for which the successor is found
     * @return  the successor of the specified node
     */
    private BST12Node<E> successor(BST12Node<E> target){
        if(target == null || target.rightChild == null){
            return null;
        }
        //go down to its rightChild
        BST12Node<E> follow = target.rightChild;
        //then go down to the left if there's any
        while(follow.leftChild != null){
            follow = follow.leftChild;
        }
        return follow;
    }

    /**
     * returns the number of elements in the tree
     */
    public int size(){
        return this.size;
    }

    /**
     * Additional methods defined in BinSearchTree12 interface that
     * returns the height of the tree.
     * An empty tree returns 0, a tree with one ele returns a height of 1.
     */
    public int height(){
        if(isEmpty()){
            return 0;
        }
        else {
            return helpHeight(getRoot());
        }
    }

    /**
     * A private helper method that uses recursion to find the depth of a
     * tree. It is called by the public height method. Incremenents the count
     * in every recursive call until the base case. 
     * @param   node    the node from which the call goes down
     * @return  the count (integer) returned by the caller node
     */ 
    private int helpHeight(BST12Node<E> node){
        //base case: when there's no more child, return 0 to the caller
        if (node == null){
            return 0;
        }

        //recursive case: go down to its left and right
        int heightLeft = helpHeight(node.leftChild);
        int heightRight = helpHeight(node.rightChild);

        //increment the count on each level after finding the max of right and
        //left
        if(heightLeft > heightRight){
            return heightLeft + 1;
        }
        else {
            return heightRight + 1;
        }
    }

    /**
     * returns true if this set contains no element
     * @return  true if this set contains no element
     */
    public boolean isEmpty(){
        if(size() == 0){
            return true;
        }
        return false;
    }

    /**
     * Returns the number of children of the Node that references target.
     * @exception   IllegalArgumentException if null pointer or
     * if there's a ClassCastException (any other problems)
     * @exception   NoSuchElementException if target is not found in the tree
     */
    public int numChildren(E target){
        if(!(contains(target))){
            throw new NoSuchElementException();
        }
        if(target == null){
            throw new IllegalArgumentException();
        }
        try {
            //if the element is in the tree, firstly use iterator to find where
            //the node is
            Iterator<E> ite = this.iterator();
            BST12Node<E> tracker = new BST12Node(null);
            E nodeEle;
            //loop through the tree using iterator
            while(ite.hasNext()){
                nodeEle = ite.next();
                tracker = popped;
                //if found where the node is, 
                //the node referenced by tracker is what I look for, exit loop
                if( nodeEle.compareTo(target) == 0){
                    break; 
                }
            }
            //see how many children tracker has
            if(tracker.leftChild != null && tracker.rightChild != null){
                return 2;
            }
            else if(tracker.leftChild != null){
                return 1;
            }
            else if(tracker.rightChild != null){
                return 1;
            }
            else {
                return 0;
            }
        } 
        catch (NullPointerException npe){
            System.out.println("HEREEEE");
            throw new IllegalArgumentException();
        }
        catch (ClassCastException cce){
            throw new IllegalArgumentException();
        }
        catch (Exception excep){
            throw new IllegalArgumentException();
        }
    }

    /** 
     * This is a private method to get the root of a tree. 
     * @return  the root of the tree which is a BST12Node
     */ 
    private BST12Node<E> getRoot(){
        return this.root;
    }

    /** INNER CLASS for a Node 
     *  A BST is composed of <tt>BST12Node</tt>s.
     *  The element stored must be comparable with other elements in the BST.
     */
    protected class BST12Node<E extends Comparable<? super E>>{

        /** instance variables */
        protected BST12Node<E> parent;
        protected BST12Node<E> leftChild;
        protected BST12Node<E> rightChild;
        protected E element;

        /** A constructor that takes a data element to store in the node 
         * @param   data    the data to be the element of the Node
         */
        public BST12Node(E data){
            element = data;

        }

        /** A constructor that takes a data element and references to become
         * the node's left and right subtrees 
         * @param   data    the data to be the element of the Node
         * @param   left    the node to be the leftChild of the Node
         * @param   right   the node to be the rightChild fo the Node
         */
        public BST12Node(E data, BST12Node<E> left, BST12Node<E> right){
            leftChild = left; 
            rightChild = right; 
            element = data;
        }
    }

    /** INNER CLASS for an Iterator */
    protected class BST12Iterator implements Iterator<E>
    {

        /** instance variables */
        //a stack of BST12Nodes
        private Stack<BST12Node> stack;
        //a BST12Node that reference to some Node in the tree
        private BST12Node<E> cursor;

        /** no arg constructor for the class */
        protected BST12Iterator()
        {
            stack = new Stack<BST12Node>();
            //push the top node onto the stack
            stack.push(getRoot());
            //cursor references to the top 
            cursor = getRoot();
            //go further left down
            while(cursor.leftChild != null){
                cursor = cursor.leftChild;
                stack.push(cursor);
            }   
        }

        /**
         * returns true if the iteration has more elements. 
         * @return  true    if the iteration has more elements. 
         */
        public boolean hasNext(){
            if(stack.empty()){
                return false;
            }
            else {
                return true;
            }
        }

        /**
         * returns the next element in the iteration
         * @return  the next element in the iteration
         * @exception   NoSuchElementException  if the iteration has no more
         * elements
         */
        public E next() throws NoSuchElementException{
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            else{
                //LIFO
                popped = stack.pop();
                //reference the cursor to the newly popped node
                cursor = popped;
                //then go downs to the right if the newly popped node has
                //rightChild
                if(cursor.rightChild != null){
                    cursor = cursor.rightChild;
                    //push the rightChild to the stack
                    stack.push(cursor);
                    //check leftChild of the rightChild, if any
                    while(cursor.leftChild != null){
                        cursor = cursor.leftChild;
                        stack.push(cursor);
                    }   
                }
                return popped.element;
            }
        }

        /**
         * Remove method is undefined. 
         * Must throw UnsupportedOperation exception when it's called. 
         * @exception   UnsupportedOperationException   if the remove
         * operation is not supported by the iterator. 
         */
        public void remove(){
            throw new UnsupportedOperationException("Invalid operation!");
        }

    }//end iterator

    /**
     * toString method redefined for tester file usage.
     * @return  a content of the tree using In-Order traversal.
     */
    public String toString(){
        if(isEmpty()){
            String empty = new String("");
            return empty;
        }
        Iterator<E> it = this.iterator();
        String str = new String("");
        E ele;
        while(it.hasNext()){
            ele = it.next();
            str = str + ele.toString() + " ";
        }
        return str;
    }

}//end BST12 class
