package algorithm;

/*
 *  乱数を使ったメソッド
 */

public class RandomMethods {
	
	// pi の値を計算する
	public static void pi () {
		int trial_time = 100000;
		int cnt = 0;
		for (int i=0; i<trial_time; i++) {
			double x = Math.random();
			double y = Math.random();
			if (x*x+y*y < 1) cnt++;
		}
		double pi = (float)cnt / trial_time * 4;
		System.out.println("pi = " + pi);
	}
	
	
	// 正規分布型の乱数を生成する
	static double gauss_pi = Math.asin(1.0) * 2;
	static double gauss_sigma = 1.0;
	static double limit_factor = 3.0;
	public static double gaussian (double x) {
		return Math.exp(-x*x/(2*gauss_sigma*gauss_sigma))
				/ (Math.sqrt(2*gauss_pi) * gauss_sigma);
	}
	public static double gaussianRand () {
		//double sigma = 1.0;
		//double pi = Math.asin(1.0) * 2;

		// 乱数生成の範囲を決める
		double top = gaussian (0);
		double limit = limit_factor * gauss_sigma;
		
		double x = limit, y = top;
		while (y > gaussian(x)) {
			x = (Math.random() - 0.5) * limit * 2;
			y = Math.random() * top;
		}
		return x;
	}
	// テストコード
	public static void gaussianRandomTest () {
		int division = 40;
		int trial_time = 80000;
		int arr[] = new int[division];
		for (int i=0; i<division; i++) arr[i] = 0;
		double dx = limit_factor * gauss_sigma * 2 / division;
		double r;
		int tmp;
		for (int i=0; i<trial_time; i++) {
			r = gaussianRand();
			tmp = (int)((r+limit_factor*gauss_sigma)/dx);
			arr[tmp]++;
			//System.out.println(r);
		}
		for (int i=0; i<division; i++) {
			// 数が多すぎるので 1/100 に
			tmp = (int)(arr[i]/100);
			for (int j=0; j<tmp; j++) {
				System.out.print("*");
			}
			System.out.println("");
		}
	}
}
