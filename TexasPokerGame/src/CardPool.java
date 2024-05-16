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

	public int numCards() {
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

	public int numCardPoolContainsSuit (Poker.Suit s) {
		int numMatches = 0;
		for (Card card: cards) {
			if (card.getSuit().equals(s)) {
				numMatches++;
			}
		}

		return numMatches;
	}

	public int longestValueRun() {
		int numConsecutive = 1, count = 1;

		for (int i = 1; i < this.cards.length; i++) {
			if (!this.cards[i].equals(this.cards[i - 1])) {
        if (this.cards[i].getCardValue().getNumValue() - this.cards[i - 1].getCardValue().getNumValue() == 1) {
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
        if (this.cards[i].getCardValue().getNumValue() - this.cards[i - 1].getCardValue().getNumValue() == 1 &&
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

	public int countValueOccurrences(Card compareCard) {
		int count = 0;
		for (Card c: this.cards) {
			if (c.getCardValue().ranksEqualTo(compareCard.getCardValue())) {
				count++;
			}
		}

		return count;
	}

	public int maxCountValueOccurrences() {
		int maxCount = 0;

		for (Card c: this.cards) {
			int currCount = countValueOccurrences(c);
			if (currCount > maxCount) {
				maxCount = currCount;
			} 
		}

		return maxCount;
	}

	//FIXME because this uses the compareTo, the ace will only apply to top end straights
	public void sortCardPool() {
		Arrays.sort(this.cards);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CardPool) || getCards().length != ((CardPool)obj).getCards().length) {
			return false;
		} 

		Card[] compareCards = ((CardPool)obj).getCards();
		for (int i = 0; i < numCards(); i++) {
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
