// Abstract Class for any 2-Dimensional game board.
import java.util.ArrayList;

public abstract class Board {

  private ArrayList<Integer> history = new ArrayList<Integer>();

  // Return the total number of rows of the Board
  abstract int getRows();

  // Return the total number of Columns of the Board
  abstract int getCols();
  
 /*
  * Return the position of the winning picese to draw a line for 
  * notifying the players how did they win
  */
  abstract int getWinningRow1();
  
  abstract int getWinningRow2();
  
  abstract int getWinningCol1();
  
  abstract int getWinningCol2();
  
  /*
   * Return the piece at row r, column c on the board.
   * Row 1 means the bottom row
   * Column 1 means the left most column
   */
  abstract int getBoardPiece(int r, int c);

  /*
   * Make a move for the current player
   * Return true if the move was successfully made
   * Return false otherwise
   */
  public boolean makeMove(int move){
    if(!isValidMove(move)) {
      return false;
    }
    history.add(new Integer(move));
    return true;
  }

  /*
   * Remove the top peice in the given column
   * Return true if the removal was successfully done
   * Return false otherwise
   */
  abstract boolean undoMove(int move);

  /*
   * Return 0 if no player has won this board.
   * Return 1 is Player 1 has won this board.
   * Return 2 if Player 2 has won this board.
   * Return 3 if this is a draw.
   */
  abstract int isWinning();

  abstract boolean isValidMove(int move);

  abstract int minMove();

  abstract int maxMove();

  public ArrayList<Integer> validMoves() {
    ArrayList<Integer> validMoves = new ArrayList<Integer>();
    for (int i = this.minMove(); i <= this.maxMove(); i++ ) {
      if (this.isValidMove(i)) {
        validMoves.add(new Integer(i));
      }
    }
    return validMoves; // placeholder
  }

  public int numTotalMoves(){
    return history.size();
  }

  public int nextTurn(){
    return ((history.size() % 2) + 1);
  }

  public void revert(int moveNum) {
    if (moveNum < 0) {
      moveNum = 0;
    }
    int NumMovesToUndo = history.size() - moveNum;
  
    for (int i = 0; i < NumMovesToUndo; i++) {
      this.undoMove(history.get(history.size()-1).intValue());
      history.remove(history.size()-1);
    }
  }
}
