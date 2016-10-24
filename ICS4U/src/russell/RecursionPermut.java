package russell;

import java.util.Scanner;

public class RecursionPermut {

	/**
	 * 
	 * @param x
	 * first factorial number
	 * @param y
	 * second factorial number
	 * @return
	 */
	public static int permut (int x, int y){
		return factorial(x)/factorial(y);
	}
	/**
	 * 
	 * @param a
	 * @return
	 */
	public static int factorial (int a){
		if(a == 0)
			return 1;
		else
		 return a * factorial(a - 1);
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		int n = scan.nextInt(), r = scan.nextInt();
		System.out.println(permut(n,r));
		scan.close();
	}


}
