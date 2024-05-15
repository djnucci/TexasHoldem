public class Player {
	private int playerNum;
	private Card[] hand;

	public Player(int playerNum) {
		hand = new Card[2];
		this.playerNum = playerNum;
	}

	public Player(int playerNum, Card[] startHand) {
		hand = startHand;
		this.playerNum = playerNum;
	}

	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();

		for (Card c: hand) {
			retString.append(c);
			retString.append(", \t");
		}

		return retString.toString().substring(0, retString.length()-3);
	}

	public int getNum() {
		return this.playerNum;
	}

	public boolean addCard(Card c) {
		if (hand[0] != null) {
			if (hand[1] != null) {
				return false;
			}
			hand[1] = c;
			return true;
		}
		hand[0] = c;
		return true;
	}

	public boolean resetHand() {
		hand[0] = hand[1] = null;
		return true;
	}
}
