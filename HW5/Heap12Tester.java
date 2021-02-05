import junit.framework.*;
import java.util.*;
/**File: Heap12Tester.java
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * ID: A92092372
 * Email: c4kao@ucsd.edu
 * Date: May 25, 2018
 */

/**
 * This class is a JUnit tester for the Heap12 class
 * This class extends junit.framwork.TestCase
 */
public class Heap12Tester extends TestCase
{

    //define instance variables
    private Heap12<Integer> five;
    private Heap12<Integer> level3;
    private Heap12<String> alpha;
    private Heap12<Integer> minHeap1;
    //this is a priority queue to compare correctness
    private PriorityQueue<Integer> pq; 

    /**
     * constructor
     */
    public Heap12Tester(){
        super();
    }

    /**
     * Standard Test Fixture.
     * Use a Deque12 object as a test fixture. 
     */
    public void setUp(){

        //five and level3 are max heap
        five = new Heap12<Integer>(true);
        level3 = new Heap12<Integer>(true);
        //priority queue has initial capacity of 5, storing integers
        pq = new PriorityQueue<Integer>(5);
        //minHeap1 is min heap
        minHeap1 = new Heap12<Integer>();

        //alpha is min heap with those initialized elements
        alpha = new Heap12<String>();
        alpha.offer(new String("giraffe"));
        alpha.offer(new String("fly"));
        alpha.offer(new String("duck"));
        alpha.offer(new String("bear"));
        alpha.offer(new String("cat"));
        alpha.offer(new String("elephant"));
        alpha.offer(new String("ape"));

    }//end setUp


    /** This priority queue is commented out. 
     * This test is generated to compare with the output of nextTest
     * for debug usage.
     * This test is mainly used to iterator remove() edge case:
     * to see what happened when I want to remove elements from the memorylist
     * 
     public void testPQIte(){

     pq.offer(new Integer(1));
     pq.offer(new Integer(7));
     pq.offer(new Integer(20));
     pq.offer(new Integer(11));
     pq.offer(new Integer(15));
     pq.offer(new Integer(27));
     pq.offer(new Integer(24));
     pq.offer(new Integer(28));
     pq.offer(new Integer(13));
     pq.offer(new Integer(16));
     pq.offer(new Integer(18));


     Iterator<Integer> it2 = pq.iterator();

     assertEquals(new Integer(1),it2.next());
     assertEquals(new Integer(7),it2.next());
     assertEquals(new Integer(20),it2.next());
     assertEquals(new Integer(11),it2.next());
     assertEquals(new Integer(15),it2.next());
     assertEquals(new Integer(27),it2.next());
    //then 18 goes up, bubble up, store to memorylist
    it2.remove();
    assertEquals(new Integer(24),it2.next());
    //then 16 goes up, bubble up, store to memorylist
    it2.remove();
    assertEquals(new Integer(28),it2.next());
    assertEquals(new Integer(13),it2.next());
    //memory:18
    assertEquals(new Integer(18),it2.next());
    //remove 18 in the memorylist
    it2.remove();
    //memory:16
    assertEquals(new Integer(16),it2.next());

    /** for debug usage
    Iterator<Integer> it = pq.iterator();
    System.out.println();
    while(it.hasNext()){
    System.out.print(it.next() + " ");
    }

     }
     */


