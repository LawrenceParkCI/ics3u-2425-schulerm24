package Unit2;

import java.util.Scanner;

public class IfChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner (System.in);
	
		int Score = 0;
		
	System.out.println("What is the largest planet in our solar system? \n 1) Jupiter \n 2) Saturn \n 3) Urnaus \n 4) Neptune");
	int Q1 = sc.nextInt();
	if (Q1 == 1) {
	System.out.println("That's correct!");	
		Score ++;
	}
	else {
		System.out.println("That's incorrect...");
	}
	System.out.println();
	System.out.println("Which is the only known planet to be inhabited solely by robots? \n 1) Mercury \n 2) Venus \n 3) Earth \n 4) Mars");
	int Q2 = sc.nextInt();
	if (Q2 == 4) {
		System.out.println("That's correct!");	
		Score ++;
	}
	else {
		System.out.println("That's incorrect...");
	}
	System.out.println();
	System.out.println("NASA just launched a mission to search for life on a moon of which planet? \n 1) Mars \n 2) Jupiter \n 3) Saturn \n 4) Uranus");
	int Q3 = sc.nextInt();
	if (Q3 == 2) {
		System.out.println("That's correct!");	
		Score ++;
	}
	else {
		System.out.println("That's incorrect...");
	}
	System.out.println();
	System.out.println("Your Score: " + Score + "/3");
	switch(Score) {
	case 0:
		System.out.println("You failed! Better luck next time!");
        break;
      case 1:
    	System.out.println("You failed! Better luck next time!");
        break;
      case 2:
        System.out.println("You passed! Good Job!");
        break;
      case 3:
        System.out.println("You got every question correct! Great job!");
        break;
		
	}
	
	
	
	
	
	
	}

}
