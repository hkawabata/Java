package main;

import characters.Enemy;
import characters.Hero;

public class Main {
	public static void main (String[] args) {
		//System.out.println("Hello, world");

		Hero taro = new Hero("太郎",1000,500,100,100,100,100);
		System.out.println(taro.getHp());
		System.out.println(taro.getAtk());
		
		Enemy slime = new Enemy("スライム",50,25,5,5,5,5);
		System.out.println(slime.getHp());
		
		Enemy slimeBes = new Enemy("スライムベス",100,50,20,20,20,20);
		System.out.println(slimeBes.getHp());
		
		taro.attack(slime);
		System.out.println(slime.getHp());
		
		taro.attack(slimeBes);
		System.out.println(slimeBes.getHp());
		
		System.out.println(Hero.getMoney());
		Hero.setMoney(Hero.getMoney() + 100);
		System.out.println(Hero.getMoney());
		Hero.setMoney(Hero.getMoney() + 10);
		System.out.println(Hero.getMoney());
		
	}
}
