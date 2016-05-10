package problems.probs4xx;

import java.math.BigInteger;


public class Problem401 {
	private static long mod = (long) Math.pow(10, 9);

	/**
	 * The divisors of 6 are 1,2,3 and 6.
	 * The sum of the squares of these numbers is 1+4+9+36=50.
	 *
	 * Let sigma2(n) represent the sum of the squares of the divisors of n. Thus sigma2(6)=50.
	 *
	 * Let SIGMA2 represent the summatory function of sigma2, that is SIGMA2(n)=∑sigma2(i) for i=1 to n.
	 * The first 6 values of SIGMA2 are: 1,6,16,37,63 and 113.
	 *
	 * Find SIGMA2(10^15) modulo 10^9.
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long maxNumber = (long) Math.pow(10, 15);

		long sum = 0;
		long counter = 1;
		while (counter <= maxNumber) {
			long divisor = (long) Math.floor(maxNumber / counter);
			//number of times we need to repeat calculation
			long noTimes = (long) (Math.floor(maxNumber / divisor) - counter + 1);
			sum += (noTimes * sumSquares(divisor)) % mod;
			counter += noTimes;
		}

		System.out.println("SIGMA2(" + maxNumber + ")=" + sum % mod);
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
	}

	private static long sumSquares(long n) {
		//first calculate sum of squares: 1^2 + 2^2 + ... + n^2 = (n)(n + 1)(2n + 1) / 6, then mod
		BigInteger nBI = BigInteger.valueOf(n);
		BigInteger sum = nBI.multiply(nBI.add(BigInteger.ONE));
		sum = sum.multiply(nBI.shiftLeft(1).add(BigInteger.ONE));
		sum = sum.divide(BigInteger.valueOf(6)).mod(BigInteger.valueOf(mod));
		return sum.longValue();
	}
}
