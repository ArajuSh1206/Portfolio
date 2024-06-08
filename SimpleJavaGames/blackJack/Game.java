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

    public Player getPlayer() {
        return player;
    }

    public Deck getDeck() {
        return deck;
    }


    public Dealer getDealer() {
        return dealer;
    }

    public void play() {
        deck.shuffle();
        player.clearHand();
        dealer.clearHand();
    
        // Deal initial cards
        for (int i = 0; i < 2; i++) {
            player.hit(deck);
            dealer.hit(deck);
        }
    
        // Check for initial Blackjack
        if (dealer.hasBlackjack()) {
            if (player.getHand().getValue() == 21) {
                gui.showMessage("Both have Blackjack! It's a tie!");
            } else {
                gui.showMessage("Dealer has Blackjack! Dealer wins.");
            }
            restartGame();
            return;
        }
    
        if (player.getHand().getValue() == 21) {
            gui.showMessage("Player has Blackjack! Player wins.");
            restartGame();
            return;
        }
    
        // Update GUI with initial hands
        gui.updateHands(player.getHand(), dealer.getHand(), false);
    
        // Offer insurance if dealer shows an Ace
        if (dealer.getHand().getCards().get(1).getValue() == 11) {
            int choice = JOptionPane.showConfirmDialog(null, "Dealer shows an Ace. Do you want insurance?", "Insurance", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                player.setInsurance(true);
            }
        }
    
        // Player's turn
        while (player.wantsToHit()) {
            player.hit(deck);
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            if (player.getHand().getValue() > 21) {
                gui.showMessage("Player busts! Dealer wins.");
                restartGame();
                return;
            }
        }
    
        // Player stands, dealer's turn begins
        while (dealer.wantsToHit()) {
            dealer.hit(deck);
            gui.updateHands(player.getHand(), dealer.getHand(), true);
        }
    
        // Determine the outcome
        int playerValue = player.getHand().getValue();
        int dealerValue = dealer.getHand().getValue();
    
        if (dealerValue > 21 || playerValue > dealerValue) {
            gui.showMessage("Player wins.");
        } else if (playerValue < dealerValue) {
            gui.showMessage("Dealer wins.");
        } else {
            gui.showMessage("It's a tie!");
        }
    
        restartGame();
    }

    void restartGame() {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Restart Game", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            // Reset any necessary game state here
            play(); // Restart the game
        } else {
            System.exit(0); // Exit the application if the player chooses not to play again
        }
    }
}    