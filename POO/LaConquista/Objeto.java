public class Objeto{
  private String nombreObjeto;
  private int tipoObjeto;
  private int aumentoAtaque;
  private int aumentoDefensa;
  private int aumentoEnergia;
  private int aumentoVida;

  /*Habra varios tipos de objetos que podran ser usados y tener un efecto diferente en el personaje. Con un numero se distiguiran los objetos para conocer su efecto en el personaje:
  1 arma
  2 armadura
  3 aumento de energia
  4 aumento de puntos de vida
  5 pista para juego
  */
  public Objeto(String nombre, String tipoObjeto){
    nombreObjeto = nombre;
    switch (tipoObjeto) {
      case "arma": this.tipoObjeto = 1; break;
      case "armadura": this.tipoObjeto = 2; break;
      case "masEnergia": this.tipoObjeto = 3; break;
      case "masVida": this.tipoObjeto = 4; break;
      case "pista": this.tipoObjeto = 5; break;
      default: System.out.println("Este tipo de objeto no es valido!");
      this.tipoObjeto=5;
    }
    aumentoAtaque = 5;
    aumentoDefensa = 5;
    aumentoEnergia = 25;
    aumentoVida = 50;
  }

  public int getAumentoAtaque(){
    return aumentoAtaque;
  }

  public int getAumentoDefensa(){
    return aumentoDefensa;
  }

  public int getAumentoEnergia(){
    return aumentoEnergia;
  }

  public int getAumentoVida(){
    return aumentoVida;
  }

  public int getTipoObjeto(){
    return tipoObjeto;
  }

  public String toString(){
    String regreso = nombreObjeto;
    switch (tipoObjeto) {
      case 1: regreso+=" => Arma: +"+getAumentoAtaque()+ " Ataque"; break;
      case 2: regreso+=" => Armadura: +"+getAumentoDefensa()+ " Defensa"; break;
      case 3: regreso+=" => Subir HP: +"+getAumentoVida()+ " HP"; break;
      case 4: regreso+=" => Subir energia: +"+getAumentoEnergia()+ " Energia"; break;
      case 5: regreso += " => Pista";
    }
    return regreso;
  }
}
