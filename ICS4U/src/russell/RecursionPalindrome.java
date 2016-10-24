package russell;

import java.util.Scanner;

public class RecursionPalindrome {
	/**
	 * 
	 * @param x
	 * string to reverse
	 * @return
	 * reversed string
	 */
	public static String isReverse (String x)// reverses the inputed word, so that it can be compared to itself
	{
		char backwards = x.charAt(x.length()-1);
		if(x.length() == 1) 
			return Character.toString(backwards);   
		return backwards + isReverse(x.substring(0,x.length()-1));
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		boolean redo = true;

		while(redo == true)// Continues to repeat as long as the user has a another possible palindrome.
		{
			System.out.println("Please input potential palindrome.");

			String reverse = scan.nextLine();// Takes in possible palindrome
			String another;
			String wordBackwards;
			wordBackwards = isReverse(reverse);

			if (wordBackwards.equalsIgnoreCase(reverse))// compares the original word and it reversed. Outputs whether it is a possible palindrome
			{
				System.out.println("This word IS a palindrome.\nTest another palindrome. y/n?");//asks user if they want to try another possible palindrome
				another = scan.nextLine();
				if (another.equalsIgnoreCase("y"))
				{
					redo = true;
				}
				else if (another.equalsIgnoreCase("n"))
					System.exit(0);
			}
			else
			{
				System.out.println("This IS NOT a palindrome.\nTest another palindrome. y/n?");//asks user if they want to try another possible palindrome
				another = scan.nextLine();
				if (another.equalsIgnoreCase("y"))
				{
					redo = true;
				}
				else if (another.equalsIgnoreCase("n"))
					System.exit(0);
			}
		}
		scan.close();
	}
}
