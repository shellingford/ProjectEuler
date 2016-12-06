package problems.probs1xx;


public class Problem100 {

  private static final long limit = (long) Math.pow(10, 12);

  /**
   * If a box contains twenty-one coloured discs, composed of fifteen blue discs and six red discs, and two
   * discs were taken at random, it can be seen that the probability of taking two blue discs,
   * P(BB) = (15/21)×(14/20) = 1/2.
   *
   * The next such arrangement, for which there is exactly 50% chance of taking two blue discs at random, is
   * a box containing eighty-five blue discs and thirty-five red discs.
   *
   * By finding the first arrangement to contain over 10^12 = 1,000,000,000,000 discs in total, determine the
   * number of blue discs that the box would contain.
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    long numBlues = findNumberOfBlues();

    System.out.println("Number of blue discs: " + numBlues);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * To find number of blues we must solve a simple quadratic equation: 2B^2 - 2B - 10^24 + 10^12, but
   * the problem is that it doesn't have integer solutions so we can try to solve it as diophantine equation
   * for which we get two equations:
   *  b(k+1) = 3b(k) + 2n(k) - 2
   *  n(k+1) = 4b(k) + 3n(k) - 3
   *
   * So we must just find first b when n > 10^12
   */
  private static long findNumberOfBlues() {
    long b = 85;
    long n = 120;

    while(n < limit) {
      long bk = 3 * b + 2 * n - 2;
      long nk = 4 * b + 3 * n - 3;

      n = nk;
      b = bk;
    }

    return b;
  }

}
