package algorithm;

public class LongNumber {
	
	// 符号
	public int sign = 1;
	public long[] value;
	// ケタ数
	public int getDigit () {
		return this.value.length;
	}
	// 1ケタの最大値
	public static final long MAX_OF_EACH_DIGIT = 10000;
	

	// コンストラクタ
	public LongNumber (long l) {
		if (l < 0) this.sign = -1;
		long abs = Math.abs(l);
		// digit を計算
		long abs_tmp = abs;
		int digit = 0;
		do {
			abs_tmp = (long)((double)abs_tmp / MAX_OF_EACH_DIGIT);
			digit ++;
		} while (abs_tmp > 0);
		
		// value を計算・格納
		this.value = new long[digit];
		abs_tmp = abs;
		for (int i=0; i<digit; i++) {
			this.value[i] = abs_tmp % MAX_OF_EACH_DIGIT;
			abs_tmp = (abs_tmp - this.value[i]) / MAX_OF_EACH_DIGIT;
		}
	}
	public LongNumber (long[] longArray, int sign) {
		for (int i=0; i<longArray.length; i++) {
			if (longArray[i] < 0 || longArray[i] >= MAX_OF_EACH_DIGIT) {
				System.err.println(
						String.format(
								"invalid argument: 'longArray' must be %d ~ %d.",
								0, MAX_OF_EACH_DIGIT));
				System.exit(1);
			}
		}
		//if (sign < 0) this.sign = -1;
		this.sign = sign;
		int digit = longArray.length;
		this.value = new long[digit];
		for (int i=0; i<digit; i++) {
			this.value[i] = longArray[digit-1-i];
		}
	}

	// 値0, ケタ数 digit の LongNumber を生成
	public static LongNumber emptyLongNumber (int digit) {
		if (digit < 1) {
			System.err.println("invalid argument: 'digit' must be positive.");
			System.exit(1);
		}
		long longArray[] = new long[digit];
		for (int i=0; i<digit; i++) longArray[i] = 0;
		return new LongNumber(longArray, 1);
	}
	
	// クローンを作成
	public static LongNumber getCloneOf (LongNumber ln) {
		return getCloneOf(ln, ln.getDigit());
		/*
		LongNumber ret = emptyLongNumber(ln.getDigit());
		ret.sign = ln.sign;
		ret.value = ln.value;
		return ret;
		*/
	}
	public static LongNumber getCloneOf (LongNumber ln, int digit) {
		if (digit < ln.getDigit()) {
			System.err.println("invalid argument: 'ln.getDigit()' must be less than or equal to 'digit'");
			System.exit(-1);
		}
		LongNumber ret = emptyLongNumber(digit);
		ret.sign = ln.sign;
		ret.value = ln.value;
		return ret;
	}

	// 符号反転
	public void inverseSign () {
		this.sign *= -1;
	}
	
	// 絶対値が大きい方を取得
	public static LongNumber getAbsoluteMax (LongNumber ln1, LongNumber ln2) {
		int digit = Integer.max(ln1.getDigit(), ln2.getDigit());
		LongNumber ln1tmp = getCloneOf(ln1, digit);
		LongNumber ln2tmp = getCloneOf(ln2, digit);
		for (int i=digit-1; i>=0; i--) {
			if (ln1tmp.value[i] > ln2tmp.value[i]) return ln1;
			if (ln1tmp.value[i] < ln2tmp.value[i]) return ln2;
		}
		return ln1;
	}
	// 絶対値が小さい方を取得
	public static LongNumber getAbsoluteMin (LongNumber ln1, LongNumber ln2) {
		int digit = Integer.max(ln1.getDigit(), ln2.getDigit());
		LongNumber ln1tmp = getCloneOf(ln1, digit);
		LongNumber ln2tmp = getCloneOf(ln2, digit);
		for (int i=digit-1; i>=0; i--) {
			if (ln1tmp.value[i] > ln2tmp.value[i]) return ln2;
			if (ln1tmp.value[i] < ln2tmp.value[i]) return ln1;
		}
		return ln1;
	}
	

