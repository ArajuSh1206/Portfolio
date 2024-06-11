import javax.swing.JOptionPane;
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
            if (dealer.hasBlackjack()) {
                handleGameEnd("Both have Blackjack! It's a tie!");
            } else {
                handleGameEnd("Player has Blackjack! Player wins!");
            }
        } else if (dealer.hasBlackjack()) {
            handleGameEnd("Dealer has Blackjack! Dealer wins.");
        } else {
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            gui.updateButtonStates(true, true, player.canDoubleDown(), player.canSplit());
        }
    }

    private void handleGameEnd(String message) {
        gui.showMessage(message);
        restartGame();
    }
    

    public void playerStands() {
        if (player.hasHitInRound() || player.getHand().getCards().size() == 2) {
            dealerPlay();
        } else {
            gui.showMessage("You must hit at least once before standing.");
        }
    }

    public void playerDoubleDowns() {
        if (player.canDoubleDown() && !player.hasHitInRound()) {
            player.doubleDown();
            player.hit(deck); // Player takes one more card
            player.setHasHitInRound(false);
            player.setHasHitInRound(true); // Mark that the player has hit
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            if (player.getHand().getValue() > 21) {
                handleGameEnd("Player busts! Dealer wins.");
            } else {
                dealerPlay();
            }
        } else {
            gui.showMessage("You can only double down with your initial two cards.");
        }
    }

    public void playerHits() {
        if (!player.hasHitInRound()) { // Checking if the player has already hit in the current round
            player.hit(deck); // Player takes one card
            player.setHasHitInRound(true); // Mark that the player has hit in this round
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            int playerValue = player.getHand().getValue();
            if (playerValue > 21) {
                handleGameEnd("Player busts! Dealer wins.");
            } else if (playerValue == 21) {
                handleGameEnd("Player has 21! Dealer's turn.");
            }
        } else {
            gui.showMessage("You can only hit once per turn.");
        }
    }
    

    private void dealerPlay() {
        while (dealer.wantsToHit()) {
            dealer.hit(deck);
            gui.updateHands(player.getHand(), dealer.getHand(), true);
        }

        int dealerValue = dealer.getHand().getValue();
        if (dealerValue > 21) {
            handleGameEnd("Dealer busts! Player wins.");
        } else {
            evaluateGameOutcome();
        }
    }

    private void evaluateGameOutcome() {
        List<Hand> playerHands = player.getAllHands();
        int dealerValue = dealer.getHand().getValue();
        boolean playerWins = false;
        boolean tie = false;

        for (Hand hand : playerHands) {
            int handValue = hand.getValue();
            if (handValue > 21) {
                // Hand is a bust
                continue;
            } else if (dealerValue > 21 || handValue > dealerValue) {
                playerWins = true;
            } else if (handValue == dealerValue) {
                tie = true;
            }
        }

        if (playerWins) {
            handleGameEnd("Player wins at least one hand.");
        } else if (tie) {
            handleGameEnd("At least one hand ties with dealer.");
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
}
