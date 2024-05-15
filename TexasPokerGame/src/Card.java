public class Card {
	private Poker.Suit s;
	private Poker.CardValue n;

	/**
	 * Default constructor makes a null card
	 */
	public Card() {
		s = Poker.Suit.NULL;
		n = Poker.CardValue.NULL;
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
				this.n = Poker.CardValue.TWO;
				return;
			case 1: // three
				this.n = Poker.CardValue.THREE;
				return;
			case 2: // four
				this.n = Poker.CardValue.FOUR;
				return;
			case 3: // five
				this.n = Poker.CardValue.FIVE;
				return;
			case 4: // six
				this.n = Poker.CardValue.SIX;
				return;
			case 5: // seven
				this.n = Poker.CardValue.SEVEN;
				return;
			case 6: // eight
				this.n = Poker.CardValue.EIGHT;
				return;
			case 7: // nine
				this.n = Poker.CardValue.NINE;
				return;
			case 8: // ten
				this.n = Poker.CardValue.TEN;
				return;
			case 9: // jack
				this.n = Poker.CardValue.JACK;
				return;
			case 10: // queen
				this.n = Poker.CardValue.QUEEN;
				return;
			case 11: // king
				this.n = Poker.CardValue.KING;
				return;
			case 12: // ace
				this.n = Poker.CardValue.ACE;
				return;
			default: // invalid case
				this.n = Poker.CardValue.NULL;
		}
	}

	public String toString() {
		return n + " of " + s;
	}
}
