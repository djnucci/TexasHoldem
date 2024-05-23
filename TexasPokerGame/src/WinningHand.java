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

	public void setHandValue(Poker.Hand handValue) {
		this.handValue = handValue;
	}

	public Poker.Hand getHandValue() {
		return handValue;
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
