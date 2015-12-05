package algorithm;

/* 
 * 多項式を効率的に計算する Horner の手法を実装
 */

public class Horner {
	
	// Horner の方法
	public static double polynomialHorner (double a[], double x) {
		int n = a.length;
		double ret = a[0];
		for (int i=1; i<n; i++) {
			ret = ret * x + a[i];
		}
		return ret;
	}
	
	// pow 関数を使って和を取る手法
	public static double polynomial (double a[], double x) {
		int n = a.length;
		double ret = 0;
		for (int i=0; i<n; i++) {
			ret += a[i] * Math.pow(x, n-i-1);
		}
		return ret;
	}
	
	/* 
	 * テストコード
	 */
	
	// 2手法の計算速度の違いを測定する
	public static void polinomialTest () {
		int arr_size = 65536;
		int trial_time = 100;

		double[] a = new double[arr_size];
		for (int i=0; i<arr_size; i++) {
			a[i] = (i + 1) * 1;
		}
		
		long start, end;
		double tmp;

		System.out.println("### Horner's method ###");
		start = System.currentTimeMillis();
		for (int i=0; i<trial_time; i++) {
			tmp = Horner.polynomialHorner(a, 0.1);
		}
		end = System.currentTimeMillis();
		System.out.println("coluculation time:\t" + (end-start) + " ms.");
		
		System.out.println("### normal method ###");
		start = System.currentTimeMillis();
		for (int i=0; i<trial_time; i++) {
			tmp = Horner.polynomial(a, 0.1);
		}
		end = System.currentTimeMillis();
		System.out.println("coluculation time:\t" + (end-start) + " ms.");
	}
}
