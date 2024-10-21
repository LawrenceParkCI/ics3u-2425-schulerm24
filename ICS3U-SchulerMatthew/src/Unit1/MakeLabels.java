package Unit1;

import java.util.Scanner;

public class MakeLabels {


public static void main(String[] args) {

		Scanner sc = new Scanner (System.in);

		
		String name;
		String ccinfo;

		
		System.out.println("Type in your full government name and press <Enter>");
		name = sc.nextLine ();
		System.out.println("Type in your credit card information and press <Enter>");
		ccinfo = sc.nextLine ();
	
		
		System.out.println("");
		System.out.println("*****************************");
		System.out.println(name);
		System.out.println(ccinfo);
		System.out.println("*****************************");
		sc.close();

	}
}
