package io.github.maasencioh.conecta4;

import java.util.Arrays;

/**
 * Project: Conecta4
 * Created by maasencioh
 */
public class Game {

    // board of the game
    private int emptyPlaces;
    private int rows = 6;
    private int columns = 7;
    private int board [][] = new int[rows][columns];
    public int level = 0;
    private int[] levels = {1,2,4};

    /**
     * Restart the game
     */
    public void restart() {
        emptyPlaces = columns*rows;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < columns; j++)
                this.board[i][j] = 0;
    }

    /**
     * Constructor: initializes board with zeros
     */
    public Game() {
        restart();
    }

    /**
     * @param i {number}: row of the position
     * @param j {number}: column of the position
     * @return {bool}: if it's possible put a piece into the (i,j) position
     */
    boolean isPossible(int i, int j) {
        boolean last = true;
        for (int k = i + 1; k < rows; k++)
            last = last && (this.board[k][j] != 0);
        return (this.board[i][j] == 0) && last;
    }

    /**
     * The machine turn to play
     */
    void machineTurn() {
        int lvl = this.levels[this.level];
        System.out.println(lvl);
        if (emptyPlaces > 0) {
            int i, j;
            do {
                i = (int) (Math.random() * rows);
                j = (int) (Math.random() * columns);
            } while (!isPossible(i, j));
            int machineId = 2;
            board[i][j] = machineId;
            emptyPlaces -= 1;
        }
    }

    /**
     * Inserts a piece into the (i,j) position
     * @param i {number}: row of the position
     * @param j {number}: column of the position
     * @return {bool}: success insertion
     */
    boolean setBoard(int i, int j) {
        if (isPossible(i, j)) {
            int humanId = 1;
            board[i][j] = humanId;
            emptyPlaces -= 1;
            return true;
        }
        return false;
    }

    /**
     * Ask for the (i,j) position
     * @param i {number}: row of the position
     * @param j {number}: column of the position
     * @return {number}: id of the current position
     */
    int getBoard(int i, int j) {
        return board[i][j];
    }

    /**
     * Check if the current player has 4 in row
     * @param playerTurn {number}: id of the player
     * @return {boolean}: if the current player wins
     */
    boolean checkRows (int playerTurn) {
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < (columns - 3); j++) {
                if ((board[i][j] == playerTurn) && (board[i][j + 1] == playerTurn) &&
                        (board[i][j + 2] == playerTurn) && (board[i][j + 3] == playerTurn)) {
                    return true;
                }
            }
        return false;
    }

    /**
     * Check if the current player has 4 in column
     * @param playerTurn {number}: id of the player
     * @return {boolean}: if the current player wins
     */
    boolean checkColumns (int playerTurn) {
        for (int j = 0; j < columns; j++)
            for (int i = 0; i < (rows - 3); i++) {
                if ((board[i][j] == playerTurn) && (board[i + 1][j] == playerTurn) &&
                        (board[i + 2][j] == playerTurn) && (board[i + 3][j] == playerTurn)) {
                    return true;
                }
            }
        return false;
    }

    /**
     * Check if the current player has 4 in diagonal
     * @param playerTurn {number}: id of the player
     * @return {boolean}: if the current player wins
     */
    boolean checkDiagonals (int playerTurn) {
        for (int j = 0; j < columns; j++)
            for (int i = 0; i < (rows - 3); i++) {
                try {
                    if ((board[i][j] == playerTurn) && (board[i + 1][j + 1] == playerTurn) &&
                            (board[i + 2][j + 2] == playerTurn) && (board[i + 3][j + 3] == playerTurn)) {
                        return true;
                    } else if ((board[i][j] == playerTurn) && (board[i + 1][j - 1] == playerTurn) &&
                            (board[i + 2][j - 2] == playerTurn) && (board[i + 3][j - 3] == playerTurn)) {
                        return true;
                    }
                }
                catch (IndexOutOfBoundsException ignored) {}
            }
        return false;
    }

    /**
     * Check if the current player wins the match
     * @param playerTurn {number}: id of the player
     * @return {boolean}: if the current player wins
     */
    boolean checkWinner (int playerTurn) {
        return checkRows(playerTurn) || checkColumns(playerTurn) || checkDiagonals(playerTurn);
    }
}
