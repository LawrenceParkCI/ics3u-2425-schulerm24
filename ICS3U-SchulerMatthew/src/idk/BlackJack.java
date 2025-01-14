package idk;

import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	Scanner sc = new Scanner (System.in);
	//give starting cards
	int PCard3 = 0;
	int PCard4 = 0;
	int PCard5 = 0;
	int PCard6 = 0;
	int PCard7 = 0;
	int PCard8 = 0;
	int PCard9 = 0;
	int PCard10 = 0;
	int CCard3 = 0;
	int CCard4 = 0;
	int CCard5 = 0;
	int CCard6 = 0;
	int CCard7 = 0;
	int CCard8 = 0;
	int CCard9 = 0;
	int CCard10 = 0;
	int CCard1 = (int) (Math. random() * 11 + 1);
	int CCard2 = (int) (Math. random() * 11 + 1);
	
	if(CCard1 == 11 && CCard2 == 11) {
		CCard2 = 1;	
	}
	int CompNum = CCard1 + CCard2;
	System.out.println("Dealer: " + CCard1 + " + ??");
	
	int PCard1 = (int) (Math. random() * 11 + 1);
	int PCard2 = (int) (Math. random() * 11 + 1);

	if(PCard1 == 11 && PCard2 == 11) {
		PCard2 = 1;
	}
	int PlayerNum = PCard1 + PCard2;
	System.out.println("You: " + PCard1 + " + " + PCard2 + " (" + PlayerNum + ")");
	
	if(PlayerNum == 21 && CompNum != 21) {
		System.out.println("You Have Black Jack! \nYou Win!");
	} else if(CompNum == 21 && PlayerNum != 21) {
		System.out.println("Dealer Has Black Jack! \nYou Lose!");
	} else if(PlayerNum == 21 && CompNum == 21) {
		System.out.println("You Both Have Black Jack! \nDraw!");
	} else if (PlayerNum != 21 && CompNum != 21) {
		System.out.println("Press <1> to Hit \nPress <2> to Stand");
		int PHoS = sc.nextInt();
		
		//if they stand
		if (PHoS == 2) {
			System.out.println("You: " + PlayerNum);
			if (CompNum == 17) {
				System.out.println("Dealer: " + CCard1 + " + " + CCard2 + " (" + CompNum + ")\nDealer Has 17!");
				if(PlayerNum > CompNum) {
					System.out.println("You Win!");
				}else if(PlayerNum == CompNum) {
					System.out.println("Draw!");
				}else if(PlayerNum < CompNum) {
					System.out.println("Dealer Wins!");
				}
				
			}else if(CompNum < 17) {
				CCard3 = CCard3 + (int) (Math. random() * 11 + 1);
				CompNum = CompNum + CCard3;
				if(CompNum < 17) {
					CCard4 = CCard4 + (int) (Math. random() * 11 + 1);
					CompNum = CompNum + CCard4;
				}else if(CompNum == 21) {
					System.out.println("Dealer: " + CCard1 + " + " + CCard2 + " + " + CCard3 + " + " + CCard4 + " (" + CompNum + ")\nDealer Has 21!");
					if(PlayerNum > CompNum) {
						System.out.println("You Win!");
					}else if(PlayerNum == CompNum) {
						System.out.println("Draw!");
					}else if(PlayerNum < CompNum) {
						System.out.println("Dealer Wins!");
					}
					
				}else if(CompNum > 21) {
					System.out.println("Dealer: " + CCard1 + " + " + CCard2 + CCard3 + " (" + CompNum + ")\nDealer Busts!\nYou Win!");
				}
				
				
				
				
			}else if(CompNum == 21) {
				System.out.println("Dealer: " + CCard1 + " + " + CCard2 + " + " + CCard3 + " (" + CompNum + ")\nDealer Has 21!");
				if(PlayerNum > CompNum) {
					System.out.println("You Win!");
				}else if(PlayerNum == CompNum) {
					System.out.println("Draw!");
				}else if(PlayerNum < CompNum) {
					System.out.println("Dealer Wins!");
				}
				
			}else if(CompNum > 21) {
				System.out.println("Dealer: " + CCard1 + " + " + CCard2 + CCard3 + " (" + CompNum + ")\nDealer Busts!\nYou Win!");
			}
		
			
			
			
			
		}else if(PHoS == 1) {
			PCard3 = PCard3 + (int) (Math. random() * 11 + 1);
			
		}

	
	
	
	
	
	
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	}

}
