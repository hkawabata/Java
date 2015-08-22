package characters;

public class Character {
	public String name;
	public double hpMax;
	public double hp;
	public double mpMax;
	public double mp;
	public double atk;
	public double def;
	public double spd;
	
	public Character(String name, double hpMax, double mpMax, double atk, double def, double spd){
		this.name  = name;
		this.hpMax = hpMax;
		this.hp    = hpMax;
		this.mpMax = mpMax;
		this.mp    = mpMax;
		this.atk   = atk;
		this.def   = def;
		this.spd   = spd;
	}
	
	public void attack (Character that) {
		double dmg = (this.atk / that.def) * 10;
		that.hp -= dmg;
		System.out.println(
				this.name + "は" + that.name +
				"に" + String.valueOf(dmg) + "ダメージを与えた！");
	}
}
