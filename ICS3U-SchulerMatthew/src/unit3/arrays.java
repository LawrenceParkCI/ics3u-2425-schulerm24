package unit3;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class arrays {
	  public static void main(String[] args) {
	    /**
	      Introduction
	    */
	    /*
	    Sometimes we want to store many values, but we don't want to
	    create so many variables

	    For example, we have almost 30 students in the class. It would
	    take a long time to write the variables for all the students,
	    let alone keeping their marks.
	    */

	    /*
	      This is why we can use an array - this allows us to refer to
	      many of the same datatype using one name (identifier), and a number (index) for which one to use.

	      To declare an array, we need the [] to indicate it is an array.
	      The type of array needs to be stated as well.
	    */

	    /**
	      Declaring and Initializing a pre-filled array
	    */
	    System.out.println("---------------------------------------------");
	    System.out.println("Declaring and Initializing a pre-filled array");
	    System.out.println("---------------------------------------------");
	    //Declares and initializes an array with 3 values, or 3 *elements*
	    String [] fruits = {"Apple","Banana","Lemon","Pear","Orange"};
	    
	    //Modify the array above by adding 2 more fruits to it

	    //Declare and initialize an array of 3 marks (double data type)
	    double[] marks = {0, 5, 50};
	   

	    /**
	      Using an element
	    */
	    System.out.println();
	    System.out.println("----------------");
	    System.out.println("Using an element");
	    System.out.println("----------------");
	    //To use an element, we use the name(identifier) of the array,
	    //and the numbered index of the elements.
	    System.out.println(fruits[1]); 
	    //What does it print out? Did it match what you think it 
	    //should print?

	    //Try printing out the first and last fruit in the array

	    
	    //Create a new variable called sum. add the values of the marks
	    //array to it, and print it out.


	    /**
	      Assigning a value in an element
	    */
	    System.out.println();
	    System.out.println("-------------------------------");
	    System.out.println("Assigning a value in an element");
	    System.out.println("-------------------------------");
	    //To assign a value to a specific element, it is the same as for
	    //when you use a variable
	    //you need the variable name(identifier), and the index
	    fruits[0] = "Mangosteen";
	    System.out.println(fruits[0]);


	    //Assign the 3rd mark to be 0

	    /**
	      Declaring and Initializing an empty array
	    */
	    System.out.println();
	    System.out.println("-----------------------------------------");
	    System.out.println("Declaring and Initializing an empty array");
	    System.out.println("-----------------------------------------");
	    //When declaring an array, it works just like any other variable.
	    boolean [] isPassing;

	    //When initializing the array, you are simply creating the space
	    //in computer memory to store the values. The actual elements may not be
	    //initialized, depending on the language.

	    //this initialized the array to hold 5 elements
	    isPassing = new boolean[5];

	    System.out.println(isPassing[0]);

	    //Declare and initialize an array of 3 student names
	    
	    //print out the first element. What does it say?

	    /*
	      Initializing the elements of the array
	    */
	    System.out.println();
	    System.out.println("--------------------------------------");
	    System.out.println("Initializing the elements of the array");
	    System.out.println("--------------------------------------");
	    //same as above
	    //you need the variable name(identifier), and the index
	    isPassing[0] = true;
	    isPassing[1] = true;
	    isPassing[2] = false;
	    isPassing[3] = true;
	    isPassing[4] = false;


	    //Initialize the elements of the student names. Print them out.
	    

	    /**
	      Printing an array
	    */
	    System.out.println();
	    System.out.println("-----------------");
	    System.out.println("Printing an array");
	    System.out.println("-----------------");

	    System.out.println(isPassing);
	    //What do you see printed? Is it useful for us?

	    //How do you think we can print out each element of the array?

	  }
	}




