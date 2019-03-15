import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.*;
import javafx.scene.input.MouseEvent;

public class Calculadora extends Application implements EventHandler<MouseEvent>{
  public static double valorPantalla = 0;
  public static double num1 = 0;
  public static double num2;
  public static String operacion = "ninguna";
  public static String ultimaTecla = "tecla";
  public static boolean num1Assigned = false;
  public static double multiplicador = 1;
  public static boolean decimales = false;
  public static boolean result = false;
  public static Label pantalla = new Label("0");

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage){
    primaryStage.setTitle("Calculadora");
    primaryStage.show();
    GridPane grid = new GridPane();
    Scene scene = new Scene(grid);

    //Declarar todos los elementos de la calculadora y id
    Button alCuadrado = new Button("x^2"); alCuadrado.setId("cuadrado");
    Button raiz = new Button("sqrt");raiz.setId("raiz");
    Button cleanEverything = new Button("CE"); cleanEverything.setId("clean everything");
    Button clean = new Button("C"); clean.setId("clean");
    Button cero = new Button("0"); cero.setId("cero");
    Button uno = new Button("1"); uno.setId("uno");
    Button dos = new Button("2"); dos.setId("dos");
    Button tres = new Button("3"); tres.setId("tres");
    Button cuatro = new Button("4"); cuatro.setId("cuatro");
    Button cinco = new Button("5"); cinco.setId("cinco");
    Button seis = new Button("6"); seis.setId("seis");
    Button siete = new Button("7"); siete.setId("siete");
    Button ocho = new Button("8"); ocho.setId("ocho");
    Button nueve = new Button("9"); nueve.setId("nueve");
    Button punto = new Button("."); punto.setId("punto decimal");
    Button restar = new Button("-"); restar.setId("restar");
    Button sumar = new Button("+"); sumar.setId("sumar");
    Button multiplicar = new Button("X"); multiplicar.setId("multiplicar");
    Button dividir = new Button("/"); dividir.setId("dividir");
    Button resultado = new Button("="); resultado.setId("resultado");

    //Acomodar elementos en el grid
    grid.add(pantalla, 0, 0, 4, 1);
    grid.add(raiz, 0, 1);
    grid.add(alCuadrado, 1, 1);
    grid.add(clean, 2, 1);
    grid.add(dividir, 3, 1);
    grid.add(siete, 0, 2);
    grid.add(ocho, 1, 2);
    grid.add(nueve, 2, 2);
    grid.add(multiplicar, 3, 2);
    grid.add(cuatro, 0, 3);
    grid.add(cinco, 1, 3);
    grid.add(seis, 2, 3);
    grid.add(restar, 3, 3);
    grid.add(uno, 0, 4);
    grid.add(dos, 1, 4);
    grid.add(tres, 2, 4);
    grid.add(sumar, 3, 4);
    grid.add(cleanEverything, 0, 5);
    grid.add(cero, 1, 5);
    grid.add(punto, 2, 5);
    grid.add(resultado, 3, 5);

    //Agregar eventHandlers
    raiz.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    alCuadrado.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    clean.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    dividir.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    siete.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    ocho.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    nueve.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    multiplicar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    cuatro.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    cinco.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    seis.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    restar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    uno.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    dos.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    tres.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    sumar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    cleanEverything.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    cero.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    punto.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
    resultado.addEventHandler(MouseEvent.MOUSE_CLICKED, this);

    primaryStage.setScene(scene);
  }

  public void handle(MouseEvent e){
    switch(((Button)e.getSource()).getId()){
      case "cero":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (0*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "uno":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (1*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "dos":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (2*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "tres":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (3*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "cuatro":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (4*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "cinco":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (5*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "seis":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (6*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "siete":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (7*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "ocho":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (8*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "nueve":
      if(ultimaTecla.equals("operacion") || result){
        valorPantalla = 0;
      }
      valorPantalla *= (decimales?1:10);
      valorPantalla += (9*multiplicador);
      ultimaTecla = "tecla";
      break;
      case "punto decimal":
      decimales = true;
      break;
      case "raiz":
        num1 = valorPantalla;
        valorPantalla = Math.sqrt(num1);
        ultimaTecla = "operacion";
        operacion = "ninguna";
        num1Assigned = false;
        decimales = false;
      break;
      case "cuadrado":
        num1 = valorPantalla;
        valorPantalla = Math.pow(num1, 2);
        ultimaTecla = "operacion";
        operacion = "ninguna";
        num1Assigned = false;
        decimales = false;
      break;
      case "clean everything":
      num1Assigned = false;
      operacion = "ninguna";
      ultimaTecla = "tecla";
      valorPantalla = 0;
      decimales = false;
      break;
      case "clean":
      valorPantalla = 0;
      decimales = false;
      break;
      case "restar":
        operacion = "restar";
        if (ultimaTecla.equals("tecla")) {
          if (!num1Assigned) {
            num1 = valorPantalla;
            num1Assigned = true;
          } else {
            num2 = valorPantalla;
            realizarOperacion();
          }
        }
        ultimaTecla = "operacion";
        decimales = false;
      break;
      case "sumar":
      operacion = "sumar";
      if (ultimaTecla.equals("tecla")) {
        if (!num1Assigned) {
          num1 = valorPantalla;
          num1Assigned = true;
        } else {
          num2 = valorPantalla;
          realizarOperacion();
        }
      }
      ultimaTecla = "operacion";
      decimales = false;
      break;
      case "multiplicar":
        operacion = "multiplicar";
        if (ultimaTecla.equals("tecla")) {
          if (!num1Assigned) {
            num1 = valorPantalla;
            num1Assigned = true;
          } else {
            num2 = valorPantalla;
            realizarOperacion();
          }
        }
        ultimaTecla = "operacion";
        decimales = false;
      break;
      case "dividir":
        operacion = "dividir";
        if (ultimaTecla.equals("tecla")) {
          if (!num1Assigned) {
            num1 = valorPantalla;
            num1Assigned = true;
          } else {
            num2 = valorPantalla;
            realizarOperacion();
          }
        }
        ultimaTecla = "operacion";
        decimales = false;
      break;
      case "resultado":
        num2 = valorPantalla;
        realizarOperacion();
        operacion = "ninguna";
        num1Assigned = false;
        decimales = false;
        result = true;
      break;
    }
    if (!(((Button)e.getSource()).getId().equals("resultado"))) {
      result = false;
    }
    if (decimales) {
      multiplicador *=0.1;
    } else {
      multiplicador = 1;
    }
    pantalla.setText(valorPantalla+"");
  }

  public static void realizarOperacion(){
    double resultado;
    switch (operacion) {
      case "sumar":
        resultado = num1 + num2;
        break;
      case "restar":
        resultado = num1 - num2;
        break;
      case "multiplicar":
        resultado = num1 * num2;
        break;
      case "dividir":
        resultado = num1 / (num2 == 0? 1: num2);
        break;
      default:
        resultado = valorPantalla;
      }
      valorPantalla = resultado;
      num1 = valorPantalla;
      num1Assigned = true;
      pantalla.setText(valorPantalla+"");
  }
}
