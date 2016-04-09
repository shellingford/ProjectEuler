package problems.fibonacciFever;

import java.util.HashMap;
import java.util.Map;

public class Problem55 {

	private static Map<Long, Long> reverseMap = new HashMap<>();

	/**
	 * How many Lychrel numbers are there below ten-thousand?
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int maxIter = 50;
		int lychrelCounter = 9990;

		for (int i = 10; i < 10000; i++) {
			long num = i;
			long sum = 0;

			for (int k = 0; k < maxIter; k++) {
				sum = num + reverseNum(num);
				if (isPalindrome(sum)) {
					lychrelCounter--;
					break;
				}
				num = sum;
			}
		}

		System.out.println("Number of Lychrel numbers below 10k: " + lychrelCounter);
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
	}

	private static boolean isPalindrome(long num) {
		return num == reverseNum(num);
	}

	private static long reverseNum(long num) {
		if (reverseMap.containsKey(num)) {
			return reverseMap.get(num);
		}

		long reverse = 0;
		int counter = powerOfTen(num) - 1;
		long tempNum = num;

		while(num > 0) {
			reverse += num % 10 * Math.pow(10, counter);
			num /= 10;
			counter--;
		}

		reverseMap.put(tempNum, reverse);
		reverseMap.put(reverse, tempNum);
		return reverse;
	}

	private static int powerOfTen(long num) {
		int i = 1;
		while(true) {
			long pow = (long) Math.pow(10, i);
			if (num < pow)  {
				return i;
			}
			i++;
		}
	}
}
