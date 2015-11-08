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
}
