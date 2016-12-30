import java.util.*;
public class TournamentPlayer extends Player {
  
  int thisPlayer;
  int lookahead;
  
  public TournamentPlayer(int thisPlayer, int lookahead) {
    this.thisPlayer = thisPlayer;
    this.lookahead = lookahead;
  }

  public String callSign() {
    return "SuperScott";
  }
  
  public int decideMove(Board b) {
    int decideMove = 0;
    Random rndgen = new Random();
    int maxScore = Integer.MIN_VALUE;
    ArrayList<Integer> validMoves = b.validMoves();
    ArrayList<Integer> maxMoves = new ArrayList<Integer>();
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
          maxMoves.clear();
        }
        if (boardScore == maxScore) {
          maxMoves.add(new Integer(validMoves.get(i).intValue()));
        }
        
        decideMove = maxMoves.get(rndgen.nextInt(maxMoves.size())).intValue();
      }
    }
    return decideMove;
  }
  
  public int alphabeta(int depth, int alpha, int beta, boolean maximizingPlayer, Board b) {
    ArrayList<Integer> childValidMoves  = b.validMoves();
    int boardValue = b.isWinning();
    if (depth == 0 || boardValue != 0) {
      if (boardValue == thisPlayer) {
        return (500 + depth);
      }
      if (boardValue == 3-thisPlayer) {
        return (-500 - depth);
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
  
  public int boardEval(Board b) {
    int boardScore = 0;
    // Add/subtract points for our/opponent's pieces near the center because they have higher chance to link
    for (int i = 0; i < 5; i++) {
      for (int j = 2; j < 5; j++) {
        if (b.getBoardPiece(i,j) == thisPlayer) {
          boardScore += 3;
        }
        if (b.getBoardPiece(i,j) == (3-thisPlayer)) {
          boardScore -= 3;
        }
      }
    }
    
    // For two continuous pieces
    
    // Add/subtract points for two continuous pieces in column in our/opponent's turns
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 7; j++) {
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j) && b.getBoardPiece(i,j) == thisPlayer) {
          boardScore += 3;
        }
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j) && b.getBoardPiece(i,j) == (3-thisPlayer)) {
          boardScore -= 3;
        }
      }
    }
    // Add/subtract points for two continuous pieces in a row in our/opponent's turns
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i,j+1) && b.getBoardPiece(i,j) == thisPlayer) {
          boardScore += 3;
        }
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i,j+1) && b.getBoardPiece(i,j) == (3-thisPlayer)) {
          boardScore -= 3;
        }
      }
    }
    // Add/subtract points for two continuous pieces in a left diagonal line in our/opponent's turns
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 6; j++) {
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j+1) && b.getBoardPiece(i,j) == thisPlayer) {
          boardScore += 3;
        }
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j+1) && b.getBoardPiece(i,j) == (3-thisPlayer)) {
          boardScore -= 3;
        }
      }
    }
    // Add/subtract points for two continuous pieces in a right diagonal line in our/opponent's turns
    for (int i = 0; i < 5; i++) {
      for (int j = 6; j > 0; j--) {
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j-1) && b.getBoardPiece(i,j) == thisPlayer) {
          boardScore += 3;
        }
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j-1) && b.getBoardPiece(i,j) == (3-thisPlayer)) {
          boardScore -= 3;
        }
      }
    }
    
    // For three continuous pieces
    
    // Add/subtract points for three continuous pieces in column in our/opponent's turns
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 7; j++) {
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j) && b.getBoardPiece(i,j) == thisPlayer) {
          boardScore += 10;
        }
         if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j) && b.getBoardPiece(i,j) == (3-thisPlayer)) {
          boardScore -= 10;
         }
      }
    }
    // Add/subtract points for three continuous pieces in a row in our/opponent's turns
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 6; j++) {
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i,j+1) && b.getBoardPiece(i,j) == thisPlayer) {
          boardScore += 10;
        }
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i,j+1) && b.getBoardPiece(i,j) == (3-thisPlayer)) {
          boardScore -= 10;
         }
      }
    }
    // Add/subtract points for three continuous pieces in a left diagonal line in our/opponent's turns
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 6; j++) {
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j+1) && b.getBoardPiece(i,j) == thisPlayer) {
          boardScore += 10;
        }
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j+1) && b.getBoardPiece(i,j) == (3-thisPlayer)) {
          boardScore -= 10;
         }
      }
    }
    // Add/subtract points for three continuous pieces in a right diagonal line in our/opponent's turns
    for (int i = 0; i < 5; i++) {
      for (int j = 6; j > 0; j--) {
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j-1) && b.getBoardPiece(i,j) == thisPlayer) {
          boardScore += 10;
        }
        if (b.getBoardPiece(i,j) == b.getBoardPiece(i+1,j-1) && b.getBoardPiece(i,j) == (3-thisPlayer)) {
          boardScore -= 10;
         }
      }
    }
    


    
    return boardScore;
  }
  
}
