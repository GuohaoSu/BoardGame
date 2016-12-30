import java.util.*;
public class LookAheadAIPlayer extends Player {

  // The number of the player (1 or 2)
  int thisPlayer;
  int lookahead;

  public LookAheadAIPlayer(int thisPlayer, int lookahead) {
    this.thisPlayer = thisPlayer;
    this.lookahead = lookahead;
  }

  public String callSign() {
    switch(lookahead) {
      case(2): return "Easy AI";
      case(4): return "Medium AI";
      case(6): return "Hard AI";
    }
    return "Tournament AI";
  }

  public int decideMove(Board b) {
    int decideMove = 0;
    Random rndgen = new Random();
    int maxScore = Integer.MIN_VALUE;
    ArrayList<Integer> validMoves = b.validMoves();
    for(int i=0; i<validMoves.size();i++ ) {
      if (b.makeMove((validMoves.get(i)).intValue())) {
        if (b.isWinning() == thisPlayer) {
          b.revert(b.numTotalMoves()-1);
          return validMoves.get(i).intValue();
        }
        int boardScore = alphabeta(lookahead-1, Integer.MIN_VALUE, Integer.MAX_VALUE, false,b);
        b.revert(b.numTotalMoves()-1);
        if (boardScore > maxScore) {
          maxScore = boardScore;
          decideMove = validMoves.get(i).intValue();
        }
        if (maxScore == -1) {
          decideMove = validMoves.get(rndgen.nextInt(validMoves.size())).intValue();
        }
      }
    }
    return decideMove;
  }
  
  public int alphabeta(int depth, int alpha, int beta, boolean maximizingPlayer, Board b) {
    ArrayList<Integer> childValidMoves  = b.validMoves();
    int boardValue = b.isWinning();
    if (depth == 0 || boardValue != 0) {
      if (boardValue == thisPlayer) {
        return 1;
      }
      if (boardValue == 3-thisPlayer) {
        return -1;
      }
      return boardEval(b);
    }
    
    int value;
    if (maximizingPlayer) {
      value = Integer.MIN_VALUE;
      for (int i = 0; i <childValidMoves.size(); i++) {
        if (b.makeMove((childValidMoves.get(i)).intValue())) {
          value = Math.max(value, alphabeta(depth - 1, alpha, beta, false, b));
          b.revert(b.numTotalMoves()-1);
          alpha = Math.max(alpha, value);
          if (beta <= alpha) {
            break;
          }
        }
      }
      return value;
    }
    else {
      value = Integer.MAX_VALUE;
      for (int i = 0; i < childValidMoves.size(); i++) {
        if (b.makeMove((childValidMoves.get(i)).intValue())) {
          value = Math.min(value, alphabeta(depth - 1, alpha, beta, true, b));
          b.revert(b.numTotalMoves()-1);
          beta = Math.min(beta, value);
          if (beta <= alpha) {
            break;
          }
        }
      }
      return value;
    }
  }
  
  public static int boardEval(Board b) {
    return 0;
  }

}
