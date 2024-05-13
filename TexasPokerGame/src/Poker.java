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
}
