

public class Dealer extends Participant {
    @Override
    public boolean wantsToHit() {
        return hand.getValue() < 17; // Dealer hits until reaching a value of 17 or more
    }

    public boolean hasSplit() {
        return hand.getValue() == 11 && hand.getCards().size() == 2;
    }

    public boolean hasStand() {
        return hand.getValue() >= 17;
    }

    public boolean hasBlackjack() {
        return hand.getValue() == 21 && hand.getCards().size() == 2;
    }
    public void clearHand() {
        hand.clear();
    }
    
}