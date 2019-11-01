public class Villano extends Personaje{
  private AtaqueEspecial habilidadEspecial;
  private int hP;
  private int ataque;
  private String nombreAtaque;
  private int puntosDefensa;
  private String nombre;
  private int defensa;

  public Villano(String nombre, int experiencia, int puntosDefensa){
	  super(nombre, experiencia, puntosDefensa);
	  switch(nombre){
		  case "Arquero":
		  this.nombre = nombre;
		  defensa = 2;
		  this.hP = 50;
		  this.ataque = 2;
		  this.nombreAtaque = "hola";
		  break;
		  
		  case "Esbirro":
		  this.nombre = nombre;
		  defensa = 2;
		  this.hP = 50;
		  this.ataque = 2;
		  break;
		  
		  case "Jefe":
		  this.nombre = nombre;
		  defensa = 5;
		  this.hP = 500;
		  this.ataque = 15;
		  break;
		  
		  case "Ladron":
		  this.nombre = nombre;
		  defensa = 2;
		  this.hP = 50;
		  this.ataque = 2;
		  break;
	  }
	  
    if (getXP()>20) {
      habilidadEspecial = new AtaqueEspecial(nombreAtaque, 2);
    } else if (getXP() > 19) {
      habilidadEspecial = new AtaqueEspecial(nombreAtaque, 1);
    } else if(getXP() > 9){
      habilidadEspecial = new AtaqueEspecial(nombreAtaque, 0);
    }
	
	
  }
  
  public String getNombre(){
	  return nombre;
  }
  
  public int getHP(){
	  return hP;
  }
  
  public void setHP(int hP){
	  this.hP = hP;
  }
  
   public int getDefensa(){
	  return defensa;
  }
  
   public int getAtaque(){
	  return ataque;
  }
    public void atacar(Personaje heroe, Personaje villano){
	villano.setHP(villano.getHP()-(ataque-villano.getDefensa())); 
  }
  
  public int getInitialLife(){
	  return 50;
  }

  public int atacar(){
    if (Math.random() > 0.5 && getXP() > 9 ) {
      if (getEnergia() >= habilidadEspecial.getCostoEnergetico()) {
        System.out.println("Ataque especial: " + habilidadEspecial.getAumentoAtaque()*getAtaque());
        setEnergia(getEnergia() - habilidadEspecial.getCostoEnergetico() + 10);
        return habilidadEspecial.getAumentoAtaque()*getAtaque();
      }
    }
    System.out.println("Ataque normal: " + getAtaque());
    return getAtaque();
  }
  public String toString(){
    return getNombre()+"\nHP: "+getHP()+"\nAtaque: "+getAtaque()+"\nDefensa: "+getDefensa()	;
  }
}
