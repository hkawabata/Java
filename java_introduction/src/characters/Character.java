package characters;

public class Character {
	private String name;   // キャラクター名
	private double hpMax;  // Max HP
	private double hp;     // HP
	private double mpMax;  // Max MP
	private double mp;     // MP
	private double atk;    // 攻撃力
	private double def;    // 守備力
	private double spd;    // 速さ
	private double intel;  // 賢さ
	
	// コンストラクタ
	public Character(
			String name, double hpMax, double mpMax,
			double atk, double def, double spd, double intel
			) {
		this.setName(name);
		this.setHpMax(hpMax);
		this.setHp(hpMax);
		this.setMpMax(mpMax);
		this.setMp(mpMax);
		this.setAtk(atk);
		this.setDef(def);
		this.setSpd(spd);
		this.setIntel(intel);
	}
	// 引数がないときのコンストラクタ（オーバーロード）
	public Character() {
		this("No name",10,10,1,1,1,1);
	}
	
	// getter
	public double getHpMax() {
		return this.hpMax;
	}
	public double getHp() {
		return this.hp;
	}
	public double getMpMax() {
		return this.mpMax;
	}
	public double getMp() {
		return this.mp;
	}
	public double getAtk() {
		return this.atk;
	}
	public double getDef() {
		return this.def;
	}
	public double getSpd() {
		return this.spd;
	}
	public double getIntel() {
		return this.intel;
	}
	public String getName() {
		return this.name;
	}
	
	// setter
	public void setName(String name) {
		if(name == null) {
			throw new IllegalArgumentException("名前が空です。");
		}
		if(name.length() > 32) {
			throw new IllegalArgumentException("名前が長すぎます：" + name);
		}
		this.name = name;
	}
	public void setHpMax(double hpMax) {
		this.hpMax = hpMax;
	}
	public void setHp(double hp) {
		this.hp = hp;
	}
	public void setMpMax(double mpMax) {
		this.mpMax= mpMax;
	}
	public void setMp(double mp) {
		this.mp = mp;
	}
	public void setAtk(double atk) {
		this.atk = atk;
	}
	public void setDef(double def) {
		this.def = def;
	}
	public void setSpd(double spd) {
		this.spd = spd;
	}
	public void setIntel(double intel) {
		this.intel = intel;
	}
	
	
	
	// 攻撃
	public void attack (Character that) {
		double dmg = (this.atk / that.def) * 10;
		if (dmg > that.hp) {
			that.hp = 0;
		}else{
			that.hp -= dmg;
		}
		System.out.println(
				this.name + "は" + that.name +
				"に" + String.valueOf(dmg) + "ダメージを与えた！");
		
		// 死亡判定
		if (that.hp == 0) {
			System.out.println(that.name + "を倒した");
		}
	}
	
	// 全回復
	public void fullcare() {
		this.hp = this.hpMax;
	}
}
