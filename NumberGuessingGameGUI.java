import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {
    private final int lowerBound = 1;
    private final int upperBound = 100;
    private final int maxAttempts = 10;
    private int numberToGuess;
    private int attempts;

    private JTextField guessField;
    private JLabel messageLabel;
    private JLabel attemptsLabel;

    public NumberGuessingGameGUI() {
        setTitle("🎯 Number Guessing Game 🎯");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        numberToGuess = new Random().nextInt(upperBound - lowerBound + 1) + lowerBound;
        attempts = 0;

        // Header
        JLabel titleLabel = new JLabel("🎮 Welcome to the Guessing Game! 🎮", JLabel.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(new Color(44, 62, 80));

        // Instructions
        JLabel instructionLabel = new JLabel("🔢 Guess a number between " + lowerBound + " and " + upperBound + ":");
        instructionLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        instructionLabel.setForeground(new Color(52, 73, 94));

        guessField = new JTextField(10);
        guessField.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JButton guessButton = new JButton("Guess");
        guessButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        guessButton.setBackground(new Color(52, 152, 219));
        guessButton.setForeground(Color.WHITE);

        messageLabel = new JLabel("📝 You have 10 attempts. Good luck!");
        messageLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        messageLabel.setForeground(new Color(39, 174, 96));

        attemptsLabel = new JLabel("Attempts: 0/" + maxAttempts);
        attemptsLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setBackground(new Color(240, 248, 255));

        panel.add(titleLabel);
        panel.add(instructionLabel);
        panel.add(guessField);
        panel.add(guessButton);
        panel.add(messageLabel);
        panel.add(attemptsLabel);

        add(panel);

        // Action Listeners
        guessButton.addActionListener(e -> handleGuess());
        guessField.addActionListener(e -> handleGuess());
    }

    private void handleGuess() {
        if (attempts >= maxAttempts) return;

        try {
            int guess = Integer.parseInt(guessField.getText().trim());
            attempts++;

            if (guess == numberToGuess) {
                messageLabel.setText("🎉 Correct! You guessed it in " + attempts + " attempts.");
                messageLabel.setForeground(new Color(41, 128, 185));
                guessField.setEditable(false);
            } else if (guess < numberToGuess) {
                messageLabel.setText("📈 Try a higher number.");
                messageLabel.setForeground(new Color(241, 196, 15));
            } else {
                messageLabel.setText("📉 Try a lower number.");
                messageLabel.setForeground(new Color(231, 76, 60));
            }

            attemptsLabel.setText("Attempts: " + attempts + "/" + maxAttempts);

            if (attempts == maxAttempts && guess != numberToGuess) {
                messageLabel.setText("❌ Out of attempts! The number was: " + numberToGuess);
                messageLabel.setForeground(new Color(192, 57, 43));
                guessField.setEditable(false);
            }

        } catch (NumberFormatException ex) {
            messageLabel.setText("⚠ Please enter a valid number.");
            messageLabel.setForeground(new Color(192, 57, 43));
        }

        guessField.setText("");
        guessField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NumberGuessingGameGUI().setVisible(true);
        });
    }
}