import java.lang.Comparable;
import java.util.List;
import java.util.ArrayList;
/**File: Merge12.java
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * ID: A92092372
 * Email: c4kao@ucsd.edu
 * Date: May 16, 2018
 * Source of Help: Zybook (online textbook)
 */

/**
 * Merge12 is a class that implements merge sort for Lists of Comparables.
 * The class Merge12 implements Sort12 interface. 
 */
public class Merge12 implements Sort12
{
    /**
     * Method: sort
     * This method takes any List object, modifies the list object to be sorted
     * at the end of the method. 
     * @param list a List object of T type
     */
    public  <T extends Comparable<? super T>> void  sort(List<T> list)
    {
        //throw exception if list is null
        if (list == null){
            throw new NullPointerException("Null argument to sort");
        }

        //calculate list size
        int listSize = list.size();
        //create a new arraylist with capacity of list object size
        ArrayList<T> arrList = new ArrayList<T>(listSize);
        //add each element into the arrayList
        arrList.addAll(list);
        
        //after completing converting to arrayList, call method to sort
        internalMergeSort(arrList, 0, listSize-1);
        
        //after performing sorting, modify back to the list 
        for(int j = 0; j < listSize; j++){
            list.set(j,arrList.get(j));
        }
    }

    /**
     * Method: internalMergeSort
     * Takes in an ArrayList (a copy of the original list), and then divides the
     * list that is to be sorted into smaller lists by recursion. 
     * @param inputArray an ArrayList to be sorted
     * @param first index of the first element
     * @param last index of the last element
     */
    private  <T extends Comparable<? super T>> void 
        internalMergeSort(ArrayList<T> inputArray, int first, int last)
        { 
            //initialize middle point
            int middle = 0;

            if(first < last){
                //find the middle point
                middle = (first + last) / 2;

                //recursively sort left and right partitions
                internalMergeSort(inputArray, first, middle);
                internalMergeSort(inputArray, middle+1, last);
                
                /* the following code is for debugging, printout output */
                /*
                System.out.print(first+ " "+ last + "\n");
                for(int i=0; i<inputArray.size(); i++){
                    System.out.println(inputArray.get(i)+ " ");
                }
                */

                //merge the sorted halves
                merge(inputArray, first, middle, last);
            }

        } // closing bracket for internalMergeSort method

    /**
     * Method: merge
     * Combines two sorted ArrayLists into one sorted list. 
     * @param inputArray an ArrayList to be sorted
     * @param first index of the first element
     * @param mid the midpoint of the current list
     * @param last index of the last element
     */
    private  <T extends Comparable<? super T>> void 
        merge(ArrayList<T> inputArray, int first, int middle, int last)
        {
            // initialize local variable for the size of combined arrayLists
            int biggerSize = last - first + 1;
            // and then creates a temporary ArrayList to dynamically allocates
            // merged numbers; the list has capacity of [biggerSize]
            ArrayList<T> tempArray = new ArrayList<T>(biggerSize);

            //initialize variables to indicate where the current position is at
            //the current position for merged list
            int mergeAt = 0;
            //the current position for left and right partition
            int leftAt = first;
            int rightAt = middle + 1;

            //as long as both partitions are nonempty, compare both sides 
            //and add smallest element from either side to temporary list
            while (leftAt <= middle && rightAt <= last){
                //if element from the left is equal to or smaller than the right
                if((inputArray.get(leftAt)).compareTo(inputArray.get(rightAt))<=0){
                    //store the element from the left
                    tempArray.add(inputArray.get(leftAt));
                    //increment the leftAt count
                    leftAt++;
                }
                //otherwise, if element from the right is smaller
                else{
                    //store the element from the right
                    tempArray.add(inputArray.get(rightAt));
                    //increment the rightAt count
                    rightAt++;
                }
                //upon successful store, increment the mergeAt count
                mergeAt++;
            }
 
            // if now the left partition has nothing, while there's something at
            // the right to be added, add them all
            while (rightAt <= last) {
                    //store the element from the right
                    tempArray.add(inputArray.get(rightAt));
                    //increment the rightAt count
                    rightAt++;
                    //increment the mergeAt count
                    mergeAt++;
            }

            // if now the right partition has nothing, while there's something at
            // the left to be added, add them all
            while (leftAt <= middle) {
                    //store the element from the right
                    tempArray.add(inputArray.get(leftAt));
                    //increment the rightAt count
                    leftAt++;
                    //increment the mergeAt count
                    mergeAt++;
            }

            //copy back to the inputArrayList
            for(int i = 0; i < biggerSize; i++){
                inputArray.set(i+first, tempArray.get(i));
            }

        } // closing bracket for merge method
}
// vim:ts=4:sw=4:sw=78
