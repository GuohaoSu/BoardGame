public class WinnerGUI extends GUI{
  
  public WinnerGUI() {
    boxes = new Box[2];
    boxes[0] = new Box(1,0,5,2,1);
    boxes[1] = new Box(5,0,9,2,2);
  }
  public void draw() {
  }
  
  // Display the winner and ask whether the players want to play again
  public void draw(int winner, String callSign) {
    StdDraw.pause(1000);
    StdDraw.clear();
    StdDraw.setXscale(0,10);
    StdDraw.setYscale(0,10);
    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
    if (winner != 3) {
      StdDraw.text(5,7,"The Winner Is " + callSign + " !");
    }
    else {
      StdDraw.text(5,7,"This is a draw!");
    }
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.text(5,5,"Would you like to to play again?");
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.setPenRadius(0.005);
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.rectangle(3,1,2,1);
    StdDraw.setPenColor(StdDraw.ORANGE);
    StdDraw.text(3,1,"Yes! ^_^");
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.rectangle(7,1,2,1);
    StdDraw.setPenColor(StdDraw.PINK);
    StdDraw.text(7,1,"No. >_<");
  }

 // Draw a line on the 4 continuous pieces by getting the winning pieces from the game board
  public void draw1(Board b) {
    StdDraw.setXscale(-1,10);
    StdDraw.setYscale(-1,10);
    int winningRow1 = b.getWinningRow1();
    int winningRow2 = b.getWinningRow2();
    int winningCol1 = b.getWinningCol1();
    int winningCol2 = b.getWinningCol2();
    StdDraw.setPenColor(StdDraw.CYAN);
    StdDraw.setPenRadius(0.01);
    StdDraw.line(winningCol1 + 1.1 ,6.5 - winningRow1,winningCol2 + 1.1,6.5 - winningRow2);
  }
  
  public void draw2(Board b) {
    StdDraw.setXscale(-1.5,4.5);
    StdDraw.setYscale(0,4);
    int winningRow1 = b.getWinningRow1();
    int winningRow2 = b.getWinningRow2();
    int winningCol1 = b.getWinningCol1();
    int winningCol2 = b.getWinningCol2();
    StdDraw.setPenColor(StdDraw.CYAN);
    StdDraw.setPenRadius(0.01);
    StdDraw.line(winningCol1 + 0.5,winningRow1 + 0.5,winningCol2 + 0.5,winningRow2 + 0.5);
  }
}
