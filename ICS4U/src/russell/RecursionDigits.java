package russell;

import java.util.Scanner;

public class RecursionDigits {
	public static int NUM_DIGITS = 0;// counter to count the number of digits in
	// the input
	/**

	 * main method

	 *

	 * @param args

	 */

	public static void main(String[] args) {



		Scanner scan = new Scanner(System.in);// Scanner

		int num = scan.nextInt();



		System.out.println(NumDigit(num));// Prints what the method returns

	}

	/**

	 * checks how many digits are in the number that is inputed

	 *

	 * @param x

	 * @return NUM_DIGITS

	 */

	public static int NumDigit(int x) {

		if (x == 0) {

			return 1;

		} else {

			NUM_DIGITS++; // Counts the number of digits in the number

			NumDigit(x / 10);

		}

		return NUM_DIGITS; // Return the to main method

	}
}
