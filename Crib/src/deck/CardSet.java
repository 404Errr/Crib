package deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardSet {
	private List<Card> cards; 
	
	public CardSet() {
		cards = new ArrayList<Card>();
	}

	public void initDeck() {
		for (int suit = 0;suit<=3;suit++) {			
			for (int val = 1;val<=13;val++) {
				append(new Card(suit, val));
			}
		}
	}
	
	public void shuffleSet() {
		Collections.shuffle(cards);
	}
	
	public void append(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}
	
	public void sort() {
		Collections.sort(cards);
	}
	
	@Override
	public String toString() {
		return cards.toString();
	}
}
