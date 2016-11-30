package problems.probssub100;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;



public class Problem72 {

  private final static int limit = 1000000;

  /**
   * Consider the fraction, n/d, where n and d are positive integers. If n < d and HCF(n, d) = 1, it is
   * called a reduced proper fraction.
   *
   * If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:
   *    1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
   *
   * It can be seen that there are 21 elements in this set.
   *
   * How many elements would be contained in the set of reduced proper fractions for d ≤ 1,000,000?
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    long fractionsCount = fareySequenceCount();

    System.out.println("Proper franction count: " + fractionsCount);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * Count of reduced proper fractions is sum of all Euler’s totient functions for range [2, 1,000,000].
   *
   * @return count of the fractions within the limit
   */
  private static long fareySequenceCount() {
    long count = 0;
    List<Integer> phi = IntStream.range(0, limit + 1).boxed().collect(Collectors.toList());
    for(int i = 2; i <= limit; i++){
      //if it was not modified so far it means it is a prime
      if (phi.get(i) == i) {
        for (int j = i; j <= limit; j += i) {
          phi.set(j, phi.get(j) / i * (i - 1));
        }
      }
      count += phi.get(i);
    }
    return count;
  }

}
