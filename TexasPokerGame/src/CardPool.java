import java.util.Arrays;
import java.util.Collections;

/**
 * Parent class of many classes in the poker game <p>
 * Provides generic behaviours that a pool of cards should possess <p>
 * Ended up being like a wrapper around an array similar to {@link java.util.ArrayList}
 */
public class CardPool {
	private Card[] cards;

	/**
	 * Constructor to instansiate a pool of cards with a set size
	 * @param numCards
	 */
	public CardPool(int numCards) {
		cards = new Card[numCards];
	}

	/**
	 * Constructor to instansiate a pool of cards with a single starting card
	 * @param card
	 */
	public CardPool(Card c) {
		cards = new Card[]{c};
	}

	/**
	 * Constructor to instansiate a pool of cards with a many starting card
	 * @param cards
	 */
	public CardPool(Card[] cards) {
		this.cards = cards;
	}

	public CardPool(Card[] cards, boolean marked) {
		this.cards = cards;

		if (marked) {
			for (Card c: this.cards) {
				c.markCard();
			}
		}
	}

	/**
	 * Constructor to instansiate a pool of cards combining two card arrays
	 * @param pileOne
	 * @param pileTwo
	 */
	public CardPool (Card[] pileOne, Card[] pileTwo) {
		cards = new Card[pileOne.length + pileTwo.length];

		for (int i = 0; i < pileOne.length; i++) {
			setCardByIndex(i, pileOne[i]);
		}
		for (int i = pileOne.length; i < pileOne.length + pileTwo.length; i++) {
			setCardByIndex(i, pileTwo[i-pileOne.length]);
		}
	}

	/**
	 * Constructor to instansiate a pool of cards combining two card pools
	 * @param pileOne
	 * @param pileTwo
	 */
	public CardPool (CardPool pileOne, CardPool pileTwo) {
		cards = new Card[pileOne.getCards().length + pileTwo.getCards().length];

		for (int i = 0; i < pileOne.getCards().length; i++) {
			setCardByIndex(i, pileOne.getCards()[i]);
		}
		for (int i = pileOne.getCards().length; i < pileOne.getCards().length + pileTwo.getCards().length; i++) {
			setCardByIndex(i, pileTwo.getCards()[i-pileOne.getCards().length]);
		}
	}

	/**
	 * cards getter
	 * @return the card array
	 */
	public Card[] getCards() {
		return cards;
	}

	/**
	 * cards setter
	 * @param cards
	 */
	public void setCards(Card[] cards) {
		this.cards = cards;
	}

