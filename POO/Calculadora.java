import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Calculadora extends Application{
  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage){
    primaryStage.setTitle("Calculadora");
    initComponents(primaryStage);
    primaryStage.show();
  }

  public void initComponents(Stage stage){
    VBox vbox = new VBox();
    Scene scene = new Scene(vbox);
    Label pantalla = new Label("0");
    Button medidasAngulares = new Button("DEG");
    Button memorySave = new Button("MS");
    Button memoryClean = new Button("MC");
    Button memoryRetrieve = new Button("MR");
    Button alCuadrado = new Button("x^2");
    Button toPwr = new Button("x^y");
    Button seno = new Button("sen");
    Button coseno = new Button("cos");
    Button tangente = new Button("tan");
    Button raiz = new Button("sqrt");
    Button logaritmo = new Button("log");
    Button cleanEverything = new Button("CE");
    Button clean = new Button("C");
    Button pi = new Button("Pi");
    Button factorial = new Button("n!");
    Button cero = new Button("0");
    Button uno = new Button("1");
    Button dos = new Button("2");
    Button tres = new Button("3");
    Button cuatro = new Button("4");
    Button cinco = new Button("5");
    Button seis = new Button("6");
    Button siete = new Button("7");
    Button ocho = new Button("8");
    Button nueve = new Button("9");
    Button punto = new Button(".");
    Button dividir = new Button("/");
    Button multiplicar = new Button("X");
    Button restar = new Button("-");
    Button sumar = new Button("+");
    Button resultado = new Button("=");
    vbox.getChildren().addAll(pantalla, medidasAngulares, memoryClean, memoryRetrieve, memorySave, alCuadrado, toPwr, seno, coseno, tangente, raiz, logaritmo, pi, factorial, cleanEverything, clean, dividir, siete, ocho, nueve, multiplicar, cuatro, cinco, seis, restar, uno, dos, tres, sumar, cero, punto, resultado);
    stage.setScene(scene);
  }
}
