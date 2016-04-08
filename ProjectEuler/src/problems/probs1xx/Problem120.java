package problems.probs1xx;

public class Problem120 {
	
	/**
	 * Let r be the remainder when (a−1)n + (a+1)n is divided by a2.
	 * For example, if a = 7 and n = 3, then r = 42: 63 + 83 = 728 ≡ 42 mod 49. And as n varies,
	 * so too will r, but for a = 7 it turns out that rmax = 42.
	 * 
	 * For 3 ≤ a ≤ 1000, find rmax.
	 */
	public static void main(String[] args) {
		//thanks to wolfram alpha i found a pattern for r, where when a % 2 == 0 then r = 2,
		//otherwise r = 2 * n * a ==> when we rearrange that we get that rmax = 2a[(a-1)/2]
		
		int rMax = 0;
		for (int i = 3; i <= 1000; i++) {
			rMax += 2 * i * ((i - 1) / 2);
		}
		
		System.out.println("Sum[3,1000] of rmax = " + rMax);
	}

}
