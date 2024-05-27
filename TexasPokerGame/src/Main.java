import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Deck mainDeck = new Deck(true);

        Player player = new Player(0, new Card[]{
            new Card(Poker.CardValue.ACE, Poker.Suit.SPADES),
            new Card(Poker.CardValue.KING, Poker.Suit.SPADES)});
        Community community = new Community(new Card[]{
            new Card(Poker.CardValue.SEVEN, Poker.Suit.SPADES),
            new Card(Poker.CardValue.EIGHT, Poker.Suit.SPADES),
            new Card(Poker.CardValue.QUEEN, Poker.Suit.SPADES),
            new Card(Poker.CardValue.TEN, Poker.Suit.SPADES),
            new Card(Poker.CardValue.JACK, Poker.Suit.SPADES),
        });

        System.out.println(Poker.determineHighestHand(player, community));

        Scanner scan = new Scanner(System.in);
        System.out.println("Game started. How many players would like to play?");
        // Player[] players = new Player[scan.nextInt()];
        Player[] players = new Player[6];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i);
        }

        for (int i = 0; i < players.length * 2; i++){
            players[i % players.length].addCard(mainDeck.dealCard());
        }

        Community commCards = new Community(mainDeck.dealFlop());
        commCards.setTurn(mainDeck.burnAndDealOne()); // turn

        commCards.setRiver(mainDeck.burnAndDealOne()); // river
        System.out.println("Here are the community cards after the River...");
        commCards.sortCardPool();
        System.out.println(commCards);

        for (Player p: players) {
            p.sortCardPool();
            System.out.println("Player " + p.getNum() + ":\t" + p + "   \t"+ Poker.determineHighestHand(p, commCards));
        }

        scan.close();

    }
}
