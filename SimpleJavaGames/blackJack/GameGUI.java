import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    private JTextArea playerArea;
    private JTextArea dealerArea;
    private JButton hitButton;
    private JButton standButton;
    private JButton doubleDownButton;
    private JButton splitButton;
    private JButton startButton;
    private Game game;
    private Player player;

    public GameGUI() {
        setTitle("Blackjack Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0, 122, 0)); // Dark green background

        // Create text areas
        playerArea = new JTextArea();
        dealerArea = new JTextArea();
        playerArea.setFont(new Font("Serif", Font.PLAIN, 18));
        dealerArea.setFont(new Font("Serif", Font.PLAIN, 18));
        playerArea.setBackground(new Color(255, 255, 200));
        dealerArea.setBackground(new Color(255, 255, 200));
        playerArea.setBorder(BorderFactory.createTitledBorder("Player's Hand"));
        dealerArea.setBorder(BorderFactory.createTitledBorder("Dealer's Hand"));

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
        buttonPanel.setBackground(new Color(0, 122, 0));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(doubleDownButton);
        buttonPanel.add(splitButton);
        buttonPanel.add(startButton);

        // Create a panel for player and dealer areas
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.add(new JScrollPane(dealerArea));
        textPanel.add(new JScrollPane(playerArea));

        // Add panels to the main frame
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Initially create game object and button listeners
        game = new Game(this);
        player = game.getPlayer();

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.hit(game.getDeck());
                GameGUI.this.updateHands(player.getHand(), game.getDealer().getHand(), false);
                if (player.getHand().getValue() > 21) {
                    showMessage("Player busts! Dealer wins.");
                    game.restartGame();
                }
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.play(); // The game logic will handle the rest when player stands
            }
        });

        doubleDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.doubleDown();
                player.hit(game.getDeck());
                GameGUI.this.updateHands(player.getHand(), game.getDealer().getHand(), false);
                if (player.getHand().getValue() > 21) {
                    showMessage("Player busts! Dealer wins.");
                    game.restartGame();
                }
                game.play(); // Proceed with the rest of the game logic
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.playerStands();
            }
        });

        doubleDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.playerDoubleDowns();
            }
        });

        splitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.splitHand();
            }
        });

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // Call the method to start the game
            }
        });

        updateButtonStates(false, false, false, false); // Initially, disable game buttons until the game starts
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(0, 0, 0));
        button.setFocusPainted(false);
    }

    // Method to start the game
    private void startGame() {
        game = new Game(this); // Create a new game instance
        game.play(); // Start the game
        updateButtonStates(true, true, false, false); // Enable Hit and Stand buttons
        startButton.setEnabled(false); // Disable the Start Game button once the game has started
    }

    // Method to update the hands in the GUI
    public void updateHands(Hand playerHand, Hand dealerHand, boolean showAllDealerCards) {
        playerArea.setText("");
        dealerArea.setText("");

        playerArea.append("Player's Hand:\n");
        for (Card card : playerHand.getCards()) {
            playerArea.append(card.toString() + "\n");
        }
        playerArea.append("Player's Hand Value: " + playerHand.getValue() + "\n");

        dealerArea.append("Dealer's Hand:\n");
        if (showAllDealerCards) {
            for (Card card : dealerHand.getCards()) {
                dealerArea.append(card.toString() + "\n");
            }
            dealerArea.append("Dealer's Hand Value: " + dealerHand.getValue() + "\n");
        } else {
            dealerArea.append(" <hidden card>\n");
            dealerArea.append(dealerHand.getCards().get(1).toString() + "\n");
        }
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