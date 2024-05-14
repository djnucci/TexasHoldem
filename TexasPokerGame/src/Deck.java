import java.util.ArrayList;
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

	/**
	 * Shuffle the cards using a piles method and randomly selecting which pile to take from and how many piles there are
	 */
	private void shuffle() {
		int numPiles = (int) (Math.random() * 3) + 2;
		ArrayList<Stack<Card>> piles = new ArrayList<Stack<Card>>();
		
		for (int i = 0; i < numPiles; i++) {
			piles.add(new Stack<Card>());
		}

		// algo for creating a random number of piles populated by the orginal deck
		for (int i = 0; !deck.isEmpty(); i++) {
			piles.get(i % numPiles).push(deck.pop());
		}

		// add back to main deck randomly
		pileLoop:
		while (true) {
			// if there are any cards present in an pile, continue
			// if no cards track and if all are empty break out of the whole thing
			boolean cardsPresent = true;
			for (int i = 0; i < numPiles; i++) {
				if (!piles.get(i).isEmpty()) {
					cardsPresent = true;
					break;
				}
				else {
					cardsPresent = false;
				}
			}

			if (!cardsPresent) {
				break pileLoop;
			}

			// randomly choose a pile that has a value and stack into original
			int randomPile;
			do {
				randomPile = (int) (Math.random() * numPiles);
				if (!piles.get(randomPile).isEmpty()) {
					deck.push(piles.get(randomPile).pop());
					break;
				}
			} while (piles.get(randomPile).isEmpty());
		}
	}

	private Card dealCard() {
		return deck.pop();
	}

	public Card[] dealFlop() {
		Card[] flopCards = new Card[3];

		//burn card
		dealCard();
		flopCards[0] = dealCard();
		flopCards[1] = dealCard();
		flopCards[2] = dealCard();

		return flopCards;
	}

	public Card burnAndDealOne() {
		dealCard();
		return dealCard();
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
