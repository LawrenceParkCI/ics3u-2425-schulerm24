import java.util.Scanner;

public class InputCharacters {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	
	Scanner sc = new Scanner (System.in);
	
	
	char ch1, ch2, ch3;
	System.out.println("Type in any three characters on the keyboard");
	System.out.println("Press <Enter> after each.");
	ch1 = sc.nextLine().charAt(0);
	ch2 = sc.nextLine().charAt(0);
	ch3 = sc.nextLine().charAt(0);
	System.out.println();
	System.out.print("Together these 3 characters spell: " + (ch1 + ch2 + ch3));
	
	
	
	
	
	
	
	
	
	}

}