	/**
	 * Appends a card to the end of the card pool
	 * @param card
	 * @return was successful
	 */
	public boolean appendCard(Card card) {
		try {
			Card[] newCards = new Card[this.size() + 1];

			for (int i = 0; i < this.size(); i++) {
				newCards[i] = this.cards[i];
			}

			newCards[size()] = card;

			this.cards = newCards;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param cards
	 * @return
	 */
	public boolean appendCards(Card[] cards) {
		try {
			Card[] newCards = new Card[this.size() + cards.length];

			for (int i = 0; i < this.size(); i++) {
				newCards[i] = this.cards[i];
			}

			for (int i = this.size(); i < this.size() + cards.length; i++) {
				newCards[i] = cards[i];
			}

			this.cards = newCards;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		}
		return true;
	}

	public boolean remove(Card c) {
		if (this.cardPoolContains(getLargestCard())) {
			Card[] newCards = new Card[getCards().length - 1];

			for (int i = 0, k = 0; i < getCards().length; i++) {
				if (!cards[i].equals(c)) {
					newCards[k] = cards[i];
					k++;
				}
			}

     	setCards(newCards);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Remove all used cards in the pool 
	 * @return the number of cards removed
	 */
	public int removeUsedCards() {
		int numRemoved = 0;

		for (Card c : cards) {
			if (c.isMarked()) {
				this.remove(c);
				numRemoved++;
			}
		}

		return numRemoved;
	} 

	

	public int size() {
		return this.cards.length;
	}

	public boolean setCardByIndex(int index, Card c) {
		cards[index] = c;
		return true;
	}

	public boolean cardPoolContains(Card c) {
		for (Card card: cards) {
			if (card.equals(c)) {
				return true;
			}
		}

		return false;	
	}

	public boolean cardPoolContainsValue(Poker.CardValue cv) {
		for (Card card: cards) {
			if (card.getCardValue().equals(cv)) {
				return true;
			}
		}

		return false;	
	}

	public boolean cardPoolContainsSuit(Poker.Suit s) {
		for (Card card: cards) {
			if (card.getSuit().equals(s)) {
				return true;
			}
		}

		return false;
	}

	public CardPool createPoolOfSuit(Poker.Suit s) {
		Card[] reversedOrderCards = getCards().clone();
		Arrays.sort(reversedOrderCards, Collections.reverseOrder());
		CardPool cPool = new CardPool(0);

		for (Card card: reversedOrderCards) {
			if (card.getSuit().equals(s) && cPool.size() < 5) {
				cPool.appendCard(card);
			}
		}

		return cPool;
	}

	public WinningHand royalFlushHelper() {
		int numRoyalRun = 0;
		WinningHand winningHand = new WinningHand();

		for (int k = Poker.Suit.HEARTS.getOrder(); k <= Poker.Suit.SPADES.getOrder(); k++) {
			numRoyalRun = 0;
			for (int i = Poker.CardValue.TEN.getOrder(); i <= Poker.CardValue.ACE.getOrder(); i++) {
				Card currCard = new Card(i, k);
				if (this.cardPoolContains(currCard)) {
					winningHand.appendCard(currCard);
					numRoyalRun++;
				}
				else {
					break;
				}
			}
			if (numRoyalRun >= 5) {
				winningHand.setHandValue(Poker.Hand.ROYAL_FLUSH);
				return winningHand;
			}
		}

		return winningHand;
	}

	public WinningHand straightFlushHelper() {
		int numConsecutive = 1, count = 1;
		WinningHand winningHand = new WinningHand();

		for (int i = this.cards.length - 1; i >= 1; i--) {
			if (!this.cards[i].equals(this.cards[i - 1])) {
        if ((this.cards[i].getCardValue().getOrder() - this.cards[i - 1].getCardValue().getOrder() == 1 &&
						this.cards[i].getSuit().ranksEqualTo(this.cards[i - 1].getSuit())) 
						&& count < 5) {
							winningHand.appendCard(this.cards[i]);
							count += 1;
        }
        else {
          numConsecutive = Math.max(count, numConsecutive);
					if (numConsecutive == 5) {
						winningHand.appendCard(this.cards[i]); // needed because append if statement does a look ahead
						winningHand.setHandValue(Poker.Hand.STRAIGHT_FLUSH);
						return winningHand;
					}
          count = 1;
        }
      }
		}

		return new WinningHand();
	}

	public WinningHand straightHelper() {
		int numConsecutive = 1, count = 1;
		WinningHand winningHand = new WinningHand();

		for (int i = this.cards.length - 1; i >= 1; i--) {
			if (!this.cards[i].ranksEqualTo(this.cards[i - 1])) {
        if (Math.abs(this.cards[i].getCardValue().getOrder() - this.cards[i - 1].getCardValue().getOrder()) == 1 
						&& count < 5) {
							winningHand.appendCard(this.cards[i]);
							count += 1;
        }
        else {
          numConsecutive = Math.max(count, numConsecutive);
					if (numConsecutive == 5) {
						winningHand.appendCard(this.cards[i]); // needed because append if statement does a look ahead
						winningHand.setHandValue(Poker.Hand.STRAIGHT);
						return winningHand;
					}
          count = 1;
        }
      }
		}

		return new WinningHand();
	}

	public WinningHand flushHelper() {
		CardPool[] suitPools = new CardPool[4];
		for (int i = Poker.Suit.HEARTS.getOrder(); i < Poker.Suit.SPADES.getOrder(); i++) {
			suitPools[i] = createPoolOfSuit(Poker.getSuit(i));
			if (suitPools[i].size() == 5) {
				return new WinningHand(suitPools[i], Poker.Hand.FLUSH);
			}
		}


		return new WinningHand();
	}

	public CardPool poolOccurences(Card compareCard) {
		CardPool cPool = new CardPool(0);

		for (Card c: this.cards) {
			if (c.getCardValue().ranksEqualTo(compareCard.getCardValue())) {
				cPool.appendCard(c);
			}
		}

		return cPool;
	}

	public WinningHand matchHandWithMaxNumOccurences() {
		Card[] reversedOrderCards = getCards().clone();
		Arrays.sort(reversedOrderCards, Collections.reverseOrder());

		for (Card c: reversedOrderCards) {
			CardPool cPool = poolOccurences(c);
			return new WinningHand(cPool, Poker.getHand(cPool.size()), true);
		}

		return new WinningHand();
	}

	// FIXME
	public WinningHand checkFullHouseTwoPair() {
		WinningHand winningHand;

		// check for a three of a kind, making sure to mark if the cards are used
		if ((winningHand = this.matchHandWithMaxNumOccurences()).getHandValue() == Poker.Hand.THREE_OF_A_KIND) {

		}

		return new WinningHand();
	}

	public Card getLargestCard() {
		return this.cards[this.cards.length-1];
	}

	//FIXME because this uses the compareTo, the ace will only apply to top end straights
	public void sortCardPool() {
		Arrays.sort(this.cards);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CardPool) || size() != ((CardPool)obj).size()) {
			return false;
		} 

		Card[] compareCards = ((CardPool)obj).getCards();
		for (int i = 0; i < size(); i++) {
			if (compareCards[i] != cards[i]) {
				return false;
			}
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();

		for (Card c: this.getCards()) {
			if (c != null){
				retString.append(c);
				retString.append(", ");
			}	
		}

		return retString.toString().substring(0, retString.length()-2);
	}

	
}
