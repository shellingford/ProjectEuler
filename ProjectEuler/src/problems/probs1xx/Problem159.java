package problems.probs1xx;

import java.util.stream.IntStream;

public class Problem159 {

  /**
   * A composite number can be factored many different ways. For instance, not including multiplication by one,
   * 24 can be factored in 7 distinct ways:
   *    24 = 2x2x2x3
   *    24 = 2x3x4
   *    24 = 2x2x6
   *    24 = 4x6
   *    24 = 3x8
   *    24 = 2x12
   *    24 = 24
   *
   * Recall that the digital root of a number, in base 10, is found by adding together the digits of that number,
   * and repeating that process until a number is arrived at that is less than 10. Thus the digital root of 467 is 8.
   *
   * We shall call a Digital Root Sum (DRS) the sum of the digital roots of the individual factors of our number.
   * The chart below demonstrates all of the DRS values for 24.
   *          Factorisation   DRS
   *            2x2x2x3        9
   *            2x3x4          9
   *            2x2x6          10
   *            4x6            10
   *            3x8            11
   *            2x12           5
   *            24             6
   *
   * The maximum Digital Root Sum of 24 is 11.
   *
   * The function mdrs(n) gives the maximum Digital Root Sum of n. So mdrs(24)=11.
   *
   * Find ∑mdrs(n) for 1 < n < 1,000,000.
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    int sum = mdrsSum(2, 1000000);

    System.out.println("∑mdrs(n) for 1 < n < 1,000,000: " + sum);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * Calculates sum of max digital root sums for numbers from sequence [from, to>.
   *
   * @param from starting number of the sequence
   * @param to end number of the sequence
   * @return
   */
  private static int mdrsSum(int from, int to) {
    int[] drs = new int[to];
    for (int i = from; i < to; i++) {
      if(drs[i] == 0) {
        drs[i] = calcDrs(i);
      }
      else {
        drs[i] = Math.max(drs[i], calcDrs(i));
      }

      for(int k = 2; k <= i && k*i < to; k++) {
        drs[k*i] = Math.max(drs[i] + drs[k], drs[k*i]);
      }
    }
    return IntStream.of(drs).sum();
  }

  /**
   * Formula for digital root of a number taken from: http://mathworld.wolfram.com/DigitalRoot.html
   *
   * @param number number for which we are calculating digital root
   * @return digital root of specified number
   */
  private static int calcDrs(int number) {
    int drs = number % 9;
    if (drs == 0) {
      return 9;
    }
    else {
      return drs;
    }
  }

}
