package problems.probssub100;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Problem64 {

  private static final int limit = 10000;

  /**
   * How many continued fractions for N ≤ 10000 have an odd period?
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    List<Integer> periods = calcPeriodSizes();
    long n = periods.stream().filter(p -> p % 2 != 0).count();

    System.out.println("Number of continued fractions under 10k with odd period:  " + n);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static List<Integer> calcPeriodSizes() {
    return IntStream.range(2, limit + 1).filter(i -> !isSquare(i)).map(num -> calcPeriodSize(num)).boxed()
        .collect(Collectors.toList());
  }

  /**
   * Checks if number is square of a smaller number.
   *
   * @param num number that we are checking if it is a square of some other number
   * @return true if num is square, false otherwise
   */
  private static boolean isSquare(int num) {
    double s1 = Math.sqrt(num);
    double s2 = Math.floor(s1);
    return Double.compare(s1, s2) == 0;
  }

  /**
   * We can use iterative algorithm from wiki: https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Algorithm
   *
   * @param n number for which sqrt we are calculating period
   * @return period size
   */
  private static int calcPeriodSize(int n) {
    int a0 = (int) Math.floor(Math.sqrt(n));
    int counter = 0;
    int d = 1;
    int m = 0;
    int a = a0;

    while(a != 2 * a0) {
      counter++;
      m = d * a - m;
      d = (n - m * m) / d;
      a = (a0 + m) / d;
    }

    return counter;
  }
}
