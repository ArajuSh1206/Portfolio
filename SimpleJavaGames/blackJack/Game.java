import javax.swing.JOptionPane;

public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private GameGUI gui;

    public Game(GameGUI gui) {
        this.gui = gui;
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
    }

    public void play() {
        for (int i = 0; i < 2; i++) {
            player.hit(deck);
            dealer.hit(deck);
        }

        // Check for Blackjack
        if (dealer.hasBlackjack()) {
            if (player.getHand().getValue() == 21) {
                gui.showMessage("Both have Blackjack! It's a tie!");
            } else {
                gui.showMessage("Dealer has Blackjack! Dealer wins.");
            }
            return;
        }

        if (player.getHand().getValue() == 21) {
            gui.showMessage("Player has Blackjack! Player wins.");
            return;
        }

        gui.updateHands(player.getHand(), dealer.getHand(), false);

        // Offer insurance if dealer shows an Ace
        if (dealer.getHand().getCards().get(1).getValue() == 11) {
            int choice = JOptionPane.showConfirmDialog(null, "Dealer shows an Ace. Do you want insurance?", "Insurance", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                player.setInsurance(true);
            }
        }

        while (player.wantsToHit()) {
            player.hit(deck);
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            if (player.getHand().getValue() > 21) {
                gui.showMessage("Player busts! Dealer wins.");
                return;
            }
        }

        // Dealer's turn
        while (dealer.wantsToHit()) {
            dealer.hit(deck);
        }

        gui.updateHands(player.getHand(), dealer.getHand(), true);

        int playerValue = player.getHand().getValue();
        int dealerValue = dealer.getHand().getValue();

        if (dealerValue > 21) {
            gui.showMessage("Dealer busts! Player wins.");
        } else if (dealerValue > playerValue) {
            gui.showMessage("Dealer wins.");
        } else if (dealerValue < playerValue) {
            gui.showMessage("Player wins.");
        } else {
            gui.showMessage("It's a tie!");
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Deck getDeck() {
        return deck;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
