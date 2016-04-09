package problems.fibonacciFever;

import java.util.ArrayList;
import java.util.List;

public class Problem34 {

	/**
	 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
	 *
	 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
	 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		List<Integer> factors = calculateFactorials();
		int sum = 0;

		//upper limit is 2540161  9!*7, because 9!*8 is 7digit number
		for(int i = 10; i < 2540161; i++) {
			int num = i;
			int numSum = 0;
			while(num > 0) {
				int digit = num % 10;
				num /= 10;
				numSum += factors.get(digit);
			}

			if (numSum == i) {
				sum += i;
			}
		}

		System.out.println("Sum of all numbers: " + sum);
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
	}

	private static List<Integer> calculateFactorials() {
		List<Integer> factors = new ArrayList<>();
		factors.add(1);

		for (int i = 1; i < 10; i++) {
			int fact = 1;
			for (int j = 1; j <= i; j++) {
				fact *= j;
			}
			factors.add(fact);
		}

		return factors;
	}

}
