package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import deck.Card;
import deck.CardSet;

public class Game {
	private static CardSet deck;
	private static Card deckCard;
	private static List<Player> players;//index 0 is dealer
	
	public static void init() {
		deck = new CardSet();
		deck.initDeck();
		deck.shuffleSet();
		players = new ArrayList<>();
		for (int i = 0;i<2;i++) {
			players.add(new Player());
		}
		System.out.println(deck);
	}
	
	public static void run() { 
		
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
