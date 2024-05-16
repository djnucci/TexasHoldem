public class Poker {
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
	}

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

	public static Hand determineHighestHand(Player p, Community c) {
		CardPool cp = new CardPool(p.getCards(), c.getCards());
		
		//FIXME this is gross but it works
		if ((cp.cardPoolContains(new Card(Poker.CardValue.ACE, 	Poker.Suit.SPADES)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.KING, 	Poker.Suit.SPADES)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.QUEEN, Poker.Suit.SPADES)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.JACK, 	Poker.Suit.SPADES)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.TEN, 	Poker.Suit.SPADES))) || 

				(cp.cardPoolContains(new Card(Poker.CardValue.ACE, 	Poker.Suit.CLUBS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.KING, 	Poker.Suit.CLUBS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.QUEEN, Poker.Suit.CLUBS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.JACK, 	Poker.Suit.CLUBS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.TEN, 	Poker.Suit.CLUBS))) ||

				(cp.cardPoolContains(new Card(Poker.CardValue.ACE, 	Poker.Suit.DIAMONDS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.KING, 	Poker.Suit.DIAMONDS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.QUEEN, Poker.Suit.DIAMONDS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.JACK, 	Poker.Suit.DIAMONDS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.TEN, 	Poker.Suit.DIAMONDS))) ||

				(cp.cardPoolContains(new Card(Poker.CardValue.ACE, 	Poker.Suit.HEARTS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.KING, 	Poker.Suit.HEARTS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.QUEEN, Poker.Suit.HEARTS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.JACK, 	Poker.Suit.HEARTS)) &&
				cp.cardPoolContains(new Card(Poker.CardValue.TEN, 	Poker.Suit.HEARTS)))) {
			return Poker.Hand.ROYAL_FLUSH;
		}

		if (cp.longestValueRun() >= 5 &&
				(cp.numCardPoolContainsSuit(Poker.Suit.SPADES) 	>= 5 || 
				cp.numCardPoolContainsSuit(Poker.Suit.CLUBS) 		>= 5 ||
				cp.numCardPoolContainsSuit(Poker.Suit.DIAMONDS) >= 5 ||
				cp.numCardPoolContainsSuit(Poker.Suit.HEARTS) 	>= 5)) {
			return Poker.Hand.STRAIGHT_FLUSH;
		}

		if (cp.numCardPoolContainsSuit(Poker.Suit.SPADES) 	>= 5 || 
				cp.numCardPoolContainsSuit(Poker.Suit.CLUBS) 		>= 5 ||
				cp.numCardPoolContainsSuit(Poker.Suit.DIAMONDS) >= 5 ||
				cp.numCardPoolContainsSuit(Poker.Suit.HEARTS) 	>= 5) {
			return Poker.Hand.FLUSH;
		}

		if (cp.longestValueRun() >= 5) {
			return Poker.Hand.STRAIGHT;
		}

		return Hand.JUNK;
	} 
}
