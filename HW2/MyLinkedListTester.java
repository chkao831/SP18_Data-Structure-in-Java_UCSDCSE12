import junit.framework.*;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.*;

/**
 *  Title: class MyLinkedListTester
 *  Description: JUnit test class for MyLinkedList class
 *  @author Philip Papadopoulos, Christine Alvarado
 *  Modified by Chih-Hsuan Kao
 *  Login: cs12sgh
 *  PID: A92092372
 *  Date: April 24, 2018
 *  @version 4.0 16-April-2018
 * */

/* 
 * compile:
 *  javac -cp '.':'*':'/usr/share/java/*' MyLinkedListTester.java
 * run:
 *  java -cp '.':'*':'/usr/share/java/*' org.junit.runner.JUnitCore MyLinkedListTester 
 */

/*
 * You should modify the information above to add your name 
 * 
 * When your tester is complete, you will rename it MyLinkedListTester.java 
 * (renaming LinkedList to MyLinkedList everywhere in the file) so that you can
 * use it to test your MyLinkedList and MyListIterator classes.
 */

public class MyLinkedListTester extends TestCase
{
    //instance variables provided in the starter code
    private MyLinkedList<Integer> empty ;
    private MyLinkedList<Integer> one ;
    private MyLinkedList<Integer> several ;
    private MyLinkedList<String>  slist ;

    //instance variables that I created
    //Sequence 'a' is a LinkedList consists of integer 0 to 99
    public MyLinkedList<Integer> a;
    //Sequence 'b' is a LinkedList consists of integer 0 to 99
    public MyLinkedList<Integer> b;
    //Sequence small 3 consists of integer 0 to 2
    public MyLinkedList<Integer> small3;
    //Sequence origianl is empty, yet to be modified
    public MyLinkedList<Integer> original;
    //provided in starter code
    public LinkedList<Integer> fib;

    static final int DIM = 5;
    static final int FIBMAX = 30;

    /*
     * Constructor. 
     **/
    public MyLinkedListTester()
    {
        super() ;
    }
    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and 
     * a list with several entries (0,1,2)
     */ 

    public void setUp()
    {
        empty = new MyLinkedList<Integer>();

        one = new MyLinkedList<Integer>();
        one.add(0,new Integer(0));

        several = new MyLinkedList<Integer>() ;
        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--)
            several.add(0,new Integer(i));

        // List: "First","Last"
        slist = new MyLinkedList<String>();
        slist.add(0,"First");
        slist.add(1,"Last");

        a = new MyLinkedList<Integer>();
        //List: 0 to 99
        for(int kk = 0; kk< 100; kk++){
            a.add(kk,new Integer(kk));
        }

        b = new MyLinkedList<Integer>();
        //List: 0 to 99
        for(int ll = 99; ll>=0; ll--){
            b.add(0,new Integer(ll));
        }

        small3 = new MyLinkedList<Integer>();
        //List: 0 to 2
        for(int qq = 0; qq < 3; qq++){
            small3.add(qq,new Integer(qq));
        }

