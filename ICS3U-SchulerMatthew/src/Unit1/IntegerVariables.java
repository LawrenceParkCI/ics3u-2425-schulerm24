package Unit1;

import java.util.Scanner;

public class IntegerVariables {

	
public static void main(String[] args) {

	
Scanner sc = new Scanner (System.in);	

//declare variables
int length, width, depth;

//get the user input
System.out.println("AREA PROGRAM");
System.out.print("Type in the length of the rectangle and press <Enter>");
length = sc.nextInt();


/*
//request unit of measurement 1
System.out.println("Type in the unit of measurement and press <Emter>");
unit1 = sc.nextInt();
*/

System.out.println("Type in the width of the rectangle and press <Enter>");
width = sc.nextInt();


/*
//request unit of measurement 2
System.out.println("Type in the unit of measurement and press <Enter>");
unit2 = sc.nextInt();
*/


//get depth

System.out.println("Type in the depth of the rectangle and press <Enter>");
depth = sc.nextInt();


//calculate the area
int area = length * width;


//print the output
System.out.println("The area of your rectangle is " + area);
sc.close();




//calculate the volume
int volume = area * depth;

//print the output
System.out.println("The volume of your prism is " + volume);
sc.close();

	}
}