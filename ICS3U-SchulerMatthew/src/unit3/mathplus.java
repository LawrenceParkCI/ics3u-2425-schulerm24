package unit3;
import java.util.Scanner;

public class mathplus {
Scanner sc = new Scanner(System.in);
	
	public static void Distance() {
		
		System.out.println("Enter the X-coordinate of your first point:");
		double x1  = sc.nextInt();
		System.out.println("Enter the X-coordinate of your second point:");
		double x2  = sc.nextInt();
		System.out.println("Enter the Y-coordinate of your first point:");
		double y1  = sc.nextInt();
		System.out.println("Enter the Y-coordinate of your second point:");
		double y2  = sc.nextInt();
		
		double x2x1 = x2 - x1;
		double y2y1 = y2 - y1;
		double d1 = Math.sqrt(Math.pow(x2x1, 2)) + Math.sqrt(Math.pow(y2y1, 2)); 
		
		 System.out.println("Distance from ("  + x1 + " ," + y1 + ") to (" + x2 + " ," + y2 + ") = " + d1);
}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your math method:\n-Distance");
		
		String mathmethod = sc.nextLine().toLowerCase();
		if (mathmethod.equals("distance")) {
			Distance();
		}
		
	}
	}








