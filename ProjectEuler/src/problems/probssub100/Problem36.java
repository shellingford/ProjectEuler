package problems.probssub100;

import java.util.stream.IntStream;

public class Problem36 {

  /**
   * The decimal number, 585 = 1001001001 (binary), is palindromic in both bases.
   *
   * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
   *
   * (Please note that the palindromic number, in either base, may not include leading zeros.)
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    int sum = IntStream.range(1, 1000000).filter(i -> arePalinrome(i)).sum();

    System.out.println("Sum of all number under 1M which are palindromic in base 10 and base 2 is: " + sum);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static boolean arePalinrome(int number) {
    return isPalindrome(Integer.toString(number)) && isPalindrome(Integer.toBinaryString(number));
  }

  private static boolean isPalindrome(String number) {
    for(int i = 0; i < (number.length() / 2); i++) {
      if (number.charAt(i) != number.charAt(number.length() - i - 1)) {
        return false;
      }
    }
    return true;
  }

}
