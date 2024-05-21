public class WinningHand extends CardPool {

	private Poker.Hand handValue;

	public WinningHand() {
		super(0);
	}

	public WinningHand(Card card) {
		super(card);
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

		return this.handValue + ": " + retString;
	}
}
