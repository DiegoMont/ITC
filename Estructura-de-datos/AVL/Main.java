import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.Group;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;

public class Main extends Application {
  private static Avl<Integer> avl = new Avl<>();
  private static GridPane grupo;
  private TextField elemento;
  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) {
    VBox cajita = new VBox();

    GridPane controles = new GridPane();
    Label lblElemento = new Label("Elemento: ");
    elemento = new TextField();
    controles.add(lblElemento, 0, 0);
    controles.add(elemento, 1, 0);

    Button bttnInsertar = new Button("Insertar");
    Button btnRemove = new Button("Borrar");
    controles.add(bttnInsertar, 2, 0);
    controles.add(btnRemove, 3, 0);
    bttnInsertar.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent e) {
        insertarElemento();
        start(primaryStage);
      }
    });

    btnRemove.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      public void handle(MouseEvent e) {
        borrarElemento();
        start(primaryStage);
      }
    });

    controles.setHgap(10);
    controles.setVgap(25);
    cajita.getChildren().add(controles);

    grupo = new GridPane();
    dibujarArbol();
    cajita.getChildren().add(grupo);

    VBox.setMargin(controles, new Insets(10, 0, 0, 10));
    VBox.setMargin(grupo, new Insets(50, 0, 0 ,10));

    Scene scene = new Scene (cajita, 1000, 500);
    primaryStage.setTitle("AVL");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void dibujarArbol() {
    grupo = new GridPane();
    int niveles = avl.getNiveles();
    if(niveles == 0) return;
    int lengthTotal = (int)(Math.pow(2,niveles-1)) * 2;
    int fila = 0;
    grupo.add(crearNodo(avl.getRaiz().getElemento().toString()), (lengthTotal/2)-1, 0);
    for (int i = 1; i < niveles; i++) {
      int nivel = i+1;
      int numElementosEnNivel = (int)Math.pow(2, nivel-1);
      int aumento = lengthTotal/(int)(Math.pow(2, nivel));
      boolean s = true;
      Object[] elementosNuevos = new Object[numElementosEnNivel];
      avl.elementosEnNivelPublico(nivel, elementosNuevos);
      int ii = 0;
      for (int j = aumento; j < lengthTotal; j+=aumento) {
        if (s) {
          if (elementosNuevos[ii] != null) {
            grupo.add(crearNodo(elementosNuevos[ii].toString()), j-1, i*2);
          }
          ii++;
        }
        s = !s;
      }
    }
  }

  private StackPane crearNodo(String valor) {
    Circle circulo = new Circle(30);
    circulo.setFill(Color.LIMEGREEN);
    Text texto = new Text(valor);
    StackPane container = new StackPane();
    container.getChildren().addAll(circulo, texto);
    return container;
  }

  private void insertarElemento() {
    int nodo = Integer.parseInt(elemento.getText());
    avl.insertar(nodo);
    elemento.setText("");
  }

  private void borrarElemento() {
    int nodo = Integer.parseInt(elemento.getText());
    avl.borrar(nodo);
    elemento.setText("");
  }
}
