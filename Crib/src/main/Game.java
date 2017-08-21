package main;

import java.util.ArrayList;
import java.util.List;

import deck.Card;
import deck.CardSet;

public class Game {
	private static CardSet deck, crib;
	private static Card deckCard;
	private static int curDealer, playCount;
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
			deck.makeDeck();
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
			//cut
			deckCard = deck.pop();
			if (deckCard.getVal()==11) {//heels
				getDealer().addScore(2);
			}
			System.out.println(deckCard);
			//scoring
			for (int p = 0;p<players.size();p++) {
				players.get(p).addScore(Scoring.calcHand(deckCard, players.get(p).getHand(), false));
			}
			getDealer().addScore(Scoring.calcHand(deckCard, crib, true));
			System.out.println("0 "+players.get(0).getScore());
			System.out.println("1 "+players.get(1).getScore());
			cycleDealer();
		} while (true);
	}

//	public void playCards(){
//		boolean playersTurn = playerOne.amIDealing();
//		playCount = 0;
//		int nextBoardSpot = 0;
//		int cardPlayed = -1;
//		if(playersTurn){
//			//add the first points to the board
//			cardPlayed = playerOne.playCard(cardPlayed);
//			playerOne.goodPlay(cardPlayed);
//		}else{
//			cardPlayed = playerTwo.playCard(cardPlayed);
//			playerTwo.goodPlay(cardPlayed);
//		}
//		playCount = cribbageDeck.getValue(cardPlayed);
//		playersTurn = !playersTurn;
//		boardCards[nextBoardSpot++] = cardPlayed;
//		//System.out.println("boardScore = "+boardScore);
//		while(playerOne.getHand()[0] != -1 || playerTwo.getHand()[0] != -1){
//			cardPlayed = -1;
//			boolean pOneHasMove = checkPlayable(playerOne.getHand());
//			boolean pTwoHasMove = checkPlayable(playerTwo.getHand());
//			if(pOneHasMove && pTwoHasMove){
//				if(playersTurn){
//					cardPlayed = playerOne.playCard(cardPlayed);
//					while(cribbageDeck.getValue(cardPlayed) + playCount > 31)
//						cardPlayed = playerOne.playCard(cardPlayed);
//					playerOne.goodPlay(cardPlayed);
//				}else{
//					cardPlayed = playerTwo.playCard(cardPlayed);
//					while(cribbageDeck.getValue(cardPlayed) + playCount > 31)
//						cardPlayed = playerTwo.playCard(cardPlayed);
//					playerTwo.goodPlay(cardPlayed);
//				}
//				//check for scores.
//				playCount += cribbageDeck.getValue(cardPlayed);
//				playersTurn = !playersTurn;
//			}else if(pOneHasMove){
//				//play the move
//				cardPlayed = playerOne.playCard(cardPlayed);
//				while(cribbageDeck.getValue(cardPlayed) + playCount > 31)
//					cardPlayed = playerOne.playCard(cardPlayed);
//				playerOne.goodPlay(cardPlayed);
//				playCount += cribbageDeck.getValue(cardPlayed);
//				playersTurn = !playersTurn;
//			}else if(pTwoHasMove){
//				//play the move
//				cardPlayed = playerTwo.playCard(cardPlayed);
//				while(cribbageDeck.getValue(cardPlayed) + playCount > 31)
//					cardPlayed = playerTwo.playCard(cardPlayed);
//				playerTwo.goodPlay(cardPlayed);
//				playCount += cribbageDeck.getValue(cardPlayed);
//				playersTurn = !playersTurn;
//			}else{
//				emptyBoard();
//				nextBoardSpot = 0;
//				playCount = 0;
//			}
//			if(cardPlayed != -1){
//				boardCards[nextBoardSpot++] = cardPlayed;
//				System.out.println("boardScore = "+playCount);
//			}else{
//				if(playCount != 31){
//					if(!playersTurn){
//						playerOne.setScore(boardPoints(nextBoardSpot));
//					}else{
//						playerTwo.setScore(boardPoints(nextBoardSpot));
//					}
//				}
//				System.out.println("***Cleared***");
//			}
//			if(!playersTurn){
//				playerOne.setScore(boardPoints(nextBoardSpot));
//			}else{
//				playerTwo.setScore(boardPoints(nextBoardSpot));
//			}
//		}
//	}
//	public int boardPoints(int nextEmptySpot){
//		int points = 0;
//		switch(playCount){
//			case 0:
//				points += 0;
//				break;
//			case 15:
//				points += 2;
//				break;
//			case 31:
//				points += 2;
//				break;
//			default:
//				break;
//		}
//		//Check for runs and pairs
//		switch(nextEmptySpot){
//			case 0:
//				break;
//			case 1:
//				break;
//			case 2:
//				points += CheckBoardPairs(nextEmptySpot);
//				break;
//			default:
//				points += CheckBoardRuns(nextEmptySpot);
//				break;
//		}
//		return points;
//	}
//	public int CheckBoardPairs(int next){
//		int amount = 0;
//		for(int i = next-1; i > 0; i--){
//			if(cribbageDeck.getFace(boardCards[i]).compareTo(cribbageDeck.getFace(boardCards[i-1])) == 0)
//				amount++;
//			else
//				break;
//		}
//		if(amount > 0)
//			System.out.println("There were: "+amount*(amount+1)+" pair points");
//		return amount*(amount+1);
//	}
//	public int CheckBoardRuns(int next){
//		int count = 2;
//		boolean aRun = true;
//		while(aRun){
//			int temp = next;
//			int[] runList = new int[count];
//			for (int i = temp-1; i > next-count; i--)
//				runList[count--] = boardCards[i];
//			sort(runList);
//			int total = 0;
//			for(int i = 0; i < runList.length-1; i++){
//				if(cribbageDeck.getNumValue(runList[i])+1 == cribbageDeck.getNumValue(runList[i+1]))
//					total++;
//				else
//					break;
//			}
//		if (total < 2)
//			return CheckBoardPairs(next);
//		}
//		//count
//		//take first three cards. sort them. see if they are a run.
//		//if not return checkboardpoints if they are add one more card until all used up to find the run amount.
//		return 0;
//	}
//
//	public boolean checkPlayable(int[] Hand){
//		//get the board score
//		for(int i = 0; i < Hand.length; i++){
//			if(Hand[i] == -1)
//				return false;
//			if(playCount + cribbageDeck.getValue(Hand[i]) <= 31)
//				return true;
//		}
//		return false;
//	}

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
		deck.shuffleSet();
		for (int p = 0;p<players.size();p++) {
			players.get(p).resetCards();
			for (int i = 0;i<handSize;i++) {
				players.get(p).getHand().push(deck.pop());
			}
			players.get(p).getHand().sortByVal();
		}
	}

}
