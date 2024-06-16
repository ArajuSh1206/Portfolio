public class ChipManager {
    private static final int INITIAL_BALANCE = 500; // Set an initial balance for both player and dealer

    private int playerBalance;
    private int dealerBalance;

    public ChipManager() {
        this.playerBalance = INITIAL_BALANCE;
        this.dealerBalance = INITIAL_BALANCE;
    }

    public boolean canPlayerBet(int amount) {
        return amount <= playerBalance;
    }

    public boolean canDealerBet(int amount) {
        return amount <= dealerBalance;
    }

    public void playerBets(int amount) {
        if (canPlayerBet(amount)) {
            playerBalance -= amount;
        } else {
            throw new IllegalArgumentException("Player doesn't have enough chips.");
        }
    }

    public void dealerBets(int amount) {
        if (canDealerBet(amount)) {
            dealerBalance -= amount;
        } else {
            throw new IllegalArgumentException("Dealer doesn't have enough chips.");
        }
    }

    public void playerWins(int amount) {
        playerBalance += amount;
    }

    public void dealerWins(int amount) {
        dealerBalance += amount;
    }

    public int getPlayerBalance() {
        return playerBalance;
    }

    public int getDealerBalance() {
        return dealerBalance;
    }
}