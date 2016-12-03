package problems.probs1xx;

import java.util.stream.LongStream;


public class Problem160 {

  //we are trying to find last 5 digits, so we can always mod with 10000
  private final static int mod = 100000;

  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    long n = 1000000000000L;
    int lastFiveDigits = findLastFiveDigits(n);

    System.out.println("Last 5 non-zero digits of 1,000,000,000,000! is: " + lastFiveDigits);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * Finds last 5 digits of number <code>n!</code> before the trailing zeroes.
   *
   * @param n
   * @return last 5 digits of <code>n!</code>
   */
  private static int findLastFiveDigits(long  n) {
    long z = count10s(n);
    //because we are always using mod of 100000 we can see that there's a cycle in values
    //after 2^2505 such that 2^5 = 2^2505 = 32 mod 100000
    if (z >= 2505) {
      z = ((z - 5) % 2500) + 5;
    }
    long remainingFactors = calcFactors(n);
    return (int) ((remainingFactors * (pow(2, z, mod)) % mod));
  }

  /**
   * We need to discard trailing zeroes from the number so we check factors and count
   * how many 2s and 5s are there, because that is how many zeroes will be at the end
   * of the number. <code>n</code> will have more 2s than 5s, so after we discard
   * all pairs of 2s and 5s, we return remaining number of 2s.
   *
   * @param n number for which we are counting zeroes
   * @return difference between number of 2s and 5s
   */
  private static long count10s(long n) {
    //number of factors for 2
    long x = numFactors(n, 2);
    //number of factors for 5
    long y = numFactors(n, 5);
    return x - y;
  }

  /**
   * Counts number of factors <code>factor</code> in <code>n</code>.
   *
   * @param n number for which we count factors
   * @param factor factor which we are counting
   * @return number of factors in n
   */
  private static long numFactors(long n, int factor) {
    if (n != 0) {
      return (n / factor) + numFactors(n / factor, factor);
    }
    return 0;
  }

  /**
   * Going through factors of <code>n</code> that are not divisible by 2 or 5 and calculating
   * their product.
   *
   * @param n
   * @return product of all factors that are not divisible by 2 or 5 modulo 100000
   */
  private static long calcFactors(long n) {
    long n2 = calcFactorsFor2(n);
    long n5 = calcFactorsFor5(n);
    return n2 * n5 % mod;
  }

  /**
   * Calculates product of all number from 1 to <code>n</code> that are coprime to 10.
   * Checks number that are divisible by 5.
   *
   *
   * @param n
   * @return product modulo 100000
   */
  private static long calcFactorsFor5(long n) {
    if (n > 0) {
      long n5 = calcFactorsFor5(n / 5);
      long fc = calcProductCoprimesTo10(n % mod);
      return (n5 * fc) % mod;
    }
    return 1;
  }

  /**
   * Calculates product of all number from 1 to <code>n</code> that are coprime to 10.
   * Checks number that are divisible by 2.
   *
   * @param n
   * @return product modulo 100000
   */
  private static long calcFactorsFor2(long n) {
    if (n > 0) {
      return calcFactors(n / 2);
    }
    return 1;
  }

  /**
   * Calculates product of all number from 1 to <code>n</code> that are coprime to 10.
   *
   * @param n
   * @return product
   */
  private static long calcProductCoprimesTo10(long n) {
    long res = LongStream.range(1, n + 1).filter(i -> i % 2 != 0 && i % 5 != 0)
        .reduce(1, (product, i) -> i * product % mod);
    return res;
  }

  /**
   * Calculates i^z % j.
   *
   * @param i
   * @param z
   * @param j
   * @return i^z % j
   */
  private static long pow(int i, long z, int j) {
    long res = i;
    for (int k = 1; k < z; k++) {
      res = (res * i) % mod;
    }
    return res;
  }

}
