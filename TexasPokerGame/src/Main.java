public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(Poker.Suit.HEARTS.ranksHigherThan(Poker.Suit.SPADES));
        System.out.println(Poker.Suit.CLUBS.ranksLowerThan(Poker.Suit.SPADES));
        System.out.println(Poker.Suit.HEARTS.ranksEqualTo(Poker.Suit.SPADES));
    }
}
