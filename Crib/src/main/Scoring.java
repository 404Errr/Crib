package main;

import java.util.List;

import deck.Card;
import deck.CardSet;

public class Scoring {
	
	public static int calcHand(Card deckCard, CardSet hand, boolean isCrib) {
		CardSet sampleSet = new CardSet();
		for (int i = 0;i<hand.getCards().size();i++) {
			sampleSet.append(hand.getCards().get(i));
		}
		sampleSet.append(deckCard);
		int totalScore = 0;
		sampleSet.sortByVal();
		List<Card> sample = sampleSet.getCards();
		//pairs
		for (int i = 0;i<sample.size()-1;i++) {
			for (int j = i+1;j<sample.size();j++) {
				if (sample.get(i).getVal()==sample.get(j).getVal()) totalScore+=2;
			}
		}
		//runs
		int runScore = 0;
		int multiplier = 1;
		int currentMultiplierCard = 0;
		for (int currentCard = 0;currentCard<sample.size()-1;currentCard++){
			int thisCard = sample.get(currentCard).getVal();
			int nextCard = sample.get(currentCard+1).getVal();
			if (thisCard==nextCard-1) {
				runScore++;
			}
			else if (thisCard==nextCard) {
				if (currentMultiplierCard==thisCard||currentMultiplierCard==0) {
					multiplier++;
					currentMultiplierCard = thisCard;
				}
				else {
					multiplier*=2;
				}
			}
			else if (runScore<2) {
				runScore = 0;
				multiplier = 1;
			}
			else break;
		}
		if (runScore>1) {
			runScore = (runScore+1)*multiplier;
			totalScore+=runScore;
		}
		//15's
		for (int one = 0;one<sample.size();one++) {
			for (int two = one+1;two<sample.size();two++) {
				if (sample.get(one).getVal()+sample.get(two).getVal()==15) totalScore+=2;	
				for (int three = two+1;three<sample.size();three++) {
					if (sample.get(one).getVal()+sample.get(two).getVal()+sample.get(three).getVal()==15) totalScore+=2;	
					for (int four = three+1;four<sample.size();four++) {
						if (sample.get(one).getVal()+sample.get(two).getVal()+sample.get(three).getVal()+sample.get(four).getVal()==15) totalScore+=2;
					}
				}
			}
		}
		if (sample.get(0).getVal()+sample.get(1).getVal()+sample.get(2).getVal()+sample.get(3).getVal()+sample.get(4).getVal()==15) totalScore+=2;
		//flush
		int matches = 0;
		for (int i = 1;i<hand.getCards().size();i++) {
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
		for (int i = 0;i<hand.getCards().size();i++) {
			if (deckCard.getVal()==11&&hand.getCards().get(i).getSuit()==deckCard.getSuit()) {
				nobs = true;
				break;
			}
		}
		if (nobs) totalScore+=1;
		return totalScore;
	}
}
