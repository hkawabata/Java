package algorithm;

/* 
 * 多項式を効率的に計算する Horner の手法を実装
 */

public class Horner {
	public static double polynomialHorner (double a[], double x) {
		int n = a.length;
		double ret = a[0];
		for (int i=1; i<n; i++) {
			ret = ret * x + a[i];
		}
		return ret;
	}
}
