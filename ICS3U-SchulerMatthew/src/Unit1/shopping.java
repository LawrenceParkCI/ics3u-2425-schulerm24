package Unit1;

import java.util.Scanner;

public class shopping {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
	
		
		//food items
		
		double AsparagusQuant, AvocadoQuant, BeetQuant, BroccoliQuant, CabbageQuant, CarrotQuant, CauliflowerQuant, CeleryQuant, CornQuant, CucumberQuant, GarlicQuant, GreenBeanQuant, OnionQuant, PeaQuant, PepperQuant, PotatoQuant, TomatoQuant;

		
		//purchase amounts
		
		System.out.println("                   Welcome To VEGETABLE VARIETY                   ");
		System.out.println("                   ~~~~~~~~~~~~~~~~~~~~~~~~~~~~                   ");
		System.out.println();
		
		System.out.println(" Type how many ASPARAGUS you would like to buy, and press <Enter> ");
		AsparagusQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("  Type how many AVOCADO you would like to buy, and press <Enter>  ");
		AvocadoQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("   Type how many BEET you would like to buy, and press <Enter>    ");
		
		BeetQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("  Type how many BROCCOLI you would like to buy, and press <Enter> ");
		
		BroccoliQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("  Type how many CABBAGE you would like to buy, and press <Enter>  ");
		
		CabbageQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("   Type how many CARROT you would like to buy, and press <Enter>  ");
	
		CarrotQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("Type how many CAULIFLOWER you would like to buy, and press <Enter>");
		
		CauliflowerQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("   Type how many CELERY you would like to buy, and press <Enter>  ");
		
		CeleryQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("    Type how many CORN you would like to buy, and press <Enter>   ");
		
		CornQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("  Type how many CUCUMBER you would like to buy, and press <Enter> ");
		
		CucumberQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("  Type how many GARILIC you would like to buy, and press <Enter>  ");
		
		GarlicQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println(" Type how many GREEN BEAN you would like to buy, and press <Enter>");
	
		GreenBeanQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("   Type how many ONION you would like to buy, and press <Enter>   ");
		
		OnionQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("    Type how many PEA you would like to buy, and press <Enter>    ");
		
		PeaQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("   Type how many PEPPER you would like to buy, and press <Enter>  ");
		
		PepperQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("   Type how many POTATO you would like to buy, and press <Enter>  ");
		
		PotatoQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println("   Type how many TOMATO you would like to buy, and press <Enter>  ");
		
		TomatoQuant = sc.nextDouble();
		System.out.println();
		
		System.out.println();
	

	//food prices	

	double Asparagus = 1.50;
	double Avocado = 1.75;
	double Beet = 1.25;
	double Broccoli = 1.50;
	double Cabbage = 2.50;
	double Carrot = 1.75;
	double Cauliflower = 1.50;
	double Celery = 1.25;
	double Corn = 2.50;
	double Cucumber = 1.25;
	double Garlic = 1.25;
	double GreenBean = 1.25;
	double Onion = 2.25;
	double Pea = 1.25;
	double Pepper = 1.50;
	double Potato = 2.75;
	double Tomato = 1.75;

	
	//food cost total
	
	double AsparagusCost = Asparagus * AsparagusQuant;
	double AvocadoCost = Avocado * AvocadoQuant;
	double BeetCost = Beet * BeetQuant;
	double BroccoliCost = Broccoli * BroccoliQuant;
	double CabbageCost = Cabbage * CabbageQuant;
	double CarrotCost = Carrot * CarrotQuant;
	double CauliflowerCost = Cauliflower * CauliflowerQuant;
	double CeleryCost = Celery * CeleryQuant;
	double CornCost = Corn * CornQuant;
	double CucumberCost = Cucumber * CucumberQuant;
	double GarlicCost = Garlic * GarlicQuant;
	double GreenBeanCost = GreenBean * GreenBeanQuant;
	double OnionCost = Onion * OnionQuant;
	double PeaCost = Pea * PeaQuant;
	double PepperCost = Pepper * PepperQuant;
	double PotatoCost = Potato * PotatoQuant;
	double TomatoCost = Tomato * TomatoQuant;
	
	
	//total
	
	double SubTotal = AsparagusCost + AvocadoCost + BeetCost + BroccoliCost + CabbageCost + CarrotCost + CauliflowerCost + CeleryCost + CornCost + CucumberCost + GarlicCost + GreenBeanCost + OnionCost + PeaCost + PepperCost + PotatoCost + TomatoCost;
	double Tax = SubTotal * 0.13;
	double Total = (SubTotal * 0.13) + SubTotal;
	
	sc.close();
	
	
	System.out.println("                  -------------------------------\n                  -------------------------------\n                         VEGITABLE VARIETY \n                  -------------------------------\n                  -------------------------------\n \n                      Asparagus x"+AsparagusQuant+"\t$" + (Math.round(AsparagusCost * 100.0) / 100.0) + "\n                      Avocado x"+AvocadoQuant+"\t$" + (Math.round(AvocadoCost * 100.0) / 100.0) + "\n                      Beet x"+BeetQuant+"\t\t$" + (Math.round(BeetCost * 100.0) / 100.0) + "\n                      Broccoli x"+BroccoliQuant+"\t$" + (Math.round(BroccoliCost * 100.0) / 100.0) + "\n                      Cabbage x"+CabbageQuant+"\t$" + (Math.round(CabbageCost * 100.0) / 100.0) + "\n                      Carrot x"+CarrotQuant+"\t$" + (Math.round(CarrotCost * 100.0) / 100.0) + "\n                      Cauliflower x"+CauliflowerQuant+"\t$" + (Math.round(CauliflowerCost * 100.0) / 100.0) + "\n                      Celery x"+CeleryQuant+"\t$" + (Math.round(CeleryCost * 100.0) / 100.0) + "\n                      Corn x"+CornQuant+"\t\t$" + (Math.round(CornCost * 100.0) / 100.0) + "\n                      Cucumber x"+CucumberQuant+"\t$" + (Math.round(CucumberCost * 100.0) / 100.0) + "\n                      Garlic x"+GarlicQuant+"\t$" + (Math.round(GarlicCost * 100.0) / 100.0) + "\n                      Green Bean x"+GreenBeanQuant+"\t$" + (Math.round(GreenBeanCost * 100.0) / 100.0) + "\n                      Onion x"+OnionQuant+"\t$" + (Math.round(OnionCost * 100.0) / 100.0) + "\n                      Pea x"+PeaQuant+"\t\t$" + (Math.round(PeaCost * 100.0) / 100.0) + "\n                      Pepper x"+PepperQuant+"\t$" + (Math.round(PepperCost * 100.0) / 100.0) + "\n                      Potato x"+PotatoQuant+"\t$" + (Math.round(PotatoCost * 100.0) / 100.0) + "\n                      Tomato x"+TomatoQuant+"\t$" + (Math.round(TomatoCost * 100.0) / 100.0) + "\n\n                  -------------------------------- \n \n                      SubTotal          $" + (Math.round(SubTotal * 100.0) / 100.0) + "\n                      Tax               $" + (Math.round(Tax * 100.0) / 100.0) + "\n                      Total             $" + (Math.round(Total * 100.0) / 100.0) + "\n\n                  -------------------------------- \n                             THANK YOU \n                  --------------------------------");
	
	
	
	
	}

}
