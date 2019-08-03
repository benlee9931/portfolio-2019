/** your file header here */

import java.util.*;
import java.io.*;

/** your class header here */
public class Tetris {

    public int linesCleared; // how many lines cleared so far

    public boolean isGameover;  // true if the game is over
                                // false if the game is not over

    public Piece activePiece;   // holds a Piece object that can be moved
                                // or rotated by the player

    public Piece nextPiece; // holds a Piece object that will become the new 
                            // activePiece when the activePiece consolidates

    // The following 2 variables are used for the extra credit 
    public boolean usedHold = false;    // set to true if the player already 
                                        // used hold once since last piece 
                                        // consolidated

    public Piece storedPiece = null;    // holds the stored piece 

    public char[][] grid;   // contains all consolidated pieces, each tile  
                            // represented by a char of the piece's shape
                            // a position stores a space char if it is empty


    public Tetris(){
        // TODO
    }


    public Tetris (String filename) throws IOException {
        // TODO
    }


    public boolean hasConflict(Piece piece) {
        // TODO
        return false;
    }


    public void consolidate() {
        // TODO
    }


    public void clearLines() {
        // TODO
    }


    public boolean move(Direction direction) {
        // TODO
        return false;
    }

    public void drop() {
        // TODO
    }

    public void rotate() {
        // TODO
    }

    public void outputToFile() throws IOException {
        // TODO
        System.out.println("Saved to output.txt");
    }

    public void play () {
        // TODO
    }

    /**
     * returns the string representation of the Tetris game state in the 
     * following format:
     *  Lines cleared: [number]
     *  Next piece: [char]  (Stored piece: [char])
     *  char[20][10]
     * @return string representation of the Tetris game
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("\nLines cleared: " + this.linesCleared + '\n');

        str.append("Next piece: " + this.nextPiece.shape);
        if (this.storedPiece == null) str.append("\n");
        else str.append("  Stored piece: " + this.storedPiece.shape + '\n');

        str.append("| - - - - - - - - - - |\n");

        /*deep copy the grid*/
        char[][] temp_grid = new char[this.grid.length][this.grid[0].length];
        for (int row=0; row<this.grid.length; row++)
            for (int col=0; col<this.grid[0].length; col++)
                temp_grid[row][col] = this.grid[row][col];
            
        /*put the active piece in the temp grid*/
        for (int row=0; row<this.activePiece.tiles.length; row++)
            for (int col=0; col<this.activePiece.tiles[0].length; col++)
                if (activePiece.tiles[row][col] == 1)
                    temp_grid[row+activePiece.rowOffset]
                             [col+activePiece.colOffset] = 
                                activePiece.shape;

        /*print the temp grid*/
        for (int row=0; row<temp_grid.length; row++){
            str.append('|');
            for (int col=0; col<temp_grid[0].length; col++){
                str.append(' ');
                str.append(temp_grid[row][col]);
            }
            str.append(" |\n");
        }

        str.append("| - - - - - - - - - - |\n");
        return str.toString();        
    }


    public void hold() {
        // TODO extra credit
    }

    /**
     * first method called during program execution
     * @param args: an array of String when running the program from the 
     * command line, either empty, or contains a valid filename to load
     * the Tetris game from
     */
    public static void main(String[] args) {
        if (args.length != 0 && args.length != 1) {
            System.err.println("Usage: java Tetris / java Tetris <filename>");
            return;
        }
        try {
            Tetris tetris;
            if (args.length == 0) tetris = new Tetris();
            else tetris = new Tetris(args[0]);
            tetris.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}