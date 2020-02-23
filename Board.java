/* *****************************************************************************
 *  Name:    Minh Vu
 *  NetID:
 *  Precept: P00
 *
 *  Description:  Model an n-by-n percolation system using the union-find
 *                data structure.
 **************************************************************************** */


import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Board {
    private final int n; // size of board
    private int[][] block;
    private int hamming;
    private int manhattan;
    private int blankR;
    private int blankC;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        // size
        n = tiles.length;
        this.hamming = 0;
        this.manhattan = 0;
        int actualPos;
        int goalRow;
        int goalCol;
        int colDiff;
        int rowDiff;
        // construct board
        this.block = new int[tiles.length][tiles.length];
        for (int r = 0; r < this.n; r++) {
            for (int c = 0; c < this.n; c++) {
                // copy
                this.block[r][c] = tiles[r][c];
                // actual position
                actualPos = r * this.n + c + 1;
                // check if this is a blank position
                if (tiles[r][c] == 0) {
                    this.blankR = r;
                    this.blankC = c;
                } else {
                    // check hamming
                    if (tiles[r][c] != actualPos) this.hamming++;
                    // manhattan distance
                    goalRow = (tiles[r][c] - 1) / tiles.length; // the row we want to get our tile to
                    rowDiff = Math.abs(goalRow - r);
                    goalCol = (tiles[r][c] - 1) % tiles.length; // the col we want to get our tile to
                    colDiff = Math.abs(goalCol - c);
                    this.manhattan += (rowDiff + colDiff);
                }
            }
        }
    }

    // helper function to copy a Board
    private Board copy(Board a) {
        return new Board(a.block);
    }

    // string representation of this board
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.n + "\n");

        for (int r = 0; r < this.block.length; r++) {
            for (int c = 0; c < this.block.length; c++) {
                result.append(String.format("%2d ", tileAt(r, c)));
            }
            result.append("\n");
        }

        return result.toString();
    }

    // tile at (row, col) or 0 if blank
    public int tileAt(int row, int col) {
        if ((0 <= row && row < this.n) && (0 <= col && col < this.n)) {
            return this.block[row][col];
        } else {
            throw new IllegalArgumentException("Row or Col is out of bound");
        }
    }

    // board size n
    public int size() {
        return this.n;
    }

    // number of tiles out of place
    public int hamming() {
        return this.hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return this.manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.hamming() == 0;
    }

    // helper method to generate a 1d array from a 2d array of a block of board a
    private static int[] to1d(Board a) {
        int[] result = new int[a.n * a.n];
        for (int r = 0; r < a.n; r++) {
            for (int c = 0; c < a.n; c++) {
                result[r * a.n + c] = a.block[r][c];
            }
        }

        return result;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        // we will check from the biggest traits to the smallest
        // if y and this is the same object
        if (y == this) return true;
        // if y is null
        if (y == null) return false;
        // if y and this doesn't share the same class, they cannot equal
        if (y.getClass() != this.getClass()) return false;
        // if they are the same class (board), check their block
        if (!Arrays.equals(to1d((Board) y), to1d(this))) return false;

        // we cover all cases
        return true;
    }

    // helper methods to move the tile by 1 spot
    private Board moveLeft(Board a) {
        // check corner case
        if (a.blankC + 1 == a.n) return a;
        a.block[blankR][blankC] = a.block[blankR][blankC + 1];
        a.block[blankR][blankC + 1] = 0;
        a = new Board(a.block);
        return a;
    }

    private Board moveRight(Board a) {
        // check corner case
        if (a.blankC == 0) return a;
        a.block[blankR][blankC] = a.block[blankR][blankC - 1];
        a.block[blankR][blankC - 1] = 0;
        a = new Board(a.block);
        return a;
    }

    private Board moveUp(Board a) {
        // check corner case
        if (a.blankR + 1 == a.n) return a;
        a.block[blankR][blankC] = a.block[blankR + 1][blankC];
        a.block[blankR + 1][blankC] = 0;
        a = new Board(a.block);
        return a;
    }

    private Board moveDown(Board a) {
        // check corner case
        if (a.blankR == 0) return a;
        a.block[blankR][blankC] = a.block[blankR - 1][blankC];
        a.block[blankR - 1][blankC] = 0;
        a = new Board(a.block);
        return a;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> result = new Stack<Board>();
        Board neighbor1 = copy(this);
        neighbor1 = moveDown(neighbor1);
        Board neighbor2 = copy(this);
        neighbor2 = moveUp(neighbor2);
        Board neighbor3 = copy(this);
        neighbor3 = moveLeft(neighbor3);
        Board neighbor4 = copy(this);
        neighbor4 = moveRight(neighbor4);

        if (!neighbor1.equals(this)) result.push(neighbor1);
        if (!neighbor2.equals(this)) result.push(neighbor2);
        if (!neighbor3.equals(this)) result.push(neighbor3);
        if (!neighbor4.equals(this)) result.push(neighbor4);

        return result;
    }

    // is this board solvable?
    public boolean isSolvable() {
        // count inversions
        int inversion = 0;
        int[] board1D = to1d(this);
        for (int i = board1D.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (board1D[i] != 0 && board1D[j] != 0 && board1D[i] < board1D[j]) {
                    inversion++;
                }
            }
        }
        // when the board is odd-sized
        if (this.n % 2 == 1) {
            return inversion % 2 == 0;
        } else { // when the board is even sized
            return (inversion + this.blankR) % 2 == 1;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        int[][] a2d = {{1, 2, 3},
                {0, 7, 6},
                {5, 4, 8}};

        Board a = new Board(a2d);

        StdOut.println(a.toString());
        int[] minh = to1d(a);
        for (int i = 0; i < minh.length; i++) {
            StdOut.print(minh[i]);
        }

        StdOut.println("    " + a.n + " h" + a.hamming + " m" + a.manhattan + " bR" + a.blankR + " bC" + a.blankC);

        StdOut.println(a.isSolvable());

        StdOut.println(a.neighbors());
    }

}
