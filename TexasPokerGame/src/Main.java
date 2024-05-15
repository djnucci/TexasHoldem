import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Deck mainDeck = new Deck(true);

        Scanner scan = new Scanner(System.in);
        System.out.println("Game started, how many players would like to play?");
        Hand[] players = new Hand[scan.nextInt()];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Hand(i);
        }

        for (int i = 0; i < players.length * 2; i++){
            players[i % players.length].addCard(mainDeck.dealCard());
        }

        Community commCards = new Community(mainDeck.dealFlop());

        System.out.println("Here are the community cards after the flop");
        System.out.println(commCards);

        commCards.setTurn(mainDeck.burnAndDealOne());
        System.out.println("Here are the community cards after the turn");
        System.out.println(commCards);

        commCards.setRiver(mainDeck.burnAndDealOne());
        System.out.println("Here are the community cards after the River");
        System.out.println(commCards);

        for (Hand h: players) {
            System.out.print("Player " + h.getNum() + ": ");
            System.out.println(h);
        }

    }
}
