public class PokerChipManager {
    private int playerBalance;
    private int dealerBalance;

    public PokerChipManager(int initialBalance) {
        this.playerBalance = initialBalance;
        this.dealerBalance = initialBalance;
    }

    public void placeBet(int amount, boolean isPlayer) {
        if (isPlayer) {
            if (amount <= playerBalance) {
                playerBalance -= amount;
            } else {
                throw new IllegalArgumentException("Player doesn't have enough chips.");
            }
        } else {
            if (amount <= dealerBalance) {
                dealerBalance -= amount;
            } else {
                throw new IllegalArgumentException("Dealer doesn't have enough chips.");
            }
        }
    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    public int getDealerBalance() {
        return dealerBalance;
    }
}
