import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class Mapa {
  private  int[][] plano;
  private PersonajePrincipal heroeMapa;
  private int progresoNivel = 0;
  private int[][][] niveles = {
    ArreglosMapas.mapa1,
    ArreglosMapas.mapa2,
    ArreglosMapas.mapa3,
    ArreglosMapas.mapa4
  };

  private Battle battle;
  private int villanosEnfrentados = 0;
  private int limiteVillanos = 0;
  private Main main;

  private static Label[][] botonesMapa;
  private Scene scene;
  private VBox vBox;
  private String imagenPersonaje;

  /*
  Valores del arreglo:
  0 camino libre
  1 obstaculo
  2 objeto de inventario
  3 enemigo
  7 jefe
  8 entrada a nivel
  9 heroe
  */

  public Mapa(int tamanio, int itemsColocar, PersonajePrincipal heroe, Main main, String claseCss){
    this.main = main;
    heroeMapa = heroe;
    imagenPersonaje = claseCss;
    plano = ArreglosMapas.mapaPrincipal;
    for (int i = 0; i < tamanio; i++) {
      for (int j = 0; j < tamanio; j++) {
        if (i == 0 || i == tamanio-1 || j == 0 || j==tamanio-1) {
          plano[i][j] = 1;
        }
      }
    }
    //colocar objetos a mapa y villanos
    colocarObjetosYVillanos(ArreglosMapas.mapaPrincipal, 5, 0);
    int numEne = 3;
for (int[][] arregloNivel: niveles) {
  colocarObjetosYVillanos(arregloNivel, 5, numEne);
  numEne++;
}

    //Interfaz grafica
    GridPane grid = new GridPane();
    vBox = new VBox();
    vBox.getChildren().add(grid);
    vBox.getChildren().add(heroe.obtenerStats());
    scene = new Scene(vBox);
    scene.getStylesheets().add("estilos.css");
    grid.getStyleClass().add("mapa-principal");
    botonesMapa = new Label[getPlano().length][getPlano()[0].length];
    for (int i = 0; i < getPlano().length; i++) {
      for (int j = 0; j < getPlano()[i].length; j++) {
        botonesMapa[i][j] = new Label(/*""+getPlano()[i][j]*/" ");
        botonesMapa[i][j].setMinWidth(23);
        botonesMapa[i][j].setMinHeight(23);
        botonesMapa[i][j].setMaxWidth(23);
        botonesMapa[i][j].setMaxHeight(23);
        botonesMapa[i][j].getStyleClass().add("opacidadMedia");
        grid.add(botonesMapa[i][j], j, i, 1, 1);
      }
    }
    pintarMapa();
    scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
      public void handle(KeyEvent e){
        int x = ubicarHeroe()[0];
        int y = ubicarHeroe()[1];
        switch(e.getCode().getName()){
          case "W": case "Up": moverHeroe(0, -1); break;
          case "A": case "Left": moverHeroe(-1, 0); break;
          case "S": case "Down": moverHeroe(0, 1); break;
          case "D": case "Right": moverHeroe(1, 0); break;
          case "P": main.setPauseScene(main, scene);break;
        }
        x = ubicarHeroe()[0];
        y = ubicarHeroe()[1];
        plano[y][x] = 9;
        pintarMapa();
        heroe.actualizarStats();
      }
    });
  }

  public void pintarMapa() {
    for (int i = 0; i < getPlano().length; i++) {
      for (int j = 0; j < getPlano()[i].length; j++) {
        botonesMapa[i][j].getStyleClass().remove(imagenPersonaje);
        botonesMapa[i][j].getStyleClass().remove("fondo-blanco");
        botonesMapa[i][j].getStyleClass().remove("fondo-negro");
        botonesMapa[i][j].getStyleClass().remove("fondo-rojo");
        switch (plano[i][j]) {
          case 9: botonesMapa[i][j].getStyleClass().add(imagenPersonaje); break;
          case 8: botonesMapa[i][j].getStyleClass().add("fondo-blanco"); break;
          case 2:
          botonesMapa[i][j].getStyleClass().add("fondo-negro"); break;
          case 3:
          botonesMapa[i][j].getStyleClass().add("fondo-rojo"); break;
        }
      }
    }
  }

  public static Objeto itemAleatorio(){
    String nombreItem;
    //switch ((int)Math.floor(Math.random()*4) + 1) {
    switch(4){
      case 1: nombreItem = "Espada"; return new Objeto(nombreItem, "arma");
      case 2: nombreItem = "Casco"; return new Objeto(nombreItem, "armadura");
      case 3: nombreItem = "Pocion Energia"; return new Objeto(nombreItem, "masEnergia");
      default: nombreItem = "Pocion Vida"; return new Objeto(nombreItem, "masVida");
    }
  }

  public int[][] getPlano(){
    return plano;
  }

  public PersonajePrincipal getHeroeMapa(){
    return heroeMapa;
  }

  public Label[][] getBotonesMapa(){
    return botonesMapa;
  }

  public VBox getVerticalBox(){
    return vBox;
  }

  public void moverHeroe(int desplazamientoX, int desplazamientoY){
    int nuevaX = ubicarHeroe()[0]+desplazamientoX;
    int nuevaY = ubicarHeroe()[1]+desplazamientoY;
    switch (plano[nuevaY][nuevaX]) {
      case 0: cambiarHeroePlano(nuevaY, nuevaX); break;
      case 1: break;
      case 2: cambiarHeroePlano(nuevaY, nuevaX);
      System.out.println("Has encontrado un objeto");
      getHeroeMapa().addInventario(itemAleatorio());break;

      case 3: cambiarHeroePlano(nuevaY, nuevaX);
      villanosEnfrentados++;
      if (villanosEnfrentados == limiteVillanos) {
        scene.lookup("GridPane").getStyleClass().add("mapa-principal");
        plano = ArreglosMapas.mapaPrincipal;
      }
      main.setSceneBatalla(heroeMapa, crearVillano("Tizoc", "Mapachazo"), main, scene);
      break;

      case 7:
      main.setSceneBatalla(heroeMapa, crearVillanoFinal("Jefe", "Mapachazo"), main, scene);
      break;

      case 8: cambiarHeroePlano(nuevaY, nuevaX);
      scene.lookup("GridPane").getStyleClass().remove("mapa-principal");
      limiteVillanos += 3 + progresoNivel;
      switch (progresoNivel) {
        case 0: scene.lookup("GridPane").getStyleClass().add("nivel-1");
        plano = ArreglosMapas.mapa1; break;
        case 1: scene.lookup("GridPane").getStyleClass().add("nivel-2");
        plano = ArreglosMapas.mapa2; break;
        case 2: scene.lookup("GridPane").getStyleClass().add("nivel-3");
        plano = ArreglosMapas.mapa3; break;
        case 3: scene.lookup("GridPane").getStyleClass().add("nivel-4");
        plano[12][16] = 8;
        plano = ArreglosMapas.mapa4; break;
        case 4: scene.lookup("GridPane").getStyleClass().add("nivel-boss");
        plano = ArreglosMapas.mapa5; break;
      }
      progresoNivel++;
      break;
    }
  }

  public int[] ubicarHeroe(){
    for (int i = 0; i < plano.length; i++) {
      for (int j = 0; j < plano[i].length; j++) {
        if(plano[i][j] == 9){
          int[] arregloX = {j, i};
          return arregloX;
        }
      }
    }
    int[] arregloX = {5, 5};
    return arregloX;
  }

  public void cambiarHeroePlano(int i, int j){
    plano[ubicarHeroe()[1]][ubicarHeroe()[0]] = 0;
    plano[i][j] = 9;
  }

  public void imprimirPlano(){
    for (int i = 0; i < plano.length; i++) {
      for (int j = 0; j < plano.length; j++) {
        System.out.print(/*(ubicarHeroe()[0]==j && ubicarHeroe()[1]==i?9:*/plano[i][j] + " ");
      }
      System.out.println();
    }
  }

  private Villano crearVillano(String nombre, String nombreAtaque){
    int puntosDefensa = (int)Math.ceil(Math.random()*5);
	int tipoVillano = (int)Math.floor(Math.random()*3);
	switch(tipoVillano){
		case 0:
		nombre = "Arquero";
		break;
		case 1:
		nombre = "Esbirro";
		break;
		case 2:
		nombre = "Ladron";
		break;

	}
	return new Villano(nombre, getHeroeMapa().getXP(), puntosDefensa);
  }
	private Villano crearVillanoFinal(String nombre, String nombreAtaque){
    int puntosDefensa = (int)Math.ceil(Math.random()*5);
    return new Villano("Jefe", getHeroeMapa().getXP(), puntosDefensa);
  }

  private void colocarObjetosYVillanos(int[][] mapa, int numObjetos, int numVillanos){
    int ubiSecretaI; int ubiSecretaJ;
    for (int i = 0; i < numObjetos; i++) {
      do {
        ubiSecretaI = (int)Math.floor(Math.random()*mapa.length);
        ubiSecretaJ = (int)Math.floor(Math.random()*mapa[0].length);
      } while (mapa[ubiSecretaI][ubiSecretaJ] != 0);
      mapa[ubiSecretaI][ubiSecretaJ] = 2;
    }

    for (int i = 0; i < numVillanos; i++) {
      do {
        ubiSecretaI = (int)Math.floor(Math.random()*mapa.length);
        ubiSecretaJ = (int)Math.floor(Math.random()*mapa[0].length);
      } while (mapa[ubiSecretaI][ubiSecretaJ] != 0);
      mapa[ubiSecretaI][ubiSecretaJ] = 3;
    }
  }

  public Scene getScene(){
    return scene;
  }
}
