import java.util.*;
import java.io.*;

public class Game {
  public static void main(String[] args) throws UnsupportedEncodingException {
    StdDraw.enableDoubleBuffering();
    StdDraw.setCanvasSize(600,400);
    int move, i;
    int ans = 0;
    int[] totWins = new int[3];
    FinalGUI finalGUI = new FinalGUI();
    Scanner scnr = new Scanner(System.in);
    do {

        
      // Create the gameboard inside the while loop so that everytime the game start over, players will have a new board and be able to choose every thing again
      Board connect4Board = new Connect4Board();
      Board ticTacToeBoard = new TicTacToeBoard();
      BoardGUI connect4GUI = new Connect4GUI();
      BoardGUI ticTacToeGUI = new TicTacToeGUI();
      Board gameBoard = null;
      BoardGUI gameGUI = null;
      SelectionGUI selectionGUI = new SelectionGUI();
      WinnerGUI winnerGUI = new WinnerGUI();
      PlayerSelectionGUI playerSelectionGUI = new PlayerSelectionGUI();
      Player[] players = new Player[2];
      
      int game = 0;
      ans = 0;
      
    
      // Display the selectionGUI for the user to choose a game
      StdDraw.clear();
      selectionGUI.draw();
      StdDraw.show();
      
      // Choose a game
      while (game != 1 & game != 2) {
        game = selectionGUI.getMove();
      }
      
      if (game == 1) {
        gameBoard = connect4Board;
        gameGUI = connect4GUI;
      }
      else if (game == 2) {
        gameBoard = ticTacToeBoard;
        gameGUI = ticTacToeGUI;
      }
      int player1 = 0;
      int player2 = 0;
      // Choose player types for two players
      playerSelectionGUI.drawPlayerNum(1);
      playerSelectionGUI.draw();
      playerSelectionGUI.drawPlayer1(player1);
      playerSelectionGUI.drawPlayer2(player2);
      StdDraw.show();
      while (player1 == 0) {
        player1 = playerSelectionGUI.getMove();
      }
      switch (player1) {
        case 1: players[0] = new HumanPlayer(1,gameGUI);
          break;
        case 2: players[0] = new RandomPlayer();
          break;
        case 3: players[0] = new LookAheadAIPlayer(1,2);
          break;
        case 4: players[0] = new LookAheadAIPlayer(1,4);
          break;
        case 5: players[0] = new LookAheadAIPlayer(1,6);
          break;
        case 6:
          if (game == 1) {
            players[0] = new TournamentPlayer(1,8);
            break;
          }
          players[0] = new LookAheadAIPlayer(1,8);
          break;
      }
      
      playerSelectionGUI.drawPlayerNum(2);
      playerSelectionGUI.draw();
      playerSelectionGUI.drawPlayer1(player1);
      playerSelectionGUI.drawPlayer2(player2);
      StdDraw.show();

      while (player2 == 0) {
        player2 = playerSelectionGUI.getMove();
      }
      switch (player2) {
        case 1: players[1] = new HumanPlayer(2,gameGUI);
          break;
        case 2: players[1] = new RandomPlayer();
          break;
        case 3: players[1] = new LookAheadAIPlayer(2,2);
          break;
        case 4: players[1] = new LookAheadAIPlayer(2,4);
          break;
        case 5: players[1] = new LookAheadAIPlayer(2,6);
          break;
        case 6:
          if (game == 1) {
            players[1] = new TournamentPlayer(2,8);
            break;
          }
          players[1] = new LookAheadAIPlayer(2,8);
          break;
      }
      playerSelectionGUI.drawPlayerNum(2);
      playerSelectionGUI.draw();
      playerSelectionGUI.drawPlayer1(player1);
      playerSelectionGUI.drawPlayer2(player2);
      StdDraw.show();
      StdDraw.pause(1000);
      StdDraw.pause(1000);
      
      
      
        
      // play the chosen game and get the winner's number
      int winner = play(gameBoard,gameGUI,players);
      totWins[winner - 1]++;
      
      // First "draw" is to draw a line to connect the pieces that determine which player wins
      // Display the winner and let the user to choose whether they want to play again
      
      if (winner == 1 || winner == 2) {
        if (game == 1) {
          winnerGUI.draw1(gameBoard);
        }
        else if (game ==2) {
          winnerGUI.draw2(gameBoard);
        }
        StdDraw.show();
        
        // Pause a little bit so that the player can notice the winning line
        StdDraw.pause(1000);
        winnerGUI.draw(winner, players[winner-1].callSign());
      }
      else if (winner ==3) {
        StdDraw.pause(1000);
        winnerGUI.draw(winner, "draw");
      }
      StdDraw.show();
      
      while (ans != 1 && ans != 2) {
        ans = winnerGUI.getMove();
      }
    
    } while(ans == 1);
    
    // Draw the final screen
    StdDraw.clear();
    finalGUI.draw(totWins[0],totWins[1],totWins[2]);
    StdDraw.show();
  }

  /*
   * Plays a two player game on Board b with BoardGUI g
   * At the conclusion of a game, this should return a 1 if player 1 wins,
   * a 2 is player 2 wins,
   * a 3 if there is a draw
   *
   */
  public static int play(Board b,BoardGUI g,Player[] players) {
    Scanner scnr = new Scanner(System.in);
      
    while (true){
      // Update the boardGUI everytime players make a move
      StdDraw.clear();
      g.draw(b);
      g.drawTurns(players[b.nextTurn()-1].callSign());
      StdDraw.show();
      
      int move = players[b.nextTurn()-1].decideMove(b);
        
        // Get user input and decide whether it's a makemove or undomove, if the value is -1, then undo, otherwise make a move
      if(move == -1) {
        b.revert(b.numTotalMoves() - 2);
      }
      else if (b.makeMove(move)) {
      }
      
      if(b.isWinning() != 0) {
        break;
      }
    }
    
    // Display the BoardGUI after the player makes the winning move
    StdDraw.clear();
    g.draw(b);
    StdDraw.show();

  return b.isWinning();
    
  }
}
