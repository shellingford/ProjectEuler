package problems.probs2xx;

import java.util.List;

import problems.common.PrimeGenerator;

public class Problem243 {

  private static double resilienceLimit = 15499d / 94744d;

  /**
   * A positive fraction whose numerator is less than its denominator is called a proper fraction.
   * For any denominator, d, there will be d−1 proper fractions; for example, with d = 12:
   *  1/12 , 2/12 , 3/12 , 4/12 , 5/12 , 6/12 , 7/12 , 8/12 , 9/12 , 10/12 , 11/12 .
   *
   * We shall call a fraction that cannot be cancelled down a resilient fraction.
   * Furthermore we shall define the resilience of a denominator, R(d), to be the ratio of its proper
   * fractions that are resilient; for example, R(12) = 4/11.
   * In fact, d = 12 is the smallest denominator having a resilience R(d) < 4/10.
   *
   * Find the smallest denominator d, having a resilience R(d) < 15499/94744 .
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    int d = findSmallestDenominator();

    System.out.println("Denominator: " + d);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static int findSmallestDenominator() {
    List<Integer> primes = PrimeGenerator.computePrimesList(1000);
    int phi = 1;
    int number = 1;
    //if number t = p1*...*pk, where p are prime numbers, then phi(t) = (p1-1)(p2-1)...(pk-1)
    for (Integer prime : primes) {
      phi *= (prime - 1);
      number *= prime;

      //if phi(n) / n is less than resilience then there is a chance that multiple of it has
      //the real resilience value that we are looking for
      if (Double.compare(resilience(phi, number + 1), resilienceLimit) < 0) {
        for (int k = 1; k < prime; k++) {
          int n = phi * k;
          int d = number * k;
          if (Double.compare(resilience(n, d), resilienceLimit) < 0) {
            return d;
          }
        }
      }
    }
    return -1;
  }

  private static double resilience(int count, int d) {
    return count / (double) (d - 1);
  }
}
