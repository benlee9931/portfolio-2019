/** 
 * name: Lee Vin Hung e-mail:vhl014@ucsd.edu
 * This file contains Piece class that emphasizes  
 * on the backend of the tetris pieces in game.
 * */

import java.util.*;

/** This class contains methods for the pieces in tetris.
 * It includes the matrices of the pieces, possible shapes 
 * in the game, and the position of the pieces */
public class Piece {

  // all possible char representation of a piece
  public static final char[] possibleShapes = 
  {'O', 'I', 'S', 'Z', 'J', 'L', 'T'}; 

  // initial state of all possible shapes, notice that this array's 
  // content shares index with the possibleShapes array 
  public static final int[][][] initialTiles = {
    {{1,1},
      {1,1}}, // O

    {{0,0,0,0},
      {1,1,1,1},
      {0,0,0,0},
      {0,0,0,0}}, // I

    {{0,0,0},
      {0,1,1},
      {1,1,0}}, // S

    {{0,0,0},
      {1,1,0},
      {0,1,1}}, // Z

    {{0,0,0},
      {1,1,1},
      {0,0,1}}, // J

    {{0,0,0},
      {1,1,1},
      {1,0,0}}, // L

    {{0,0,0},
      {1,1,1},
      {0,1,0}} // T
  };  

  // random object used to generate a random shape
  public static Random random = new Random(); 

  // char representation of shape of the piece, I, J, L, O, S, Z, T
  public char shape;

  // the position of the upper-left corner of the tiles array 
  // relative to the Tetris grid
  public int rowOffset;
  public int colOffset;

  // used to determine 2-state-rotations for shapes S, Z, I
  // set to true to indicate the next call to rotate() should
  // rotate clockwise
  public boolean rotateClockwiseNext = false;

  // an array marking where the visible tiles are
  // a 1 indicates there is a visible tile in that position
  // a 0 indicates there is no visible tile in that position
  public int[][] tiles;


  //No arg constructor 
  public Piece(){
    //Creates a random number between 0 & 7
    int ran = random.nextInt(7);
    //Assigns one of the char from possible shape array to shape
    shape = possibleShapes[(ran)];
    System.out.println(shape);
    //Creates a new tiles array
    tiles  = new int [initialTiles[ran].length][initialTiles[ran].length];
    for ( int row = 0; row < initialTiles[ran].length; row++) {
      for (int col = 0; col < initialTiles[ran][row].length; col++) {
        tiles[row][col] = initialTiles[ran][row][col];
        System.out.println(tiles[row][col]);
      }
    }
    if (ran == 0) {
      rowOffset = 0;
      colOffset = 4;
    }
    else {
      rowOffset = -1;
      colOffset = 3;
    }
  }
  //Constructor with a char argument
  public Piece(char shape) {
    //Sets the instance variable shape to the parameter char
    this.shape = shape;
    //Deep copies the initial tiles array to tiles
    for ( int i = 0; i < possibleShapes.length; i++) {
      if (possibleShapes[i] == shape) {
    int n = i;
      
    tiles  = new int [initialTiles[n].length][initialTiles[n].length];
    for ( int row = 0; row < initialTiles[n].length; row++) {
      for (int col = 0; col < initialTiles[n][row].length; col++) {
        tiles[row][col] = initialTiles[n][row][col];
        System.out.println(tiles[row][col]);
      }
    }
      }
    }
    if (shape == 'O') {
      rowOffset = 0;
      colOffset = 4;
    }
    else {
      rowOffset = -1;
      colOffset = 3;

  }

  }
  //Copy constructor for piece  
  public Piece(Piece other){
    this.shape = other.shape;
    this.rowOffset = other.rowOffset;
    this.colOffset = other.colOffset;
    this.rotateClockwiseNext = other.rotateClockwiseNext;
    this.tiles = new int[other.tiles.length][other.tiles.length];
    for ( int row = 0; row < other.tiles.length; row++) {
      for (int col = 0; col < other.tiles[row].length; col++) {
        tiles[row][col] = other.tiles[row][col];
        System.out.println(tiles[row][col]);
      }
    }
    

  }
  //Method to rotate the tiles
  public void rotate(){
    if (shape == 'O'|| shape == 'T'|| shape == 'J' || shape == 'L'){
      this.rotateClockwise();
    }
    else  {
      if (rotateClockwiseNext == false) {
        this.rotateCounterClockwise();
      }
      else {
      this.rotateClockwise();
      }
    }
  }
  
  //Method to rotate the tiles clockwise 
  public void rotateClockwise() {
   int [][] cTiles = new int [tiles.length][tiles.length];
    for ( int row = 0; row < tiles.length; row++) {
      for (int col = 0; col < tiles[row].length; col++) {
        cTiles [row][col] = tiles[row][col];
      }
    }

    for ( int i= 0; i < cTiles.length; i++) {
      for (int j = 0; j < cTiles.length; j++) {
        tiles[j][tiles.length-1-i] = cTiles[i][j];
      }
    }

  }
  //rotates the tiles counter clockwise 
  public void rotateCounterClockwise () {
   int [][] cTiles = new int [tiles.length][tiles.length];
    for ( int row = 0; row < tiles.length; row++) {
      for (int col = 0; col < tiles[row].length; col++) {
        cTiles [row][col] = tiles[row][col];
      }
    }

    for ( int i= 0; i < cTiles.length; i++) {
      for (int j = 0; j < cTiles.length; j++) {
        tiles[tiles.length-1-j][i] = cTiles[i][j];
      }
    }

    
  }

  //Moves the tiles 
  public void move(Direction direction) {
    if ( direction == Direction.RIGHT) {
      colOffset += 1;
    }
    else if (direction == Direction.LEFT) {
      colOffset -= 1;
    }
    else {
      rowOffset -= 1;
    }
  }
  //To test the result
  public static void main (String[] args) {
    Piece a = new Piece('S');
    Piece b = new Piece(a);
    System.out.println("A's shape: " + a.shape);
    System.out.println("B's shape: " + b.shape);

    System.out.println("A's rowOff: " + a.rowOffset);
    System.out.println("B's rowOff: " + b.rowOffset);

    System.out.println("A's colOff: " + a.colOffset);
    System.out.println("B's colOff: " + b.colOffset);

    System.out.println("A's rot: " + a.rotateClockwiseNext);
    System.out.println("B's rot: " + b.rotateClockwiseNext);

    for ( int row = 0; row < a.tiles.length; row++) {
      for (int col = 0; col < a.tiles[row].length; col++) {

    System.out.print  (a.tiles[row][col]+ " ");
    

  
      }
      System.out.println();
    }
    a.rotate();
    for ( int row = 0; row < a.tiles.length; row++) {
      for (int col = 0; col < a.tiles[row].length; col++) {

    System.out.print  (a.tiles[row][col]+ " ");
    

  
      }
      System.out.println();
    }
  }

}
