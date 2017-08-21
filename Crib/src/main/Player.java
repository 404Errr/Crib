package main;

import java.util.Random;
import java.util.Scanner;

import deck.Card;
import deck.CardSet;

public class Player {
	private int score;
	private CardSet hand;
	private CardSet playPile;
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
		hand = new CardSet(false);
		playPile = new CardSet(false);
	}

	public CardSet getHand() {
		return hand;
	}

	public CardSet getPlayPile() {
		return playPile;
	}

	public Card playCard() {
		while (true) {
			int choice = -1;
			if (!playerControlled) {
				choice = new Random().nextInt(hand.size());
			}
			else {
				System.out.println(hand);
				@SuppressWarnings("resource")
				String input = new Scanner(System.in).next();
				try {
					choice = Integer.parseInt(input);
				}
				catch (Exception e) {}
			}
			if (choice<0||choice>=hand.size()) continue;
			Card cardChoice = hand.remove(choice);
			if (!Game.canPlay(cardChoice)) continue;
			playPile.push(cardChoice);
			return cardChoice;
		}
	}

	public void giveToCrib() {
		while (hand.size()>4) {
			int choice = -1;
			if (!playerControlled) {
				choice = new Random().nextInt(hand.size());
			}
			else {
				System.out.println(hand);
				@SuppressWarnings("resource")
				String input = new Scanner(System.in).next();
				try {
					choice = Integer.parseInt(input);
				}
				catch (Exception e) {}
			}
			if (choice<0||choice>=hand.size()) continue;
			Game.giveToCrib(hand.remove(choice));
		}
	}


}
