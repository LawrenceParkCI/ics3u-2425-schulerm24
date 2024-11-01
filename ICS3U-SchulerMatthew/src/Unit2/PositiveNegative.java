package Unit2;

import java.util.Scanner;

public class PositiveNegative {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
	
		int num;
		
	System.out.println("Enter an integer and press <ENTER>");
		
	num = sc.nextInt();
	
	if (num >= 0) {
		System.out.print(num + " is positive"); 
		} 
	else { 
		System.out.print(num + " is negative"); 
		
		} 
	
	if (num % 7 == 0) {
		System.out.print(" and divisible by 7."); 
		} 
	else { 
		System.out.print(" and not divisible by 7."); 
		
		} 
	
	
}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}


