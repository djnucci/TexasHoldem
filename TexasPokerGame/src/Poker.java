/**
 * Class that contains the majority of the rules and elements of poker
 */
public class Poker {

	/**
	 * The suits of the cards in poker <p>
	 * Contains the order of precedence for each flush
	 */
	public enum Suit {
		NULL(-1), HEARTS(0), DIAMONDS(1), CLUBS(2), SPADES(3);

		private final int ORDER;
		private Suit (int order) {
			this.ORDER = order;
		}

		public boolean ranksHigherThan(Suit s) {
			return this.ORDER > s.ORDER;
		}
		
		public boolean ranksLowerThan(Suit s) {
			return this.ORDER < s.ORDER;
		}
		
		public boolean ranksEqualTo(Suit s) {
			return this.ORDER == s.ORDER;
		}

		public int getOrder() {
			return ORDER;
		}
	}

	/**
	 * The different numbers / names of all the cards in poker (minus joker) <p>
	 * Each has two values (one is used only for aces and should probably be removed) <p>
	 * Contains the order of precedence for each hand that requires it 
	 */
	public enum CardValue {
		NULL(-1,-1,-1), ACE(1, 11, 12), TWO(2, 0, 0), THREE(3, 0, 1), FOUR(4, 0, 2), 
		FIVE(5, 0, 3), SIX(6, 0, 4), SEVEN(7, 0, 5), EIGHT(8, 0, 6), NINE(9, 0, 7), 
		TEN(10, 0, 8), JACK(10, 0, 9), QUEEN(10, 0, 10), KING(10, 0, 11);

		private final int VALUE, ALT_VALUE, ORDER;
		private CardValue (int val, int altVal, int ord) {
			this.VALUE = val;
			this.ALT_VALUE = altVal;
			this.ORDER = ord;
		}

		public boolean ranksHigherThan(CardValue c) {
			return this.ORDER > c.ORDER;
		}
		
		public boolean ranksLowerThan(CardValue c) {
			return this.ORDER < c.ORDER;
		}
		
		public boolean ranksEqualTo(CardValue c) {
			return this.ORDER == c.ORDER;
		}


		public int getNumValue() {
			return VALUE;
		}

		public int getAltValue() {
			return ALT_VALUE;
		}

		public int getOrder() {
			return ORDER;
		}
	}

	/**
	 * All the valid hands in poker plus junk for a null value <p>
	 * Ranked by which is the better hand
	 */
	public enum Hand {
		JUNK(0), HIGH_CARD(1), PAIR(2), TWO_PAIR(3), THREE_OF_A_KIND(4), STRAIGHT(5), 
		FLUSH(6), FULL_HOUSE(7), FOUR_OF_A_KIND(8), STRAIGHT_FLUSH(9), ROYAL_FLUSH(10);

		private final int RANK;
		private Hand(int rank) {
			this.RANK = rank;
		}
		public int getRank() {
			return RANK;
		}
	}

	/**
	 * Get the enum of card value based on it's order
	 * @param order - the coder created order value
	 * @return card value enum
	 */
	public static Poker.CardValue getCardValue(int order) {
		switch (order) {
			case 0: // two
				return Poker.CardValue.TWO;
			case 1: // three
				return Poker.CardValue.THREE;
			case 2: // four
				return Poker.CardValue.FOUR;
			case 3: // five
				return Poker.CardValue.FIVE;
			case 4: // six
				return Poker.CardValue.SIX;
			case 5: // seven
				return Poker.CardValue.SEVEN;
			case 6: // eight
				return Poker.CardValue.EIGHT;
			case 7: // nine
				return Poker.CardValue.NINE;
			case 8: // ten
				return Poker.CardValue.TEN;
			case 9: // jack
				return Poker.CardValue.JACK;
			case 10: // queen
				return Poker.CardValue.QUEEN;
			case 11: // king
				return Poker.CardValue.KING;
			case 12: // ace
				return Poker.CardValue.ACE;
			default: // invalid case
				return Poker.CardValue.NULL;
			}
	}

	
	/**
	 * Get the enum of suit based on it's order
	 * @param order - the coder created order value
	 * @return suit enum
	 */
	public static Poker.Suit getSuit(int order) {
		switch (order) {
			case 0: // hearts
				return Poker.Suit.HEARTS;
			case 1: // diamonds
				return Poker.Suit.DIAMONDS;
			case 2: // clubs
				return Poker.Suit.CLUBS;
			case 3: // spades
				return Poker.Suit.SPADES;
			default: // invalid case
				return Poker.Suit.NULL;
		}
	}

	/**
	 * Get the enum of hand value based on it's order
	 * @param order - the coder created order value
	 * @return hand enum
	 */
	public static Poker.Hand getHand(int numOccurences) {
		switch (numOccurences) {
			case 1:
				return Poker.Hand.HIGH_CARD;
			case 2:
				return Poker.Hand.PAIR;
			case 3:
				return Poker.Hand.THREE_OF_A_KIND;
			case 4:
				return Poker.Hand.FOUR_OF_A_KIND;
			default:
				return Poker.Hand.JUNK;
		}
	}


	/**
	 * Evaluate what the winning hand based on a Player's hand and the Community pile
	 * @param hand - Player hand
	 * @param commCards - Community card pile
	 * @return Winning pile of cards
	 */
	public static WinningHand determineHighestHand(Player hand, Community commCards) {
		CardPool cPool = new CardPool(hand, commCards);
		cPool.sortCardPool();
		WinningHand winningHand;

		// ROYAL FLUSH
		if ((winningHand = cPool.royalFlushHelper()).getHandValue() == Poker.Hand.ROYAL_FLUSH) {
			return winningHand; 
		}

		// STRAIGHT FLUSH
		if ((winningHand = cPool.straightFlushHelper()).getHandValue() == Poker.Hand.STRAIGHT_FLUSH) {
			return winningHand;
		}

		// FOUR OF A KIND
		if ((winningHand = cPool.matchHandWithMaxNumOccurences()).getHandValue() == Poker.Hand.FOUR_OF_A_KIND) {
			return winningHand;
		}

		// FULL HOUSE TODO
		if ((winningHand = cPool.checkFullHouseTwoPair()).getHandValue() == Poker.Hand.FULL_HOUSE) {
			return winningHand;
		}

		// FLUSH
		if ((winningHand = cPool.flushHelper()).getHandValue() == Poker.Hand.FLUSH) {
			return winningHand;
		}

		// STRAIGHT
		if ((winningHand = cPool.straightHelper()).getHandValue() == Poker.Hand.STRAIGHT) {
			return winningHand;
		}

		// THREE OF A KIND 
		if ((winningHand = cPool.matchHandWithMaxNumOccurences()).getHandValue() == Poker.Hand.THREE_OF_A_KIND) {
			return winningHand;
		}

		// TWO PAIR TODO
		if ((winningHand = cPool.checkFullHouseTwoPair()).getHandValue() == Poker.Hand.FULL_HOUSE) {
			return winningHand;
		}
		
		// PAIR 
		if ((winningHand = cPool.matchHandWithMaxNumOccurences()).getHandValue() == Poker.Hand.PAIR) {
			return winningHand;
		}

		return new WinningHand(hand.getLargestCard(), Poker.Hand.HIGH_CARD);
	} 
}
