package characters;

public class Hero extends Character{
	
	public Hero(String name, double hpMax, double mpMax, double atk, double def, double spd, double intel){
		super(name, hpMax, mpMax, atk, def, spd, intel);
	}
	public Hero() {
		super();
	}
	
	// 何も書かなければ初期値0
	private int money;
	public int getMoney() {
		return this.money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
}