    /** test HeapIterator compared to previous priority queue 
     * This test is mainly used to iterator remove() edge case:
     * to see what happened when I want to remove elements from the memorylist
     */
    public void testIteCompare(){

        minHeap1.offer(new Integer(1));
        minHeap1.offer(new Integer(7));
        minHeap1.offer(new Integer(20));
        minHeap1.offer(new Integer(11));
        minHeap1.offer(new Integer(15));
        minHeap1.offer(new Integer(27));
        minHeap1.offer(new Integer(24));
        minHeap1.offer(new Integer(28));
        minHeap1.offer(new Integer(13));
        minHeap1.offer(new Integer(16));
        minHeap1.offer(new Integer(18));

        Iterator<Integer> it2 = minHeap1.iterator();

        assertEquals(new Integer(1),it2.next());
        assertEquals(new Integer(7),it2.next());
        assertEquals(new Integer(20),it2.next());
        assertEquals(new Integer(11),it2.next());
        assertEquals(new Integer(15),it2.next());
        assertEquals(new Integer(27),it2.next());
        //then 18 goes up, bubble up, store to memorylist
        it2.remove();
        assertEquals(new Integer(24),it2.next());
        //then 16 goes up, bubble up, store to memorylist
        it2.remove();
        assertEquals(new Integer(28),it2.next());
        assertEquals(new Integer(13),it2.next());
        //memory:18
        assertEquals(new Integer(18),it2.next());
        //remove 18 in the memorylist
        it2.remove();
        //memory:16
        assertEquals(new Integer(16),it2.next());

        /** for debug usage
          Iterator<Integer> it = minHeap1.iterator();
          System.out.println();
          while(it.hasNext()){
          System.out.print(it.next() + " ");
          }
          */

    }

    /**
     * This test is commented out. 
     * This test uses priority queue to compare with the next test. 
     * This test mainly tests the trickle down functionality
     * by calling poll three times. 
     * The iterator is used for debug usage. 
     *
     public void testPQTrickleDown(){
     pq.offer(new Integer(5));
     pq.offer(new Integer(6));
     pq.offer(new Integer(10));
     pq.offer(new Integer(7));
     pq.offer(new Integer(14));
     pq.offer(new Integer(11));
     pq.offer(new Integer(21));
     pq.offer(new Integer(27));
     pq.offer(new Integer(18));
     pq.poll();
     pq.poll();
     pq.poll();
     Iterator<Integer> it = pq.iterator();
     while(it.hasNext()){
     System.out.print(it.next() + " ");
     }
     }
     */
    /** DEBUG USAGE
     * This test mainly tests the trickle down functionality
     * by calling poll three times. 
     * The iterator is used for debug usage. 
     * the minHeap1 is a min Heap. 
     */ 
    public void testTrickleDown(){

        minHeap1.offer(new Integer(5));
        minHeap1.offer(new Integer(6));
        minHeap1.offer(new Integer(10));
        minHeap1.offer(new Integer(7));
        minHeap1.offer(new Integer(14));
        minHeap1.offer(new Integer(11));
        minHeap1.offer(new Integer(21));
        minHeap1.offer(new Integer(27));
        minHeap1.offer(new Integer(18));
        minHeap1.poll();
        //minHeap1.printOut();
        minHeap1.poll();
        //minHeap1.printOut();
        minHeap1.poll();
        //minHeap1.printOut();
    }

    /**
     * This tester uses priority queue. 
     * This is commented out. This tester tests the offer() functionality 
     * by adding elements and printing the output out using iterator

     public void testPQLargeNumbers(){
     pq.offer(new Integer(2));
     pq.offer(new Integer(38));
     pq.offer(new Integer(18));
     pq.offer(new Integer(26));
     pq.offer(new Integer(40));
     pq.offer(new Integer(106));
     pq.offer(new Integer(762));
     pq.offer(new Integer(83));
     pq.offer(new Integer(3));
     pq.offer(new Integer(80));
     pq.offer(new Integer(43));
     pq.offer(new Integer(77));
     pq.offer(new Integer(29));
     pq.offer(new Integer(902));
     Iterator<Integer> it = pq.iterator();
     while(it.hasNext()){
     System.out.print(it.next() + " ");
     }

     }
     */

