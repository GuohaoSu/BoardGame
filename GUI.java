import java.awt.Color;
public abstract class GUI {
  protected String title = "";
  protected Color titleColor = StdDraw.BLACK;

  protected Box[] boxes;

  public void setTitleAndColor(String title, Color titleColor) {
    this.title = title;
    this.titleColor = titleColor;
  }

  
  // Returns an integer representing the move if one has been made
  
  public int getMove(){
    if(StdDraw.mousePressed()) {
      while(StdDraw.mousePressed()) {
      }
      double x = StdDraw.mouseX();
      double y = StdDraw.mouseY();
      for(int i=0; i < boxes.length; i++) {
        if(boxes[i].contains(x,y)) {
          return boxes[i].getBoxcode();
        }
      }
    }
    return 0;
  }
  
  // Display output to the screen
  
  abstract void draw();

  //Available to subtypes
  protected class Box{
    private double x0,y0,x1,y1;
    private int boxcode;

    public Box(double x0, double y0, double x1, double y1, int boxcode) {
      this.x0 = x0;
      this.y0 = y0;
      this.x1 = x1;
      this.y1 = y1;
      this.boxcode = boxcode;
    }

    
    // Return true if this Box contains this point
    public boolean contains(double x, double y) {
      return(x0 < x && x < x1 && y0 < y && y < y1);
    }
    
    public int getBoxcode() {
      return boxcode;
    }
  }
}
