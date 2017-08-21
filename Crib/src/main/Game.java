package main;

import java.util.ArrayList;
import java.util.List;

import deck.Card;
import deck.CardSet;

public class Game {
	private static CardSet deck, crib, currentPlayHistory;
	private static Card deckCard;
	private static int curDealer, playCount;
	private static List<Player> players;

	public static void init() {
		deck = new CardSet(true);
		crib = new CardSet(false);
		currentPlayHistory = new CardSet(false);
		players = new ArrayList<>();
		players.add(new Player(true));
		players.add(new Player(false));
	}

	public static void run() {
		do {
			deck.resetDeck();
			deckCard = null;
			crib.clear();
			deal(6);
			//create crib
			for (int p = 0;p<players.size();p++) {
				players.get(p).giveToCrib();
			}

			//cut
			deckCard = deck.pop();
			if (deckCard.getVal()==11) {//heels
				getDealer().addScore(2);
			}
			System.out.println(deckCard);
			//play
			playCount = 0;
			currentPlayHistory.clear();
			int turn = curDealer;
			boolean handsEmpty = false;
			do {
				handsEmpty = true;
				for (int p = 0;p<players.size();p++) {
					if (!players.get(p).getHand().isEmpty()) {
						handsEmpty = false;
					}
				}
				if (handsEmpty) break;
				boolean someoneCanPlay = false;
				for (int p = 0;p<players.size();p++) {
					if (canPlay(players.get(p))) someoneCanPlay = true;
				}
				if (playCount==31||!someoneCanPlay) {
					currentPlayHistory.clear();
					playCount = 0;
				}
				System.out.println(playCount);
				turn++;
				if (turn==players.size()) turn = 0;
				if (!canPlay(players.get(turn))) continue;
				Card playedCard = players.get(turn).playCard();
				playCount+=playedCard.getSVal();
				currentPlayHistory.push(playedCard);
				players.get(turn).addScore(Scoring.playScore(currentPlayHistory));


			} while (true);
			for (int p = 0;p<players.size();p++) {
				for (int i = 0;i<4;i++) {
					players.get(p).getHand().push(players.get(p).getPlayPile().pop());
				}
			}
			//scoring
			System.out.println("0 "+players.get(0).getHand());
			System.out.println("1 "+players.get(1).getHand());
			System.out.println("c "+crib);
			for (int p = 0;p<players.size();p++) {
				players.get(p).addScore(Scoring.calcHand(deckCard, players.get(p).getHand(), false));
			}
			getDealer().addScore(Scoring.calcHand(deckCard, crib, true));
			System.out.println("0 "+players.get(0).getScore());
			System.out.println("1 "+players.get(1).getScore());
			cycleDealer();
		} while (true);
	}

	public static boolean canPlay(Player player) {
		boolean cardValsTooHigh = true;
		for (int i = 0;i<player.getHand().size();i++) {
			if (canPlay(player.getHand().get(i))) cardValsTooHigh = false;
		}
		return !(cardValsTooHigh||player.getHand().isEmpty());
	}

	public static boolean canPlay(Card card){
		return playCount+card.getSVal()<=31;
	}

	public static void giveToCrib(Card card) {
		crib.push(card);
	}

	public static void cycleDealer() {
		curDealer++;
		if (curDealer>players.size()-1) curDealer = 0;
	}

	public static Player getDealer() {
		return players.get(curDealer);
	}

	public static void deal(int handSize) {
		for (int p = 0;p<players.size();p++) {
			players.get(p).resetCards();
			for (int i = 0;i<handSize;i++) {
				players.get(p).getHand().push(deck.pop());
			}
			players.get(p).getHand().sort();
		}
	}

}
