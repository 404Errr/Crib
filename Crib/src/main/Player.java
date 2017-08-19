package main;

import java.util.Random;
import java.util.Scanner;

import javax.net.ssl.HandshakeCompletedEvent;

import deck.CardSet;

public class Player {
	private int score;
	private CardSet hand;
	private CardSet pile;
	private final boolean playerControlled;
	
	public Player(boolean playerControlled) {
		resetCards();
		this.playerControlled = playerControlled;
	}

	public void addScore(int dScore) {
		this.score+=dScore;
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

	public void giveToCrib() {
		while (hand.getCards().size()>4) {
			int choice = 0;
			if (!playerControlled) {
				choice = new Random().nextInt(hand.getCards().size());
			}
			else {
				System.out.println(hand);
				choice = new Scanner(System.in).nextInt();
			}
			if (choice<0||choice>=hand.getCards().size()) continue;
			Game.giveToCrib(hand.getCards().remove(choice));
		}
	}
	
	
}
