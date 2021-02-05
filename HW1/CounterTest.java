import junit.framework.TestCase;
/**
 * File: CounterTest.java
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * Email: c4kao@ucsd.edu
 * Date: Apr 9, 2018
 * Sources of Help: N/A 
 */
 
/**
 * Class CounterTest extends TestCase from junit framwork. Purpose is to test
 * code from the command line. 
 */
public class CounterTest extends TestCase {

	private Counter counter, counter2;

	@Override
	/* this sets up the test fixture. JUnit invokes this method before
 	   every testXXX method */
	protected void setUp() throws Exception {
		super.setUp();
		counter = new Counter();
		counter2 = new Counter(2);
	}

	public void testDefaultValueOfCounterIsZero() {
		System.out.println("Checking Default Counter Value is Zero");
		assertEquals(0, counter.getCount());
		assertEquals(0, counter2.getCount());
	}

	public void testIncrement() {
		System.out.println("Checking Proper Increment");
		counter.increment();
		assertEquals(1, counter.getCount());
		counter2.increment();
		assertEquals(2, counter2.getCount());
	}

	public void testMultipleIncrements() {
		System.out.println("Checking Multiple Increments");
		for (int i = 0; i < 10; i++) {
			counter.increment();
			assertEquals(i + 1, counter.getCount());
		}
	}

	public void testReset() {
		System.out.println("Checking Reset");
		/* write a test the verifies Reset */
        counter.reset();
        assertEquals(0,counter.getCount());
    }

	public void testDecrement() {
		System.out.println("Checking Decrement");
		for (int i = 0; i < 5; i++)
		{
			counter.increment();
			counter2.increment();
		}
		for (int i = 0; i < 6; i++)
		{
			counter.decrement();
			counter2.decrement();
		}
		/* write a test that verifies the proper values of
			 counter and counter2  */
	    assertEquals(0,counter.getCount());
        assertEquals(0,counter2.getCount());
    }
}