	public LongNumber plus (LongNumber ln) {
		
		if (this.sign * ln.sign < 0) {
			// 異符号の場合
			LongNumber lnNegative;
			LongNumber lnPositive;
			if (this.sign > 0) {
				lnNegative = getCloneOf(ln);
				lnPositive = getCloneOf(this);
			} else {
				lnNegative = getCloneOf(this);
				lnPositive = getCloneOf(ln);
			}
			lnNegative.sign = 1;
			return lnPositive.minus(lnNegative);
		} else {
			// 同符号の場合
			// 和による繰り上がりは大きくて1桁
			int newDigit = Integer.max(this.getDigit(), ln.getDigit()) + 1;
			LongNumber ret = emptyLongNumber(newDigit);
			ret.sign = this.sign;
			
			for (int i=0; i<this.getDigit(); i++) ret.value[i] += this.value[i];
			for (int i=0; i<ln.getDigit(); i++) ret.value[i] += ln.value[i];
			for (int i=0; i<newDigit-1; i++) {
				if (ret.value[i] >= MAX_OF_EACH_DIGIT) {
					ret.value[i]   -= MAX_OF_EACH_DIGIT;
					ret.value[i+1] += 1;
				}
			}
			return ret.refresh();
		}
	}
	
	
	public LongNumber minus (LongNumber ln) {
		
		if (this.sign * ln.sign < 0) {
			// 異符号の場合			
			LongNumber lnTmp = getCloneOf(ln);
			lnTmp.inverseSign();
			return this.plus(lnTmp);
		} else {
			// 同符号の場合
			// 同じ符号の引き算でケタは増えない
			int newDigit = Integer.max(this.getDigit(), ln.getDigit());
			LongNumber ret = getCloneOf(LongNumber.getAbsoluteMax(this, ln), newDigit);
			LongNumber small = getCloneOf(LongNumber.getAbsoluteMin(this, ln), newDigit);
			
			if (small.equals(ln)) {
				ret.sign = 1;
			} else {
				ret.sign = -1;
			}

			for (int i=0; i<newDigit; i++) {
				if (ret.value[i] >= small.value[i]) {
					ret.value[i] -= small.value[i];
				} else {
					ret.value[i+1] -= 1;
					ret.value[i] = ret.value[i] + MAX_OF_EACH_DIGIT - small.value[i];
				}
			}
			return ret.refresh();
		}
	}
	
	public LongNumber multiple (LongNumber ln) {
		int digit = this.getDigit() + ln.getDigit();
		LongNumber ret = emptyLongNumber(digit);
		ret.sign = this.sign * ln.sign;
		for (int i=0; i<this.getDigit(); i++) {
			for (int j=0; j<ln.getDigit(); j++) {
				ret.value[i+j] += this.value[i] * ln.value[j];
			}
		}
		int carry;
		for (int i=0; i<digit-1; i++) {
			carry = (int)((double)ret.value[i] / MAX_OF_EACH_DIGIT);
			ret.value[i] -= MAX_OF_EACH_DIGIT * carry;
			ret.value[i+1] += carry;
		}
		return ret.refresh();
	}
	public LongNumber multiple (long l) {
		return this.multiple(new LongNumber(l));
	}
	
	
	// 上のケタがゼロだったら取り除く
	public LongNumber refresh () {
		int cnt = 0;
		// ゼロである上位ケタ数を数える
		// 一番下の位はゼロでいいので数えない
		for (int i=this.getDigit()-1; i>0; i--) {
			if (this.value[i] != 0) break;
			cnt ++;
		}
		
		int newDigit = this.getDigit() - cnt;
		LongNumber ret = emptyLongNumber(newDigit);
		if (newDigit == 1 && this.value[0] == 0) {
			System.out.println(ret);
			ret.sign = 1;
		} else {
			ret.sign = this.sign;
		}
		for (int i=0; i<newDigit; i++) {
			ret.value[i] = this.value[i];
		}
		return ret;
	}
	
	public String toString () {
		String ret = "";
		if (this.sign < 0) ret += "-";
		for (int i=this.getDigit()-1; i>=0; i--) {
			ret += String.format("%04d", this.value[i]);
		}
		return ret;
	}
	
	// 値を比較する.
	// 上位にゼロがつまったケタがあっても良い.
	public boolean equals (LongNumber ln) {
		if (this.sign * ln.sign < 0) return false;
		// ケタ数合わせ
		int digit = Integer.max(this.getDigit(), ln.getDigit());
		LongNumber ln1 = getCloneOf(this, digit);
		LongNumber ln2 = getCloneOf(ln, digit);

		for (int i=0; i<digit; i++) {
			if (ln1.value[i] != ln2.value[i]) return false;
		}
		return true;
	}
}
