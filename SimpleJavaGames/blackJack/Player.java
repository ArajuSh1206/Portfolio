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
        if (hand.getCards().size() == 2) {
            Card card1 = hand.getCards().get(0);
            Card card2 = hand.getCards().get(1);
            if (card1.getRank() == card2.getRank()) {
                split = true;
                splitHand = new Hand();
                splitHand.addCard(hand.getCards().remove(1));
            } else {
                System.out.println("Cannot split: the two cards are not of the same rank.");
            }
        } else {
            System.out.println("Cannot split: hand does not have exactly two cards.");
        }
        
    }

    public void clearHand() {
        hand.clear();
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
