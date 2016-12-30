public class Connect4Board extends Board {
  
  private static int numRow = 6;
  private static int numCol = 7;
  private static char[][] gameBoard;
  // Add some new variables to store the winning pieces position so that after one player wins, I can draw a line to connect the winning pieces and show them where are the four continuous pieces in case they don't know how the other player wins or they just win by accident
  private int winningRow1 = 0;
  private int winningRow2 = 0;
  private int winningCol1 = 0;
  private int winningCol2 = 0;
  
  public int minMove() {
    return 1;
  }
  
  public int maxMove() {
    return 7;
  }
  
  // A new Board constructor
  public Connect4Board() {
    
    gameBoard = new char[numRow][numCol];
    
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        gameBoard[i][j] = ' ';
      }
    }
    
  }
  
  public int getRows() {
    return numRow;
  }
  
  public int getCols() {
    return numCol;
  }
  // Some getter method for the WinnerGUI to get the pieces position
  
  public int getWinningRow1() {
    return winningRow1;
  }
  
  public int getWinningRow2() {
    return winningRow2;
  }
  
  public int getWinningCol1() {
    return winningCol1;
  }
  
  public int getWinningCol2() {
    return winningCol2;
  }
  
  public int getBoardPiece(int row, int col) {
    char BoardPiece = ' ';
    int BoardPieceInInt = 0;
    BoardPiece = gameBoard[5-row][col];
    
    switch (BoardPiece) {
      case ' ': BoardPieceInInt = 0;
        break;
      case 'x': BoardPieceInInt = 1;
        break;
      case 'o': BoardPieceInInt = 2;
        break;
    }
    return BoardPieceInInt;
  }
  
  public boolean isValidMove(int move) {
    move--;
    return (move >= 0 && move < 7 && gameBoard[0][move] == ' ');
  }
  
  // To find out which piece the current player is using
  private char pieceOfSide(int nextTurn) {
    char pieceOfSide = ' ';
    if (nextTurn == 1) {
      pieceOfSide = 'x';
    } else if (nextTurn == 2) {
      pieceOfSide = 'o';
    }
    
    return pieceOfSide;
  }
  
  // To find out which piece is requested to be removed
  private char pieceToRemove(int nextTurn) {
    
    return pieceOfSide(3 - nextTurn);
  }
  
  // Find out which side just made a move and need to check isWinning
  private char pieceToWin(int nextTurn) {
    
    return pieceOfSide(3 - nextTurn);
  }
  
  // Use number to represent the player who won the game
  private int winningNum(int nextTurn) {
    
    int winningNum = 0;
    
    if ((3 - nextTurn) == 1) {
      winningNum = 1;
    } else if ((3 - nextTurn) == 2) {
      winningNum = 2;
    }
    
    return winningNum;
  }
  
  public boolean makeMove(int move) {
    
    if (!super.makeMove(move)) {
      return false;
    }
    move--;
    for (int i = 5; i >= 0; i--) {
      if (gameBoard[i][move] == ' ') {
        gameBoard[i][move] = pieceOfSide(3 - nextTurn());
        break;
      }
    }

    return true;
  }
  
  public boolean undoMove(int move) {
    
    boolean isValid = true;
    
    // There are two situations, one is to remove the piece on the top row of that column, the other is to remove the piece that is the top piece but not in the top row
    move--;
    
    if (move >= 0 && move <= 6) {
      if (gameBoard[0][move] != ' '){
        gameBoard[0][move] = ' ';
        return true;
      }
      for (int i = 5; i >= 0; i--) {
        if (gameBoard[i - 1][move] == ' ') {
          gameBoard[i][move] = ' ';
          return true;
        }
      }
    }
    
    return false;
  }
  
  public int isWinning() {
    int isWinning = 0;
    
    // winning with 4 continuous pieces in a column
    for (int j = 0; j < 7; j++) {
      for (int i = 5; i >= 3; i--) {
        if (gameBoard[i][j] == gameBoard[i - 1][j] && gameBoard[i - 1][j] == gameBoard[i - 2][j] && gameBoard[i - 2][j] == gameBoard[i - 3][j] && gameBoard[i - 3][j] == pieceToWin(nextTurn())) {
          isWinning = winningNum(nextTurn());
          winningRow1 = i;
          winningRow2 = i - 3;
          winningCol1 = j;
          winningCol2 = j;
          break;
        }
      }
    }
    
    // winning with 4 continuous pieces in a row
    for (int i = 5; i >= 0; i--) {
      for (int j = 0; j < 4; j++) {
        if (gameBoard[i][j] == gameBoard[i][j + 1] && gameBoard[i][j + 1] == gameBoard[i][j + 2] && gameBoard[i][j + 2] == gameBoard[i][j + 3] && gameBoard[i][j + 3] == pieceToWin(nextTurn())) {
          isWinning = winningNum(nextTurn());
          winningRow1 = i;
          winningRow2 = i;
          winningCol1 = j;
          winningCol2 = j + 3;
          break;
        }
      }
    }
    
    // winning with 4 continuous pieces in a left diagonal line
    for (int i = 5; i >= 3; i--) {
      for (int j = 0; j < 4; j++) {
        if (gameBoard[i][j] == gameBoard[i - 1][j + 1] && gameBoard[i - 1][j + 1] == gameBoard[i - 2][j + 2] && gameBoard[i - 2][j + 2] == gameBoard[i - 3][j + 3] && gameBoard[i - 3][j + 3] == pieceToWin(nextTurn())) {
          isWinning = winningNum(nextTurn());
          winningRow1 = i;
          winningRow2 = i - 3;
          winningCol1 = j;
          winningCol2 = j + 3;
          break;
        }
      }
    }
    
    // winning with 4 continuous pieces in a right diagonal line
    for (int i = 5; i >= 3; i--) {
      for (int j = 6; j > 2; j--) {
        if (gameBoard[i][j] == gameBoard[i - 1][j - 1] && gameBoard[i - 1][j - 1] == gameBoard[i - 2][j - 2] && gameBoard[i - 2][j - 2] == gameBoard[i - 3][j - 3] && gameBoard[i - 3][j - 3] == pieceToWin(nextTurn())) {
          isWinning = winningNum(nextTurn());
          winningRow1 = i;
          winningRow2 = i - 3;
          winningCol1 = j;
          winningCol2 = j - 3;
          break;
        }
      }
    }
    
    // No one wins after 42 moves;
    if (numTotalMoves() >= 42) {
      isWinning = 3;
    }
    
    return isWinning;
  }
}
