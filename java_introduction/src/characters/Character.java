package characters;

public class Character {
	public String name;   // キャラクター名
	public double hpMax;  // Max HP
	public double hp;     // HP
	public double mpMax;  // Max MP
	public double mp;     // MP
	public double atk;    // 攻撃力
	public double def;    // 守備力
	public double spd;    // 速さ
	public double intel;  // 賢さ
	
	public Character(String name, double hpMax, double mpMax, double atk, double def, double spd, double intel){
		this.name  = name;
		this.hpMax = hpMax;
		this.hp    = hpMax;
		this.mpMax = mpMax;
		this.mp    = mpMax;
		this.atk   = atk;
		this.def   = def;
		this.spd   = spd;
		this.intel = intel;
	}
	
	public void attack (Character that) {
		double dmg = (this.atk / that.def) * 10;
		that.hp -= dmg;
		System.out.println(
				this.name + "は" + that.name +
				"に" + String.valueOf(dmg) + "ダメージを与えた！");
	}
}
