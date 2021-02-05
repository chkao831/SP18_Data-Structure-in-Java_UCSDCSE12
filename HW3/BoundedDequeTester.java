import junit.framework.*;
import java.util.*;
/**File: BoundedDequeTester.java
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * ID: A92092372
 * Email: c4kao@ucsd.edu
 * Date: May 2, 2018
 */

/**
 * This class is a JUnit tester for the Deque12 class
 * This class extends junit.framwork.TestCase
 */
public class BoundedDequeTester extends TestCase
{

    //define instance variables
    private Deque12<Integer> five;


    /**
     * constructor
     */
    public BoundedDequeTester(){
        super();
    }

    /**
     * Standard Test Fixture.
     * Use a Deque12 object as a test fixture. 
     */
    public void setUp(){

        five = new Deque12<Integer>(5);

    }//end setUp

    /** test capacity for five */
    public void testGetCapaForFive(){
        assertEquals("Check capa",5,five.capacity());
    }

    /** test size for five */
    public void testSizeForFive(){
        assertEquals("Check size",0,five.size());
    }

    /** test addFront */
    public void testAddFrontForFive(){
        five.addFront(new Integer(1));
        assertEquals("first",0,five.front);
        assertEquals("size",1,five.size());
    }

    /** test addBack */
    public void testAddBackForFive(){
        five.addBack(new Integer(1));
        assertEquals("last",0,five.rear);
        assertEquals("size",1,five.size());
    }

    /** test removeFront for five */
    public void testRemoveFrontForFive(){
        five.addFront(new Integer(1));
        assertEquals("removed",new Integer(1),five.removeFront());
    }

    /** test removeBack for five */
    public void testRemoveBackForFive(){
        five.addBack(new Integer(5));
        assertEquals("removed",new Integer(5),five.removeBack());
    }

    /** test peekfront for five */
    public void testPeekFrontFive(){
        five.addFront(new Integer(1));
        five.addFront(new Integer(2));
        five.addFront(new Integer(3));
        assertEquals("peekfront",new Integer(3),five.peekFront());
    }

    /** test peekback for five */
    public void testPeekBackFive(){
        five.addBack(new Integer(1));
        five.addBack(new Integer(2));
        five.addBack(new Integer(3));
        five.addBack(new Integer(4));
        five.addBack(new Integer(5));
        assertEquals("size",5,five.size());
        assertEquals("peekback",new Integer(5),five.peekBack());
    }

    /** test if empty, how peekback works */
    public void testPeekBackEmpty(){
        assertNull("null",five.peekBack());
    }

    /** test if empty, how peekfront works */
    public void testPeekFrontEmpty(){
        five.addFront(new Integer(1));
        five.addFront(new Integer(2));
        assertEquals("returned 2",new Integer(2),five.removeFront());
        assertEquals("returned 1",new Integer(1),five.removeFront());
        assertNull("null",five.peekBack());
    }

    /** An extensive test for addback and addfront on five */
    public void testExtensiveAdd(){
        five.addFront(new Integer(1));
        five.addBack(new Integer(2));

        assertEquals("peekfront",new Integer(1),five.peekFront());
        assertEquals("peekback",new Integer(2),five.peekBack());

        five.addFront(new Integer(3));
        five.addFront(new Integer(4));
        five.addBack(new Integer(5));

        assertEquals("size",5,five.size());
        assertEquals("peekfront",new Integer(4),five.peekFront());
        assertEquals("peekback",new Integer(5),five.peekBack());

        assertEquals("0th index",new Integer(1),five.list.get(0));
        assertEquals("1st index",new Integer(2),five.list.get(1));
        assertEquals("2nd index",new Integer(5),five.list.get(2));
        assertNull("it is gap",five.list.get(3));
        assertEquals("4th index",new Integer(4),five.list.get(4));
        assertEquals("5th index",new Integer(3),five.list.get(5));

        assertTrue("this should be false",!five.addFront(new Integer(5)));
    }

    /** cannot add null element */
    public void testAddFrontException(){
        try
        {
            five.addFront(null);
            fail();
        }
        catch(NullPointerException e){

        }
    }

    /** after adding, step by step testing remove back */
    public void testRemoveBackToEmpty(){
        five.addBack(new Integer(1));
        five.addBack(new Integer(2));
        five.addBack(new Integer(3));
        five.addBack(new Integer(4));
        five.addBack(new Integer(5));
        assertEquals("peekback",new Integer(5),five.peekBack());
        assertEquals("peekfront",new Integer(1),five.peekFront());

        assertEquals("rmback5",new Integer(5),five.removeBack());
        assertEquals("size",4,five.size());
        assertEquals("rmback4",new Integer(4),five.removeBack());
        assertEquals("size",3,five.size());
        assertEquals("rmback3",new Integer(3),five.removeBack());
        assertEquals("size",2,five.size());
        assertEquals("rmback2",new Integer(2),five.removeBack());
        assertEquals("size",1,five.size());
        assertEquals("rmback1",new Integer(1),five.removeBack());
        assertEquals("size",0,five.size());
        assertNull("no more",five.removeBack());
    }

    /** after adding, remove back and front step by step until empty */
    public void testAnotherRemove(){
        five.addFront(new Integer(1));
        five.addFront(new Integer(2));
        five.addFront(new Integer(3));
        five.addBack(new Integer(4));
        assertEquals("size",4,five.size());
        assertEquals("rmback",new Integer(4),five.removeBack());
        assertEquals("size",3,five.size());
        assertEquals("rmback",new Integer(1),five.removeBack());
        assertEquals("size",2,five.size());
        assertEquals("rmback",new Integer(2),five.removeBack());
        assertEquals("size",1,five.size());
        assertEquals("rmlast",new Integer(3),five.removeFront());
        assertEquals("size",0,five.size());
        assertNull("no more",five.removeBack());
        assertNull("no more",five.removeFront());

    }

    /** ensure that the capacity cannot be negative */
    public void testConstrucIllegalArgExp(){
        try{
            Deque12<Integer> negaDeque  = new Deque12<Integer>(-1);
            fail();
        } catch(IllegalArgumentException e){

        }
    }

    /** ensure that addFront and addBack do not work with zero capa*/
    public void testEmptyCannotAdd(){
        Deque12<String> noCapa = new Deque12<String>(0);
        assertEquals("capa",0,noCapa.capacity());
        assertEquals("size",0,noCapa.size());
        String str = new String("   ");
        assertTrue("false",!(noCapa.addFront(str)));
        assertTrue("false",!(noCapa.addBack(str)));
        assertNull("no size",noCapa.removeFront());
        assertNull("no size",noCapa.removeBack());

    }
}//end class
