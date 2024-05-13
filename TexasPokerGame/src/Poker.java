public class Poker {
	public enum Suit {
		HEARTS(0), DIAMONDS(1), CLUBS(2), SPADES(3);

		private int order;
		private Suit (int order) {
			this.order = order;
		}

		public boolean ranksHigherThan(Suit s) {
			return this.order > s.order;
		}
		
		public boolean ranksLowerThan(Suit s) {
			return this.order < s.order;
		}
		
		public boolean ranksEqualTo(Suit s) {
			return this.order == s.order;
		}
	}

	public enum Card {
		ACE(1, 11, 12), TWO(2, 0, 0), THREE(3, 0, 1), FOUR(4, 0, 2), 
		FIVE(5, 0, 3), SIX(6, 0, 4), SEVEN(7, 0, 5), EIGHT(8, 0, 6), NINE(9, 0, 7), 
		TEN(10, 0, 8), JACK(10, 0, 9), QUEEN(10, 0, 10), KING(10, 0, 11);

		private int value, altValue, order;
		private Card (int val, int altVal, int ord) {
			this.value = val;
			this.altValue = altVal;
			this.order = ord;
		}

		public boolean ranksHigherThan(Card c) {
			return this.order > c.order;
		}
		
		public boolean ranksLowerThan(Card c) {
			return this.order < c.order;
		}
		
		public boolean ranksEqualTo(Card c) {
			return this.order == c.order;
		}


		public int getValue() {
			return value;
		}

		public int getAltValue() {
			return altValue;
		}
	}
}
