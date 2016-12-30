public class Connect4GUI extends BoardGUI {

  public Connect4GUI() {
    boxes = new Box[8];
    for (int i = 0; i < 7; i++) {
      boxes[i] = new Box(i+1,1,i+2,8,i+1);
    }
    // Undo Box
    boxes[7] = new Box(8.5,0,10.5,1,-1);
  }
  
  public void draw(Board b){
    StdDraw.setXscale(-1,11);
    StdDraw.setYscale(-1,10);
    StdDraw.clear();
    // Draw Lines
    StdDraw.setPenRadius(0.008);
    StdDraw.setPenColor(StdDraw.BLACK);
    for (int i=0; i<8; i++) {
      StdDraw.line(i+1,1,i+1,8);
    }
    StdDraw.line(1,1,8,1);
    StdDraw.setPenColor(StdDraw.BOOK_BLUE);
    StdDraw.text(4.5,9,"Have Fun in Connect Four !");
    
    for (int i = 0; i < 6; i++) {
      for (int j = 0; j < 7; j++) {
        switch(b.getBoardPiece(i,j)){
          case 1:
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.filledCircle(j+1.5,i+1.5,.375);
            break;
          case 2:
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.filledCircle(j+1.5,i+1.5,.375);
            break;
        }
      }
    }
    //Draw UndoBox
    StdDraw.setPenRadius(0.005);
    StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
    StdDraw.rectangle(9.5,0.5,1,0.5);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.text(9.5, 0.5, "Undo");
  
  }
  
  // Draw Turns
  public void drawTurns(String callsign) {
    StdDraw.text(4.5,8.5,callsign + "'s turn!");
  }
}
