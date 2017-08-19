package main;

import deck.Deck;

public class Game {
	private static Deck deck;
	
	public static void init() {
		deck = new Deck();
		deck.init();
		deck.shuffle();
		System.out.println(deck);
	}
}
