package Unit1;

import java.util.Scanner;

public class shopping {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
	
		


		
		//purchase amounts
		
		System.out.println("Welcome To VEGETABLE VARIETY                   ");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~                   ");
		System.out.println();
		System.out.println("What is the name of your first item?");
		String item1 = sc.next();
		System.out.println("What is the cost of " + item1 + "?");
		double item1cost = sc.nextDouble();
		System.out.println("How many " + item1 + " are you purchasing?");
		int item1quant = sc.nextInt();
		
		System.out.println("What is the name of your second item?");
		String item2 = sc.next();
		System.out.println("What is the cost of " + item2 + "?");
		double item2cost = sc.nextDouble();
		System.out.println("How many " + item2 + " are you purchasing?");
		int item2quant = sc.nextInt();
		
		System.out.println("What is the name of your third item?");
		String item3 = sc.next();
		System.out.println("What is the cost of " + item3 + "?");
		double item3cost = sc.nextDouble();
		System.out.println("How many " + item3 + " are you purchasing?");
		int item3quant = sc.nextInt();
		
		System.out.println("What is the name of your fourth item?");
		String item4 = sc.next();
		System.out.println("What is the cost of " + item4 + "?");
		double item4cost = sc.nextDouble();
		System.out.println("How many " + item4 + " are you purchasing?");
		int item4quant = sc.nextInt();
		
		System.out.println("What is the name of your fifth item?");
		String item5 = sc.next();
		System.out.println("What is the cost of " + item5 + "?");
		double item5cost = sc.nextDouble();
		System.out.println("How many " + item5 + " are you purchasing?");
		int item5quant = sc.nextInt();
		
		double item1total = (item1cost * item1quant);
		double item2total = (item2cost * item2quant);
		double item3total = (item3cost * item3quant);
		double item4total = (item4cost * item4quant);
		double item5total = (item5cost * item5quant);
	
	//total
	
	double SubTotal =  (item1total + item2total + item3total + item4total + item5total);
	double Tax = (SubTotal * 0.13);
	double Total = (SubTotal + Tax);
	
	sc.close();
	System.out.println("------------------------------");
	System.out.println("      Veggies and Veyond      ");
	System.out.println("------------------------------");
	System.out.println("------------------------------");
	System.out.println(item1 + " x" + item1quant + "\t\t$" + item1total);
	System.out.println(item2 + " x" + item2quant + "\t\t$" + item2total);
	System.out.println(item3 + " x" + item3quant + "\t\t$" + item3total);
	System.out.println(item4 + " x" + item4quant + "\t\t$" + item4total);
	System.out.println(item5 + " x" + item5quant + "\t\t$" + item5total);
	System.out.println("------------------------------");
	System.out.println("Subtotal\t\t$" + SubTotal);
	System.out.println("Tax\t\t\t$" + Tax);
	System.out.println("Total\t\t\t$" + Total);
	System.out.println("------------------------------");
	System.out.println("------------------------------");
	System.out.println("          Thank You!          ");
	System.out.println("------------------------------");
	
	}

}