    /** DEBUG usage: (COMMENTED OUT)
     * this tester prints out the output of the heap every time after offer
     * method is called and I use this to compare with my speculation. 

     public void testLargeNumbers(){
     five.offer(new Integer(2));
     five.printOut(); 

     five.offer(new Integer(38));
     five.printOut(); 
     five.offer(new Integer(18));
     five.printOut(); 
     five.offer(new Integer(26));
     five.printOut(); 
     five.offer(new Integer(40));
     five.printOut(); 
     five.offer(new Integer(106));
     five.printOut(); 
     five.offer(new Integer(762));
     five.printOut(); 
     five.offer(new Integer(83));
     five.printOut(); 
     five.offer(new Integer(3));
     five.printOut(); 
     five.offer(new Integer(80));
     five.printOut(); 
     five.offer(new Integer(43));
     five.printOut(); 
     five.offer(new Integer(77));
     five.printOut(); 
     five.offer(new Integer(29));
     five.printOut(); 
     five.offer(new Integer(902));
     five.printOut(); 
     }
     */

    /** test basic size property for five */
    public void testFiveProperty(){
        five.offer(new Integer(0));
        assertEquals("Check size",1,five.size());
    }

    /** test basic iterator functionality for alpha
     * This test uses iterator to go through a heap of String elements. 
     * The iterator calls next() to go through the whole list until there's no
     * more elements.
     */
    public void testAlphaOffer(){
        assertEquals(7, alpha.size());

        Iterator<String> it = alpha.iterator();
        assertEquals(new String("ape"),it.next()); 
        assertEquals(new String("cat"),it.next()); 
        assertEquals(new String("bear"),it.next()); 
        assertEquals(new String("giraffe"),it.next()); 
        assertEquals(new String("duck"),it.next()); 
        assertEquals(new String("fly"),it.next()); 
        assertEquals(new String("elephant"),it.next()); 
        assertFalse(it.hasNext());
    }

    /** test copy constructor
     *  This test uses the copy constructor to copy another heap called alpha. 
     *  Such that I ensure that the copy constructor is working
     */
    public void testAlphaCopy(){

        Heap12<String> alphaCopy = new Heap12<String>(alpha);
        assertEquals(new String("ape"),alphaCopy.peek());
        Iterator<String> it = alpha.iterator();
        assertEquals(new String("ape"),it.next()); 
        assertEquals(new String("cat"),it.next()); 
        assertEquals(new String("bear"),it.next()); 
        assertEquals(new String("giraffe"),it.next()); 
        assertEquals(new String("duck"),it.next()); 
        assertEquals(new String("fly"),it.next()); 
        assertEquals(new String("elephant"),it.next()); 
        assertFalse(it.hasNext());
    }

    /** test alpha poll functionality 
     *  This tester test the poll() on the heap alpha. 
     *  the poll() is called step by step such that the top element and the
     *  size both matches my speculation
     */
    public void testAlphaPoll(){
        assertEquals(new String("ape"),alpha.poll());
        assertEquals(new String("bear"),alpha.peek());
        assertEquals(6,alpha.size());
        //alpha.printOut();
        //System.out.println();
        assertEquals(new String("bear"),alpha.poll());
        assertEquals(new String("cat"),alpha.peek());
        assertEquals(5,alpha.size());
        //alpha.printOut();
        //System.out.println();
        alpha.poll();
        assertEquals(new String("duck"),alpha.peek());
        assertEquals(4,alpha.size());
        //alpha.printOut();
        //System.out.println();
        alpha.poll();
        assertEquals(new String("elephant"),alpha.peek());
        assertEquals(3,alpha.size());
        //alpha.printOut();
        //System.out.println();
        alpha.poll();
        assertEquals(new String("fly"),alpha.peek());
        assertEquals(2,alpha.size());
        //alpha.printOut();
        //System.out.println();
        alpha.poll();
        assertEquals(new String("giraffe"),alpha.peek());
        assertEquals(1,alpha.size());
        //alpha.printOut();
        //System.out.println();
        alpha.poll();
        assertEquals(0,alpha.size());
        assertNull(alpha.poll());
    }

