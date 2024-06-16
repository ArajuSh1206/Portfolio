import javax.swing.ImageIcon;

public class Card {
    private String rank;
    private String suit;
    private int value;
    private boolean faceUp;
    

    public Card(String rank, String suit, int value) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.faceUp = true; // By default, the card is face-up when created
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    // Method to generate the file name of the card image
    public String getImageFileName() {
        String rankString;
        switch (rank) {
            case "Ace":
                rankString = "A";
                break;
            case "Jack":
                rankString = "J";
                break;
            case "Queen":
                rankString = "Q";
                break;
            case "King":
                rankString = "K";
                break;
            default:
                rankString = rank;
        }
    
        String suitString;
        switch (suit) {
            case "Hearts":
                suitString = "H";
                break;
            case "Diamonds":
                suitString = "D";
                break;
            case "Clubs":
                suitString = "C";
                break;
            case "Spades":
                suitString = "S";
                break;
            default:
                throw new IllegalArgumentException("Invalid suit: " + suit);
        }
    
        return "/cards/" + rankString + suitString + ".png"; 
    }
    
}
