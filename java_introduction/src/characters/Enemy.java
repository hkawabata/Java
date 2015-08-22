package characters;

public class Enemy extends Character {

	public Enemy(String name, double hpMax, double mpMax, double atk, double def, double spd, double intel){
		super(name, hpMax, mpMax, atk, def, spd, intel);
	}
	
	public void sample () {
		this.hpMax = 100;
		System.out.println(this.hpMax);
	}
}
