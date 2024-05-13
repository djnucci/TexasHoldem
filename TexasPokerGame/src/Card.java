public class Card {
	private Poker.Suit s;
	private Poker.Card n;

	public Card() {
		s = Poker.Suit.HEARTS;
		n = Poker.Card.TWO;
	}

	public String toString() {
		return n + " of " + s;
	}
}
