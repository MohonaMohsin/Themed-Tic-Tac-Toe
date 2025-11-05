import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe implements ThemeObserver {
    private JFrame frame;
    private JLabel textLabel;
    private JPanel boardPanel;
    private JButton[][] board = new JButton[3][3];
    private JButton switchThemeButton;
    private JButton restartButton;

    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private int turns = 0;
    private boolean gameOver = false;

    public TicTacToe() {
        GameManager.getInstance().addObserver(this);
        initPlayers(GameManager.getInstance().getTheme());
        createGUI();
    }

    private void initPlayers(Theme theme) {
        playerX = new Player.Builder().setName(theme.getPlayerXName()).setSymbol(theme.getPlayerXSymbol()).build();
        playerO = new Player.Builder().setName(theme.getPlayerOName()).setSymbol(theme.getPlayerOSymbol()).build();
        currentPlayer = playerX;
    }

    private void createGUI() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setSize(600, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        textLabel = new JLabel(currentPlayer.getName() + "'s turn");
        textLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 40));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);
        textLabel.setBackground(GameManager.getInstance().getTheme().getBackgroundColor());
        frame.add(textLabel, BorderLayout.NORTH);

        boardPanel = new JPanel(new GridLayout(3, 3));
        frame.add(boardPanel, BorderLayout.CENTER);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 120));
                btn.setBackground(GameManager.getInstance().getTheme().getBackgroundColor());
                int row = r, col = c;
                btn.addActionListener(e -> makeMove(btn, row, col));
                board[r][c] = btn;
                boardPanel.add(btn);
            }
        }

        JPanel bottomPanel = new JPanel();
        switchThemeButton = new JButton("Switch Theme");
        restartButton = new JButton("Restart");

        switchThemeButton.addActionListener(e -> switchTheme());
        restartButton.addActionListener(e -> restartGame());

        bottomPanel.add(switchThemeButton);
        bottomPanel.add(restartButton);

        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void makeMove(JButton btn, int r, int c) {
        if (!gameOver && btn.getText().equals("")) {
            btn.setText(currentPlayer.getSymbol());
            turns++;
            checkWinner();
            if (!gameOver) switchPlayer();
        }
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == playerX ? playerO : playerX;
        textLabel.setText(currentPlayer.getName() + "'s turn");
    }

    private void checkWinner() {
        String sym = currentPlayer.getSymbol();
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals(sym) && board[i][1].getText().equals(sym) && board[i][2].getText().equals(sym)) { declareWinner(); return; }
            if (board[0][i].getText().equals(sym) && board[1][i].getText().equals(sym) && board[2][i].getText().equals(sym)) { declareWinner(); return; }
        }
        if (board[0][0].getText().equals(sym) && board[1][1].getText().equals(sym) && board[2][2].getText().equals(sym)) { declareWinner(); return; }
        if (board[0][2].getText().equals(sym) && board[1][1].getText().equals(sym) && board[2][0].getText().equals(sym)) { declareWinner(); return; }

        if (turns == 9) {
            textLabel.setText("Tie!");
            gameOver = true;
        }
    }

    private void declareWinner() {
        textLabel.setText(currentPlayer.getName() + " wins!");
        gameOver = true;
    }

    private void switchTheme() {
        Theme current = GameManager.getInstance().getTheme();
        if (current instanceof AnimalTheme) {
            GameManager.getInstance().setTheme(ThemeFactory.getTheme("Weather"));
        } else {
            GameManager.getInstance().setTheme(ThemeFactory.getTheme("Animal"));
        }
    }

    private void restartGame() {
        GameManager gm = GameManager.getInstance();
        initPlayers(gm.getTheme());
        currentPlayer = playerX;
        turns = 0;
        gameOver = false;
        textLabel.setText(currentPlayer.getName() + "'s turn");

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c].setText("");
                board[r][c].setBackground(gm.getTheme().getBackgroundColor());
            }
        }
    }

    @Override
    public void onThemeChanged(Theme theme) {
        textLabel.setBackground(theme.getBackgroundColor());
        restartGame();
    }
}
