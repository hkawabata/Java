package main;

import characters.Character;
import characters.Enemy;

public class Main {
	public static void main (String[] args) {
		//System.out.println("Hello, world");

		Character taro = new Character("太郎",1000,500,100,100,100,100);
		System.out.println(taro.hp);
		System.out.println(taro.atk);
		
		Enemy slime = new Enemy("スライム",500,250,50,50,50,50);
		System.out.println(slime.hp);
		System.out.println(slime.atk);
		taro.attack(slime);
		System.out.println(slime.hp);
		System.out.println(slime.atk);
		
	}
}
