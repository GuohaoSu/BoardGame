public class FinalGUI extends GUI{
  
  public FinalGUI() {
  }
  
  public void draw() {
  }
  
  public void draw(int p1Wins, int p2Wins, int numDraw) {
    StdDraw.setPenColor(StdDraw.BOOK_RED);
    StdDraw.rectangle(5,5,3.5,1.5);
    StdDraw.setPenColor(StdDraw.BOOK_RED);
    StdDraw.text(5,8,"Final Result");
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.textLeft(4,6,"Player 1: " + p1Wins + " wins");
    StdDraw.textLeft(4,5,"Player 2: " + p2Wins + " wins");
    StdDraw.textLeft(4,4,"Draws: " + numDraw);

  }
}
