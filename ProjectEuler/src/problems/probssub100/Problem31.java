package problems.probssub100;




public class Problem31 {

	/**
	 * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general circulation:
	 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
	 *
	 * It is possible to make £2 in the following way:
	 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
	 *
	 * How many different ways can £2 be made using any number of coins?
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int[] coins = { 1, 2, 5, 10, 20, 50, 100, 200 };
		int amount = 200;
		int[] noWays = new int[amount + 1];
		noWays[0] = 1;

		for(int coin : coins) {
		    for (int j = coin; j <= amount; j++) {
		        noWays[j] += noWays[j - coin];
		    }
		}

		System.out.println("Number of different ways: " + noWays[noWays.length - 1]);
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
	}

}
