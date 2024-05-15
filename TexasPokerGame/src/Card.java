public class Card {
	private Poker.Suit s;
	private Poker.Card n;

	/**
	 * Default constructor makes a null card
	 */
	public Card() {
		s = Poker.Suit.NULL;
		n = Poker.Card.NULL;
	}

	/**
	 * Generate a card based on it's order value
	 * @param cardValue
	 * @param suit
	 */
	public Card(int cardOrderValue, Poker.Suit suit) {
		this.s = suit;

		switch (cardOrderValue) {
			case 0: // two
				this.n = Poker.Card.TWO;
				return;
			case 1: // three
				this.n = Poker.Card.THREE;
				return;
			case 2: // four
				this.n = Poker.Card.FOUR;
				return;
			case 3: // five
				this.n = Poker.Card.FIVE;
				return;
			case 4: // six
				this.n = Poker.Card.SIX;
				return;
			case 5: // seven
				this.n = Poker.Card.SEVEN;
				return;
			case 6: // eight
				this.n = Poker.Card.EIGHT;
				return;
			case 7: // nine
				this.n = Poker.Card.NINE;
				return;
			case 8: // ten
				this.n = Poker.Card.TEN;
				return;
			case 9: // jack
				this.n = Poker.Card.JACK;
				return;
			case 10: // queen
				this.n = Poker.Card.QUEEN;
				return;
			case 11: // king
				this.n = Poker.Card.KING;
				return;
			case 12: // ace
				this.n = Poker.Card.ACE;
				return;
			default: // invalid case
				this.n = Poker.Card.NULL;
		}
	}

	public String toString() {
		return n + " of " + s;
	}
}
