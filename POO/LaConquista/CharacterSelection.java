import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class CharacterSelection{

  private PersonajePrincipal heroe;
  private Button warriorButton = new Button("Guerrero");
  private Button tlatoaniButton = new Button("Tlatoani");
  private Button priestButton = new Button("Sacerdote");

  public CharacterSelection(PersonajePrincipal heroe){
    this.heroe = heroe;
  }

  public  Scene createCharacterSelection(){
    GridPane gp = new GridPane();

    Button[] botones = new Button[3];
    botones[0] = warriorButton;
    botones[1] = tlatoaniButton;
    botones[2] = priestButton;

    for (Button btnPersonaje: botones) {
      btnPersonaje.setMinHeight(500);
      btnPersonaje.setMinWidth(200);
    }

//
    gp.getStylesheets().add("estilos.css");

    warriorButton.getStyleClass().add("warriorButton");
		//warriorButton.setStyle("-fx-background-image:url('C:/Users/hp/Documents/Tec/Computing/Images/warrior.png');");
    tlatoaniButton.getStyleClass().add("tlatoaniButton");
		//tlatoaniButton.setStyle("-fx-background-image:url('C:/Users/hp/Documents/Tec/Computing/Images/Tlatoani.png');");
    priestButton.getStyleClass().add("sacerdoteButton");
		//priestButton.setStyle("-fx-background-image:url(´C:/Users/hp/Documents/Tec/Computing/Images/Priest.png);");


// 

		gp.add(warriorButton,0,0);
		gp.add(tlatoaniButton,1,0);
		gp.add(priestButton,2,0);
    Scene scene = new Scene(gp);
    return scene;
	}

  public Button getWarriorButton(){
    return warriorButton;
  }

  public Button getTlatoaniButton(){
    return tlatoaniButton;
  }

  public Button getPriestButton(){
    return priestButton;
  }

  public PersonajePrincipal getHeroe(){
    return heroe;
  }

  public void definirHeroe(String tipoHeroe){
    switch (tipoHeroe) {
      case "guerrero": heroe = new Guerrero("warrior",10,10,10,10); break;
      case "tlatoani": heroe = new Tlatoani("Tlatoani",10,10,10,10); break;
      case "sacerdote": heroe= new Sacerdote("Sacerdote",10,10,10,10); break;
    }
  }
}
