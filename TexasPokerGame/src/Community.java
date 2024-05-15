import java.util.Arrays;

public class Community {
	private Card[] communityCards;

	public Community() {
		communityCards = new Card[5];
	}

	public Community(Card[] cards) {
		if (cards.length > 3) {
			System.err.println("Not a valid card array length, ensure your community is less than 5 cards");
			return;
		}

		communityCards = new Card[5];

		for (int i = 0; i < cards.length; i++) {
			communityCards[i] = cards[i];
		}
	}

	@Override
	public String toString() {
		StringBuilder retString = new StringBuilder();

		for (Card c: communityCards) {
			if (c != null){
				retString.append(c);
				retString.append(", ");
			}	
		}

		return retString.toString().substring(0, retString.length()-2);
	}

	public Card[] getCards(){
		return communityCards;
	}

	public Card[] getFlop(){
		return Arrays.copyOf(communityCards, 3);
	}

	public Card getTurn(){
		return communityCards[4];
	}

	public Card getRiver(){
		return communityCards[4];
	}

	/**
	 * Sets the flop community cards
	 * @param Card[] cards - the card array to add (must be 3 in length)
	 * @return true if successful
	 */
	public boolean setFlop(Card[] cards){
		if (cards.length != 3) {
			System.err.println("Not a valid card array length, ensure your flop only contains 3 cards");
			return false;
		}

		communityCards[0] = cards[0];
		communityCards[1] = cards[1];
		communityCards[2] = cards[2];

		return true;
	}

	public void setTurn(Card c){
		communityCards[3] = c;
	}

	public void setRiver(Card c){
		communityCards[4] = c;
	}

	public boolean resetCommunity() {
		communityCards = new Card[5];
		return true;
	}
}
