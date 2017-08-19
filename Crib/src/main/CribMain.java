package main;

import deck.Card;
import deck.CardSet;

public class CribMain {
	public static void main(String[] args) {
		Game.init();
		Game.run();
//		CardSet test = new CardSet();
//		test.append(new Card(1, 7));
//		test.append(new Card(1, 1));
//		test.append(new Card(1, 3));
//		test.append(new Card(1, 6));
//		Card testCard = new Card(1, 11);
//		System.out.println(testCard);
//		System.out.println(test);
//		System.out.println(Scoring.calcHand(testCard, test, false));
	}
}
