public class Card implements Comparable<Card> {
	private final Poker.Suit SUIT;
	private final Poker.CardValue CARD_VALUE;

	/**
	 * Default constructor makes a null card
	 */
	public Card() {
		SUIT = Poker.Suit.NULL;
		CARD_VALUE = Poker.CardValue.NULL;
	}

	/**
	 * Generate a card based on both it's card and suit order value
	 * @param cardOrderValue
	 * @param suitOrderValue
	 */
	public Card(int cardOrderValue, int suitOrderValue) {
		this.SUIT = Poker.getSuit(suitOrderValue);
		this.CARD_VALUE = Poker.getCardValue(cardOrderValue);
	}

	/**
	 * Generate a card based on only it's card order value
	 * @param CARD_VALUE
	 * @param suit
	 */
	public Card(int cardOrderValue, Poker.Suit suit) {
		this.SUIT = suit;
		this.CARD_VALUE = Poker.getCardValue(cardOrderValue);
	}

	public Card(Poker.CardValue v, Poker.Suit s) {
		this.SUIT = s;
		this.CARD_VALUE = v;
	}

	public Poker.Suit getSuit() {
		return this.SUIT;
	}

	public Poker.CardValue getCardValue() {
		return this.CARD_VALUE;
	}

	public boolean ranksEqualTo(Card c) {
		return this.getCardValue() == c.getCardValue();
	}

	@Override
	public String toString() {
		return CARD_VALUE + " of " + SUIT;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Card)) {
			return false;
		}
		
		return this.getCardValue() == ((Card)obj).getCardValue() && this.getSuit() == ((Card)obj).getSuit();
	}

	//FIXME compare to will always sort with Aces at the top
	@Override
	public int compareTo(Card c) {
		if (this.getCardValue() == c.getCardValue()) {
			if (this.getSuit().ranksEqualTo(c.getSuit())) {
				return 0;
			}
			else if (this.getSuit().ranksHigherThan(c.getSuit())) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else if (this.getCardValue().ranksHigherThan(c.getCardValue())) {
			return 1;
		}
		else {
			return -1;
		}
	}

}
