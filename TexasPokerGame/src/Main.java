public class Main {
    public static void main(String[] args) throws Exception {
        Deck mainDeck = new Deck(false);
        System.out.println(mainDeck);

        System.out.println();
        System.out.println(mainDeck.dealCard() + ", " + mainDeck.size());
    }
}