    /**This tester tests if the null pointer exception is called
     * when I pass in null element to the offer method
     */
    public void testOfferNull(){
        try{
            alpha.offer(null);
            fail();
        } catch (NullPointerException e){

        }
    }

    /** test basic functionalities for five
     * This test makes use of offer() and poll() and size()
     * Then I printed the result out to see if output
     * matches my speculation
     */
    public void testFivePoll(){
        five.offer(new Integer(5));
        five.offer(new Integer(6));
        five.offer(new Integer(4));
        five.offer(new Integer(3));
        assertEquals(4,five.size());
        //five.printOut();
        five.poll();
        //five.printOut();
    }

    /**
     * Test Basic functionality of iterator
     * This test uses max Heap. 
     * First adding some elements using offer()
     * then uses iterator to ensure things are returned correctly.
     */
    public void testBasicIterator(){
        //maxHeap
        five.offer(new Integer(5));
        five.offer(new Integer(6));
        five.offer(new Integer(4));
        five.offer(new Integer(3));
        //iterator
        Iterator<Integer> it = five.iterator();
        assertTrue(it.hasNext());
        assertEquals(new Integer(6),it.next()); 
        assertTrue(it.hasNext());
        assertEquals(new Integer(5),it.next()); 
        assertTrue(it.hasNext());
        assertEquals(new Integer(4),it.next()); 
        assertTrue(it.hasNext());
        assertEquals(new Integer(3),it.next()); 
        assertTrue(!(it.hasNext()));
    }

    /**
     * This tester tests if IllegalStateException is thrown when I remove
     * things twice consecutively using iterator.
     */
    public void testIteException(){
        //maxHeap
        five.offer(new Integer(5));
        five.offer(new Integer(6));
        five.offer(new Integer(4));
        five.offer(new Integer(3));

        //iterator
        Iterator<Integer> it = five.iterator();

        try{
            it.next();
            it.remove();
            it.remove();
            fail();
        } catch(IllegalStateException e){
            //System.out.println("caught!");
        }
    }

    /**
     * This tester tests basic remove functionality on iterator
     * I step-by-step check the size and output (printed out) and keep track
     * of the next element while iterating through the heap.
     */
    public void testIteBasicRemove(){

        //maxHeap
        five.offer(new Integer(5));
        five.offer(new Integer(6));
        five.offer(new Integer(4));
        five.offer(new Integer(3));

        //iterator
        Iterator<Integer> it = five.iterator();

        assertEquals(new Integer(6),it.next()); 
        it.remove();
        //five.printOut();
        assertEquals(new Integer(5),it.next()); 
        it.remove();
        //five.printOut();
        assertEquals(new Integer(4),it.next()); 
        assertEquals(new Integer(3),it.next()); 
        assertTrue(!(it.hasNext())); 
        it.remove(); 
        //five.printOut();
        assertEquals(new Integer(4),five.peek()); 
        assertEquals(1,five.size()); 
    }

    /**
     * This tester tests one edge case of the iterator remove():
     * to remove the last element. 
     * When removing the last element, no swap is performed. 
     * The last element is simply removed. 
     * And then when I call next again, since there's no more element, 
     * a NoSuchElementException is thrown. 
     */
    public void testIteRemoveLastElement(){
        try{
            Iterator<String> it = alpha.iterator();
            assertEquals(new String("ape"),it.next()); 
            assertEquals(new String("cat"),it.next()); 
            assertEquals(new String("bear"),it.next()); 
            assertEquals(new String("giraffe"),it.next()); 
            assertEquals(new String("duck"),it.next()); 
            assertEquals(new String("fly"),it.next());
            //elephant is last
            assertEquals(new String("elephant"),it.next()); 
            //try to call iterator remove
            it.remove();
            //alpha.printOut(); 
            it.next();
            fail();
        } catch (NoSuchElementException excep){

        }
    }

}//end class
