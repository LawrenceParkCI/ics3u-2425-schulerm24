package Unit1;

import java.util.Scanner;

public class Alive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub


		Scanner sc = new Scanner (System.in);
	
		int birthyear, birthmonth, birthday, todaysyear, todaysmonth, todaysday;
		
		System.out.println ("Enter your birth year and press <Enter>");
		birthyear = sc.nextInt(); 
		System.out.println ("Enter your birth month (0-12) and press <Enter>");
		birthmonth = sc.nextInt(); 
		System.out.println ("Enter your birth date and press <Enter>");
		birthday = sc.nextInt(); 
		System.out.println ("Enter today's year and press <Enter>");
		todaysyear = sc.nextInt(); 
		System.out.println ("Enter today's month (0-12) and press <Enter>");
		todaysmonth = sc.nextInt(); 
		System.out.println ("Enter today's date and press <Enter>");
		todaysday = sc.nextInt(); 
		
		int alive = (((todaysyear -1) * 365)) + ((30 * (todaysmonth -1)) + (todaysday)) - (((birthyear -1) * 365) + (30 * (birthmonth -1)) + (birthday));
		int sleep = 8 * alive;
	
		System.out.println();
		System.out.println ("You were born on " + birthday + "/" + birthmonth + "/" + birthyear);
		System.out.println ("Today is " + todaysday + "/" + todaysmonth + "/" + todaysyear);
		System.out.println ("You Have Been Alive For " + alive + " Days");
		System.out.println ("You Have Been Slept For " + sleep + " hours");	
		sc.close();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
