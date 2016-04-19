package problems.fibonacciFever;

public class Problem144 {

	/**
	 * https://projecteuler.net/problem=144
	 */
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		int noHits = 0;

		double xO = 1.4;
		double yO = -9.6;
		double xA = 0.0;
		double yA = 10.1;

		while (xO > 0.01 || xO < -0.01 || yO < 0) {
		    double slopeA = (yO - yA) / (xO - xA);
		    double slopeO = -4 * xO / yO;

		    double tanA = (slopeA - slopeO) / (1 + slopeA * slopeO);
		    double slopeB = (slopeO - tanA) / (1 + tanA * slopeO);
		    double interceptB = yO - slopeB * xO;

		    double a = 4 + slopeB * slopeB;
		    double b = 2 * slopeB * interceptB;
		    double c = interceptB * interceptB - 100;

		    //2 solutions for quadratic equation: x = (-b +- sqrt(b^2 - 4ac))/2a
		    double s = (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
		    double s2 = (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);

		    xA = xO;
		    yA = yO;

		    //one point is the current one where line crosses the ellipse
		    //so we need to take the one further away
		    xO = (Math.abs(s - xO) < Math.abs(s2 - xO)) ? s2 : s;
		    yO = slopeB * xO + interceptB;

		    noHits++;
		}

		System.out.println("Number of hits: " + noHits);
		System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
	}

}
