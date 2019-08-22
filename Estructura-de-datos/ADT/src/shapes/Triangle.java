package shapes;

public class Triangle extends Shape {
  private double sideA;
  private double sideB;
  private double sideC;

  public Triangle(double side){
    name = "Equilateral triangle";
    sideA = side;
    sideB = side;
    sideC = side;
  }

  public Triangle(double sideA, double sideB) {
    name = "Isosceles triangle";
    this.sideA = sideA;
    this.sideB = sideB;
    this.sideC = sideB;
  }

  public Triangle(double sideA, double sideB, double sideC){
    name= "Scanlane tringle";
    this.sideA= sideA;
    this.sideB= sideB;
    this.sideC= sideC;
  }

  @Override
	public double calculateArea() {
		// TODO Auto-generated method stub
    switch(name){
      case "Equilateral triangle" :
        return Math.sqrt(Math.pow((sideA/2), 2)- Math.pow(sideB, 2));
        break;

      case "Isosceles triangle" :
        return Math.sqrt(Math.pow((sideA), 2)- Math.pow(sideB, 2));
        break;

      case "Scanlane triangle":
      return 1;
        break;
      default



    }
	}

	@Override
	public double calculatePerimeter() {
		// TODO Auto-generated method stub
		return sideA+sideB+sideC;
	}
}
