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
    	boolean done  = false; // Checks if user wants to end
    	while (!done) {
	    	System.out.println("Enter a math expression, or type quit to stop: ");
	    	String input = scanner.nextLine();
	    	if (input.equals("quit")) { // Ends code
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
        
    	//Variables
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
        
        while (!(input.charAt(i) == ' ')) { //Skips past whole nums to get to space operand space section
        	i++;
        }
       	firstOperand = input.substring(0, i); //First fraction will be before string
       	operator = input.substring(i + 1, i + 2); //Operator will be immediately after space
       	i += 3; // Gets index past space operand space
       	secondOperand = input.substring(i); 
        int[] parts1 = wholeNumDenom(firstOperand); //Method to convert inputs into a list of {whole, numerator, denominator}
        int[] parts2 = wholeNumDenom(secondOperand);
        whole1 = parts1[0];
        numerator1 = parts1[1];
        denom1 = parts1[2];
        
        whole2 = parts2[0];
        numerator2 = parts2[1];
        denom2 = parts2[2];
        
        int lcm = leastCommonMultiple(denom1, denom2); //Find common denominator for addition and subtraction
        int numeratorAnswer = 0;
        int denomAnswer = 0;
        
        if (operator.equals("*")) {
        	numeratorAnswer = (numerator1 + whole1 * denom1)* (numerator2 + whole2 * denom2); // Converts both to an improper fraction to multiply
        	denomAnswer = denom1 * denom2;
        }
        else if (operator.equals("/")) {
        	numeratorAnswer = (numerator1 + whole1 * denom1) * (denom2); // Same as multiplication, but flip second operand to multiply
        	denomAnswer = denom1 * (numerator2 + whole2 * denom2);
		}
        else if (operator.equals("+")) {
			numeratorAnswer = (((numerator1 + whole1 * denom1) * (lcm / denom1)) + ((numerator2 + whole2 * denom2) * (lcm / denom2))); // Converts to an improper fraction and then adds 
			denomAnswer = lcm;
		}
        else if (operator.equals("-")) {
        	numeratorAnswer = (((numerator1 + whole1 * denom1) * (lcm / denom1)) - ((numerator2 + whole2 * denom2) * (lcm / denom2))); //Converts to an improper fraction and substracts
        	denomAnswer = lcm;
		}
        
        return simplifying(numeratorAnswer, denomAnswer); // Simplifies improper fractions to mixed
    }

    // TODO: Fill in the space below with helper methods
    public static String simplifying(int numerator, int denom) {
    	int whole = 0;
    	int simpNum = 0;
    	if (numerator / denom == 0) {
    		return String.valueOf(numerator) + "/" + String.valueOf(denom); // If not a improper fraction, return as usual
    	}
    	else {
    		whole = numerator/denom; // Remove extra nums more than denom
    		simpNum = numerator - (whole * denom); // Gets simplified numerator
    		if (simpNum == 0) { // Prevents return of a 0/denom
				return String.valueOf(whole);
			}
    		return String.valueOf(whole) + "_" + String.valueOf(simpNum) + "/" + String.valueOf(denom);
    	}
    }
    public static int[] wholeNumDenom(String operand) {
    	int whole = 0;
    	int numerator = 0;
    	int denom = 0;
		String[] fraction = new String[operand.length()];
		for (int i = 0; i < fraction.length; i++) {
			fraction[i] = String.valueOf(operand.charAt(i)); //Sets array to what operand has
		}
		int prev = 0;
		if (hasItem(fraction)) { //Checks if has _ meaning there is a whole number
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
		else { // If just a fraction
			int i = 0;
			while (!fraction[i].equals("/")) {
				i++;
			}
			numerator = Integer.parseInt(operand.substring(prev, i));
			denom = Integer.parseInt(operand.substring(i+1));
			int[] returnList = {0, numerator, denom};
			return returnList;
		}
    	
	}
    public static boolean hasItem(String[] fraction) { //Just checks if _ is in the fraction
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
