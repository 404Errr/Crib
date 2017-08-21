package main;

import java.util.List;

import deck.Card;
import deck.CardSet;

public class Scoring {

	public static int calcHand(Card deckCard, CardSet hand, boolean isCrib) {
		CardSet sampleSet = new CardSet();
		for (int i = 0;i<4;i++) {
			sampleSet.add(hand.getCards().get(i));
		}
		sampleSet.add(deckCard);
		int totalScore = 0;
		sampleSet.sortByVal();
		List<Card> sample = sampleSet.getCards();
		//pairs
		for (int i = 0;i<5-1;i++) {
			for (int j = i;j<5;j++) {
				if (i!=j&&sample.get(i).getVal()==sample.get(j).getVal()) totalScore+=2;
			}
		}
		//runs
		int runScore = 0, multi = 1, currentMultiCard = 0;
		for (int currentCard = 0;currentCard<5-1;currentCard++) {
			int thisCard = sample.get(currentCard).getVal(), nextCard = sample.get(currentCard+1).getVal();
			if (thisCard==nextCard-1) {
				runScore++;
			}
			else {
				if (thisCard==nextCard) {
					if (currentMultiCard==thisCard||currentMultiCard==0) {
						multi++;
						currentMultiCard = thisCard;
					}
					else {
						multi*=2;
					}
				}
				else {
					if (runScore<2) {
						runScore = 0;
						multi = 1;
					}
					else {
						break;
					}
				}
			}
		}
		if (runScore>1) {
			runScore = (runScore+1)*multi;
			totalScore+=runScore;
		}
		//15's
		for (int one = 0;one<5;one++) {
			for (int two = one+1;two<5;two++) {
				if (sample.get(one).getSVal()+sample.get(two).getSVal()==15) {
					totalScore+=2;
				}
				for (int three = two+1;three<5;three++) {
					if (sample.get(one).getSVal()+sample.get(two).getSVal()+sample.get(three).getSVal()==15) {
						totalScore+=2;
					}
					for (int four = three+1;four<5;four++) {
						if (sample.get(one).getSVal()+sample.get(two).getSVal()+sample.get(three).getSVal()+sample.get(four).getSVal()==15) {
							totalScore+=2;
						}
					}
				}
			}
		}
		if (sample.get(0).getSVal()+sample.get(1).getSVal()+sample.get(2).getSVal()+sample.get(3).getSVal()+sample.get(4).getSVal()==15) {
			totalScore+=2;
		}
		//flush
		int matches = 0;
		for (int i = 1;i<4;i++) {
			if (hand.getCards().get(i).getSuit()==hand.getCards().get(i-1).getSuit()) {
				matches++;
			}
		}
		if (matches==3) {
			if (!isCrib) totalScore+=4;
			if (hand.getCards().get(0).getSuit()==deckCard.getSuit()) {
				if (!isCrib) totalScore+=1;
				else totalScore+=5;
			}
		}
		//nobs
		boolean nobs = false;
		for (int i = 0;i<4;i++) {
			if (hand.getCards().get(i).getVal()==11&&hand.getCards().get(i).getSuit()==deckCard.getSuit()) {
				nobs = true;
				break;
			}
		}
		if (nobs) totalScore+=1;
		return totalScore;
	}
}
