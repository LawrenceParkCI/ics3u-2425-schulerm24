package Unit1;

/**
* Description:
* Date:
* @author 
*/
public class commenting {
  
  /**
   * This is the entry point to the program.
   * @param args unused
   */
  public static void main(String[] args) {
    /*
     * Comments are meant to communicate ideas with other programmers and to keep track of one's logic
    */


    /**
     * This is a javadoc comment
	 * 
     * These comments are used to create official documentation. We see them at the top of our programs (header) as well as to describe java-defined blocks of code (class, methods, attributes)
  	 *
     * Javadoc comments can be multi-line.
     * For now, we don't use Javdoc comments beside the header until unit 3.
    */

    /*
     * This is a block comment
	 * 
     * Block comments can be multi-line
     * These comments are used to explain a section of code.
    */

    //this is a line comment
    //line comments are single line
    System.out.println("Testing"); //line comments can also be placed at the end of a line

    /*
     * Tracing Practice
     * For the following code:
     * -every time a variable is assigned a new value, write a line comment beside it telling what is the value currently stored in the variable.
	 * 
     * -every time you see the block comment, write down each variable and what are the values at that time in the code
    */


    int num1 = 20;
    double num2 = 36.2;
    String stringNum = "";
    System.out.println("num1 = " + num1);
    System.out.println("num2 = " + num2);

    /*
    * Variables Name    |   Value
    * ---------------------------------
    * num1              |
    * num2              |
    * stringNum         |
    */

    num1 += 20;
    System.out.println("Num1 = " + num1);
    num1 = num1 / 3 * 2;
    System.out.println("Num1 = " + num1);
    num1 = (int)(num2 * 3 - (2 + 5) * 8);
    System.out.println("Num1 = " + num1);
    num2 = ((int)num2) - num1;
    System.out.println("Num2 = " + num2);
    stringNum = num1 + " " + num2;
  

    /*
    * Variables Name    |   Value
    * ---------------------------------
    * num1              |
    * num2              |
    * stringNum         |
    */

    num2 /= 2.5;
    System.out.println("Num2 = " + num2);
    num1 /= 2;
    System.out.println("Num1 = " + num1);
    stringNum = stringNum + "; ";
    stringNum = stringNum + num1 + " " + num2;


    /*
    * Variables Name    |   Value
    * ---------------------------------
    * num1              |
    * num2              |
    * stringNum         |
    */
  }
}