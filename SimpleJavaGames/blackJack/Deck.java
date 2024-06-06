// Deck.java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    private static final String[] SUITS = {"Hearts", "Diamonds", "Spades", "Clubs"};
    private static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private static final int[] VALUES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};

    public Deck() {
        cards = new ArrayList<>();
        for (int i = 0; i < SUITS.length; i++) {
            for (int j = 0; j < RANKS.length; j++) {
                cards.add(new Card(SUITS[i], RANKS[j], VALUES[j]));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        return cards.remove(cards.size() - 1);
    }
}