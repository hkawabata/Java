package algorithm;

/*
 * 積分を以下の幾つかの方法で計算する。
 */


public class Integration {

	// 被積分関数
	public static double function (double x) {
		return x*x;
		//return 1;
	}
	
	
	// 台形則による積分
	public static double integrationTrapezoid (double bottom, double top, double dx) {
		double sign;
		if (bottom > top) {
			double tmp = top;
			top = bottom;
			bottom = tmp;
			sign = -1;
		} else {
			sign = 1;
		}

		double x = bottom;
		double ret = 0;
		while (x < top) {
			ret += (function(x) + function(x+dx)) * dx / 2;
			x += dx;
		}
		
		return ret * sign;
	}
	
	
	// 中点則による積分
	public static double integrationMiddlePoint (double bottom, double top, double dx) {
		double sign;
		if (bottom > top) {
			double tmp = top;
			top = bottom;
			bottom = tmp;
			sign = -1;
		} else {
			sign = 1;
		}

		double x = bottom + dx / 2;
		double ret = 0;
		while (x < top) {
			ret += function(x) * dx;
			x += dx;
		}
		
		return ret * sign;		
	}
	
	
	// シンプソン則
	public static double integrationSimpson (double bottom, double top, double dx) {
		double sign;
		if (bottom > top) {
			double tmp = top;
			top = bottom;
			bottom = tmp;
			sign = -1;
		} else {
			sign = 1;
		}

		double fa, fb, fm;
		double x = bottom;
		double ret = 0;
		while (x < top) {

			fa = function(x);
			fb = function(x + dx);
			fm = function(x + dx * 0.5);
			
			ret += (fa + 4*fm + fb) * dx / 6;
			x += dx;
		}
		
		return ret * sign;		
	}
	
	
	/*
	 * テストコード
	 */
	public static void integrationTest () {
		double bottom = -3, top = 3;
		double dx;
		double result;
		
		// 台形則
		System.out.println("### Trapezoid method");
		for (int i=0; i<9; i++) {
			dx = Math.pow(0.1, i);
			result = integrationTrapezoid(bottom, top, dx);
			System.out.println(dx + "\t" + result + "\t" + Math.abs(result-18));
		}
		
		// 中点則
		System.out.println("### MiddlePoint method");
		for (int i=0; i<9; i++) {
			dx = Math.pow(0.1, i);
			result = integrationMiddlePoint(bottom, top, dx);
			System.out.println(dx + "\t" + result + "\t" + Math.abs(result-18));
		}
		
		// シンプソン則
		System.out.println("### Simpson's method");
		for (int i=0; i<9; i++) {
			dx = Math.pow(0.1, i);
			result = integrationSimpson(bottom, top, dx);
			System.out.println(dx + "\t" + result + "\t" + Math.abs(result-18));
		}
	}
}
