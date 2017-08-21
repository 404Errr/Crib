package deck;

import java.util.Collections;
import java.util.Stack;

@SuppressWarnings("serial")
public class CardSet extends Stack<Card> {
	public CardSet(boolean makeAsDeck) {
		if (makeAsDeck) {
			resetDeck();
		}
	}
	public void resetDeck() {
		clear();
		for (int suit = 0;suit<4;suit++) {
			for (int val = 1;val<=13;val++) {
				add(new Card(suit, val));
			}
		}
		Collections.shuffle(this);
	}

	public void sort() {
		Collections.sort(this);
	}

	public void pushBottom(Card card) {
		add(0, card);
	}
}
