package russell;

import java.util.Scanner;

public class RecursionStar {
	/**
	 * 
	 * @param n 
	 * number of lines of stars
	 * @throws IllegalArgumentException
	 */
	public static void starString(int n)throws IllegalArgumentException{
		if(n==0){
			System.out.println("*");
		}
		else{
			starString(n-1);
			for(int i=0; i<Math.pow(2, n); i++){
				System.out.print("*");
			}
			System.out.println();
		}
		
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please input a number");
		int inputNum = scan.nextInt();
		starString(inputNum);
		scan.close();
	}
}
