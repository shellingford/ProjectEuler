package problems.probssub100;

import java.math.BigInteger;

public class Problem16 {

  /**
   * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
   *
   * What is the sum of the digits of the number 2^1000?
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    BigInteger num = BigInteger.valueOf(2).pow(1000);
    int sum = num.toString(10).chars().map(c -> c - 48).sum();

    System.out.println("Sum of the digits of the number 2^1000: " + sum);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

}
