package shapes;

public class Rectangle extends Shape {
  private double sideA;
  private double sideB;

  public Rectangle(double sideA, double sideB) {
    this.sideA = sideA;
    this.sideB = sideB;
  }

  public void setSideA(double sideA) {
    this.sideA = sideA;
  }

  public double getSideA() {
    return sideA;
  }

  public void setSideB(double sideB) {
    this.sideB = sideB;
  }

  public double getSideB() {
    return sideB;
  }

  @Override
	public double calculateArea() {
		// TODO Auto-generated method stub
		return sideA*sideB;
	}

	@Override
	public double calculatePerimeter() {
		// TODO Auto-generated method stub
		return sideA * 2 + sideB * 2;
	}
}
