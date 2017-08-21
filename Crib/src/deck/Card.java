package deck;

import main.Data;

public class Card implements Comparable<Card> {
	private int suit, val;

	public Card(int suit, int val) {
		this.suit = suit;
		this.val = val;
	}

	public int getSuit() {
		return suit;
	}

	public int getSVal() {
		if (val>=10) return 10;
		return val;
	}

	public int getVal() {
		return val;
	}

	@Override
	public String toString() {
		return getSuitStr(suit)+""+getValStr(val);
	}

	public static String getSuitStr(int suit) {
		switch (suit) {
		case Data.SUIT_SPADE: return "\u2660";
		case Data.SUIT_DIAMOND: return "\u2666";
		case Data.SUIT_CLUB: return "\u2663";
		case Data.SUIT_HEART: return "\u2665";
		}
		return null;
	}

	public static String getValStr(int val) {
		switch (val) {
		case 1: return "A";
		case 2: return "2";
		case 3: return "3";
		case 4: return "4";
		case 5: return "5";
		case 6: return "6";
		case 7: return "7";
		case 8: return "8";
		case 9: return "9";
		case 10: return "10";
		case 11: return "J";
		case 12: return "Q";
		case 13: return "K";
		}
		return null;
	}

	@Override
	public int compareTo(Card that) {
		if (this.val>that.val) return 1;
		if (this.val<that.val) return -1;
		return 0;
	}
}
