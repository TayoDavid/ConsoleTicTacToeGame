package Game;

import java.util.*;

/** 
 * @author Omotayo
 *
 * This program is an exercise in the book "Introduction to programming, Comprehensive Version" by Daniel Liang.
 * 
 * This program is a Tic Tac Toe game.
 */

public class TicTacToe {
	private static final int NUMBER_OF_ROW_N_COLUMN = 3;
	static String[][] gameBoard = new String[NUMBER_OF_ROW_N_COLUMN][NUMBER_OF_ROW_N_COLUMN]; 
	static String firstPlayer = "X";
	static String secondPlayer = "O";
	static String winner; 
	static int filledCells = 0;						// Counts the number of cells that have been marked as played.
	static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("Start Game!");
		System.out.println("\nThe first player is X and the second player is O.\n");
		
		// Print the initial state of the game.
		printGameState(gameBoard);
		
		// Start and continue playing until there is a winner or a draw.
		play(gameBoard);
		
		// This prints the state of the game and the completion of the game.
		printGameState(gameBoard);
		if (winner != null) {
			System.out.println("\nGame Over!");
			System.out.print("\nPlayer " + winner + " wins!" );
		}
	}
	
	/*
	 *  This method prints the current state of the game.
	 */
	public static void printGameState(String[][] board) {
		System.out.println("___________________");
		for (int i = 0; i < NUMBER_OF_ROW_N_COLUMN; i++) {
			System.out.print("|");
			
			for (int j = 0; j < NUMBER_OF_ROW_N_COLUMN; j++) {
				if (board[i][j] == null)
					System.out.print("     " + "|");
				else if (board[i][j] == "X")
					System.out.print("  X  " + "|");
				else
					System.out.print("  O  " + "|");
			}
			System.out.println("\n___________________");
		}
	}
	
	/*
	 *  This is the method that handles all the activities of the game.
	 *  This method calls the current player, checks if there is a winner
	 *  or calls the next player and also prints the current state of the 
	 *  game. 
	 */
	public static void play(String[][] board) {
		boolean someOneWins = false;
		boolean gameCheck;
		
		do {
			System.out.println();
			currentPlayer(firstPlayer);
			gameCheck = check(board);
			if (gameCheck == true) {
				someOneWins = true;
				winner = firstPlayer;
				continue;
			} else {
				System.out.println();
				printGameState(gameBoard);
			}
			
			System.out.println();
			currentPlayer(secondPlayer);
			gameCheck = check(board);
			if (gameCheck == true) {
				someOneWins = true;
				winner = secondPlayer;
			} else {
				System.out.println();
				printGameState(gameBoard);
			}
			
		} while (someOneWins == false );	
	}

	/*
	 * This method handles the taking of turns of the players.
	 */
	public static void currentPlayer (String player) {
		if (filledCells == 9) {
			System.out.println("\nThe game is a DRAW. \n");
			System.exit(1);
		} else {

			System.out.print("Enter a row (0, 1, 2) for player " + player + " : ");
			int row = input.nextInt();
			System.out.print("Enter a column (0, 1, 2) for player " + player + " : ");
			int col = input.nextInt();
			
			if (gameBoard[row][col] == null) {
				gameBoard[row][col] = player;
				filledCells++;
			} else {
				System.out.println("\nSorry! This location is not empty. Please try again.\n");
				currentPlayer(player);
			}
		}
	}

	/*
	 * This method checks if there is a winner in the game.
	 */
	public static boolean check(String[][] board) {
		boolean thereIsAWinner = false;
		
		// This loop checks the rows for a winner.
		for (int i = 0; i < board.length; i++) {
			int countX = 0;
			int countO = 0;
			
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == "X") {
					countX++;
				} else if (board[i][j] == "O") {
					countO++;
				}
			}
			
			if (countX == 3) thereIsAWinner = true;
			if (countO == 3) thereIsAWinner = true;
		}

		// This loop checks the columns for a winner.
		for (int j = 0; j < board.length; j++) {
			
			int countX = 0;
			int countO = 0;
			
			for (int i = 0; i < board.length; i++) {
				if (board[i][j] == "X") {
					countX++;
				} else if (board[i][j] == "O") {
					countO++;
				}
			}
			
			if (countX == 3) thereIsAWinner = true;
			if (countO == 3) thereIsAWinner = true;
		}
		
		// This will check the backward diagonal for a winner.
		int backwardDiagonalCountForX = 0;
		int backwardDiagonalCountForO = 0;
		int diagonalRow = 0;
		int diagonalColumn = 0;
		
		do {
			if (board[diagonalRow][diagonalColumn] == "X")
				backwardDiagonalCountForX++;
			else if (board[diagonalRow][diagonalColumn] == "O")
				backwardDiagonalCountForO++;
				
			diagonalRow++;
			diagonalColumn++;
				
		} while (diagonalRow < 3);
			
		if (backwardDiagonalCountForX == 3) thereIsAWinner = true;
		if (backwardDiagonalCountForO == 3) thereIsAWinner = true;
	
		// This will check the forward diagonal for a winner.
		int forwardDiagonalCountForX = 0;
		int forwardDiagonalCountForO = 0;
		int fDiagonalRow = 0;
		int fDiagonalColumn = 2;
		
		do {
			if (board[fDiagonalRow][fDiagonalColumn] == "X")
				forwardDiagonalCountForX++;
			else if (board[fDiagonalRow][fDiagonalColumn] == "O")
				forwardDiagonalCountForO++;
				
			fDiagonalRow++;
			fDiagonalColumn--;
				
		} while (fDiagonalRow < 3);
			
		if (forwardDiagonalCountForX == 3) thereIsAWinner = true;
		if (forwardDiagonalCountForO == 3) thereIsAWinner = true;
			
		return thereIsAWinner;
	}
	  
}