        fib  = new LinkedList<Integer>();
        // List: 0 1 1 2 3 5 8 13 ... 
        // Build the list with integers 1 .. FIBMAX
        int t, p = 0, q = 1;
        fib.add(0,p);
        fib.add(1,q);
        for (int k = 2; k <= FIBMAX; k++)
        {
            t = p+q;
            fib.add(k,t);
            p = q; q = t; 
        }
    }

    public void testGetSize(){
        assertEquals("Check size",100,a.size());
    }
    public void testGetSize2(){
        assertEquals("Check size 2 ",100,b.size());
    }

    public void testGetFibonacci(){
        int fibSize = fib.size();
        int totalCount = 0;
        for(int i = 0; i<fibSize ; i++){
            //System.out.println(fib.get(i));
            totalCount++;
        }
        assertEquals("succesfully get",totalCount,fibSize);
    }

    public void testSmall3Add(){
        try{
            assertEquals("small3size",3,small3.size());
            small3.add(4,new Integer(1));
            fail();
        }catch(IndexOutOfBoundsException e){

        }
    }

    public void testaGetException(){
        try{
            assertEquals("aSize",100,a.size());
            int ele = 0;
            for(int i=0; i<=100; i++){
                ele = a.get(i);
            }
            fail();
        } catch(IndexOutOfBoundsException e){
        }
    }

    public void testContent(){
        assertEquals("Check 0th",new Integer(0), b.get(0));
        assertEquals("Check 1st",new Integer(1), b.get(1));
        assertEquals("Check 99th",new Integer(99),b.get(99));
    }

    public void testClear(){
        try{small3.clear();
            assertTrue("small3 is empty",small3.isEmpty());
            small3.remove(0);
            fail();
        } catch(IndexOutOfBoundsException e){

        }
    }

    public void testListSet(){
        small3.set(0,new Integer(10));
        small3.set(1,new Integer(11));
        small3.set(2,new Integer(12));
        assertEquals("Check 0th",new Integer(10),small3.get(0));
        assertEquals("Check 1st",new Integer(11),small3.get(1));
        assertEquals("Check 2nd",new Integer(12),small3.get(2));
    }

    public void testSetNullExcep(){
        try{String nullStr = null;
            slist.set(1,nullStr);
            fail();
        } catch(NullPointerException e){

        }
    }

    /* Test emptiness of an empty list with size of it**/
    public void testEmp(){
        assertEquals("Check Empty",0,empty.size());
    }

    /** Test if heads of the lists are correct */
    public void testGetHead()
    {
        assertEquals("Check 0",new Integer(0),one.get(0)) ;
        assertEquals("Check 0",new Integer(1),several.get(0)) ;
    }

    /** Test if size of lists are correct */
    public void testListSize()
    {
        assertEquals("Check Empty Size",0,empty.size()) ;
        assertEquals("Check One Size",1,one.size()) ;
        assertEquals("Check Several Size",DIM,several.size()) ;
    }

    /** Test setting a specific entry */ 
    public void testSet()
    {
        slist.set(1,"Final");
        assertEquals("Setting specific value", "Final",slist.get(1));
    }

    /** Test isEmpty */ 
    public void testEmpty()
    {
        assertTrue("empty is empty",empty.isEmpty()) ;
        assertTrue("one is not empty",!one.isEmpty()) ;
        assertTrue("several is not empty",!several.isEmpty()) ;
    }

    /** Test out of bounds exception on get */
    public void testGetException()
    {
        try 
        {
            empty.get(0);
            // This is how you can test when an exception is supposed 
            // to be thrown
            fail("Should have generated an exception");  
        }
        catch(IndexOutOfBoundsException e)
        {
            //  normal
        }
    }

    /** Test the add method of iterator*/
    public void testIterAddException(){
        try{
            ListIterator<String> it = slist.listIterator();
            String str = null;
            assertNull(str);
            it.add(str);
            fail();
        } catch (NullPointerException e){
            //System.out.println("NPException caught!");
        }
    }

    /** Test NoSuchElementException on iterator next() method*/
    public void testIterNextException() throws NoSuchElementException{
        try{
            ListIterator<Integer> it = a.listIterator();
            while(it.hasNext()){
                it.next();
            }
            it.next();
            fail();
        } catch(NoSuchElementException e){
            //System.out.println("NSEException caught!");
        }
    }

    /**Test IllegalStateException on iterator remove() method */
    public void testIterRemoveException(){
        try{
            ListIterator<Integer> it = a.listIterator();
            it.add(new Integer(3));
            it.add(new Integer(5));
            it.remove();
            fail();
        }catch(IllegalStateException e){
            //System.out.println("ISException caught!");
        }   
    }

    /** Test if remove method still works after calling set of iterator*/
    public void testIterRemoveAfterSet(){

        ListIterator<Integer> it = a.listIterator();
        it.add(new Integer(3));
        it.add(new Integer(5));
        System.out.println(it.nextIndex());
        assertEquals("beginning index",0,it.nextIndex());
        it.next();
        assertEquals("next index",1,it.nextIndex());
        it.set(new Integer(4));
        assertEquals("updated 0th index is ",new Integer(4),a.get(0));
        assertEquals("1st index remains ",new Integer(3),a.get(1));
        it.remove();
        assertEquals("updated first index",new Integer(3),a.get(0));
    }

    /** Test IllegalStateException on set after add() is called on iterator*/
    public void testSetAfterAdd(){
        try{
            ListIterator<String> it = slist.listIterator();
            it.next();
            it.add(new String("a new String"));
            it.set(new String("want to set"));
            fail();
        } catch(IllegalStateException e){
            //System.out.println("cannot set after add!");
        }
    }

    /** Test index traverse on iterator */
    public void testIterPrevIndexAtStart(){
        ListIterator<String> it = slist.listIterator();
        it.next();
        it.next();
        it.previous();
        it.previous();
        assertEquals("next Index is",0,it.nextIndex());
        assertEquals("previous Index is ",-1,it.previousIndex());
    }

    /** Test add() functionality on iterator */
    public void testIterAdd(){
        original = new MyLinkedList<Integer>();
        ListIterator<Integer> it = original.listIterator();

        assertTrue("there is no next",!it.hasNext());
        assertTrue("there is no prev",!it.hasPrevious());

        it.add(new Integer(2));
        it.add(new Integer(0));
        it.next();
        it.add(new Integer(1));
        it.next();
        it.next();
        it.add(new Integer(3));

        assertEquals("list size",4,original.size());
        assertEquals("ite index",3,(it.nextIndex()));

    }

    /**Test general functionality on remove method by traversing through the
     * list**/
    public void testIterRemove(){
        ListIterator<Integer> it = small3.listIterator();
        /**
          System.out.println(it.nextIndex());
          it.next();
          it.remove();
          System.out.println(it.nextIndex());
          it.next();
          it.remove();
          it.next();
          it.remove();
         **/
        while(it.hasNext()){
            //System.out.println(it.nextIndex());
            //System.out.println("current list size is "+small3.size());
            it.next();
            it.remove();
        }
        assertEquals("after removal size is ",0,small3.size());
    }

    /** Test remove() on iterater with reverse direction */
    public void testIterRemoveReverse(){
        ListIterator<Integer> it = small3.listIterator();
        while(it.hasNext()){
            it.next();
        }
        //when reach the end, start to reverse
        while(it.hasPrevious()){
            //System.out.println("index is "+it.previousIndex());
            //System.out.println("current list size is " + small3.size());
            it.previous();
            it.remove();
        }
        assertEquals("after reverse removal size is ",0,small3.size());
    }

    /** Test set() on iterator */
    public void testSetIte(){

        ListIterator<Integer> ite = several.listIterator();
        /*
           while(ite.hasNext()){
           System.out.println(ite.next());
           }
           */
        ite = several.listIterator();
        ite.next();
        assertEquals("current index",1,ite.nextIndex()) ;
        ite.set(131);
        assertEquals("index content",new Integer(131),several.get(0));
    }


    /* Test iterator on empty list and several list */
    public void testIterator()
    {
        int counter = 0 ;
        ListIterator<Integer> iter;
        for (iter = empty.QQQlistIterator() ; iter.hasNext(); )
        {
            fail("Iterating empty list and found element") ;
        }
        counter = 0 ;
        for (iter = several.listIterator() ; iter.hasNext(); iter.next())
            counter++;
        assertEquals("Iterator several count", counter, DIM);
    }


    /** test Iterator Fibonacci.
     * This is a more holistic test for the iterator.  You should add
     * several unit tests that do more targeted testing of the individual
     * iterator methods.  
     */
    public void testIteratorFibonacci()
    {
        ListIterator<Integer> iter;

        // Now iterate through the list to near the middle, read the
        // previous two entries and verify the sum.
        iter = fib.listIterator();
        int sum = 0;
        for (int j = 1; j < FIBMAX/2; j++)
            sum = iter.next();
        iter.previous();
        assertEquals(iter.previous() + iter.previous(),sum);
        // Go forward with the list iterator
        assertEquals(iter.next() + iter.next(),sum);
    }
}


