package shapes;

public class Tetrahedron extends Shape {
  private Triangle face;
  public Tetrahedron(double side){
    face = new Triangle(side, side, side);
  }

  @Override
	public double calculateArea() {
		// TODO Auto-generated method stub
    return face.calculateArea()*4;
  }

	@Override
	public double calculatePerimeter() {
    //Calculates volume instead of perimeter
		// TODO Auto-generated method stub
		return Math.sqrt(2)*Math.pow(face.getSideA(), 3) / 12;
	}
}
