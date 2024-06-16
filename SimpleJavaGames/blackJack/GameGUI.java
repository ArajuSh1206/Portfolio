  import javax.swing.*;
  import java.awt.*;
  import java.awt.event.ActionEvent;
  import java.awt.event.ActionListener;
  import java.util.List;
  
  public class GameGUI extends JFrame {
      private Game game;
      private JButton startButton, hitButton, standButton, doubleDownButton, splitButton;
    JButton resetGameButton;
      JButton[] betButtons;
      private JLabel playerMoneyLabel, playerBetLabel, dealerBetLabel, playerHandValueLabel, dealerMoneyLabel;
      JLabel dealerHandValueLabel;
      private JPanel dealerCardsPanel, playerCardsPanel;
      private int playerMoney = 500;
      private int dealerMoney = 500;
      private int currentBet = 0;
  
      public GameGUI() {
          setTitle("Blackjack Game");
          setSize(1000, 800);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setLayout(new BorderLayout());
  
          // Initialize game
          game = new Game(this);
  
          // Initialize components
          initComponents();
  
          setVisible(true);
      }
  
          private void initComponents() {
        // Create dealer panel
        JPanel dealerPanel = new JPanel(new BorderLayout());
        dealerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dealerPanel.setBackground(Color.DARK_GRAY);

        // Dealer money label
        dealerMoneyLabel = new JLabel("Dealer Money: $" + dealerMoney);
        dealerMoneyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dealerMoneyLabel.setForeground(Color.WHITE);
        dealerPanel.add(dealerMoneyLabel, BorderLayout.NORTH);

        // Dealer hand value label
        dealerHandValueLabel = new JLabel("Dealer Hand Value: Hidden");
        dealerHandValueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        dealerHandValueLabel.setForeground(Color.WHITE);
        dealerPanel.add(dealerHandValueLabel, BorderLayout.CENTER);

        // Dealer cards panel
        dealerCardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        dealerCardsPanel.setBackground(Color.DARK_GRAY);
        dealerPanel.add(dealerCardsPanel, BorderLayout.SOUTH);

        // Dealer bet label
        dealerBetLabel = new JLabel("Dealer Bet: $0");
        dealerBetLabel.setFont(new Font("Arial", Font.BOLD, 16));
        dealerBetLabel.setForeground(Color.WHITE);
        dealerPanel.add(dealerBetLabel, BorderLayout.EAST);

        add(dealerPanel, BorderLayout.NORTH);

        // Create player panel
        JPanel playerPanel = new JPanel(new BorderLayout());
        playerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        playerPanel.setBackground(Color.DARK_GRAY);

        // Player money label
        playerMoneyLabel = new JLabel("Player Money: $" + playerMoney);
        playerMoneyLabel.setFont(new Font("Arial", Font.BOLD, 18));
        playerMoneyLabel.setForeground(Color.WHITE);
        playerPanel.add(playerMoneyLabel, BorderLayout.NORTH);

        // Player bet and hand value labels
        JPanel playerLabelsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        playerLabelsPanel.setBackground(Color.DARK_GRAY);
        playerBetLabel = new JLabel("Player Bet: $0");
        playerBetLabel.setFont(new Font("Arial", Font.BOLD, 16));
        playerBetLabel.setForeground(Color.WHITE);
        playerBetLabel.setForeground(Color.WHITE);
        playerPanel.add(playerBetLabel,BorderLayout.EAST);

        playerHandValueLabel = new JLabel("Player Hand Value: 0");
        playerHandValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        playerHandValueLabel.setForeground(Color.WHITE);
        playerLabelsPanel.add(playerHandValueLabel);

        playerPanel.add(playerLabelsPanel, BorderLayout.CENTER);

        // Player cards panel
        playerCardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        playerCardsPanel.setBackground(Color.DARK_GRAY);
        playerPanel.add(playerCardsPanel, BorderLayout.SOUTH);

        add(playerPanel, BorderLayout.SOUTH);

        // Control panel for betting and game actions
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        controlPanel.setBackground(Color.GREEN);

        // Betting panel
        JPanel bettingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bettingPanel.setBackground(Color.GRAY);
        String[] betAmounts = {"10", "20", "50", "100"};
        betButtons = new JButton[betAmounts.length];

        for (int i = 0; i < betAmounts.length; i++) {
            betButtons[i] = new JButton(betAmounts[i]);
            betButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            betButtons[i].setPreferredSize(new Dimension(80, 40));
            betButtons[i].setBackground(Color.LIGHT_GRAY);
            betButtons[i].setForeground(Color.BLACK);
            betButtons[i].addActionListener(new BetButtonListener()); // Register listener
            bettingPanel.add(betButtons[i]);
        }

        controlPanel.add(bettingPanel, BorderLayout.NORTH);

        // Game action buttons panel
        JPanel actionPanel = new JPanel(new GridLayout(1, 0, 10, 10));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        actionPanel.setBackground(Color.GRAY);

        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.setBackground(Color.WHITE);
        startButton.setForeground(Color.BLACK);
        startButton.addActionListener(new StartButtonListener()); // Register listener
        actionPanel.add(startButton);

        hitButton = new JButton("Hit");
        hitButton.setFont(new Font("Arial", Font.BOLD, 16));
        hitButton.setBackground(Color.WHITE);
        hitButton.setForeground(Color.BLACK);
        hitButton.addActionListener(new HitButtonListener()); // Register listener
        hitButton.setEnabled(false);
        actionPanel.add(hitButton);

        standButton = new JButton("Stand");
        standButton.setFont(new Font("Arial", Font.BOLD, 16));
        standButton.setBackground(Color.WHITE);
        standButton.setForeground(Color.BLACK);
        standButton.addActionListener(new StandButtonListener()); // Register listener
        standButton.setEnabled(false);
        actionPanel.add(standButton);

        doubleDownButton = new JButton("Double Down");
        doubleDownButton.setFont(new Font("Arial", Font.BOLD, 16));
        doubleDownButton.setBackground(Color.WHITE);
        doubleDownButton.setForeground(Color.BLACK);
        doubleDownButton.addActionListener(new DoubleDownButtonListener()); // Register listener
        doubleDownButton.setEnabled(false);
        actionPanel.add(doubleDownButton);

        splitButton = new JButton("Split");
        splitButton.setFont(new Font("Arial", Font.BOLD, 16));
        splitButton.setBackground(Color.WHITE);
        splitButton.setForeground(Color.BLACK);
        splitButton.addActionListener(new SplitButtonListener()); // Register listener
        splitButton.setEnabled(false);
        actionPanel.add(splitButton);

        resetGameButton = new JButton("Reset Game");
        resetGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetGameButton.setBackground(Color.WHITE);
        resetGameButton.setForeground(Color.BLACK);
        resetGameButton.addActionListener(new ResetGameButtonListener()); // Register listener
        resetGameButton.setEnabled(true); // Enable reset button initially
        actionPanel.add(resetGameButton);

        controlPanel.add(actionPanel, BorderLayout.SOUTH);

        add(controlPanel, BorderLayout.CENTER);
    }
     

    private class BetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            int betAmount = Integer.parseInt(source.getText());
    
            if (playerMoney >= betAmount) {
                currentBet += betAmount;
                playerMoney -= betAmount;
                game.setPlayerBet(currentBet);
                updateBetLabels();
                updatePlayerBalance(playerMoney);
    
                // Enable game start button if valid bet is placed
                if (currentBet > 0) {
                    updateButtonStates(true, false, false, false, false);
                }
    
                source.setEnabled(false); // Disable the button after bet is placed
    
                if (currentBet > 0) {
                    startButton.setEnabled(true); // Enable game start button if valid bet is placed
                }
    
                JOptionPane.showMessageDialog(GameGUI.this, 
                    "Bet placed: $" + betAmount + "\nRemaining player money: $" + playerMoney);
            } else {
                JOptionPane.showMessageDialog(GameGUI.this, "Insufficient funds.");
            }
        }
    }
      
  
     private class StartButtonListener implements ActionListener {
         @Override
         public void actionPerformed(ActionEvent e) {
             if (currentBet > 0) {
                 JOptionPane.showMessageDialog(GameGUI.this, "Game started with a bet of $" + currentBet);
                 game.play(); // Start the game if a valid bet has been placed
             } else {
                 JOptionPane.showMessageDialog(GameGUI.this, "Please place a bet before starting the game.");
             }
         }
     }
     
  
     private class HitButtonListener implements ActionListener {
         @Override
         public void actionPerformed(ActionEvent e) {
             JOptionPane.showMessageDialog(GameGUI.this, "Player chose to Hit.");
             game.playerHits();
         }
     }
     
  
     private class StandButtonListener implements ActionListener {
         @Override
         public void actionPerformed(ActionEvent e) {
             JOptionPane.showMessageDialog(GameGUI.this, "Player chose to Stand.");
             game.playerStands();
 
         }
     }
     
  
     private class DoubleDownButtonListener implements ActionListener {
         @Override
         public void actionPerformed(ActionEvent e) {
             JOptionPane.showMessageDialog(GameGUI.this, "Player chose to Double Down.");
             game.playerDoubleDowns();
         }
     }
     
  
     private class SplitButtonListener implements ActionListener {
         @Override
         public void actionPerformed(ActionEvent e) {
             JOptionPane.showMessageDialog(GameGUI.this, "Player chose to Split.");
             game.playerSplits();
         }
     }
     
  
      private class ResetGameButtonListener implements ActionListener {
          @Override
          public void actionPerformed(ActionEvent e) {
              game.resetGame();
              currentBet = 0;
              updateBetLabels();
              updatePlayerBalance(playerMoney);
              updateDealerBalance(dealerMoney);
              updateButtonStates(false, true, true, true, true);
          }
      }
  
      private void updateBetLabels() {
          playerBetLabel.setText("Player Bet: $" + currentBet);
          dealerBetLabel.setText("Dealer Bet: $" + currentBet);
      }
  

 public void updateButtonStates(boolean start, boolean hit, boolean stand, boolean doubleDown, boolean split) {
     startButton.setEnabled(start);
     hitButton.setEnabled(hit);
     standButton.setEnabled(stand);
     doubleDownButton.setEnabled(doubleDown);
     splitButton.setEnabled(split);
 }
 
  
      // Update player's balance display
      public void updatePlayerBalance(int newBalance) {
          playerMoney = newBalance;
          playerMoneyLabel.setText("Player Money: $" + playerMoney);
      }
  
      // Update dealer's balance display
      public void updateDealerBalance(int newBalance) {
          dealerMoney = newBalance;
          dealerMoneyLabel.setText("Dealer Money: $" + dealerMoney);
      }
  
      // Disable betting buttons when game starts
      public void disableBetting() {
          for (JButton button : betButtons) {
              button.setEnabled(false);
          }
      }
  
      // Update dealer's balance when dealer wins
  public void dealerWins(int betAmount) {
      int newBalance = playerMoney - betAmount;
      updatePlayerBalance(newBalance);
      JOptionPane.showMessageDialog(this, "Dealer wins!");
      resetGame();
  }
  
      // Update player's balance when player wins
      public void playerWins(int betAmount) {
          int newBalance = playerMoney + betAmount;
          updatePlayerBalance(newBalance);
          JOptionPane.showMessageDialog(this, "Player wins!");
          resetGame();
      }
      
  
      // Inform player that it's a draw, reset game
      public void draw() {
          JOptionPane.showMessageDialog(this, "It's a draw!");
          resetGame();
      }
  
      // Update the display of player's and dealer's hands
      public void updateHands(Hand playerHand, Hand dealerHand, boolean showAllDealerCards) {
        if (playerHand != null) {
            updateCardPanel(playerCardsPanel, playerHand.getCards(), true);
            playerHandValueLabel.setText("Player Hand Value: " + calculateHandValue(playerHand.getCards()));
        } else {
            System.err.println("Error: Player's hand is null.");
            return;
        }
    
        if (dealerHand != null) {
            List<Card> dealerCards = dealerHand.getCards();
    
            if (!showAllDealerCards && dealerCards.size() > 1) {
                List<Card> visibleDealerCards = dealerCards.subList(0, 1); // Sublist with first card only
                updateCardPanel(dealerCardsPanel, visibleDealerCards, false);
            } else {
                updateCardPanel(dealerCardsPanel, dealerCards, true);
            }
        }
    }
    
    
  
      // Reset the game state for a new round
      public void resetGame() {
        currentBet = 0;
        updateBetLabels();
        updateButtonStates(true, false, false, false, false);

        // Clear cards panel
        playerCardsPanel.removeAll();
        dealerCardsPanel.removeAll();
        playerCardsPanel.revalidate();
        dealerCardsPanel.revalidate();
        playerCardsPanel.repaint();
        dealerCardsPanel.repaint();

        // Reset bet labels
        playerBetLabel.setText("Player Bet: $0");
        dealerBetLabel.setText("Dealer Bet: $0");

        // Enable or disable reset button based on game state
        if (playerMoney > 0) {
            resetGameButton.setEnabled(true);
        } else {
            resetGameButton.setEnabled(false);
        }
    }
    
  
      // Update the display of cards in a panel
      private void updateCardPanel(JPanel panel, List<Card> cards, boolean showAllCards) {
          panel.removeAll();
  
          for (int i = 0; i < cards.size(); i++) {
              Card card = cards.get(i);
              String imagePath = card.getImageFileName();
              ImageIcon icon = createImageIcon(imagePath);
  
              if (icon != null) {
                  JLabel label = new JLabel(icon);
                  panel.add(label);
              } else {
                  System.err.println("Image not found for card: " + card);
              }
  
              // Show only the first dealer card face down initially
              if (!showAllCards && i == 0) {
                  JLabel backLabel = new JLabel(createImageIcon("/cards/backR.png")); // Use a back image for hidden card
                  panel.add(backLabel);
              }
          }
  
          panel.revalidate();
          panel.repaint();
      }
  
      // Utility method to create an ImageIcon from the specified file path
      private ImageIcon createImageIcon(String path) {
          java.net.URL imgURL = getClass().getResource(path);
          if (imgURL != null) {
              return new ImageIcon(imgURL);
          } else {
              System.err.println("Couldn't find file: " + path);
              return null;
          }
      }
  
      // Calculate the total value of the hand
      private int calculateHandValue(List<Card> cards) {
          int value = 0;
          int numAces = 0;
  
          for (Card card : cards) {
              if (card.getRank().equals("Ace")) {
                  numAces++;
              }
              value += card.getValue();
          }
  
          // Adjust for aces (count them as 11 if it doesn't bust the hand)
          while (numAces > 0 && value > 21) {
              value -= 10;
              numAces--;
          }
  
          return value;
      }
 
      
  
      public void showBalanceChanges(int playerAdded, int dealerAdded) {
          String message = "Player Balance Change: ";
          if (playerAdded > 0) {
              message += "+$" + playerAdded;
          } else if (playerAdded < 0) {
              message += "-$" + (-playerAdded);
          } else {
              message += "$0";
          }
          message += "\n";
      
          message += "Dealer Balance Change: ";
          if (dealerAdded > 0) {
              message += "+$" + dealerAdded;
          } else if (dealerAdded < 0) {
              message += "-$" + (-dealerAdded);
          } else {
              message += "$0";
          }
      
          JOptionPane.showMessageDialog(this, message);
      }    
  
      // Main method to start the application
      public static void main(String[] args) {
          // Run the GUI application in the Event Dispatch Thread for thread safety
          SwingUtilities.invokeLater(() -> {
              new GameGUI();
          });
     }

  }
  
  
  
 
