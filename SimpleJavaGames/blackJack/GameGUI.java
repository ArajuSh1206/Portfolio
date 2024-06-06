import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI extends JFrame {
    private JTextArea playerArea;
    private JTextArea dealerArea;
    private JButton hitButton;
    private JButton standButton;
    private Game game;

    public GameGUI() {
        setTitle("Blackjack Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        playerArea = new JTextArea();
        dealerArea = new JTextArea();
        hitButton = new JButton("Hit");
        standButton = new JButton("Stand");

        JPanel panel = new JPanel();
        panel.add(hitButton);
        panel.add(standButton);

        add(new JScrollPane(playerArea), BorderLayout.SOUTH);
        add(new JScrollPane(dealerArea), BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

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

        game = new Game(this);
        game.play();
    }

    public void updateHands(Hand playerHand, Hand dealerHand, boolean showAllDealerCards) {
        playerArea.setText("Player's Hand:\n");
        for (Card card : playerHand.getCards()) {
            playerArea.append(card.toString() + "\n");
        }
        playerArea.append("Player's Hand Value: " + playerHand.getValue() + "\n");

        dealerArea.setText("Dealer's Hand:\n");
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

