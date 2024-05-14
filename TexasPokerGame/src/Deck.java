public class Deck {
	Card[] deck;

	public Deck() {
		deck = new Card[52];

		for (int i = 0; i <= 12; i++) {
			deck[i+(0*13)] = new Card(i, Poker.Suit.SPADES);
		}

		for (int i = 0; i <= 12; i++) {
			deck[i+(1*13)] = new Card(i, Poker.Suit.CLUBS);
		}

		for (int i = 0; i <= 12; i++) {
			deck[i+(2*13)] = new Card(i, Poker.Suit.DIAMONDS);
		}

		for (int i = 0; i <= 12; i++) {
			deck[i+(3*13)] = new Card(i, Poker.Suit.HEARTS);
		}


	}

	//TODO implement shuffle
	private void shuffle() {

	}

	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder();

		for (Card c : deck) {
			returnString.append(c);
			returnString.append(", ");
		}

		return returnString.toString();
	}

}
