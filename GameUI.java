package ui;

import game.TicTacToeGame;

import java.util.Scanner;

public class GameUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");
        boolean exitGame = false;

        while (!exitGame) {
            System.out.println("1. Start Game\n2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    TicTacToeGame game = new TicTacToeGame();
                    game.play();
                    break;
                case 2:
                    exitGame = true;
                    System.out.println("Thank you for playing Tic Tac Toe!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
}
