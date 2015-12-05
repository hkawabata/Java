package algorithm;

//import java.util.Arrays;

/*
 * 多項式関数による点の補間を行うオブジェクト
 */

public class Interpolator {
	
	private double x0[];
	private double y0[];
	private int numOfPoints;
	private double a[];
	
	public Interpolator (double x0[], double y0[]) {
		this.x0 = x0;
		this.y0 = y0;
		// x, y の要素数が等しくなければエラー
		if (this.x0.length != this.y0.length) {
			System.out.println("Lengthes of arrays do not correspond.");
			System.exit(1);
		}
		this.numOfPoints = this.x0.length;
		this.a = new double[this.numOfPoints];
	}
	
	
	/*
	 * ラグランジュ補間
	 */

	public double interpolationLagrange (double x) {
		double ret = 0;
		double p;
		for (int i=0; i<this.numOfPoints; i++) {
			p = this.y0[i];
			for (int j=0; j<this.numOfPoints; j++) {
			if (i != j) p *= (x - this.x0[j]) / (this.x0[i] - this.x0[j]);
			}
			ret = ret+p;
		}
		return ret;
	}
	
	
	/*
	 * ニュートン補間
	 */

	// 係数が計算済みかどうかのフラグ
	private boolean coefficientIsCalculated = false;
	// x に対する y の補間値を返す
	public double interpolationNewton (double x) {
		// 係数用の配列を（未計算の場合）計算
		if (this.coefficientIsCalculated == false) {
			this.calculateCoefficient();
		}
		// a[] を使って値を計算
		double ret = this.a[this.numOfPoints - 1];
		for (int i=2; i<=this.numOfPoints; i++) {
			ret = ret * (x - this.x0[this.numOfPoints - i])
					+ this.a[this.numOfPoints - i];
		}
		return ret;
	}
	// 係数を計算するメソッド
	private void calculateCoefficient () {
		double w[] = new double[this.numOfPoints];
		for (int i=0; i<this.numOfPoints; i++) {
			w[i] = this.y0[i];
		}
		this.a[0] = w[0];
		int cnt = 1;
		while (this.numOfPoints - cnt > 0) {
			for (int i=0; i<this.numOfPoints-cnt; i++) {
				w[i] = (w[i+1] - w[i]) / (this.x0[i+cnt] - this.x0[i]);
			}
			this.a[cnt] = w[0];
			cnt++;
		}
		this.coefficientIsCalculated = true;
	}
	
	
	/*
	 * テストコード
	 */
	public static void interpolationTest () {
		
		int bottom = 0;
		int top = 10;
		int dx = 1;
		Interpolator interpolator;
		
		System.out.println("### y = 2x");
		double x[] = {1,2,3,4,5};
		double y[] = {2,4,6,8,10};
		interpolator = new Interpolator(x, y);
		System.out.println("x\tLagrange\tNewton");
		for (int i=bottom; i<=top; i+=dx) {
			System.out.println(i + "\t" +
				interpolator.interpolationLagrange(i) + "\t" +
				interpolator.interpolationNewton(i));
		}

		System.out.println("### y = x^2 + 1");
		double x2[] = {1,2,4,8,16};
		double y2[] = {2,5,17,65,257};
		interpolator = new Interpolator(x2, y2);
		System.out.println("x\tLagrange\tNewton");
		for (int i=bottom; i<=top; i+=dx) {
			System.out.println(i + "\t" +
				interpolator.interpolationLagrange(i) + "\t" +
				interpolator.interpolationNewton(i));
		}
	}

}
