package main.java.chat;

public class TestPOS {

	public static void main(String[] args) {
		POS p1=new POS();
		p1.init("Hello there Mr Rabbit.");
		
		
		String[] temp1=p1.getSent();
		String[] temp2=p1.getTags();
		System.out.println("sent:\n");
		for(String s: temp1){
			System.out.println(s);
		}
		
		System.out.println("tags:\n");
		for(String s: temp2){
			System.out.println(s);
		}
		
		System.out.println("tags:\n");
		for(String s: temp2){
			System.out.println(s);
		}
		
		
		
		
		
		
		
		
		
	}//main

}//class
