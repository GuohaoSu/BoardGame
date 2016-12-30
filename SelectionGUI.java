public class SelectionGUI extends GUI{
  
  public SelectionGUI() {
    boxes = new Box[2];
    boxes[0] = new Box(1,0,5,2,1);
    boxes[1] = new Box(5,0,9,2,2);
  }
  
  
  public void draw() {
    StdDraw.setXscale(0,10);
    StdDraw.setYscale(0,10);
    StdDraw.clear();
    //StdDraw.pause(500);
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.rectangle(5,8,2.5,1);
    StdDraw.setPenColor(StdDraw.BOOK_RED);
    StdDraw.text(5,8,"Welcome to the Central Game!");
    StdDraw.show();
    //StdDraw.pause(500);
    StdDraw.setPenColor(StdDraw.DARK_GRAY);
    StdDraw.rectangle(5,5,2,0.7);
    StdDraw.text(5,5,"You can choose a game.");
    StdDraw.show();
    //StdDraw.pause(500);
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.rectangle(3,1,2,1);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.text(3,1,"Connect4");
    StdDraw.show();
    //StdDraw.pause(500);
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.rectangle(7,1,2,1);
    StdDraw.setPenColor(StdDraw.BLUE);
    StdDraw.text(7,1,"TicTacToe");
  }
}
