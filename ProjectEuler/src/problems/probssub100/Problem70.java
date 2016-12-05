package problems.probssub100;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import problems.common.PrimeGenerator;

public class Problem70 {

  private static final int limit = 10000000;

  /**
   * Euler's Totient function, φ(n) [sometimes called the phi function], is used to determine the number of positive
   * numbers less than n which are relatively prime to n. For example, as 1, 2, 4, 5, 7, and 8, are all less than nine
   * and relatively prime to nine, φ(9)=6.
   * The number 1 is considered to be relatively prime to every positive number, so φ(1)=1.
   *
   * Interestingly, φ(87109)=79180, and it can be seen that 87109 is a permutation of 79180.
   *
   * Find the value of n, 1 < n < 10^7, for which φ(n) is a permutation of n and the ratio n/φ(n) produces a minimum.
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    int n = findMinN();

    System.out.println("N: " + n);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * To produce minimum n/φ(n), it is best when n is a product of only two large primes, because if n = p1 * p2 then
   * n/φ(n) = p1*p2 / (p1 - 1)*(p2 - 1). With smaller primes or larger number of primes, ratio gets larger value.
   */
  private static int findMinN() {
    //usually we would compute primes till sqrt(limit), but here we are multiplying two primes, so one can be
    //larger than sqrt(limit) while other is sufficiently lower than sqrt(limit), so that product < limit
    //3162 ~ sqrt(10^7), so I guessed for a bit larger number that might be enough for solution (3500 is not enough)
    List<Integer> primes = PrimeGenerator.computePrimesList(4000);
    int phi = 1;
    int number = 1;

    double minRatio = Integer.MAX_VALUE;
    int minN = 0;

    for (int i = 0; i < primes.size(); i++) {
      for (int j = i + 1; j < primes.size(); j++) {
        number = primes.get(i) * primes.get(j);
        if (number > limit) {
          break;
        }

        phi = (primes.get(i) - 1) * (primes.get(j) - 1);
        if (arePermutation(number, phi)) {
          double ratio = ratio(number, phi);
          if (Double.compare(ratio, minRatio) < 0) {
            minRatio = ratio;
            minN = number;
          }
        }
      }
    }
    return minN;
  }

  private static boolean arePermutation(int num1, int num2) {
    List<Integer> chars1 = Integer.toString(num1).chars().sorted().boxed().collect(Collectors.toList());
    List<Integer> chars2 = Integer.toString(num2).chars().sorted().boxed().collect(Collectors.toList());
    return chars1.size() == chars2.size() &&
        IntStream.range(0, chars1.size()).filter(i -> chars1.get(i) != chars2.get(i)).count() == 0;
  }

  private static double ratio(int n, int phi) {
    return n / (double) phi;
  }
}
