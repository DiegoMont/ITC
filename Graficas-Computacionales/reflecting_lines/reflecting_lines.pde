import java.util.LinkedList;
import java.util.ListIterator;

final int SCREEN_WIDTH = 1024;
final int SCREEN_HEIGHT = SCREEN_WIDTH * 9 / 16;
final int VERTICAL_MID = SCREEN_HEIGHT / 2;

LinkedList<Coordinate> points = new LinkedList<>();

void setup(){
  size(1024, 576);
  print(SCREEN_WIDTH, SCREEN_HEIGHT);
}

void draw(){
  background(0);
  drawCenterLine();
  drawMouseLines();
  delay(100);
}

void drawCenterLine(){
  stroke(250);
  line(0, VERTICAL_MID, SCREEN_WIDTH, VERTICAL_MID);
}

void drawMouseLines(){
  Coordinate currentMouseCoordinate = new Coordinate(mouseX, mouseY);
  points.add(currentMouseCoordinate);
  stroke(255);
  if(points.isEmpty())
    return;
  ListIterator<Coordinate> i = points.listIterator(0);
  Coordinate initialPoint = i.next();
  while(i.hasNext()){
    Coordinate nextPoint = i.next();
    line(initialPoint.x, initialPoint.y, nextPoint.x, nextPoint.y);
    line(initialPoint.x, initialPoint.oppositeY, nextPoint.x, nextPoint.oppositeY);
    initialPoint = nextPoint;
  }
}

class Coordinate{
  int x, y;
  int oppositeY;
  
  public Coordinate(int x, int y){
    this.x = x;
    this.y = y;
    calculateReflectedY();
  }
  
  void calculateReflectedY(){
    int distanceFromCentre = Math.max(VERTICAL_MID, y) - Math.min(VERTICAL_MID, y);
    oppositeY = (y > VERTICAL_MID) ? VERTICAL_MID - distanceFromCentre: distanceFromCentre + VERTICAL_MID;
  }
}
