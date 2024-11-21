package Unit2;

import java.util.Scanner;

public class Switch1 {



	public static void main(String[] args) {	

	    Scanner in = new Scanner(System.in);
	    
	    System.out.println("What day is it?");
	    String day = in.next();

	
	    switch(day) {
	      case "Sunday":
	        System.out.println("It's already the weekend!");
	        break;
	      case "Monday":
	        System.out.println("5 days until the weekend");
	        break;
	      case "Tuesday":
	        System.out.println("4 days until the weekend");
	        break;
	      case "Wednesday":
	        System.out.println("3 days until the weekend");
	        break;
	      case "Thursday":
	        System.out.println("2 ays until the weekend");
	        break;
	      case "Friday":
	        System.out.println("1 day until the weekend");
	        break;
	      case "Saturday":
	        System.out.println("It's already the weekend!");
	        break;	       
		  
	

	
	
	    	}
		}
	}


