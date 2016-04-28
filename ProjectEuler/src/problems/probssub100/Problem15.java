package problems.probssub100;

public class Problem15 {

	/**
	 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are
	 * exactly 6 routes to the bottom right corner.
	 *
	 * How many such routes are there through a 20×20 grid?
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		long noPaths = 1;

		//binomial coefficient: (40 / 20)
		for (int i = 40; i > 20; i--) {
			noPaths *= i;
			noPaths /= (40 - i + 1);
		}

		System.out.println("Number of routes: " + noPaths);
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
	}

}
