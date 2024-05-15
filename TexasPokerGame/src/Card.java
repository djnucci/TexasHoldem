public class Card {
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
	 * Generate a card based on it's order value
	 * @param CARD_VALUE
	 * @param suit
	 */
	public Card(int cardOrderValue, Poker.Suit suit) {
		this.SUIT = suit;

		switch (cardOrderValue) {
			case 0: // two
				this.CARD_VALUE = Poker.CardValue.TWO;
				return;
			case 1: // three
				this.CARD_VALUE = Poker.CardValue.THREE;
				return;
			case 2: // four
				this.CARD_VALUE = Poker.CardValue.FOUR;
				return;
			case 3: // five
				this.CARD_VALUE = Poker.CardValue.FIVE;
				return;
			case 4: // six
				this.CARD_VALUE = Poker.CardValue.SIX;
				return;
			case 5: // seven
				this.CARD_VALUE = Poker.CardValue.SEVEN;
				return;
			case 6: // eight
				this.CARD_VALUE = Poker.CardValue.EIGHT;
				return;
			case 7: // nine
				this.CARD_VALUE = Poker.CardValue.NINE;
				return;
			case 8: // ten
				this.CARD_VALUE = Poker.CardValue.TEN;
				return;
			case 9: // jack
				this.CARD_VALUE = Poker.CardValue.JACK;
				return;
			case 10: // queen
				this.CARD_VALUE = Poker.CardValue.QUEEN;
				return;
			case 11: // king
				this.CARD_VALUE = Poker.CardValue.KING;
				return;
			case 12: // ace
				this.CARD_VALUE = Poker.CardValue.ACE;
				return;
			default: // invalid case
				this.CARD_VALUE = Poker.CardValue.NULL;
		}
	}

	public Poker.Suit getSuit() {
		return SUIT;
	}

	public Poker.CardValue getCardValue() {
		return CARD_VALUE;
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
}
