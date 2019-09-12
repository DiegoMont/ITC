import java.util.Scanner;
import shapes.Circle;
import shapes.Shape;
import shapes.Square;
import shapes.Triangle;
import shapes.Rectangle;
import shapes.Tetrahedron;

public class ADT {

	public static void main(String args[]) {
		System.out.println("In code we trust");
		Scanner sc= new Scanner(System.in);
		String selectedOption= preguntarFigura(sc);
		while(!selectedOption.equals("0")){


			Shape shape;
			boolean volumenInsteadOfPerimeter = false;
			switch(selectedOption) {
				case "1":
					System.out.println("Dame el lado del cuadrado: ");
					double side= sc.nextDouble();
					shape= new Square(side);
					break;
				case "2":
					System.out.println("Dame el radio del círculo");
					double radius=sc.nextDouble();
					shape=new Circle(radius);
					break;
				case "3":
					System.out.println("Dame los lados del rectangulo");
					double rectangleSideA=sc.nextDouble();
					double rectangleSideB=sc.nextDouble();
					shape=new Rectangle(rectangleSideA, rectangleSideB);
					break;
				case "4":
					System.out.println("Dame los lados del trángulo");
					double triangleSideA=sc.nextDouble();
					double triangleSideB=sc.nextDouble();
					double triangleSideC=sc.nextDouble();
					shape=new Triangle(triangleSideA, triangleSideB, triangleSideC);
					break;
				case "5":
					System.out.println("Dame la arista del tetraedro");
					double arista=sc.nextDouble();
					shape=new Tetrahedron(arista);
					volumenInsteadOfPerimeter = true;
					break;
				default:
					System.out.println("Dame el lado del cuadrado: ");
					double sideD= sc.nextDouble();
					shape=new Square(sideD);
					break;
			}
			sc.nextLine();
			System.out.println("¿Que deseas calcular? 1) Area 2)"+ (volumenInsteadOfPerimeter?"Volumen": "Perimetro"));
			volumenInsteadOfPerimeter = false;
			String selectedCalculation= sc.nextLine();
			switch(selectedCalculation) {
				case "1":
					System.out.println("Resultado: "+shape.calculateArea());
					break;
				case "2":
					System.out.println("Resultado: "+shape.calculatePerimeter());
					break;
			}
			selectedOption= preguntarFigura(sc);

		}
		sc.close();

	}

	public static String preguntarFigura(Scanner sc) {
		System.out.println("Elige la figura: 1)Cuadrado 2)Círculo 3)Rectangulo 4) triángulo 5)Tetraedro");
		String selectedOption="1";
		selectedOption=sc.nextLine();
		return selectedOption;
	}

}
