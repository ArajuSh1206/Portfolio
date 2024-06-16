import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Game {
    private Player player;
    private Dealer dealer;
    private GameGUI gameGUI;
    private int playerBet;
    private int dealerBet;
    private int playerBalance;
    private int dealerBalance;
    private Deck deck;

    public Game(GameGUI gameGUI) {
        this.gameGUI = gameGUI;
        this.player = new Player();
        this.dealer = new Dealer();
        this.playerBalance = 500; // Initialize player's balance
        this.dealerBalance = 500; // Initialize dealer's balance
        this.deck = new Deck();
    }

    public void play() {
        if (playerBet <= 0) {
            JOptionPane.showMessageDialog(gameGUI, "You must place a bet before starting the game.");

            // Reset game if player doesn't place a bet
            return;
        }

        setDealerBet(playerBet); // Set dealer's bet equal to player's bet

        // Reset hands and deal initial cards
        player.clearHand();
        dealer.clearHand();
        player.hit(deck);
        player.hit(deck);
        dealer.hit(deck);
        dealer.hit(deck);

        // Update GUI with initial hands
        gameGUI.updateHands(player.getHand(), dealer.getHand(), false); // Show one dealer card

        // Enable/disable buttons based on game state
        gameGUI.updateButtonStates(false, true, true, player.canDoubleDown(), player.canSplit());
        gameGUI.disableBetting();

        // Check for initial blackjack
        if (player.hasBlackjack() && dealer.hasBlackjack()) {
            endGame("Both have Blackjack! It's a tie.");
        } else if (player.hasBlackjack()) {
            endGame("Player has Blackjack! Player wins.");
        } else if (dealer.hasBlackjack()) {
            endGame("Dealer has Blackjack! Dealer wins.");
        } else {
            // Show one of the dealer's cards as hidden (faced down)
            gameGUI.updateHands(player.getHand(), null, false); // Show one dealer card
        }
    }

    public void setPlayerBet(int bet) {
        if (playerBalance >= bet) {
            playerBet = bet;
            playerBalance -= bet; // Deduct bet from player's balance
            gameGUI.updatePlayerBalance(playerBalance);
        } else {
            JOptionPane.showMessageDialog(gameGUI, "Insufficient funds.");
        }
    }

    public void setDealerBet(int bet) {
        if (dealerBalance >= bet) {
            dealerBet = bet;
            dealerBalance -= bet; // Deduct bet from dealer's balance
            gameGUI.updateDealerBalance(dealerBalance);
        } else {
            JOptionPane.showMessageDialog(gameGUI, "Dealer has insufficient funds to place the bet.");
        }
    }

    public int getPlayerBet() {
        return playerBet;
    }

    public int getDealerBet() {
        return dealerBet;
    }

    public void playerHits() {
        player.hit(deck);
        gameGUI.updateHands(player.getHand(), null, false); // Show one dealer card

        if (player.getHand().getValue() > 21) {
            endGame("Player busts! Dealer wins.");
        }
    }

    public void playerStands() {
        while (dealer.wantsToHit()) {
            dealer.hit(deck);
        }

        gameGUI.updateHands(player.getHand(), dealer.getHand(), true); // Show all dealer cards
        determineWinner();
    }

    public void playerDoubleDowns() {
        if (playerBalance >= playerBet) {
            player.doubleDown();
            setPlayerBet(playerBet * 2); // Double the player's bet

            player.hit(deck);
            gameGUI.updateHands(player.getHand(), null, false); // Show one dealer card

            if (player.getHand().getValue() > 21) {
                endGame("Player busts after doubling down! Dealer wins.");
            } else {
                playerStands();
            }
        } else {
            JOptionPane.showMessageDialog(gameGUI, "Insufficient funds to double down.");
        }
    }

    public void playerSplits() {
        player.splitHand();
        gameGUI.updateHands(player.getHand(), null, false); // Show player's split hand
        gameGUI.updateButtonStates(false, true, true, false, false); // Update button states for split hand

        // Handle second hand's actions if necessary
    }

    private void determineWinner() {
        int playerHandValue = player.getHand().getValue();
        int dealerHandValue = dealer.getHand().getValue();

        if (playerHandValue > 21) {
            endGame("Player busts! Dealer wins.");
        } else if (dealerHandValue > 21) {
            endGame("Dealer busts! Player wins.");
        } else if (playerHandValue > dealerHandValue) {
            endGame("Player wins!");
        } else if (playerHandValue < dealerHandValue) {
            endGame("Dealer wins.");
        } else {
            endGame("It's a tie.");
        }
    }

 
    private void endGame(String message) {
        // Show all dealer cards and update dealer's total value
        gameGUI.updateHands(player.getHand(), dealer.getHand(), true);
    
        // Determine winner and update balances
        int playerHandValue = player.getHand().getValue();
        int dealerHandValue = dealer.getHand().getValue();
    
        if (playerHandValue > 21 || (dealerHandValue <= 21 && playerHandValue < dealerHandValue)) {
            // Dealer wins if player busts or player has a lower hand value
            dealerBalance += dealerBet * 2;
        } else if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
            // Player wins if dealer busts or player has a higher hand value
            playerBalance += playerBet * 2;
        } else {
            // It's a tie if both players have the same hand value
            playerBalance += playerBet;
            dealerBalance += dealerBet;
        }
    
        // Update player and dealer balances on GUI
        gameGUI.updatePlayerBalance(playerBalance);
        gameGUI.updateDealerBalance(dealerBalance);
    
        // Reset for next round
        player.clearHand();
        dealer.clearHand();
        playerBet = 0;
        dealerBet = 0;
    
        int response = JOptionPane.showConfirmDialog(gameGUI, "Do you want to play again?", message, JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            gameGUI.resetGame(); // This will reset the game state in the GUI
        } else {
            System.exit(0); // Optionally exit the application if the user chooses not to play again
        }
    
        // Enable buttons for new round, including reset game option
        for (JButton button : gameGUI.betButtons) {
            button.setEnabled(true); // Enable betting buttons for a new game
        }
        gameGUI.resetGameButton.setEnabled(true); // Enable the reset button for next game
        gameGUI.updateHands(player.getHand(), dealer.getHand(), false); // Show dealer's first card only
    }
    
    public void resetGame() {
        // Disable the reset button at the beginning of the method
        gameGUI.resetGameButton.setEnabled(false);
    
        player.clearHand();
        dealer.clearHand();
        playerBet = 0;
        dealerBet = 0;
    
        gameGUI.updateHands(player.getHand(), dealer.getHand(), false); // Show dealer's first card only
        gameGUI.updateButtonStates(true, false, false, false, false);
    
        gameGUI.updatePlayerBalance(playerBalance);
        gameGUI.updateDealerBalance(dealerBalance);
    
        for (JButton button : gameGUI.betButtons) {
            button.setEnabled(true); // Enable betting buttons for a new game
        }
    
        // Enable the reset button only after resetting the game state
        gameGUI.resetGameButton.setEnabled(true);
        gameGUI.updateHands(player.getHand(), dealer.getHand(), false); // Show dealer's first card only
        gameGUI.dealerHandValueLabel.setText("Dealer Hand Value: ?");
    }
}    