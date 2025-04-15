import javax.swing.*;
import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RockPaperScissorsGame extends JFrame {
    private JButton rockButton, paperButton, scissorsButton;
    private JLabel resultLabel, playerScoreLabel, computerScoreLabel;
    private int playerScore = 0;
    private int computerScore = 0;

    private Map<String, ImageIcon> images;

    public RockPaperScissorsGame() {
        setTitle("Rock, Paper, Scissors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.BLACK);

        // Load images
        loadImages();

        rockButton = createImageButton("Rock");
        paperButton = createImageButton("Paper");
        scissorsButton = createImageButton("Scissors");
        resultLabel = new JLabel("Choose your move!");
        playerScoreLabel = createScoreLabel("Player: 0", 24);
        computerScoreLabel = createScoreLabel("Computer: 0", 24);

        resultLabel.setForeground(Color.WHITE);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(rockButton, gbc);

        gbc.gridx = 1;
        add(paperButton, gbc);

        gbc.gridx = 2;
        add(scissorsButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(resultLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(playerScoreLabel, gbc);

        gbc.gridx = 2;
        add(computerScoreLabel, gbc);

        rockButton.addActionListener(e -> play("Rock"));
        paperButton.addActionListener(e -> play("Paper"));
        scissorsButton.addActionListener(e -> play("Scissors"));
    }

    private void loadImages() {
        images = new HashMap<>();
        images.put("Rock", new ImageIcon(resizeImage("rock.png")));
        images.put("Paper", new ImageIcon(resizeImage("paper.png")));
        images.put("Scissors", new ImageIcon(resizeImage("scissors.png")));
    }

    private Image resizeImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage).getImage();
    }

    private JButton createImageButton(String label) {
        JButton button = new JButton();
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBackground(Color.BLACK);
        button.setPreferredSize(new Dimension(80, 80));
        button.setIcon(images.get(label));

        return button;
    }

    private JLabel createScoreLabel(String text, int fontSize) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, fontSize));
        return label;
    }

    private void play(String userChoice) {
        String[] choices = {"Rock", "Paper", "Scissors"};
        Random random = new Random();
        int computerIndex = random.nextInt(3);
        String computerChoice = choices[computerIndex];

        String result = getGameResult(userChoice, computerChoice);
        resultLabel.setText("Computer chose " + computerChoice + ". " + result);

        updateScore(result);
    }

    private String getGameResult(String userChoice, String computerChoice) {
        if (userChoice.equals(computerChoice)) {
            return "It's a tie!";
        } else if (
                (userChoice.equals("Rock") && computerChoice.equals("Scissors")) ||
                (userChoice.equals("Paper") && computerChoice.equals("Rock")) ||
                (userChoice.equals("Scissors") && computerChoice.equals("Paper"))
        ) {
            return "You win!";
        } else {
            return "Computer wins!";
        }
    }

    private void updateScore(String result) {
        if (result.contains("You win!")) {
            playerScore++;
        } else if (result.contains("Computer wins!")) {
            computerScore++;
        }
        playerScoreLabel.setText("Player: " + playerScore);
        computerScoreLabel.setText("Computer: " + computerScore);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsGame game = new RockPaperScissorsGame();
            game.setVisible(true);
        });
    }
}
