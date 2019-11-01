public class Sacerdote extends PersonajePrincipal{
	public Sacerdote(String nombre, int experiencia, int puntosDefensa, int hP, int ataque){
		super(nombre, "Golpe de fe", "Colera de Tezcatlipoca", " Sacrificio a los dioses", "sacerdoteButton");
		experiencia = 0;
		puntosDefensa = 6;
		hP = 100;
		ataque = 2;
	}
}
