package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import deck.Card;
import deck.CardSet;

public class Game {
	private static CardSet deck, crib;
	private static Card deckCard;
	private static List<Player> players;//index 0 is dealer
	
	public static void init() {
		deck = new CardSet();
		crib = new CardSet();
		players = new ArrayList<>();
		players.add(new Player(true));
		players.add(new Player(false));
//		System.out.println(deck);
	}
	
	public static void run() { 
		do {
			deck.initDeck();
			deck.shuffleSet();
			deckCard = null;
			crib.clear();
			deal(6);
//			System.out.println(players.get(0).getHand());
//			System.out.println(players.get(1).getHand());
			//create crib
			for (int p = 0;p<players.size();p++) {
				players.get(p).giveToCrib();
			}
			System.out.println("0 "+players.get(0).getHand());
			System.out.println("1 "+players.get(1).getHand());
			System.out.println("c "+crib);
			//flip deck card
			deckCard = deck.getCards().remove(0);
			System.out.println(deckCard);
			//scoring
			for (int p = 0;p<players.size();p++) {
				players.get(p).addScore(Scoring.calcHand(deckCard, players.get(p).getHand(), false));
			}
			players.get(0).addScore(Scoring.calcHand(deckCard, crib, true));
			System.out.println(players.get(0).getScore());
			System.out.println(players.get(1).getScore());
			cycleDealer();
		} while (true);
	}

	public static void giveToCrib(Card card) {
		crib.append(card);
	}
	
	public static void cycleDealer() {
		players.add(players.remove(0));
	}
	
	public static void deal(int handSize) {
		deck.shuffleSet();
		for (int p = 0;p<players.size();p++) {
			players.get(p).resetCards();
		}
		for (int i = 0;i<handSize;i++) {
			for (int p = 0;p<players.size();p++) {
				players.get(p).getHand().append(deck.getCards().remove(0));
			}
		}
	}
	
}
