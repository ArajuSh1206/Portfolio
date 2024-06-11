import javax.swing.*;
import java.util.List;

public class Game {
    private Deck deck;
    private Player player;
    private Dealer dealer;
    private GameGUI gui;

    public Game(GameGUI gui) {
        this.gui = gui;
        this.deck = new Deck();
        this.player = new Player();
        this.dealer = new Dealer();
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

    public void play() {
        deck.shuffle();
        dealer.clearHand();
        player.clearHand();

        // Initial deal
        player.hit(deck); // Player gets the first card
        player.hit(deck); // Player gets the second card
        dealer.hit(deck); // Dealer gets the first card
        dealer.hit(deck); // Dealer gets the second card

        // Check for initial Blackjack
        if (player.hasBlackjack()) {
            if (player.hasBlackjack() || dealer.hasBlackjack()) {
                handleGameEnd("Initial Blackjack! No winner.");
            } else {
                gui.updateHands(player.getHand(), dealer.getHand(), false);
                gui.updateButtonStates(true, true, player.canDoubleDown(), player.canSplit());
            }        
        } else if (dealer.hasBlackjack()) {
            handleGameEnd("Dealer has Blackjack! Dealer wins.");
        } else {
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            gui.updateButtonStates(true, true, player.canDoubleDown(), player.canSplit());
        }
    }

    // Method to update the GUI with current hands
    public void updateGUI(Hand playerHand, Hand dealerHand, boolean showAllDealerCards) {
        gui.updateHands(playerHand, dealerHand, showAllDealerCards);
    }

    public void playerHits() {
        if (!isGameOver() && player.getHand().getValue() < 21) {
            player.hit(deck); // Player takes one card
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            int playerValue = player.getHand().getValue();
            if (playerValue > 21) {
                handleGameEnd("Player busts! Dealer wins.");
            } else if (playerValue == 21) {
                handleGameEnd("Player has 21! Player Wins.");
            }
        }
    }

    public void playerStands() {
        if (!isGameOver()) {
            dealerPlay();
        }
    }

    public void playerDoubleDowns() {
        if (!isGameOver() && player.canDoubleDown() && !player.hasHitInRound() && player.getHand().getCards().size() == 2) {
            player.doubleDown();
            player.hit(deck); // Player takes one more card
            player.setHasHitInRound(false);
            player.setHasHitInRound(true); // Mark that the player has hit
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            if (player.getHand().getValue() > 21) {
                handleGameEnd("Player busts! Dealer wins.");
            } else {
                dealerPlay(); // Proceed with the rest of the game logic
            }
        } else {
            gui.showMessage("You can only double down with your initial two cards and if you haven't hit yet.");
        }
    }
    
    public void playerSplits() {
        if (!isGameOver() && player.canSplit() && player.getHand().getCards().size() == 2) {
            player.splitHand();
            player.hit(deck); // Deal one card to each split hand
            player.hit(deck);
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            // Continue the game with the split hands
            dealerPlay();
        } else {
            gui.showMessage("You can only split with your initial two cards and if it's not game over.");
        }
    }
    
    
    private void dealerPlay() {
        // Dealer hits only once if hand value is less than 17
        if (dealer.wantsToHit()) {
            dealer.hit(deck);
            gui.updateHands(player.getHand(), dealer.getHand(), true);
        }
    
        int dealerValue = dealer.getHand().getValue();
        int playerValue = player.getHand().getValue();
    
        if (dealerValue > 21 || playerValue > dealerValue) {
            handleGameEnd("Dealer busts! Player wins.");
        } else if (playerValue == dealerValue) {
            handleGameEnd("It's a tie!");
        } else {
            handleGameEnd("Dealer wins.");
        }
    }
    

    public void restartGame() {
        int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Restart Game", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            play();
        } else {
            System.exit(0);
        }
    }

    private boolean isGameOver() {
        return player.hasBlackjack() || dealer.hasBlackjack() || player.getHand().getValue() > 21;
    }


    private void handleGameEnd(String message) {
        gui.showMessage(message);
        restartGame();
    }
}
