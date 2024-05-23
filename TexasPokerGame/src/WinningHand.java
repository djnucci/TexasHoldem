public class WinningHand extends CardPool {

	private Poker.Hand handValue;

	public WinningHand() {
		super(0);
		handValue = Poker.Hand.JUNK;
	}

	public WinningHand(Card card) {
		super(card);
		handValue = Poker.Hand.JUNK;
	}

	public WinningHand(Card card, Poker.Hand ph) {
		super(card);
		this.handValue = ph;
	}

	public WinningHand(Card[] cards, Poker.Hand ph) {
		super(cards);
		this.handValue = ph;
	}

	public WinningHand(CardPool cPool, Poker.Hand ph) {
		super(cPool.getCards());
		handValue = ph;
	}

	public void setHandValue(Poker.Hand handValue) {
		this.handValue = handValue;
	}

	public Poker.Hand getHandValue() {
		return handValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof WinningHand) || size() != ((WinningHand)obj).size()) {
			return false;
		} 

		Card[] compareCards = ((WinningHand)obj).getCards();
		for (int i = 0; i < size(); i++) {
			if (compareCards[i] != getCards()[i]) {
				return false;
			}
		}

		if (this.getHandValue() != ((WinningHand)obj).getHandValue()) {
			return false;
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

		return this.handValue + ": " + (this.size() < 1 ? retString : retString.toString().substring(0, retString.length()-2));
	}
}
