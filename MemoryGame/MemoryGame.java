import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGame extends JFrame {

    private List<String> symbols;
    private JButton[][] buttons;
    private JButton firstButton;
    private boolean canClick;
    private Timer timer;
    private int matchesFound;
    private long startTime;

    public MemoryGame() {
        setTitle("Memory Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        symbols = new ArrayList<>();
        symbols.add("A");
        symbols.add("B");
        symbols.add("C");
        symbols.add("D");
        symbols.add("E");
        symbols.add("F");

        // Add each symbol twice to make pairs
        symbols.addAll(symbols);

        Collections.shuffle(symbols);

        buttons = new JButton[3][3];
        canClick = true;
        matchesFound = 0;

        initializeButtons();
        startTimer();
        startTime = System.currentTimeMillis();
    }

    private void initializeButtons() {
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 24));
                buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].setForeground(Color.WHITE);
                buttons[i][j].setOpaque(true);
                buttons[i][j].addActionListener(new ButtonClickListener());
                add(buttons[i][j]);
            }
        }
    }

    private void startTimer() {
        int delay = 60000; // 1 minute
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                endGame(false);
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (canClick) {
                JButton clickedButton = (JButton) e.getSource();

                if (firstButton == null) {
                    firstButton = clickedButton;
                    firstButton.setText(symbols.get(getSymbolIndex(clickedButton)));
                } else {
                    JButton secondButton = clickedButton;
                    secondButton.setText(symbols.get(getSymbolIndex(secondButton)));

                    if (!firstButton.getText().equals(secondButton.getText())) {
                        canClick = false;
                        Timer mismatchTimer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                firstButton.setText("");
                                secondButton.setText("");
                                firstButton = null;
                                canClick = true;
                            }
                        });
                        mismatchTimer.setRepeats(false);
                        mismatchTimer.start();
                    } else {
                        firstButton.setEnabled(false);
                        secondButton.setEnabled(false);
                        firstButton = null;
                        matchesFound++;
                        if (matchesFound == 3) {
                            endGame(true);
                        }
                    }
                }
            }
        }
    }

    private int getSymbolIndex(JButton button) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == button) {
                    return i * 3 + j;
                }
            }
        }
        return -1;
    }

    private void endGame(boolean playerWon) {
        timer.stop();
        long endTime = System.currentTimeMillis();
        long totalTime = (endTime - startTime) / 1000;

        if (playerWon) {
            JOptionPane.showMessageDialog(this, "Congratulations! You won in " + totalTime + " seconds.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Game Over! Your time is up.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MemoryGame memoryGame = new MemoryGame();
            memoryGame.setVisible(true);
        });
    }
}
