import java.util.Scanner;

public class FracCalc {

    /**
     * Prompts user for input, passes that input to produceAnswer, then outputs the result.
     * @param args - unused
     */
    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
        // Checkpoint 1: Create a Scanner, read one line of input, pass that input to produceAnswer, print the result.
        // Checkpoint 2: Accept user input multiple times.
    	Scanner scanner = new Scanner(System.in);
    	boolean done  = false;
    	while (!done) {
	    	System.out.println("Enter a math expression, or type quit to stop: ");
	    	String input = scanner.nextLine();
	    	if (input.equals("quit")) {
	    		done = true;
	    	}
	    	else {
	    		String a = produceAnswer(input);   //"4/5 + 1_3/4");
	    		System.out.println(a);
	    	}
    	}
    }
    
    /**
     * produceAnswer - This function takes a String 'input' and produces the result.
     * @param input - A fraction string that needs to be evaluated.  For your program, this will be the user input.
     *      Example: input ==> "1/2 + 3/4"
     * @return the result of the fraction after it has been calculated.
     *      Example: return ==> "1_1/4"
     */
    public static String produceAnswer(String input)
    { 
        // TODO: Implement this function to produce the solution to the input
        // Checkpoint 1: Return the second operand.  Example "4/5 * 1_2/4" returns "1_2/4".
        // Checkpoint 2: Return the second operand as a string representing each part.
        //               Example "4/5 * 1_2/4" returns "whole:1 numerator:2 denominator:4".
        // Checkpoint 3: Evaluate the formula and return the result as a fraction.
        //               Example "4/5 * 1_2/4" returns "6/5".
        //               Note: Answer does not need to be reduced, but it must be correct.
        // Final project: All answers must be reduced.
        //               Example "4/5 * 1_2/4" returns "1_1/5".
        String firstOperand = "";
        String secondOperand = "";
        String operator = "";
        int whole1 = 0;
        int numerator1 = 0;
        int denom1 = 0;
        int whole2 = 0;
        int numerator2 = 0;
        int denom2 = 0;
        int i = 0;
        while (!(input.charAt(i) == ' ')) {
        	i++;
        }
       	firstOperand = input.substring(0, i);
       	operator = input.substring(i, i + 2);
       	i += 3;
       	secondOperand = input.substring(i);
        int[] parts1 = wholeNumDenom(firstOperand);
        int[] parts2 = wholeNumDenom(secondOperand);
        whole1 = parts1[0];
        numerator1 = parts1[1];
        denom1 = parts1[2];
        
        whole2 = parts2[0];
        numerator2 = parts2[1];
        denom2 = parts2[2];
        return "whole:" + whole2 + " numerator:" + numerator2 + " denominator:" + denom2;
    }

    // TODO: Fill in the space below with helper methods
    public static int[] wholeNumDenom(String operand) {
    	int whole = 0;
    	int numerator = 0;
    	int denom = 0;
		String[] fraction = new String[operand.length()];
		for (int i = 0; i < fraction.length; i++) {
			fraction[i] = String.valueOf(operand.charAt(i)); //Sets array to what operand has
		}
		int prev = 0;
		if (hasItem(fraction)) { //Checks if has _ meaning there is a whole num
			int i = 0;
			while (!fraction[i].equals("_")) {
				i++;
			}
			whole = Integer.parseInt(operand.substring(0, i));
			prev = i + 1;
			while (!fraction[i].equals("/")) {
				i++;
			}
			numerator = Integer.parseInt(operand.substring(prev, i));
			denom = Integer.parseInt(operand.substring(i+1));
		
			int[] returnList = {whole, numerator, denom};
			return returnList;
    	}
		else {
			int i = 0;
			while (!fraction[i].equals("/")) {
				System.out.println(fraction[i]);
				i++;
			}
			numerator = Integer.parseInt(operand.substring(prev, i));
			denom = Integer.parseInt(operand.substring(i+1));
			int[] returnList = {0, numerator, denom};
			return returnList;
		}
    	
	}
    public static boolean hasItem(String[] fraction) {
		for (String s:fraction) {
			if (s.equals("_")) {
				return true;
			}
		}
    	return false;
		
	}

	/**
     * greatestCommonDivisor - Find the largest integer that evenly divides two integers.
     *      Use this helper method in the Final Checkpoint to reduce fractions.
     *      Note: There is a different (recursive) implementation in BJP Chapter 12.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The GCD.
     */
    public static int greatestCommonDivisor(int a, int b)
    {
        a = Math.abs(a);
        b = Math.abs(b);
        int max = Math.max(a, b);
        int min = Math.min(a, b);
        while (min != 0) {
            int tmp = min;
            min = max % min;
            max = tmp;
        }
        return max;
    }
    
    /**
     * leastCommonMultiple - Find the smallest integer that can be evenly divided by two integers.
     *      Use this helper method in Checkpoint 3 to evaluate expressions.
     * @param a - First integer.
     * @param b - Second integer.
     * @return The LCM.
     */
    public static int leastCommonMultiple(int a, int b)
    {
        int gcd = greatestCommonDivisor(a, b);
        return (a*b)/gcd;
    }
}
