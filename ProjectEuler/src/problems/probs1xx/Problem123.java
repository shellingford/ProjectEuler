package problems.probs1xx;

import java.util.List;

import problems.common.PrimeGenerator;

public class Problem123 {

  /**
   * Let pn be the nth prime: 2, 3, 5, 7, 11, ..., and let r be the remainder when (pn−1)^n + (pn+1)^n is divided by pn^2.
   * For example, when n = 3, p3 = 5, and 4^3 + 6^3 = 280 ≡ 5 mod 25.
   * The least value of n for which the remainder first exceeds 109 is 7037.
   *
   * Find the least value of n for which the remainder first exceeds 1010.
   */

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    //r must be over 10^10, so n must be at least for first prime over 10^5
    int startingN = PrimeGenerator.findNumberOfPrimes(100000) + 1;

    //thanks to prob120 we know we need to look for odd n, as for even r is always 2
    //r>10^5 => 2na > 10^5
    long rMax = 10000000000L;
    List<Integer> primes = PrimeGenerator.computePrimesList(3000000); //guessing limit

    //n cannot be even
    if (startingN % 2 == 0) {
      startingN++;
    }

    long r = 0;
    int n = startingN;
    while(r < rMax) {
      int prime = primes.get(n);
      //number must be over 10bil so it can't fit in an int
      r = ((long)(2 * prime)) * n;
      n += 2;
    }

    System.out.println("Min value of n: " + n + " with rmax=" + r);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }
}
