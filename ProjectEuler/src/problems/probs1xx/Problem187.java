package problems.probs1xx;

import java.util.List;

import problems.PrimeGenerator;

public class Problem187 {

  /**
   * A composite is a number containing at least two prime factors.
   * For example, 15 = 3 × 5; 9 = 3 × 3; 12 = 2 × 2 × 3.
   *
   * There are ten composites below thirty containing precisely two, not necessarily distinct,
   * prime factors: 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.
   *
   * How many composite integers, n < 108, have precisely two, not necessarily distinct, prime factors?
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    int limit = 100000000;
    List<Integer> primes = PrimeGenerator.computePrimesList(limit / 2);

    int count = 0;
    for (int i = 0; i < primes.size(); i++) {
      Integer prime = primes.get(i);
      int max = limit / prime;
      int toIndex = findFirstGreater(primes, max + 1);
      if (toIndex > i) {
        List<Integer> sublist = primes.subList(i, toIndex);
        count += findFirstProductGreater(sublist, prime, limit);
      }
    }

    System.out.println("count = "  + count);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * Binary search algorithm to find from the list of primes first prime which
   * product with <code>prime</code> is greater than <code>limit</code>.
   *
   * @param primes list of primes
   * @param prime one part of the product that shouldn't be greater than limit
   * @return index of first prime from the list which product with <code>prime</code> is greater than limit
   */
  private static int findFirstProductGreater(List<Integer> primes, int prime, int limit) {
    int start = 0;
    int end = primes.size();

    while (start != end) {
      int mid = (start + end) / 2;
      if (primes.get(mid) * prime < limit) {
        start = mid + 1;
      }
      else {
        end = mid;
      }
    }
    return start;
  }

  /**
   * Binary search algorithm to find from the list of values first element that is
   * greater than the target.
   *
   * @param values list of int values
   * @param target target value
   * @return index of first element that is greater than the target
   */
  private static int findFirstGreater(List<Integer> values, int target) {
    int start = 0;
    int end = values.size();

    while (start != end) {
      int mid = (start + end) / 2;
      if (values.get(mid) < target) {
        start = mid + 1;
      }
      else {
        end = mid;
      }
    }
    return start;
  }

}
