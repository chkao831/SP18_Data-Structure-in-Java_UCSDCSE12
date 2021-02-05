import java.util.*;
import java.io.*;

/**
 * File: ReverseArray.java
 * This file contains the ReverseArray class. 
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * PID: A92092372
 * Email: c4kao@ucsd.edu
 * Date: April 10, 2018
 * Sources of Help: TA OH
 */ 

/**
 * ReverseArray Class has capabilities of reading a file of text line-by-line
 * and printing the file to standard output in reverse line order using arrays
 * of strings.  
 */
public class ReverseArray {

    //an int to use when create an array that references 100 strings
    public static final int strArrSize = 100;
    //a String array instance that references String objects 
    public static String[] strArr = new String[0];

    /**
     * Main method
     * @param args supplied command-line arguments as an array of String objects
     * @exception FileNotFoundException if File does not exist
     */
    public static void main(String[] args) throws FileNotFoundException{

        ReverseArray ra = new ReverseArray();

        //if a file is not supplied in the command line
        if(args.length == 0){

            //print a usage statement to standard error
            System.err.println("usage: ReverseArray <filename>");
            System.exit(1);
        }

        //otherwise, perform reversion by calling reverse method
        ra.reverse(args[0]);

    }//End Main Method

    /**
     * Method: reverse
     * Perform reversion by extending slots (with multiple of 100) in the array
     * of Strings when there's no space to store any more line. 
     * @param fileName the supplied textfile as a string
     * @exception FileNotFoundException if File does not exist
     */
    public static void reverse(String fileName) throws FileNotFoundException{

        //reading in file and using a scanner to parse strings
        File sourceFile = new File(fileName);
        Scanner input = null;

        try{
            input = new Scanner(sourceFile);
        }
        //if the file does not exist
        catch (FileNotFoundException e){
            //print File Not Found to standard error
            System.err.println("File Not Found.");
            throw e;
        }

        //initialize local variables
        int count = 0;
        String[] newArr = null;
        String line = null;

        //if there is next line
        while( input.hasNextLine()){

            //and if count reaches the multiple of 100
            if(count%(strArrSize) == 0){

                //referencing newArr to an empty string
                newArr = new String[(strArrSize)*((count/100)+1)];

                //copying over old to new
                for(int i=0; i<strArr.length; i++){
                    newArr[i] = strArr[i];
                }
            }

            //reading in input
            line = input.nextLine();
            //assigning the input to the newArray
            newArr[count] = line;
            //make the strArr refer to newArray
            strArr = newArr;
            //increment the count of total lines
            count++;
        }

        //how many lines have been read
        int totalCount = strArr.length;
        //create a string array with this size
        String[] rev = new String[totalCount];

        //perform reversion by reading strings from the end
        for(int k= count-1; k>=0; k--){
            rev[count-k-1] = strArr[k];
            System.out.println(rev[count-k-1]);
        }

    }//End reverse method
}//End class
