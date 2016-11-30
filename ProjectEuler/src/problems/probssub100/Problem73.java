package problems.probssub100;


public class Problem73 {

  private final static int limit = 12000;

  /**
   * Consider the fraction, n/d, where n and d are positive integers. If n < d and HCF(n, d) = 1, it is
   * called a reduced proper fraction.
   *
   * If we list the set of reduced proper fractions for d ≤ 8 in ascending order of size, we get:
   *    1/8, 1/7, 1/6, 1/5, 1/4, 2/7, 1/3, 3/8, 2/5, 3/7, 1/2, 4/7, 3/5, 5/8, 2/3, 5/7, 3/4, 4/5, 5/6, 6/7, 7/8
   *
   * It can be seen that there are 3 fractions between 1/3 and 1/2.
   *
   * How many fractions lie between 1/3 and 1/2 in the sorted set of reduced proper fractions for d ≤ 12,000?
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    int fractionsCount = fareySequenceCount(1, 3, 1, 2);

    System.out.println("Proper franction count between 1/3 and 1/2: " + fractionsCount);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * Calculates Farey sequence and counts it until it satisfies that d in n / d is max 12k,
   * and that fraction is between 1/3 and 1/2.
   *
   * Farey sequence property: if a/b < c/d then a+c/b+d lies between them, a/b < a+c/b+d < c/d.
   *
   * @param a
   * @param b
   * @param c
   * @param d
   * @return count of the fractions within the limit
   */
  private static int fareySequenceCount(int a, int b, int c, int d) {
    if (b + d > limit) {
      return 0;
    }
    return 1 + fareySequenceCount(a, b, a + c, b + d) + fareySequenceCount(a + c, b + d, c, d);
  }

}
