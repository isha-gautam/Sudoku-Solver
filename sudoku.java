package Assignments;

import java.util.Scanner;

public class sudoku {

	static Scanner scan = new Scanner(System.in);

//	driver function
	public static void main(String[] args) {

		int[][] board = createBoard();
//		display(board);
		boolean isSolved = solveSudoku(board, 0, 0);
		if (!isSolved)
			System.out.println("Sudoku is unsolvable!");
	}

//	actual function to solve sudoku
	public static boolean solveSudoku(int[][] board, int row, int col) {

		if (row == board.length) {
			display(board);
			return true;
		}

		// move to the next row
		if (col == board[0].length)
			return solveSudoku(board, row + 1, 0);

		// in case the cell already holds a digit
		if (board[row][col] != 0)
			return solveSudoku(board, row, col + 1);

		for (int i = 1; i <= 9; i++) {

			if (isPossible(board, row, col, i)) {
				board[row][col] = i;
				boolean ans = solveSudoku(board, row, col + 1);

				if (ans)
					return true;

				board[row][col] = 0;

			}

		}
		return false;

	}

//	check against placing of a digit
	private static boolean isPossible(int[][] board, int row, int col, int value) {

		if (!isPossibleRow(board, row, col, value))
			return false;
		if (!isPossibleCol(board, row, col, value))
			return false;
		if (!isPossibleGrid(board, row, col, value))
			return false;

		return true;

	}

//	check against placing in a grid
	private static boolean isPossibleGrid(int[][] board, int row, int col, int value) {
		int srow = row - row % 3;
		int scol = col - col % 3;
		for (int i = srow; i < srow + 3; i++) {
			for (int j = scol; j < scol + 3; j++) {
				if (board[i][j] == value)
					return false;
			}
		}
		return true;
	}

//	check against placing in a column
	private static boolean isPossibleCol(int[][] board, int row, int col, int value) {

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == value)
				return false;
		}

		return true;

	}

//	check against placing in a row
	private static boolean isPossibleRow(int[][] board, int row, int col, int value) {

		for (int i = 0; i < board[0].length; i++) {
			if (board[row][i] == value)
				return false;
		}

		return true;

	}

//	takes input in the form of a 9x9 matrix with '0' to indicate empty cells
	public static int[][] createBoard() {

		int[][] board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				board[i][j] = scan.nextInt();
		}
		return board;

	}

//	displays the solved grid
	public static void display(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++)
				System.out.print(board[i][j] + " ");
			System.out.println();
		}
	}

}
