package idk;

import java.util.Scanner;

public class Random {

	public static void main(String[] args) {
	
	Scanner sc = new Scanner (System.in);
	int PScore = 0; 
	int CScore = 0;	
	//choose random number
	System.out.println("ROCK PAPER SCISSORS");
	Thread.sleep(500);
	
	while(true) {
	
		int CompNum = (int) (Math. random() * 3 + 1);
	
	//1 = rock
	//2 = paper
	//3 = scissors
	
	
	//computer rock
	if(CompNum == 1) {
	String CompChoice = "Rock";
	
	System.out.println("Enter <1> for 'Rock' \nEnter <2> for 'Paper' \nEnter <3> for 'Scissors'");
	int PlayerNum = sc.nextInt();

	if(PlayerNum == 1) {
		String PlayerChoice = "Rock";
		System.out.println("You played Rock");
		System.out.println("Computer played Rock");
		System.out.println("You Tied!");
		System.out.println("Score (You - Computer): " + PScore + " - " + CScore);
	} else if(PlayerNum == 2) {
	    String PlayerChoice = "Paper";
	    System.out.println("You played Paper");
		System.out.println("Computer played Rock");
		System.out.println("You Win!");	
	    PScore ++;
	    System.out.println("Score (You - Computer): " + PScore + " - " + CScore);
	    }else if(PlayerNum == 3) {
		    String PlayerChoice = "Scissors";	
		    System.out.println("You played Scissors");
			System.out.println("Computer played Rock");
			System.out.println("You Lose!");	
			CScore ++;
			System.out.println("Score (You - Computer): " + PScore + " - " + CScore);
		    }else {
		    	System.out.println("That is not an option");
		    }
	     
	    
	   
	    
	} 
     
	//computer Paper
	if(CompNum == 2) {
    String CompChoice = "Paper";
   
    System.out.println("Enter <1> for 'Rock' \nEnter <2> for 'Paper' \nEnter <3> for 'Scissors'");
	int PlayerNum = sc.nextInt();

	if(PlayerNum == 1) {
		String PlayerChoice = "Rock";
		System.out.println("You played Rock");
		System.out.println("Computer played Paper");
		System.out.println("You Lose!");
		CScore ++;
		System.out.println("Score (You - Computer): " + PScore + " - " + CScore);
	} else if(PlayerNum == 2) {
	    String PlayerChoice = "Paper";
	    System.out.println("You played Paper");
		System.out.println("Computer played Paper");
		System.out.println("You Tied!");	
		System.out.println("Score (You - Computer): " + PScore + " - " + CScore);
	    }else if(PlayerNum == 3) {
	    String PlayerChoice = "Scissors";	
	    System.out.println("You played Scissors");
		System.out.println("Computer played Paper");
		System.out.println("You Win!");	
		PScore ++;
		System.out.println("Score (You - Computer): " + PScore + " - " + CScore);
	    }else
	    	System.out.println("That is not an option");
	
	}
   
	//computer Scissors
    if(CompNum == 3) {
    String CompChoice = "Scissors";	
	
    System.out.println("Enter <1> for 'Rock' \nEnter <2> for 'Paper' \nEnter <3> for 'Scissors'");
	int PlayerNum = sc.nextInt();

	if(PlayerNum == 1) {
		String PlayerChoice = "Rock";
		System.out.println("You played Rock");
		System.out.println("Computer played Scissors");
		System.out.println("You Win!");	
		PScore ++;
		System.out.println("Score (You - Computer): " + PScore + " - " + CScore);
	} else if(PlayerNum == 2) {
	    String PlayerChoice = "Paper";
	    System.out.println("You played Paper");
		System.out.println("Computer played Scissors");
		System.out.println("You Lose!");	
		CScore ++;
		System.out.println("Score (You - Computer): " + PScore + " - " + CScore);
	    }else if(PlayerNum == 3) {
	    String PlayerChoice = "Scissors";	
	    System.out.println("You played Scissors");
		System.out.println("Computer played Scissors");
		System.out.println("You Tied!");	
		System.out.println("Score (You - Computer): " + PScore + " - " + CScore);
	    }else
	    	System.out.println("That is not an option");
 
    }
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		if(PScore == 5) {
			System.out.println("YOU WIN!");
			System.out.println("Final Score: " + PScore + " - " + CScore);
			break;
		}
		if(CScore == 5) {
			System.out.println("COMPUTER WINS!");
			System.out.println("Final Score: " + PScore + " - " + CScore);
			break;
		}
		
		
		}
		
	    }
		
	

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


