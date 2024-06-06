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
    private Game game;

    public GameGUI() {
        setTitle("Blackjack Game");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(0, 122, 0));  // Dark green background

        // Create text areas with better fonts and colors
        playerArea = new JTextArea();
        dealerArea = new JTextArea();
        playerArea.setFont(new Font("Serif", Font.PLAIN, 18));
        dealerArea.setFont(new Font("Serif", Font.PLAIN, 18));
        playerArea.setBackground(new Color(255, 255, 200));
        dealerArea.setBackground(new Color(255, 255, 200));
        playerArea.setBorder(BorderFactory.createTitledBorder("Player's Hand"));
        dealerArea.setBorder(BorderFactory.createTitledBorder("Dealer's Hand"));

        // Create buttons with a consistent style
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");
        doubleDownButton = new JButton("Double Down");
        splitButton = new JButton("Split");

        styleButton(hitButton);
        styleButton(standButton);
        styleButton(doubleDownButton);
        styleButton(splitButton);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 122, 0));
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(doubleDownButton);
        buttonPanel.add(splitButton);

        // Create a panel for player and dealer areas
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(2, 1));
        textPanel.add(new JScrollPane(dealerArea));
        textPanel.add(new JScrollPane(playerArea));

        // Add panels to the main frame
        add(textPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().hit(game.getDeck());
                updateHands(game.getPlayer().getHand(), game.getDealer().getHand(), false);
                if (game.getPlayer().getHand().getValue() > 21) {
                    showMessage("Player busts! Dealer wins.");
                }
            }
        });

        standButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.play();
            }
        });

        doubleDownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().doubleDown();
                game.getPlayer().hit(game.getDeck());
                updateHands(game.getPlayer().getHand(), game.getDealer().getHand(), false);
                if (game.getPlayer().getHand().getValue() > 21) {
                    showMessage("Player busts! Dealer wins.");
                    return;
                }
                game.play();
            }
        });

        splitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer().splitHand();
                updateHands(game.getPlayer().getHand(), game.getDealer().getHand(), false);
            }
        });

        game = new Game(this);
        game.play();
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(0, 0, 0));
        button.setFocusPainted(false);
    }

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
