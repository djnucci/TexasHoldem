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

	//TODO create
	public boolean cardPoolContains() {
		return false;	
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
