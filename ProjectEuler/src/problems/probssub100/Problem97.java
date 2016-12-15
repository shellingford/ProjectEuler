package problems.probssub100;



public class Problem97 {

  /**
   * The first known prime found to exceed one million digits was discovered in 1999, and is a Mersenne prime
   * of the form 2^6972593−1; it contains exactly 2,098,960 digits. Subsequently other Mersenne primes, of the
   * form 2^p−1, have been found which contain more digits.
   *
   * However, in 2004 there was found a massive non-Mersenne prime which contains 2,357,207 digits: 28433×2^7830457+1.
   *
   * Find the last ten digits of this prime number.
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    //we are trying to find last 10 digits, so we can always mod with 10000000000
    long mod = 10000000000L;
    int power = 7830457;
    long pow = calc2ToMod(power, mod);
    long last10Digits = ((pow * 28433) % mod + 1) % mod;

    System.out.println("Last 10 digits: " + last10Digits);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  /**
   * Calculates 2^power % mod.
   *
   * @param power power to which we calculate 2
   * @param mod mod value
   * @return 2^power % mod
   */
  private static long calc2ToMod(int power, long mod) {
    long result = 1;
    for (int i = 1; i <= power; i++) {
      result *= 2;
      result %= mod;
    }
    return result;
  }
}
