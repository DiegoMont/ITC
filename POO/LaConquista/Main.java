import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;


public class Main extends Application implements Serializable{
  /*
  Main:
  Debe haber un menu para crear un personaje mapaPrincipal
  Despues, debera aparecer en el mapa principal.
  Debera recorrerlo para llegar al primer Nivel y superarlo
  Seguira asi hasta completar la historia y llegar al jefe.
  */

  //private static MapaPrincipal mapaPrincipal;
  private static Mapa mapaPrincipal;
  private static PersonajePrincipal heroe;
  private Stage primaryStage;
  private Main main;
  private Scene scene;

  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage){
    this.main = this	;
	  this.primaryStage = primaryStage;
    CharacterSelection menu = new CharacterSelection(heroe);
    primaryStage.setScene(menu.createCharacterSelection());
    menu.getWarriorButton().addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
        System.out.println("Boton guerrero presionado");
				menu.definirHeroe("guerrero");
        heroe = menu.getHeroe();
        mapaPrincipal = new Mapa(30, 5, heroe, main, "guerreroMapa");
        //mapaPrincipal = new Mapa(30, 5, heroe, ArreglosMapas.mapaPrincipal, 23, 27, "mapa-principal", 20);
        primaryStage.setScene(mapaPrincipal.getScene());
			}
		});
    menu.getTlatoaniButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){
        System.out.println("Boton tlatoani presionado");
        menu.definirHeroe("tlatoani");
        heroe = menu.getHeroe();
        mapaPrincipal = new Mapa(30, 5, heroe, main, "tlatoaniMapa");
        //mapaPrincipal = new Mapa(30, 5, heroe, ArreglosMapas.mapaPrincipal, 23, 27, "mapa-principal", 20);
        primaryStage.setScene(mapaPrincipal.getScene());
      }
		});
    menu.getPriestButton().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
		public void handle(MouseEvent e){
      System.out.println("Boton sacerdote presionado");
			menu.definirHeroe("sacerdote");
      heroe = menu.getHeroe();
      mapaPrincipal = new Mapa(30, 5, heroe, main, "sacerdoteMapa");
      //mapaPrincipal = new Mapa(30, 5, heroe, ArreglosMapas.mapaPrincipal, 23, 27, "mapa-principal", 20);
      primaryStage.setScene(mapaPrincipal.getScene());
		}
		});
    primaryStage.show();
  }

  public void setSceneBatalla(PersonajePrincipal heroe, Villano villano, Main main,Scene scene){
	  this.scene = scene;
	  Battle battle = new Battle(heroe, villano, main);
	  primaryStage.setScene(battle.createBattleScene());
  }
  public void guardar(){
	  try{
		  FileOutputStream fout = new FileOutputStream("Partida1.atm");
		  ObjectOutputStream oos = new ObjectOutputStream(fout);
		  oos.writeObject(scene);
		  oos.close();
	  }catch(IOException ex){

	  }
  }
  public void cargar(){
	  try{
		  File selectedFile = new File("Partida1.atm");
		  FileInputStream fin = new FileInputStream(selectedFile);
		  ObjectInputStream ois = new ObjectInputStream(fin);
		  scene = (Scene) ois.readObject();
		  primaryStage.setScene(scene);
	  }catch(IOException ex){
		 ex.printStackTrace();
	  }
	  catch(ClassNotFoundException ex){
		  ex.printStackTrace();
	  }
  }
  public void setPauseScene(Main main, Scene scene){
	  this.scene = scene;
	  Pause pause = new Pause();
	  primaryStage.setScene(pause.createPauseScene(main));
  }
  public void setPreviousScene(){
    heroe.adquirirExperiencia(2);
	  heroe.actualizarStats();
	  primaryStage.setScene(scene);
  }
}
