import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getValue() {
        int value = 0;
        int aceCount = 0;
    
        for (Card card : cards) {
            if (card != null) { // Null check added here
                value += card.getValue();
                if (card.toString().contains("Ace")) {
                    aceCount++;
                }
            }
        }
    
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
    
        return value;
    }    
    

    public void clear() {
        cards.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : cards) {
            sb.append(card).append(" ");
        }
        return sb.toString().trim();
    }

    public String toString(boolean showAllDealerCards) {
        if (showAllDealerCards) {
            return toString();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(cards.get(0)).append(" ?");
            return sb.toString();
        }
    }
}