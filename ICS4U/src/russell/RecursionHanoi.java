package russell;

import java.util.Scanner;

public class RecursionHanoi {

	public static void solve(int n, String a, String b, String c){
		if(n!=0){
			solve(n-1, a, c, b);
			System.out.println("Move " + a + " to " + c);
			solve(n-1, b, a, c);
		}
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("How many discs are there on the towers?");
		try{
			int input = Integer.parseInt(scan.nextLine());
			int discs = input;
			while(discs<0){
				System.out.println("Please enter an integer above 0");
				input = Integer.parseInt(scan.nextLine());
				discs = input;
			}
			System.out.println("A " +  discs + " disc tower can be solved in " + (int)((Math.pow(2, discs))-1) + " moves.");
			solve(discs, "Source", "Auxilliary", "Destination");
			System.out.println();
			System.out.println("Do you want to try another tower? (y/n)");
			if(scan.nextLine().equalsIgnoreCase("y"))
				main(args);
			scan.close();
		}catch(IllegalArgumentException nfe){
			System.out.println("Please enter an integer above 0");
			main(args);
		}
	}
}
