// Dealer class inheriting from Participant
class Dealer extends Participant {
    @Override
    public boolean wantsToHit() {
        return hand.getValue() < 17;
    }
}