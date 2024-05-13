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

	public void setFlop(Card[] cards){
		if (cards.length != 3) {
			System.err.println("Not a valid card array length, ensure your flop only contains 3 cards");
			return;
		}

		communityCards[0] = cards[0];
		communityCards[1] = cards[1];
		communityCards[2] = cards[2];
	}

	public void setTurn(Card c){
		communityCards[3] = c;
	}

	public void setRiver(Card c){
		communityCards[4] = c;
	}
}
