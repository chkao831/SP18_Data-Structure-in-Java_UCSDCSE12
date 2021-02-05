import java.util.*;
import java.io.*;

/**
 * File: ReverseList.java
 * This file contains the RevereseList class. 
 * @author Chih-Hsuan Kao
 * Login: cs12sgh
 * PID: A92092372
 * Email: c4kao@ucsd.edu
 * Date: April 11, 2018
 * Sources of Help: N/A
 */

/**
 * ReverseList class has capabilities of reading a file of text line-by-line and
 * printing the file to standard output in reverse line order using linkedlist.  
 */ 
public class ReverseList {

    //a linkedlist instance that references string objects
    public static LinkedList<String> ll = new LinkedList<String>();

    /**
     * Main method
     * @param args supplied command-line arguments as an array of String objects
     * @exception FileNotFoundException if File does not exist
     */
    public static void main(String[] args) throws FileNotFoundException{

        ReverseList rl = new ReverseList();

        //if a file is not supplied in the command line
        if(args.length == 0){

            //print a usage statement to standard error
            System.err.println("usage: ReverseList <filename>");
            System.exit(1);
        }

        //otherwise, perform reversion by calling the method
        rl.reverse(args[0]);

    }//End Main Method

    /**
     * Method: reverse
     * Perform reversion using a LinkedList of String type from the Java
     * Collections Framework. 
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

        //otherwise, operate reversion
        //declare local variables
        String line = null;
        int count = 0;

        //if there is next line
        while(input.hasNextLine()){

            //use s string to catch the line
            line = input.nextLine();
            //add it to the linkedlist
            ll.add(count,line);
            //increment the count
            count++;
        }

        //after adding, get the total count of elements in the linkedlist
        int total = ll.size();

        //create a new linkedlist and add from the last element

        LinkedList<String> reverse = new LinkedList<String>();
        for(int i=(total-1); i>=0; i--){
            reverse.add((total-i-1),ll.get(i));
            System.out.println(reverse.get(total-i-1));
        }
    }//End reverse method   

}//End ReverseList class
