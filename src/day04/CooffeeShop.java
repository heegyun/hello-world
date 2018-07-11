package day04;

public class CooffeeShop {
	
	public static void main(String[] args) {
		CoffeeMachine cm=new CoffeeMachine();
		//cm.coffee=10;
		//cm.sugar=20;
		//cm.cream=30;
		cm.setCoffee(10);
                cm.setSugar(20);
		cm.setCream(30);
		
		System.out.println("커피: "+cm.getCoffee());
		System.out.println("설탕: "+cm.getSugar());
		System.out.println("크림: "+cm.getCream());

	}
}/////////////////////////////
	
