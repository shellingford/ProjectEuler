package problems.fibonacciFever;

import java.util.HashSet;
import java.util.Set;

public class Problem21 {

	/**
	 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
	 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called
	 * amicable numbers.
	 *
	 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
	 * The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
	 *
	 * Evaluate the sum of all the amicable numbers under 10000.
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Set<Integer> amicableNums = new HashSet<>();
		int totalSum = 0;

		for (int num = 4; num < 10000; num++) {
			if (!amicableNums.contains(num)) {
				int sum = calculateProperDivisorsSum(num);
				int sum2 = calculateProperDivisorsSum(sum);

				if (sum2 == num && sum != sum2) {
					totalSum += sum;
					amicableNums.add(sum);
					totalSum += num;
					amicableNums.add(num);
				}
 			}
		}

		System.out.println("Sum of amicable numbers under 10k is: " + totalSum);
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
	}

	private static int calculateProperDivisorsSum(int num) {
		int sum = 1;
		int maxDiv = (int) (Math.round(Math.sqrt(num)) + 1);

		for (int i = 2; i < maxDiv; i++) {
			if (num % i == 0) {
				sum += i + num/i;
			}
		}

		return sum;
	}

}
