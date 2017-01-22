package jp.hkawabata.algorithm;

/*
 * コンビネーション nCr を計算する。
 */


public class Combination {

	/*
	 * 漸化式
	 *     nCr = nC_(r-1) * (n-r+1)/r
	 * による計算。階乗がないのでメモリに優しい。
	 */
	public static int combinationRecursion (int n, int r) {
		if (n < r) {
			System.out.println("invalid argument.");
			System.exit(1);
		}
		int ret = 1;
		for (int i=1; i<=r; i++) {
			ret = ret * (n - i + 1) / i;
		}
		return ret;
	}
	
	/*
	 * 公式
	 *     nCr = n! / (n-r)! / r!
	 * による計算。n が大きいと階乗がオーバフローして破綻する
	 */
	public static int combinationFactorial (int n, int r) {
		if (n < r) {
			System.out.println("invalid argument.");
			System.exit(1);
		}
		int ret = factorial(n) / factorial(n-r) / factorial(r);
		return ret;
	}

	public static int factorial (int m) {
		if (m == 0) {
			return 1;
		}else{
			int ret = 1;
			for (int i = 1; i <= m; i++) {
				ret *= i;
			}
			return ret;
		}
	}

	
	/**
	 * テストコード
	 */
	public static void combinationTest() {
		System.out.println("combination 5C2, 10C3, 7C0, 9C1 is ...");
		System.out.println("recursion\tfactorial");
		int n, r;
		n = 5; r = 2;
		System.out.println(combinationRecursion(n, r) + "\t" + combinationFactorial(n, r));
		n = 10; r = 3;
		System.out.println(combinationRecursion(n, r) + "\t" + combinationFactorial(n, r));
		n = 7; r = 0;
		System.out.println(combinationRecursion(n, r) + "\t" + combinationFactorial(n, r));
		n = 9; r = 1;
		System.out.println(combinationRecursion(n, r) + "\t" + combinationFactorial(n, r));
	}
	
	public static void factorialTest() {
		System.out.println("factorials of 0,1,2,3,4,5 is ...");
		for (int i=0; i<=5; i++) {
			System.out.println(factorial(i));
		}
	}
}
