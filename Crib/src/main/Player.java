package main;

import deck.CardSet;

public class Player {
	private int score;
	private CardSet hand;
	private CardSet pile;
	
	public Player() {
		resetCards();
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public void resetCards() {
		hand = new CardSet();
		pile = new CardSet();
	}
	
	public CardSet getHand() {
		return hand;
	}
	
	public CardSet getPile() {
		return pile;
	}
	
	
}
