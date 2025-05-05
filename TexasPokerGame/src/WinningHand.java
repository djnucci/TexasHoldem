/**
 * The WinningHand class extends the CardPool class to represent a winning poker
 * hand.
 * It includes the hand value (e.g., full house, flush) in addition to the
 * cards.
 */
public class WinningHand extends CardPool {

	// The hand value of this WinningHand instance.
	private Poker.Hand handValue;

	/**
	 * Default constructor for WinningHand. Initializes with an empty card pool and
	 * a JUNK hand value.
	 */
	public WinningHand() {
		super(0);
		handValue = Poker.Hand.JUNK;
	}

	/**
	 * Constructor that initializes WinningHand with a single card and a JUNK hand
	 * value.
	 * 
	 * @param card the single card to initialize the hand with.
	 */
	public WinningHand(Card card) {
		super(card);
		handValue = Poker.Hand.JUNK;
	}

	/**
	 * Constructor that initializes WinningHand with a single card and a specified
	 * hand value.
	 * 
	 * @param card the single card to initialize the hand with.
	 * @param ph   the specified hand value.
	 */
	public WinningHand(Card card, Poker.Hand ph) {
		super(card);
		this.handValue = ph;
	}

	/**
	 * Constructor that initializes WinningHand with an array of cards and a
	 * specified hand value.
	 * 
	 * @param cards the array of cards to initialize the hand with.
	 * @param ph    the specified hand value.
	 */
	public WinningHand(Card[] cards, Poker.Hand ph) {
		super(cards);
		this.handValue = ph;
	}

	/**
	 * Constructor that initializes WinningHand with a CardPool and a specified hand
	 * value.
	 * 
	 * @param cPool the CardPool to initialize the hand with.
	 * @param ph    the specified hand value.
	 */
	public WinningHand(CardPool cPool, Poker.Hand ph) {
		super(cPool.getCards());
		handValue = ph;
	}

	public WinningHand(CardPool cPool, Poker.Hand ph, boolean marked) {
		super(cPool.getCards(), marked);
		handValue = ph;
	}

	/**
	 * Sets the hand value for this WinningHand instance.
	 * 
	 * @param handValue the new hand value.
	 */
	public void setHandValue(Poker.Hand handValue) {
		this.handValue = handValue;
	}

	/**
	 * Gets the hand value of this WinningHand instance.
	 * 
	 * @return the current hand value.
	 */
	public Poker.Hand getHandValue() {
		return handValue;
	}

	/**
	 * Compares this WinningHand instance to another object for equality.
	 * 
	 * @param obj the object to compare against.
	 * @return true if the object is a WinningHand with the same cards and hand
	 *         value; false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof WinningHand) || size() != ((WinningHand) obj).size()) {
			return false;
		}

		Card[] compareCards = ((WinningHand) obj).getCards();
		for (int i = 0; i < size(); i++) {
			if (compareCards[i] != getCards()[i]) {
				return false;
			}
		}

 		if (this.getHandValue() != ((WinningHand) obj).getHandValue()) {
			return false;
		}

		return true;
	}

	/**
	 * Returns a string representation of the WinningHand instance.
	 * The string includes the hand value and a comma-separated list of cards.
	 * 
	 * @return the string representation of the WinningHand.
	 */
	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();

		for (Card c : this.getCards()) {
			if (c != null) {
				retString.append(c);
				retString.append(", ");
			}
		}

		// Remove the trailing comma and space, if any, and return the formatted string.
		return this.handValue + ": "
				+ (this.size() < 1 ? retString : retString.toString().substring(0, retString.length() - 2));
	}
}
