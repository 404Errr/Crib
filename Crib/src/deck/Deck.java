package deck;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
	private Stack<Card> cards; 
	
	public void init() {
		cards = new Stack<Card>();
		for (int suit = 0;suit<=3;suit++) {			
			for (int val = 1;val<=13;val++) {
				cards.push(new Card(suit, val));
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}

	public List<Card> getCards() {
		return cards;
	}
	
	@Override
	public String toString() {
		return cards.toString();
	}
	
	
}
