
package game;

import db.DataBaseManager;

import java.util.Scanner;

public class TicTacToeGame {
    private char[][] board = new char[3][3];
    private String player1, player2;
    private final DataBaseManager dbManager = new DataBaseManager();

    public TicTacToeGame() {
        initializeBoard();
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Player 1 name:");
        player1 = scanner.nextLine();
        System.out.println("Enter Player 2 name:");
        player2 = scanner.nextLine();

        boolean playAgain;
        do {
            runGameLoop(scanner);
            System.out.println("Do you want to play again? (yes/no):");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        } while (playAgain);
        scanner.close();
    }

    private void runGameLoop(Scanner scanner) {
        char currentPlayer = 'X';
        String currentName = player1;
        boolean gameWon = false;
        int moves = 0;

        while (!gameWon && moves < 9) {
            displayBoard();
            System.out.println(currentName + ", enter your move (row and column):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;
                moves++;
                gameWon = checkWin(currentPlayer);

                if (gameWon) {
                    displayBoard();
                    System.out.println(currentName + " wins!");
                    dbManager.updateLeaderboard(currentName, moves, System.currentTimeMillis());
                } else if (moves == 9) {
                    displayBoard();
                    System.out.println("It's a draw!");
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                    currentName = (currentPlayer == 'X') ? player1 : player2;
                }
            } else {
                System.out.println("Invalid move, try again.");
            }
        }
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-';
    }

    private boolean checkWin(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
               (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    private void displayBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
