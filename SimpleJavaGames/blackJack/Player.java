import javax.swing.JOptionPane;

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

    public void doubleDown() {
        doubledDown = true;
    }

    public boolean hasDoubledDown() {
        return doubledDown;
    }

    public void splitHand() {
        split = true;
        splitHand = new Hand();
        splitHand.addCard(hand.getCards().remove(1));
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
}
