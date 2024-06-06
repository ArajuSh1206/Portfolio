public abstract class Participant {
    protected Hand hand;

    public Participant() {
        hand = new Hand();
    }

    public void hit(Deck deck) {
        hand.addCard(deck.draw());
    }

    public Hand getHand() {
        return hand;
    }

    public abstract boolean wantsToHit();
}
