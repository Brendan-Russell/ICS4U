package russell;

public class RecursionReverse {
	public static int NUM = 0;

	public static boolean isReverse(String one, String two){		
		if(NUM>one.length())
			return true;
		if(one.charAt(NUM)==two.charAt(NUM)){
			NUM++;
			isReverse(one,two);
		}
		if(NUM>one.length())
			return true;
		return false;

	}

	public static void main(String[] args) {
		if(isReverse("one","one"))
			System.out.println(true);
		else{
			System.out.println(false);
		}

	}

}
