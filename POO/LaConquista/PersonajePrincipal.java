import java.util.Scanner;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;

public class PersonajePrincipal extends Personaje implements EventHandler<MouseEvent> {
  public static Scanner sc = new Scanner(System.in);
  private Objeto[] inventario = new Objeto[10];
  private AtaqueEspecial[] habilidadesEspeciales = new AtaqueEspecial[3];
  //arreglo para guardar la ubicacion del personajes en el mapa principal
  private int[] ubicacionHeroeMP = new int[2];
  private String specialAttack1;
  private String specialAttack2;
  private String specialAttack3;
  private String claseImagen;

  public PersonajePrincipal(String nombre, String specialAttack1, String specialAttack2, String specialAttack3, String imagen){
    super(nombre, 0, 0);
    this.specialAttack1 = specialAttack1;
    this.specialAttack2 = specialAttack2;
    this.specialAttack3 = specialAttack3;
    claseImagen = imagen;

    setUbicacionHeroeMP(1, 1);
  }
  public int[] getUbiacionHeroeMP(){
    return ubicacionHeroeMP;
  }

  public int getInitialLife(){
	  return 100;
  }

  public String getSpecialAttack1(){
	  return specialAttack1;
  }
  public String getSpecialAttack2(){
	  return specialAttack2;
  }
  public String getSpecialAttack3(){
	  return specialAttack3;
  }

  public void setUbicacionHeroeMP(int i, int j){
    ubicacionHeroeMP[0] = i;
    ubicacionHeroeMP[1] = j;
  }

  public Objeto[] getInventario(){
    return inventario;
  }

  public void addInventario(Objeto item){
    for (int i = 0; i < 10; i++) {
      if (inventario[i] == null) {
        inventario[i] = item;
        break;
      }
    }
  }

  public void usarObjeto(int indice){
    Objeto item = inventario[indice];
    switch (item.getTipoObjeto()) {
      case 1: setAtaque(getAtaque() + item.getAumentoAtaque());break;
      case 2: setDefensa(getDefensa() + item.getAumentoDefensa()); break;
      case 3: setEnergia(getEnergia()+ item.getAumentoEnergia()); if(getEnergia() > 100){setEnergia(100);} removeInventario(indice); break;
      case 4: setHP(getHP()+ item.getAumentoVida()); if(getHP() > 100){setHP(100);} removeInventario(indice); break;
      default: System.out.println("No se puede usar");
    }
  }

  public void removeInventario(int index){
    inventario[index] = null;
  }

  public int atacar(){
    //primero se deben mostrar los ataques disponibles al usuario
    System.out.println("1 Ataque normal");
    /*Guardar en un arreglo la informacion de cada ataque.
    El primer indice guarda el daño total y el segundo el costo energetico*/
    int[][] listaAtaques = new int[4][2];
    listaAtaques[0][0] = getAtaque();
    listaAtaques[0][1] = 10;
    int j = 1;
    for (int i = 0; i < 3; i++) {
      if (habilidadesEspeciales[i] != null) {
        if(habilidadesEspeciales[i].getCostoEnergetico() <= getEnergia()){
          j++;
          System.out.println(j+" "+habilidadesEspeciales[i].toString());
          //Guardar la informacion del ataque especial disponible en el arreglo
          listaAtaques[j-1][0] = habilidadesEspeciales[i].getAumentoAtaque()*getAtaque();
          listaAtaques[j-1][1] = habilidadesEspeciales[i].getCostoEnergetico();
        }
      }
    }
    int eleccion = sc.nextInt()-1;
    if (eleccion >= j) {
      //Si el usuario ingresa un numero correcto
      eleccion = 0;
    }
    //Hacer un ataque y restarle la energia al personaje
    setEnergia(getEnergia()-listaAtaques[eleccion][1]+10);
    System.out.println("Ataque: " + listaAtaques[eleccion][0]);
    return listaAtaques[eleccion][0];
  }

  public void adquirirExperiencia(int puntosExperiencia){
    setXP(puntosExperiencia+getXP());
    if (getXP() > 29) {
      habilidadesEspeciales[2] = new AtaqueEspecial("Habilidad 3", 2);
    }
    if (getXP()>19) {
      habilidadesEspeciales[1] = new AtaqueEspecial("Habilidad 2", 1);
    }
    if (getXP()>9) {
      habilidadesEspeciales[0] = new AtaqueEspecial("Habilidad 1", 0);
    }
  }

  private Label labelHp;
  private Label labelXp;
  private Label labelAtaque;
  private Label labelDef;
  private Button[] inventarioUI = new Button[10];
  public GridPane obtenerStats(){
    GridPane grid = new GridPane();

    grid.getStyleClass().add("stats");

    //Imagen personaje
    Label imagenPersonaje = new Label("");
    imagenPersonaje.getStyleClass().add(claseImagen);
    imagenPersonaje.setMinHeight(200);
    imagenPersonaje.setMinWidth(200);
    grid.add(imagenPersonaje, 0, 0, 1, 8);

    //Label nombre de personaje
    Label nombrePersonaje = new Label(getNombre());
    grid.add(nombrePersonaje, 1, 0, 5, 1);

    //Seccion labels vida, xp, ataque y defensa
    labelHp = new Label("HP: " + getHP());
    grid.add(labelHp, 1, 1, 5, 1);
    labelXp = new Label("XP: " + getXP());
    grid.add(labelXp, 1, 2, 5, 1);
    labelAtaque = new Label("Ataque: " + getAtaque());
    grid.add(labelAtaque, 1, 3, 5, 1);
    labelDef= new Label("Defensa: " + getDefensa());
    grid.add(labelDef, 1, 4, 5, 1);

    //inventario
    Label labelInventario = new Label("Inventario");
    grid.add(labelInventario, 1, 5, 5, 1);
    for (int i = 0; i < inventarioUI.length; i++) {
      inventarioUI[i] = new Button("");
      inventarioUI[i].setMinWidth(25);
      inventarioUI[i].setMinHeight(25);
      inventarioUI[i].setMaxWidth(25);
      inventarioUI[i].setMaxHeight(25);
      inventarioUI[i].setId("" + i);
      inventarioUI[i].addEventHandler(MouseEvent.MOUSE_CLICKED, this);
      if (i < 5) {
        grid.add(inventarioUI[i], 1+i, 6, 1, 1);
      } else {
        grid.add(inventarioUI[i], i-4, 7, 1, 1);
      }
    }

    return grid;
  }

  public void actualizarStats(){
    labelHp.setText("HP: " + getHP());
    labelXp.setText("XP: " + getXP());
    labelAtaque.setText("Ataque: " + getAtaque());
    labelDef.setText("Defensa: " + getDefensa());
    for (int i = 0; i < inventario.length; i++) {
      inventarioUI[i].getStyleClass().remove("pocion");
      if (inventario[i] != null) {
        inventarioUI[i].getStyleClass().add("pocion");
      }
    }
  }

  public void handle(MouseEvent e){
    int indiceInventario = Integer.parseInt(((Button)e.getSource()).getId());
    if (getInventario()[indiceInventario] != null){
      usarObjeto(indiceInventario);
      removeInventario(indiceInventario);
    }
    actualizarStats();
  }
}
