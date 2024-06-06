public class Dealer extends Participant {
    @Override
    public boolean wantsToHit() {
        return hand.getValue() < 17;
    }

    public boolean hasBlackjack() {
        return hand.getValue() == 21 && hand.getCards().size() == 2;
    }
}
