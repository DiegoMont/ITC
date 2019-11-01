public class Personaje {
  private String nombre;//nombre del personaje
  private int hP;//vida del personaje con maximo de 100
  private int xP;//experiencia con un maximo de treinta
  private int ataque;//daño a inflingir a un enemigo
  private int defensa;//cuanto daño restar al ataque de enemigo
  private int energia; //energia disponible para hacer ataques especiales

  public Personaje(String nombre, int experiencia, int puntosDefensa){
    this.nombre = nombre;
    hP = 100;
    xP = experiencia;
    ataque = 10;
    defensa = puntosDefensa;
    energia = 100;
  }

  public String getNombre(){
    return nombre;
  }

  public int getHP(){
    return hP;
  }
  
  public void substractLife(int substractor){
	  hP = hP - substractor;
  }

  public void setHP(int healthPoints){
    if (healthPoints > 100) {
      hP = 100;
    } else if (healthPoints < 0) {
      hP = 0;
    } else {
      hP = healthPoints;
    }
  }
  
  public void atacar(Personaje heroe, Personaje villano){
	villano.setHP(villano.getHP()-(ataque-villano.getDefensa())); 
  }
  
  public void atacar(Personaje heroe, Personaje villano, int aumento){
	  villano.setHP(villano.getHP()-((ataque+aumento)-villano.getDefensa())); 
  }

  public int getXP(){
    return xP;
  }

  public void setXP(int experiencePoints){
    xP = experiencePoints;
    if (xP > 30) {
      xP = 30;
    }
  }

  public int getAtaque(){
    return ataque;
  }

  public void setAtaque(int puntosAtaque){
    ataque = puntosAtaque;
  }

  public int getDefensa(){
    return defensa;
  }

  public void setDefensa(int puntosDefensa){
    defensa = puntosDefensa;
  }

  public int getEnergia(){
    return energia;
  }

  public void setEnergia(int puntos){
    if (puntos > 100) {
      energia = 100;
    } else if(puntos < 10){
      energia = 0;
    } else {
      energia = puntos;
    }
  }

  public String toString(){
    return getNombre()+"\nHP: "+getHP()+"\nXP: "+getXP()+"\nAtaque: "+getAtaque()+"\nDefensa: "+getDefensa()+"\nEnergia: "+getEnergia();
  }

  public void recibirAtaque(int danio){
    if (danio > getDefensa()) {
      setHP(getHP()-danio+getDefensa());
    }
  }
}
