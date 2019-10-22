import java.util.LinkedList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aplicacion extends Application {

  LinkedList<Estudiante> listaLigada = new LinkedList<>();

  public static void main(String[] args) {
    launch();
  }

  public void start(Stage stage){
    stage.setTitle("SISAA");

    BorderPane panePrincipal = new BorderPane();
    FlowPane paneContenido = new FlowPane();
    panePrincipal.setCenter(paneContenido);
    GridPane paneFormulario = new GridPane();

    Label lblNombre = new Label("Nombre:");
    TextField txtNombre = new TextField();
    paneFormulario.add(lblNombre, 0, 0);
    paneFormulario.add(txtNombre, 1, 0);

    Label lblMatricula = new Label("Matricula:");
    TextField txtMatricula = new TextField();
    paneFormulario.add(lblMatricula, 0, 1);
    paneFormulario.add(txtMatricula, 1, 1);

    Label lblCalificacion = new Label("Calificacion:");
    TextField txtCalificacion = new TextField();
    paneFormulario.add(lblCalificacion, 0, 2);
    paneFormulario.add(txtCalificacion, 1, 2);

    Label listaEstudiantes = new Label("Vacia");
    TextField eliminar = new TextField();

    Button btnAdd = new Button("Add");
    paneFormulario.add(btnAdd, 0, 3);
    btnAdd.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
      public void handle(MouseEvent e) {
        addEstudiante(txtNombre.getText(), txtMatricula.getText(), Double.parseDouble(txtCalificacion.getText()));
        txtNombre.clear();
        txtMatricula.clear();
        txtCalificacion.clear();
        actualizarLista(listaEstudiantes);
      }
    });

    Button btnEliminar = new Button("Delete");
    paneFormulario.add(btnEliminar, 1, 3);
    btnEliminar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
      public void handle(MouseEvent e) {
        eliminarEstudiante(eliminar);
        actualizarLista(listaEstudiantes);
      }
    });


    paneFormulario.add(eliminar, 2, 3);

    Button btnSort = new Button("Sort");
    paneFormulario.add(btnSort, 0, 4);
    btnSort.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
      public void handle(MouseEvent e) {
        ordenarEstudiantes();
        actualizarLista(listaEstudiantes);
      }
    });

    paneFormulario.setHgap(10);
    paneFormulario.setVgap(25);
    paneContenido.getChildren().add(paneFormulario);
    paneContenido.getChildren().add(listaEstudiantes);

    Scene scene = new Scene(panePrincipal);
    stage.setScene(scene);
    stage.setHeight(450);
    stage.setWidth(500);
    stage.show();
  }

  private void actualizarLista(Label texto){
    if (listaLigada.size() == 0) {
      texto.setText("Vacia");
    } else {
      String nombres = "";
      for (int i = 0; listaLigada.size() > i; i++) {
        nombres += (i+1) + ". " + listaLigada.get(i).toString() + "\n";
      }
      texto.setText(nombres);
    }
    System.out.println(listaLigada.toString());
  }

  private void addEstudiante(String nombre, String matricula, double calificacion){
    Estudiante e = new Estudiante(nombre, matricula, calificacion);
    listaLigada.add(e);
  }

  private void eliminarEstudiante(TextField texto){
    int index = Integer.parseInt(texto.getText()) - 1;
    if (index > -1 && index < listaLigada.size()) {
      listaLigada.remove(index);
    }
    texto.clear();
  }

  private void ordenarEstudiantes(){
    quickSort(0, listaLigada.size()-1);
  }

  private void intercambiar(int indice1, int indice2) {
        Estudiante temp1 = listaLigada.get(indice1);
        Estudiante temp2 = listaLigada.get(indice2);
        listaLigada.set(indice1, temp2);
        listaLigada.set(indice2, temp1);
    }

    private void quickSort(int izquierda, int derecha) {
        Estudiante pivote = listaLigada.get((derecha + izquierda) / 2);
        int i = izquierda;
        int j = derecha;
        while (i <= j) {
            while (listaLigada.get(i).getCalificacion() < pivote.getCalificacion()) {
                i++;
            }
            while (listaLigada.get(j).getCalificacion() > pivote.getCalificacion()) {
                j--;
            }
            if (i <= j) {
                if (i != j) {
                    intercambiar(i, j);
                }
                i++;
                j--;
            }
        }
        if (izquierda < j) {
            quickSort(izquierda, j);
        }
        if (i < derecha) {
            quickSort(i, derecha);
        }
    }
}
