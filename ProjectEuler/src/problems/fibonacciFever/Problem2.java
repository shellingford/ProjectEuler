package problems.fibonacciFever;

public class Problem2 {

	/**
	 * Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2,
	 * the first 10 terms will be: 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
	 *
	 * By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the
	 * even-valued terms.
	 */
	public static void main(String[] args) {
		//let's complicate this a little and make fibonacci formula only with even numbers:
		//Fn = 4Fn-3 + Fn-6 (3rd and 6th fibonacci numbers are even so we can just use them)
		int term3 = 2;
		int term6 = 8;
		int newTerm = 0;
		int sum = term6 + term3;

		while (newTerm < 4000000) {
			sum += newTerm;
			newTerm = 4*term6 + term3;
		    term3 = term6;
		    term6 = newTerm;
		}

		System.out.println("Sum: " + sum);
	}
}
