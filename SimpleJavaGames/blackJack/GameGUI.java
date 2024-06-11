import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GameGUI extends JFrame {
    private JPanel playerPanel;
    private JPanel dealerPanel;
    private JButton hitButton;
    private JButton standButton;
    private JButton doubleDownButton;
    private JButton splitButton;
    private JButton startButton;
    private Game game;
    private Player player;
    private Player dealer;

    public GameGUI() {
        setTitle("Blackjack Game");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE); // White background

        // Create panels for player and dealer areas
        playerPanel = new JPanel();
        dealerPanel = new JPanel();
        playerPanel.setBackground(Color.WHITE); // White background
        dealerPanel.setBackground(Color.WHITE); // White background
        add(playerPanel, BorderLayout.SOUTH);
        add(dealerPanel, BorderLayout.NORTH);

        // Create buttons
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        doubleDownButton = new JButton("Double Down");
        splitButton = new JButton("Split");
        startButton = new JButton("Start Game");

        styleButton(hitButton);
        styleButton(standButton);
        styleButton(doubleDownButton);
        styleButton(splitButton);
        styleButton(startButton);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE); // White background
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(doubleDownButton);
        buttonPanel.add(splitButton);
        buttonPanel.add(startButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Initially create game object and button listeners
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // Call the method to start the game
            }
        });

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerHits();
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerStands();
            }
        });

        doubleDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerDoubleDowns();
            }
        });

        splitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerSplits();
            }
        });

        updateButtonStates(false, false, false, false); //
        updateButtonStates(false, false, false, false); // Initially, disable game buttons until the game starts
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(Color.BLACK); // Black background
        button.setForeground(Color.BLACK); // Black foreground
        button.setFocusPainted(false);
    }
    

// Method to start the game
private void startGame() {
    game = new Game(this); // Create a new game instance
    player = game.getPlayer(); // Update player reference

    game.play(); // Start the game
    updateButtonStates(true, true, false, player.canSplit()); // Enable Hit and Stand buttons
    startButton.setEnabled(false); // Disable the Start Game button once the game has started
}

public void updateGUI(boolean showAllDealerCards) {
    game.updateGUI(player.getHand(), dealer.getHand(), showAllDealerCards);
}


    // Method to handle player hitting
    private void playerHits() {
        game.playerHits();
    }

    // Method to handle player standing
    private void playerStands() {
        game.playerStands();
    }

    // Method to handle player doubling down
    private void playerDoubleDowns() {
        game.playerDoubleDowns();
    }

    // Method to handle player splitting
    private void playerSplits() {
        player.splitHand();
    }

    // Method to update the hands in the GUI with card images
    public void updateHands(Hand playerHand, Hand dealerHand, boolean showAllDealerCards) {
        playerPanel.removeAll();
        dealerPanel.removeAll();

        displayCards(playerHand.getCards(), playerPanel);
        displayCards(dealerHand.getCards(), dealerPanel, showAllDealerCards);

        playerPanel.revalidate();
        playerPanel.repaint();
        dealerPanel.revalidate();
        dealerPanel.repaint();
    }

    // Method to display cards on a panel
    private void displayCards(List<Card> cards, JPanel panel) {
        displayCards(cards, panel, true);
    }

    private void displayCards(List<Card> cards, JPanel panel, boolean showAllCards) {
        panel.removeAll();
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            String fileName;
            if (showAllCards || i == 0) {
                fileName = "/Cards/" + card.getImageFileName() + ".png";
            } else {
                fileName = "/Cards/backB.png"; // Change to backR.png for red back
            }
            ImageIcon cardIcon = new ImageIcon(getClass().getResource(fileName));
            // Adjust the size of the card image
            int width = 85; // Adjust width according to your preference
            int height = (int) (width * 1.5); // Maintain aspect ratio
            Image scaledImage = cardIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon scaledCardIcon = new ImageIcon(scaledImage);
            JLabel cardLabel = new JLabel(scaledCardIcon);
            panel.add(cardLabel);
        }
        panel.revalidate();
        panel.repaint();
    } 
    
    public void updateButtonStates(boolean hit, boolean stand, boolean doubleDown, boolean split) {
        hitButton.setEnabled(hit);
        standButton.setEnabled(stand);
        doubleDownButton.setEnabled(doubleDown);
        splitButton.setEnabled(split);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameGUI().setVisible(true);
            }
        });
    }
}
