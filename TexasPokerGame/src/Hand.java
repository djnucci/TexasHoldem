import java.util.ArrayList;

public class Hand {
	private int playerNum;
	private ArrayList<Card> hand;

	public Hand(int playerNum) {
		hand  = new ArrayList<Card>();
		this.playerNum = playerNum;
	}

	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();

		for (Card c: hand) {
			retString.append(c);
			retString.append(", ");
		}

		return retString.toString().substring(0, retString.length()-2);
	}

	public int getNum() {
		return this.playerNum;
	}

	public boolean addCard(Card c) {
		if (hand.size() >= 2) {
			return false;
		}

		hand.add(c);
		return true;
	}

	public boolean resetHand() {
		hand  = new ArrayList<Card>();
		return true;
	}
}
