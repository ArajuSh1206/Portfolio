import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Player extends Participant {
    private boolean doubledDown;
    private boolean split;
    private Hand splitHand;
    private boolean insurance;

    public Player() {
        super();
        doubledDown = false;
        split = false;
        splitHand = null;
        insurance = false;
    }

    @Override
    public boolean wantsToHit() {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to hit?", "Player's Turn", JOptionPane.YES_NO_OPTION);
        return choice == JOptionPane.YES_OPTION;
    }

    private boolean hasHitInRound;

public boolean hasHitInRound() {
    return hasHitInRound;
}

public void setHasHitInRound(boolean hasHitInRound) {
    this.hasHitInRound = hasHitInRound;
}


    public void doubleDown() {
        doubledDown = true;
    }

    public boolean hasDoubledDown() {
        return doubledDown;
    }

    public void splitHand() {
        if (canSplit()) {
            split = true;
            splitHand = new Hand();
            splitHand.addCard(hand.getCards().remove(1));
        } else {
            System.out.println("Cannot split: the conditions for splitting are not met.");
        }
    }

    public void clearHand() {
        hand.clear();
        if (splitHand != null) {
            splitHand.clear();
        }
        splitHand = null;
        split = false;
        doubledDown = false;
        insurance = false;
    }

    public boolean canDoubleDown() {
        if (hand.getCards().size() == 2) {
            int handValue = hand.getValue();
            return handValue >= 9 && handValue <= 11;
        }
        return false;
    }

    public boolean canSplit() {
        if (hand.getCards().size() == 2) {
            Card card1 = hand.getCards().get(0);
            Card card2 = hand.getCards().get(1);
            return card1.getRank().equals(card2.getRank());
        }
        return false;
    }

    public boolean hasBlackjack() {
        boolean mainHandBlackjack = hand.getValue() == 21 && hand.getCards().size() == 2;
        boolean splitHandBlackjack = split && splitHand != null && splitHand.getValue() == 21 && splitHand.getCards().size() == 2;
        return mainHandBlackjack || splitHandBlackjack;
    }

    public boolean hasSplit() {
        return split;
    }

    public Hand getSplitHand() {
        return splitHand;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public boolean hasInsurance() {
        return insurance;
    }

    public void hitOnSplitHand(Deck deck) {
        if (splitHand != null) {
            splitHand.addCard(deck.draw());
        }
    }

    public List<Hand> getAllHands() {
        List<Hand> hands = new ArrayList<>();
        hands.add(hand);
        if (split && splitHand != null) {
            hands.add(splitHand);
        }
        return hands;
    }
}
