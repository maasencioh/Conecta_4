package io.github.maasencioh.conecta4;

import java.util.Arrays;

/**
 * Project: Conecta4
 * Created by maasencioh
 */
public class Game {

    // board of the game
    private int board [][] = new int[6][7];
    private int emptyPlaces;

    /**
     * Constructor: initializes board with zeros
     */
    public Game() {
        emptyPlaces = 7*6;
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 7; j++)
                this.board[i][j] = 0;
    }

    /**
     * @param i {number}: row of the position
     * @param j {number}: column of the position
     * @return {bool}: if it's possible put a piece into the (i,j) position
     */
    boolean isPossible(int i, int j) {
        boolean last = true;
        for (int k = i + 1; k < 6; k++)
            last = last && (this.board[k][j] != 0);
        return (this.board[i][j] == 0) && last;
    }

    /**
     * The machine turn to play
     */
    void machineTurn() {
        if (emptyPlaces > 0) {
            int i, j;
            do {
                i = (int) (Math.random() * 6);
                j = (int) (Math.random() * 7);
            } while (!isPossible(i, j));
            board[i][j] = 2;
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
            board[i][j] = 1;
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
}
