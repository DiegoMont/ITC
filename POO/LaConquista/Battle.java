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

public class Battle {

	private PersonajePrincipal heroe;
	private Villano enemigo;
	private Scene battleScene;
	private AtaqueEspecial special;
	private Main main;

	public Battle(PersonajePrincipal heroe, Villano enemigo, Main main){
		this.heroe = heroe;
		this.enemigo = enemigo;
		this.main = main;
	}

	public Scene createBattleScene(){
		BorderPane bp = new BorderPane();
		//
		bp.getStylesheets().add("estilos.css");

        bp.getStyleClass().add("batallaCasa");
		//


		Label lb1 = new Label(heroe.toString());
		Label lb2 = new Label(enemigo.toString());
		Label action = new Label();
		Label vsScreen = new Label(heroe.getNombre() + " vs " +  enemigo.getNombre());
		Button normalAttack = new Button("Ataque normal");
		Button specialAttack1 = new Button(heroe.getSpecialAttack1());
		Button specialAttack2 = new Button(heroe.getSpecialAttack2());
		Button specialAttack3 = new Button(heroe.getSpecialAttack3());
		VBox statsHeroe = new VBox();
		VBox statsEnemigo = new VBox();

		//
		lb1.getStyleClass().add("label1");
		lb2.getStyleClass().add("label1");
		action.getStyleClass().add("label1");
		vsScreen.getStyleClass().add("label1");
		normalAttack.getStyleClass().add("button1");
		specialAttack1.getStyleClass().add("button1");
		specialAttack2.getStyleClass().add("button1");
		specialAttack3.getStyleClass().add("button1");

		//
			
		//Se agregan las stats del héroe
		statsHeroe.getChildren().add(lb1);

		
		//Se agregan las sat de los enemigos 
		statsEnemigo.getChildren().add(lb2);

		
		
		//Barra de elección
		HBox actions = new HBox();
		actions.getChildren().add(normalAttack);
		actions.getChildren().add(specialAttack1);
		actions.getChildren().add(specialAttack2);
		actions.getChildren().add(specialAttack3);
		
		
		bp.setTop(vsScreen);
		bp.setAlignment(vsScreen, Pos.TOP_CENTER);
		bp.setCenter(action);
		bp.setLeft(statsHeroe);
		bp.setRight(statsEnemigo);
		bp.setBottom(actions);
		
		normalAttack.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				heroe.atacar(heroe, enemigo);
				action.setText(heroe.getNombre() +" ataco");
				lb2.setText(enemigo.toString());
				if(enemigo.getHP()<=0){
					System.out.println("El enemigo murio");
					main.setPreviousScene();
				}
				enemigo.atacar(enemigo,heroe);
				lb1.setText(heroe.toString());
				action.setText(enemigo.getNombre() + " ataco");
			}
		});
				specialAttack1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				special = new AtaqueEspecial("hola",0);
				heroe.substractLife(1);
				heroe.atacar(heroe, enemigo,special.getAumentoAtaque());
				action.setText(heroe.getNombre() +" ataco");
				lb2.setText(enemigo.toString());
				if(enemigo.getHP()<=0){
					main.setPreviousScene();
					System.out.println("El enemigo murio");
				}
				enemigo.atacar(enemigo,heroe);
				action.setText(enemigo.getNombre() + " ataco");
				lb1.setText(heroe.toString());
			}
		});
				specialAttack2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				special = new AtaqueEspecial("hola",1);
				heroe.substractLife(2);
				heroe.atacar(heroe, enemigo,special.getAumentoAtaque());
				action.setText(heroe.getNombre() +" ataco");
				lb2.setText(enemigo.toString());
				if(enemigo.getHP()<=0){
					main.setPreviousScene();
					System.out.println("El enemigo murio");
				}
				enemigo.atacar(enemigo,heroe);
				action.setText(enemigo.getNombre() + " ataco");
				lb1.setText(heroe.toString());
			}
		});
				specialAttack3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
				special = new AtaqueEspecial("hola",2);
				heroe.substractLife(3);
				heroe.atacar(heroe, enemigo,special.getAumentoAtaque());
				action.setText(heroe.getNombre() +" ataco");
				lb2.setText(enemigo.toString());
				if(enemigo.getHP()<=0){
					main.setPreviousScene();
					System.out.println("El enemigo murio");
				}
				enemigo.atacar(enemigo,heroe);
				action.setText(enemigo.getNombre() + " ataco");
				lb1.setText(heroe.toString());
			}
		});
		
		//Se crean los botones para poder atacar al adversario
		battleScene = new Scene(bp);
		return battleScene;
	}
}
