package problems.probssub100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import problems.PrimeGenerator;

public class Problem50 {

  private static final int limit = 1000000;

  /**
   * The prime 41, can be written as the sum of six consecutive primes:
   * 		41 = 2 + 3 + 5 + 7 + 11 + 13
   * This is the longest sum of consecutive primes that adds to a prime below one-hundred.
   *
   * The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms,
   * and is equal to 953.
   *
   * Which prime, below one-million, can be written as the sum of the most consecutive primes?
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    List<Integer> primes = PrimeGenerator.computePrimesList(limit);
    longestConsecutivePrimesSum(primes);

    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static void longestConsecutivePrimesSum(List<Integer> primes) {
    int maxNumPrime = 0;
    int prime = 0;
    Set<Integer> primesSet = new HashSet<>(primes);

    List<Integer> sums = new ArrayList<>();
    sums.add(2);
    primes.stream().forEach(p -> sums.add(sums.get(sums.size() - 1) + p));

    for (int i = 0; i < primes.size(); i++) {
      for (int j = i - (maxNumPrime + 1); j >= 0; j--) {
        int sum = sums.get(i) - sums.get(j);
        if (sum > limit) {
          break;
        }

        if (primesSet.contains(sum)) {
          maxNumPrime = i - j;
          prime = sum;
        }
      }
    }

    System.out.println(String.format("Prime: %s, with %s consecutive primes", prime, maxNumPrime));
  }

}
