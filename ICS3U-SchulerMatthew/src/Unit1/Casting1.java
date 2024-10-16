package unit1;

import java.util.Scanner;

/**
* Description:
* Date:
* @author 
*/
public class Casting1 {
	
	/**
	 * This is the entry point to the program.
	 * @param args unused
	 */
  public static void main(String[] args) {
    /*
    *Casting* means to change data from one type to another.

    Implicit casting - changing of data types without specifically writing extra code

    Explicit casting - changing of data types by specifically writing extra code
    */

    System.out.println("Part 1");

    int intNum;
    intNum = 10;

    double doubleNum;
    
    //implicit casting
    doubleNum = intNum;

    //What type of data is printed here? Why?
    //floating point
    System.out.println(doubleNum);

    //can you explain what is happening in this code?
    //it is making doubleNum = 2.2
    doubleNum = doubleNum + 2.2;

    //explicit casting
    intNum = (int) doubleNum;

    //What type of data is printed here? Why?
    //short
    System.out.println(intNum);

    /*
    Demonstrate casting between each of the data type. Write down whether it is explicit or implicit
      int to double
      double to int
      int to long
      long to int

    Why do you think these are implicit/explicit? (Hint: Think about the size of each data and the type of data)
    */

    System.out.println("Part 2");

    //understanding what you can do with casting, can you round the following variable to the nearest 10th?
    double myNum;
    double myNum
    Scanner in = new Scanner(System.in);
    System.out.println("Give me decimal number up to the hundredth");
    //code

    System.out.print("Rounding down to the nearest tenth, it is: ");
    
  }
}