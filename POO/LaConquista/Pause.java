import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.collections.ObservableList;
import 	javafx.geometry.Pos;


public class Pause{
	Scene sc ;
	public Pause(){
		
	}
	public Scene createPauseScene(Main main){

		BorderPane bp = new BorderPane();
		VBox vb1 = new VBox();
		HBox hb = new HBox();

	

		Button b1 = new Button("Continuar");
		Button b2 = new Button("Guardar");
		Button b3 = new Button("Salir");
		Button b4 = new Button("Cargar");
		
		vb1.getChildren().add(b1);
		vb1.getChildren().add(b4);
		vb1.getChildren().add(b2);
		vb1.getChildren().add(b3);

		vb1.setAlignment(Pos.CENTER);

		hb.getChildren().add(vb1);
		hb.setAlignment(Pos.CENTER);

		bp.setCenter(hb);
		bp.setPrefSize(600,600);

		bp.getStylesheets().add("estilos.css");
		bp.getStyleClass().add("pausa");
		b1.getStyleClass().add("botonPausa");
		b2.getStyleClass().add("botonPausa");
		b3.getStyleClass().add("botonPausa");
		b4.getStyleClass().add("botonPausa");
	

		b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				main.setPreviousScene();
			}
		});

		
		b2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				main.guardar();
			}
		});


		b3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				System.exit(0);
			}
		});
		
		b4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				main.cargar();
			}
		});
		
		sc = new Scene(bp);

		return sc;
		}
	
}