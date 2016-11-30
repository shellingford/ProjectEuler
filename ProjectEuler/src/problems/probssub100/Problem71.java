package problems.probssub100;


public class Problem71 {

  private final static int limit = 1000000;

  /**
   * Consider the fraction, n/d, where n and d are positive integers. If n < d and HCF(n, d) = 1, it is
   * called a reduced proper fraction.
   *
   * If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:
   *    1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
   *
   * It can be seen that 2/5 is the fraction immediately to the left of 3/7.
   *
   * By listing the set of reduced proper fractions for d ≤ 1,000,000 in ascending order of size, find the
   * numerator of the fraction immediately to the left of 3/7.
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    findClosestFraction(2, 5, 3, 7);

    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * Calculates closest reduced proper fraction to 3/7 so that denominator is max 1,000,000. We use Farey sequence
   * property to calculate the closest proper fraction.
   *
   * Farey sequence property: if a/b < c/d then a+c/b+d lies between them, a/b < a+c/b+d < c/d.
   *
   * @param a numerator of lower bound
   * @param b denominator of lower bound
   * @param c numerator of upper bound
   * @param d denominator of upper bound
   * @return count of the fractions within the limit
   */
  private static void findClosestFraction(int a, int b, int c, int d) {
    while (b + d <= limit) {
      a = a + c;
      b = b + d;
    }
    System.out.println("Fraction immediately to the left of 3/7 is: " + a + "/" + b);
  }

}
