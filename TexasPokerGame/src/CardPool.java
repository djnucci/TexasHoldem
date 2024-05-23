import java.util.Arrays;

public class CardPool {
	private Card[] cards;

	public CardPool(int numCards) {
		cards = new Card[numCards];
	}

	public CardPool(Card c) {
		cards = new Card[]{c};
	}

	public CardPool(Card[] cards) {
		this.cards = cards;
	}

	public CardPool (Card[] pileOne, Card[] pileTwo) {
		cards = new Card[pileOne.length + pileTwo.length];

		for (int i = 0; i < pileOne.length; i++) {
			setCardByIndex(i, pileOne[i]);
		}
		for (int i = pileOne.length; i < pileOne.length + pileTwo.length; i++) {
			setCardByIndex(i, pileTwo[i-pileOne.length]);
		}
	}

	public Card[] getCards() {
		return cards;
	}

	public void setCards(Card[] cards) {
		this.cards = cards;
	}

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

	public int numCardPoolContainsSuit(Poker.Suit s) {
		int numMatches = 0;
		for (Card card: cards) {
			if (card.getSuit().equals(s)) {
				numMatches++;
			}
		}

		return numMatches;
	}

	public int maxNumCardPoolContainsSuit() {
		int maxSuitVal = 1;
		for (int i = Poker.Suit.HEARTS.getOrder(); i < Poker.Suit.SPADES.getOrder(); i++) {
			int currNumSuitVal = numCardPoolContainsSuit(Poker.getSuit(i));
			if (currNumSuitVal > maxSuitVal) {
				maxSuitVal = currNumSuitVal;
			}
		}

		return maxSuitVal;
	}

	public int longestValueRun() {
		int numConsecutive = 1, count = 1;

		for (int i = 1; i < this.cards.length; i++) {
			if (!this.cards[i].equals(this.cards[i - 1])) {
        if (this.cards[i].getCardValue().getOrder() - this.cards[i - 1].getCardValue().getOrder() == 1) {
          count += 1;
        }
        else {
          numConsecutive = Math.max(count, numConsecutive);
          count = 1;
        }
      }
		}

		return Math.max(count, numConsecutive);
	}

	public int longestValueAndSuitRun() {
		int numConsecutive = 1, count = 1;

		for (int i = 1; i < this.cards.length; i++) {
			if (!this.cards[i].equals(this.cards[i - 1])) {
        if (this.cards[i].getCardValue().getOrder() - this.cards[i - 1].getCardValue().getOrder() == 1 &&
						this.cards[i].getSuit().ranksEqualTo(this.cards[i - 1].getSuit())) {
          count += 1;
        }
        else {
          numConsecutive = Math.max(count, numConsecutive);
          count = 1;
        }
      }
		}

		return Math.max(count, numConsecutive);
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

	public CardPool poolOccurences(Card compareCard) {
		CardPool cPool = new CardPool(0);

		for (Card c: this.cards) {
			if (c.getCardValue().ranksEqualTo(compareCard.getCardValue())) {
				cPool.appendCard(c);
			}
		}

		return cPool;
	}

	public WinningHand handOfNumOccurences(int numOccurences) {
		for (Card c: this.cards) {
			CardPool cPool = poolOccurences(c);
			if (cPool.size() == numOccurences) {
				return new WinningHand(cPool, Poker.getHand(numOccurences));
			} 
		}

		return new WinningHand();
	}

	@Deprecated
	public int maxCountValueOccurrences() {
		int maxCount = 0;

		for (Card c: this.cards) {
			// int currCount = countValueOccurrences(c);
			int currCount = 0;
			if (currCount > maxCount) {
				maxCount = currCount;
			} 
		}

		return maxCount;
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
