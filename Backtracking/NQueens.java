package Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    private final static int N = 4;
    private static List<List<String>> res;

    public static void main(String[] args) {
        res = new ArrayList<>();
        final var board = new char[N][N];

        for (int i = 0; i < N; i++) Arrays.fill(board[i], '.');

        placeNQueens(board, N, 0);
        System.out.println(res.size() + " solutions found.");
        res.forEach(System.out::println);
    }

    private static void placeNQueens(final char[][] board, int n, int row) throws IllegalArgumentException {
        if (n < 4) throw new IllegalArgumentException("board must be greater than 3x3");
        if (row == board.length) {
            res.add(Arrays.stream(board).map(String::valueOf).toList());
        } else {
            for (int col = 0; col < n; col++) {
                if (validatedQueenPosition(board, n, row, col)) {
                    board[row][col] = 'Q';
                    placeNQueens(board, n, row + 1);
                    board[row][col] = '.';
                }
            }
        }
    }

    // checks if queen can be placed in the given position
    private static boolean validatedQueenPosition(char[][] board, int n, int row, int col) {

        // check if queen can be placed in the given row given col
        for (int k = 0; k < row; k++) {
            if (board[k][col] == 'Q') return false;
        }

        // check if queen can be placed diagonally - upper left side
        for (int k = row - 1, l = col - 1; k >= 0 && l >= 0; k--, l--) {
            if (board[k][l] == 'Q') return false;
        }

        // check if queen can be placed diagonally - upper right side
        for (int k = row - 1, l = col + 1; k >= 0 && l < n; k--, l++) {
            if (board[k][l] == 'Q') return false;
        }

        return true;
    }
}
