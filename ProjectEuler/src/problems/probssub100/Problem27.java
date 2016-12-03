package problems.probssub100;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import problems.common.PrimeGenerator;

public class Problem27 {

  /**
   * Euler discovered the remarkable quadratic formula:
   *  n^2 + n + 41
   *
   * It turns out that the formula will produce 40 primes for the consecutive integer values 0 ≤ n ≤ 39.
   * However, when n=40, 40^2 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly
   * when n=41, 41^2 + 41 + 41 is clearly divisible by 41.
   *
   * The incredible formula n^2 − 79n + 1601 was discovered, which produces 80 primes for the consecutive
   * values 0 ≤ n ≤ 79. The product of the coefficients, −79 and 1601, is −126479.
   *
   * Considering quadratics of the form:
   *  n^2 + an + b, where |a| < 1000 and |b| ≤ 1000
   *  where |n| is the modulus/absolute value of n
   *  e.g. |11|=11 and |−4|=4
   *
   *  Find the product of the coefficients, a and b, for the quadratic expression that produces the maximum
   *  number of primes for consecutive values of n, starting with n=0n=0.
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    List<Integer> primes = PrimeGenerator.computePrimesList(1000);
    Set<Integer> primesSet = new HashSet<>(primes);
    int maxPrimesCount = 0;
    int product = 0;
    for (Integer b : primes) {
      for (int a = -999; a < 1000; a++) {
        int n = 0;
        int primeCounter = 0;
        while (isPrime(n, a, b, primesSet)) {
          primeCounter++;
          n++;
        }
        if (primeCounter > maxPrimesCount) {
          product = a * b;
          maxPrimesCount = primeCounter;
        }
      }
    }

    System.out.println("Product of coefficients: " + product);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static boolean isPrime(int n, int a, Integer b, Set<Integer> primes) {
    int r = n*n + a*n + b;
    return primes.contains(r);
  }

}
