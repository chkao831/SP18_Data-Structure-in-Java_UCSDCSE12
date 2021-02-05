import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * File: HeapSort12.java
 * @author Philip Papadopoulos 
 * @date 28 April 2014
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * PID: A92092372
 * Email: c4kao@ucsd.edu
 * Source of Help: Zybook (textbook)
 * @date 27 May 2018
 */

/**
 * This is a heap sort algorithm. This uses Heap12 class to sort a list of
 * generic type T. This class implements Sort12 interface. 
 */
public class HeapSort12 implements Sort12
{
	public  <T extends Comparable<? super T>> void sort(List<T> list)
	{   
        //throw exception if list is null
        if(list == null){
            throw new NullPointerException("Null argument");
        }
        //create an empty Heap
        Heap12<T> heapsort = new Heap12<T>(true);
        //iterate through the list, adding each element returned by next() to
        //the heap
        Iterator<T> lt = list.listIterator();
       
        //heapify by calling offer
        while(lt.hasNext()){
            heapsort.offer(lt.next());
        }
        //catch size of heap
        int totalSize = heapsort.size();

        //removes the maximum value, stores that value at the end index, 
        //and decrements the end index, until the end index is 0
        for (int j = totalSize; j>0; j--){
            T ele= heapsort.poll();
            list.set(heapsort.size(), ele);
        }
    }
}
                                                                                  
