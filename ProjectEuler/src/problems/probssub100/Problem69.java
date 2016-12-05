package problems.probssub100;

import java.util.List;

import problems.common.PrimeGenerator;

public class Problem69 {

  private static final int limit = 1000000;

  /**
   * Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of numbers
   * less than n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine and
   * relatively prime to nine, φ(9)=6.
   *
   * It can be seen that n=6 produces a maximum n/φ(n) for n ≤ 10.
   *
   * Find the value of n ≤ 1,000,000 for which n/φ(n) is a maximum.
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    int n = findMaxN();

    System.out.println("N: " + n);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static int findMaxN() {
    List<Integer> primes = PrimeGenerator.computePrimesList((int)Math.floor(Math.sqrt(limit)));
    int phi = 1;
    int number = 1;

    double maxRatio = 0d;
    int maxN = 0;
    //if number t = p1*...*pk, where p are prime numbers, then phi(t) = (p1-1)(p2-1)...(pk-1)
    for (Integer prime : primes) {
      number *= prime;
      if (number > limit) {
        break;
      }

      phi *= (prime - 1);
      double ratio = ratio(number, phi);
      if (Double.compare(ratio, maxRatio) > 0) {
        maxRatio = ratio;
        maxN = number;
      }
    }
    return maxN;
  }

  private static double ratio(int n, int phi) {
    return n / (double) phi;
  }
}
