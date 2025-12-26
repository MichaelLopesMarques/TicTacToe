import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board {

    private JFrame menuWindow;
    private JFrame gameWindow;
    private JButton[][] fields = new JButton[3][3];
    private Game game;

    public Board() {
        startMenu();
        menuWindow.setVisible(true);
    }

    public void startMenu(){
        menuWindow = new JFrame("TicTacToe");
        menuWindow.setSize(300, 400);
        menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuWindow.setLocationRelativeTo(null);

        // Label
        JLabel titleLabel = new JLabel("TicTacToe");
        titleLabel.setFont(new Font("Impact", Font.PLAIN, 30));

        // Buttons
        JButton startButton = new JButton("Start");
        JButton exitButton = new JButton("Beenden");

        startButton.setMaximumSize(new Dimension(200, 50));
        exitButton.setMaximumSize(new Dimension(200, 50));

        // Menu Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 50, 20));

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuPanel.add(titleLabel);
        menuPanel.add(Box.createVerticalStrut(50));
        menuPanel.add(startButton);
        menuPanel.add(Box.createVerticalStrut(50));
        menuPanel.add(exitButton);

        // Outer Panel
        JPanel outerPanel = new JPanel(new GridLayout());
        outerPanel.add(menuPanel);

        // Button Action
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuWindow.setVisible(false);
                initBoard();
                setGrid();
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        menuWindow.add(outerPanel);
    }

    public void initBoard(){
        gameWindow = new JFrame("TicTacToe");
        gameWindow.setSize(300,300);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setLayout(new GridLayout(3,3));
        gameWindow.setVisible(true);

        game = new Game();
    }

    public void setGrid(){
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                JButton field = new JButton("");
                fields[row][col] = field;

                final int r = row;
                final int c = col;

                field.addActionListener(e -> onFieldClicked(r, c));
                gameWindow.add(field);
            }
        }
    }

    private void onFieldClicked(int row, int col) {
        System.out.println("Klick auf: " + row + ", " + col);
        if (game.makeMove(row, col)){
            fields[row][col].setText(game.getField(row, col));

            String winner = game.checkWinner();
            if (winner != null){
                JOptionPane.showMessageDialog(null, "Spieler "+ winner +" hat gewonnen!", "Spielende", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Spieler "+ winner +" hat gewonnen!");
                gameWindow.dispose();
                menuWindow.setVisible(true);
            }
            if (game.tie()){
                JOptionPane.showMessageDialog(null, "Unentschieden", "Spielende", JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Unentschieden");
                gameWindow.dispose();
                menuWindow.setVisible(true);
            }
            if ((game.isBotTurn()) && (winner == null)){
                startBotTurnWithDelay();
            }
        } else{
            JOptionPane.showMessageDialog(null, "Das Feld ist bereits belegt!", "Ungültiger Spielzug", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void startBotTurnWithDelay() {
        Timer timer = new Timer(1000, e -> {
            int[] botMove = game.botMove();
            if (botMove != null) {
                fields[botMove[0]][botMove[1]]
                        .setText(game.getField(botMove[0], botMove[1]));
            }

            String winner = game.checkWinner();
            if (winner != null){
                JOptionPane.showMessageDialog(null, "Spieler " + winner + " hat gewonnen!");
                gameWindow.dispose();
                menuWindow.setVisible(true);
            }

            if (game.tie()){
                JOptionPane.showMessageDialog(null, "Unentschieden");
                gameWindow.dispose();
                menuWindow.setVisible(true);
            }
        });

        timer.setRepeats(false);
        timer.start();
    }



}
