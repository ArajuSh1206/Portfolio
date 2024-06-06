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
 
        gui.updateHands(player.getHand(), dealer.getHand(), false);

        while (player.wantsToHit()) {
            player.hit(deck);
            gui.updateHands(player.getHand(), dealer.getHand(), false);
            if (player.getHand().getValue() > 21) {
                gui.showMessage("Player busts! Dealer wins.");
                return;
            }
        }

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
}
