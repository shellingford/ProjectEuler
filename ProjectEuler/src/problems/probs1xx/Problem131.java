package problems.probs1xx;

import java.util.Set;

import problems.common.PrimeGenerator;

public class Problem131 {

  /**
   * There are some prime values, p, for which there exists a positive integer, n, such that the expression n^3 + n^2p
   * is a perfect cube.
   * For example, when p = 19, 8^3 + 8^2×19 = 12^3.
   *
   * What is perhaps most surprising is that for each prime with this property the value of n is unique, and there
   * are only four such primes below one-hundred.
   *
   * How many primes below one million have this remarkable property?
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    int limit = 1000000;

    //n^3 + n^2p=k^3 => k=n*cbrt(n+p)/cbrt(n), which means n and n+p needs to be cubes for k to be an integer
    //so if n=r^3 and n+p=s^3 => p=s^3-r^3 => p = (s - r)(s^2 + s*r + r^2), s-r is factor so it must be 1, which
    //gives us final prime form: p = (r+1)^3 - r^3
    Set<Integer> primes = PrimeGenerator.computePrimesSet(limit);

    int count = 0;
    int r = 1;
    int possiblePrime = calcPrime(r);
    while(possiblePrime < limit) {
      if(primes.contains(possiblePrime)) {
        count++;
      }
      r++;
      possiblePrime = calcPrime(r);
    }

    System.out.println("Number of primes below 1M: " + count);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static int calcPrime(int r) {
    return (int) Math.pow(r + 1, 3) - r*r*r;
  }
}
