import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Deck mainDeck = new Deck(true);

        Scanner scan = new Scanner(System.in);
        System.out.println("Game started, how many players would like to play?");
        Player[] players = new Player[scan.nextInt()];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(i);
        }

        for (int i = 0; i < players.length * 2; i++){
            players[i % players.length].addCard(mainDeck.dealCard());
        }

        Community commCards = new Community(mainDeck.dealFlop());
        commCards.setTurn(mainDeck.burnAndDealOne()); // turn

        commCards.setRiver(mainDeck.burnAndDealOne()); // river
        System.out.println("Here are the community cards after the River");
        System.out.println(commCards);

        for (Player p: players) {
            System.out.println("Player " + p.getNum() + ": " + p + "\t\t"+ Poker.determineHighestHand(p, commCards));
        }

    }
}
