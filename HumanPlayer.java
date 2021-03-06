import java.util.*;
public class HumanPlayer extends Player {

  // The GUI that the Human uses to input/output
  BoardGUI myGUI;
  // The number of the player (1 or 2)
  int number;

  public HumanPlayer(int number, BoardGUI myGUI) {
    this.number = number;
    this.myGUI = myGUI;
  }

  public String callSign() {
    return "Player "+number;
  }

  //Returns -1 for an undo, or any other move
  public int decideMove(Board b) {
    int move;
    while(true) {
      move = myGUI.getMove();
      if(move != 0 && (move==-1 || b.isValidMove(move))) {
        return move;
      }
    }
  }
}
