package algorithm;

public class Eucledean {
	// greatest common divisor （最大公約数）を計算する
	public static int gcd (int m, int n) {
		int max = Math.max(m, n);
		int min = Math.min(m, n);
		int max_tmp, min_tmp=0;
		while (min != 0) {
			max_tmp = max;
			min_tmp = min;
			max = min_tmp;
			min = max_tmp % min_tmp;
		}
		return min_tmp;
	}
	
	/* 
	 * テストコード
	 */
	public static void gcdTest () {
		int mn_pair[][] = {
				{10, 15},
				{14, 63},
				{128, 1140},
				{100, 100},
				{1, 25}};
		for (int mn[]: mn_pair) {
			System.out.println("GCD of [" + mn[0] + "," + mn[1] + "] is " + gcd(mn[0], mn[1]));
		}
	}
}
