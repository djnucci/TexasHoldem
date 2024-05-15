import java.util.Arrays;

public class Community extends CardPool {

	public Community() {
		super(5);
	}

	public Community(Card[] cards) {
		super(5);
		if (cards.length > 5) {
			System.err.println("Community cards declared as greater than 5.");
		}

		for (int i = 0; i < cards.length; i++) {
			if (!this.setCardByIndex(i, cards[i])) {
				System.err.println("Error setting the " + i+1 + " card in the community constructor.");
			}
		}
	}

	public Card[] getFlop(){
		return Arrays.copyOf(this.getCards(), 3);
	}

	public Card getTurn(){
		return this.getCards()[3];
	}

	public Card getRiver(){
		return this.getCards()[4];
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

		for (int i = 0; i < 3; i++) {
			if (!this.setCardByIndex(i, cards[i])) {
				System.err.println("Error setting the " + i+1 + " card in the flop.");
				return false;
			}
		}

		return true;
	}

	public void setTurn(Card c){
		this.setCardByIndex(3, c);
	}

	public void setRiver(Card c){
		this.setCardByIndex(4, c);
	}

	public boolean resetCommunity() {
		this.setCards(new Card[5]);
		return true;
	}
}
