package main;

import characters.Character;
import characters.Enemy;

public class Main {
	public static void main (String[] args) {
		//System.out.println("Hello, world");

		Character taro = new Character("太郎",1000,500,100,100,100,100);
		System.out.println(taro.hp);
		System.out.println(taro.atk);
		
		Enemy slime = new Enemy("スライム",50,25,5,5,5,5);
		System.out.println(slime.hp);
		
		Enemy slimeBes = new Enemy("スライムベス",100,50,20,20,20,20);
		System.out.println(slimeBes.hp);
		
		taro.attack(slime);
		System.out.println(slime.hp);
		
		taro.attack(slimeBes);
		System.out.println(slimeBes.hp);
		
		System.out.println(Character.money);
		taro.money += 100;
		System.out.println(Character.money);
		slime.money += 10;
		System.out.println(Character.money);
		
	}
}
