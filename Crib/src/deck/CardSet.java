package deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardSet {
	private List<Card> cards; 
	
	public CardSet() {
		cards = new ArrayList<Card>();
	}

	/**
	 * sets cardset to 52 playing card deck
	 */
	public void makeDeck() {
		clear();
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
	
	public void clear() {
		cards.clear();
	}

	public List<Card> getCards() {
		return cards;
	}
	
	public void sortByVal() {
		Collections.sort(cards);
	}
	
	public Card peek() {
		return cards.get(cards.size()-1);
	}
	
	public Card pop() {
		return cards.remove(cards.size()-1);
	}
	
	public boolean push(Card card) {
		return cards.add(card);
	}
	
	public boolean add(Card card) {
		return cards.add(card);
	}
	
	public Card remove(Object obj) {
		return remove(obj);
	}
	
	@Override
	public String toString() {
		return cards.toString();
	}
}
