import java.util.ArrayList;
import java.util.Stack;

public class Deck {
	private Stack<Card> deck;

	/**
	 * Default constructor
	 * @param isShuffled - if the deck should be shuffled after creation
	 */
	public Deck(boolean isShuffled) {
		deck = new Stack<Card>();

		for (int i = Poker.CardValue.TWO.getOrder(); i <= Poker.CardValue.ACE.getOrder(); i++) {
			for (int k = Poker.Suit.HEARTS.getOrder(); k <= Poker.Suit.SPADES.getOrder(); k++) {
				deck.push(new Card(i, k));
			}
		}

		if (isShuffled) {
			shuffle();
		}
	}

	/**
	 * Shuffle the cards using a piles method and randomly selecting which pile to take from and how many piles there are
	 */
	private void shuffle() {
		int randomShuffles = (int) (Math.random() * 10) + 30;
		for (int i = 0; i < randomShuffles; i++) {
			int numPiles = (int) (Math.random() * 7) + 5;
			ArrayList<Stack<Card>> piles = new ArrayList<Stack<Card>>();
			
			for (int k = 0; k < numPiles; k++) {
				piles.add(new Stack<Card>());
			}

			// algo for creating a random number of piles populated by the orginal deck
			for (int k = 0; !deck.isEmpty(); k++) {
				piles.get(k % numPiles).push(deck.pop());
			}

			// invert 50% of the piles
			for (int k = 0; k < numPiles; k++) {
				boolean fiftyFifty = Math.random() < 0.5;

				if (fiftyFifty) {
					piles.add(invertPile(piles.remove(k)));
				}
			}

			// add back to main deck randomly
			pileLoop:
			while (true) {
				// if there are any cards present in an pile, continue
				// if no cards track and if all are empty break out of the whole thing
				boolean cardsPresent = true;
				for (int k = 0; k < numPiles; k++) {
					if (!piles.get(k).isEmpty()) {
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
	}

	/**
	 * Private helper for the shuffle method to invert a pile of cards
	 * @param pile - the pile to be inverted
	 * @return the inverted pile
	 */
	private Stack<Card> invertPile(Stack<Card> pile) {
		Stack<Card> tempStack = new Stack<Card>();

		while (!pile.isEmpty()) {
			tempStack.push(pile.pop());
		}
		
		return tempStack;
	}

	/**
	 * Deal a card from the top of the deck
	 * @return the card
	 */
	public Card dealCard() {
		return deck.pop();
	}

	/**
	 * Burn a card and deal 3 for the flop in the community
	 * @return array of cards dealt
	 */
	public Card[] dealFlop() {
		Card[] flopCards = new Card[3];

		//burn card
		dealCard();
		flopCards[0] = dealCard();
		flopCards[1] = dealCard();
		flopCards[2] = dealCard();

		return flopCards;
	}

	/**
	 * Burn a card and deal one for the turn and river
	 * @return the card delt
	 */
	public Card burnAndDealOne() {
		dealCard();
		return dealCard();
	}

	/**
	 * @return the size of the deck
	 */
	public int size() {
		return deck.size();
	}

	@Override
	public String toString() {
		@SuppressWarnings("unchecked")
		Stack<Card> deckCopy = (Stack<Card>)deck.clone();
		StringBuilder retString = new StringBuilder();

		while (!deckCopy.isEmpty()) {
			retString.append(deckCopy.pop());
			retString.append(", ");
		}

		return retString.toString().substring(0, retString.length()-2) + ": " + size();
	}

}
