import java.util.Scanner;

public class NetPay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


	Scanner sc = new Scanner (System.in);
		
	//define variables
		
	double hours, wage, insurance, tax;
	
	
	//get info
	
	System.out.println ("Type in your hours and press <Enter>");
	hours = sc.nextDouble(); 
	System.out.println ("Type in your hourly wage and press <Enter>");
	wage = sc.nextDouble();
	System.out.println ("Type in how much you pay for insurance and press <Enter>");
	insurance = sc.nextDouble();
	System.out.println("Type in the percentage your are taxed and press <Enter>");
	tax = sc.nextDouble();
	
	
// calculate
	
	double netPay = (hours*wage-insurance) - tax*(hours*wage-insurance);
	
	System.out.println("Your net pay is $" + netPay);
	sc.close();
	

	
	
	
	
	
	
	
	
	
	}

}
