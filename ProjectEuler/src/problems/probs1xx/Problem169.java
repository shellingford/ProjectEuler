package problems.probs1xx;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Problem169 {

  /**
   * Define f(0)=1 and f(n) to be the number of different ways n can be expressed as a sum of integer powers
   * of 2 using each power no more than twice.
   *
   * For example, f(10)=5 since there are five different ways to express 10:
   *    1 + 1 + 8
   *    1 + 1 + 4 + 4
   *    1 + 1 + 2 + 2 + 4
   *    2 + 4 + 4
   *    2 + 8
   *
   * What is f(10^25)?
   */
  public static void main(String[] args) {
    long start = System.currentTimeMillis();

    BigInteger m = BigInteger.valueOf(10).pow(25);
    BigInteger numWays = count(m);
    System.out.println("Number of different ways: " + numWays);

    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
  }

  private static Map<BigInteger, BigInteger> values = new HashMap<>();
  private static BigInteger TWO = BigInteger.valueOf(2);

  private static BigInteger count(BigInteger k) {
    if (values.containsKey(k)) {
      return values.get(k);
    }
    if (BigInteger.ZERO.equals(k)) {
      return BigInteger.ONE;
    }
    if (BigInteger.ONE.equals(k)) {
      return BigInteger.ONE;
    }
    //f(2k) = f(k - 1) + f(k)
    if (k.mod(TWO).equals(BigInteger.ZERO)) {
      BigInteger mDiv2 = k.divide(TWO);
      BigInteger res1 = count(mDiv2.subtract(BigInteger.ONE));
      BigInteger res2 = count(mDiv2);

      BigInteger sum = res1.add(res2);
      values.put(k, sum);
      return sum;
    }
    //f(2k + 1) = f(k)
    else {
      BigInteger res = count(k.divide(TWO));
      values.put(k, res);
      return res;
    }
  }

}
