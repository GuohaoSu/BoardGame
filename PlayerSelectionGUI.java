public class PlayerSelectionGUI extends GUI{
  
  public PlayerSelectionGUI() {
    boxes = new Box[6];
    // Player Selection boxes
    for (int i = 0; i < 6; i++) {
      boxes[i] = new Box(3.5,7.5-i,6.5,8.5-i,i+1);
    }
  }
  
  public void drawPlayerNum(int number) {
    StdDraw.clear();
    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
    StdDraw.text(5,9,"Choose a player for player " + number);
  }
  
  public void draw() {
    StdDraw.setXscale(0,10);
    StdDraw.setYscale(0,10);
    //StdDraw.pause(500);
    StdDraw.setPenColor(StdDraw.GRAY);
    for (int i = 0; i < 6; i++) {
      StdDraw.rectangle(5,8-i,1.5,0.5);
    }
    StdDraw.setPenColor(StdDraw.BOOK_RED);
    StdDraw.text(5,8,"Human");
    StdDraw.text(5,7,"Random");
    StdDraw.text(5,6,"Easy");
    StdDraw.text(5,5,"Medium");
    StdDraw.text(5,4,"Hard");
    StdDraw.text(5,3,"Tournament");
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.text(5,1.5,"VS");
    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
  }

  public void drawPlayer1(int player1) {
    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
    switch (player1) {
      case 0: StdDraw.text(3,1.5,"Player 1");
        break;
      case 1: StdDraw.text(3,1.5,"Human");
        break;
      case 2: StdDraw.text(3,1.5,"Random");
        break;
      case 3: StdDraw.text(3,1.5,"Easy");
        break;
      case 4: StdDraw.text(3,1.5,"Medium");
        break;
      case 5: StdDraw.text(3,1.5,"Hard");
        break;
      case 6: StdDraw.text(3,1.5,"Tournament");
        break;
    }
  }
  public void drawPlayer2(int player2) {
    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
    switch (player2) {
      case 0: StdDraw.text(7,1.5,"Player 2");
         break;
      case 1: StdDraw.text(7,1.5,"Human");
         break;
      case 2: StdDraw.text(7,1.5,"Random");
         break;
      case 3: StdDraw.text(7,1.5,"Easy");
         break;
      case 4: StdDraw.text(7,1.5,"Medium");
         break;
      case 5: StdDraw.text(7,1.5,"Hard");
         break;
      case 6: StdDraw.text(7,1.5,"Tournament");
         break;
    }
  }
}
