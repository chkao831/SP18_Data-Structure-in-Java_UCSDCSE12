/**
 * This File contains the class Counter. 
 *
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * PID: A92092372
 * Email: c4kao@ucsd.edu
 *
 * @version 1.0
 * @see java.lang.Object
 */

public class Counter {

    /**
     * The {@link int} instance representing integer count
     * The {@link int} instance representing integer step
     */
	private int count = 0;
	private int step = 1;

    /**
     * Create a counter initialized to zero, step increment of 1.
     */
	public Counter()
	{
		// Nothing to do here
	}

    /**
     * Creates a counter initialized to zero
     * @param theStep positive step increment for counter
     */
	public Counter(int theStep)
	{
		int s;
		if ( (s = Math.abs(theStep)) >  0)
			step = s; 
	}

    /**
     * Retrieve current value of Counter
     * @return current value of counter
     */
	public int getCount() {
		return count;
	}
    
    /**
     * Increment the counter by its step
     */
	public void increment() {
		count += step;
	}

    /**
     * Decrement the counter by its step. Stop at zero. 
     */
	public void decrement() {
		if (count > step ) 
			count -= step;
		else
			count = 0;
	}

    /**
     * Reset the counter to zero
     */
	public void reset() {
        //as instructed, modify Counter.java so that the Reset Test fails
        count = 5;
	}
}
