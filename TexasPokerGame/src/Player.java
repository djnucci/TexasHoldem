public class Player extends CardPool{
	private int playerNum;

	public Player(int playerNum) {
		super(2); //2 cards in a hand
		this.playerNum = playerNum;
	}

	public Player(int playerNum, Card[] startHand) {
		super(startHand);
		this.playerNum = playerNum;
	}

	public int getNum() {
		return this.playerNum;
	}

	public boolean addCard(Card c) {
		if (this.getCards()[0] != null) {
			if (this.getCards()[1] != null) {
				return false;
			}
			this.getCards()[1] = c;
			return true;
		}
		this.getCards()[0] = c;
		return true;
	}

	public boolean resetHand() {
		this.getCards()[0] = this.getCards()[1] = null;
		return true;
	}
}
