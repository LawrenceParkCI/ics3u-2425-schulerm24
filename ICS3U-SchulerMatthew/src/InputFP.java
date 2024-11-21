import java.util.Scanner;

public class InputFP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	Scanner sc = new Scanner (System.in);	
	
	//declare variables	
	double num1, num2;
	
	//get first number input
	System.out.println("Input your first number");
	num1 = sc.nextDouble();
	
	//get second number input
	System.out.println("Input your second number");
	num2 = sc.nextDouble();
	
	System.out.println();
	
	//output numbers
	System.out.println("The first number entered was " + num1);
	System.out.println("The second number entered was " + num2);
	
	//add numbers
	System.out.println("SUM");
	
	double sum = num1 + num2;
	
	//output sum
	System.out.println("The sum of the numbers is " + sum);
	sc.close();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
