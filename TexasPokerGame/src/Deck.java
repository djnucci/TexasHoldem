import java.util.Stack;

public class Deck {
	Stack<Card> deck;

	public Deck(boolean isShuffled) {
		deck = new Stack<Card>();

		for (int i = 0; i <= 12; i++) {
			deck.push(new Card(i, Poker.Suit.HEARTS));
		}

		for (int i = 0; i <= 12; i++) {
			deck.push(new Card(i, Poker.Suit.DIAMONDS));
		}

		for (int i = 0; i <= 12; i++) {
			deck.push(new Card(i, Poker.Suit.CLUBS));
		}

		for (int i = 0; i <= 12; i++) {
			deck.push(new Card(i, Poker.Suit.SPADES));
		}

		if (isShuffled) {
			shuffle();
		}
	}

	//TODO implement shuffle
	private void shuffle() {

	}

	public Card dealCard() {
		return deck.pop();
	}

	public int size() {
		return deck.size();
	}

	@Override
	public String toString() {
		Stack<Card> deckCopy = (Stack<Card>)deck.clone();
		StringBuilder retString = new StringBuilder();

		while (!deckCopy.isEmpty()) {
			retString.append(deckCopy.pop());
			retString.append(", ");
		}

		return retString.toString();
	}

}
