public class TorresDeHanoi {
  public static void main(String[] args) {
    torresDeHanoi(args==null?2:Integer.parseInt(args[0]));
  }
  public static void torresDeHanoi(int pisos){
    moverPiezas(pisos, (pisos%2==0?3:3), 1);
  }

  public static void moverPiezas(int pisos, int dondeQuieroMiTorre, int procedencia){
    if (pisos == 1) {
      System.out.println("Mover aro 1 a torre " + dondeQuieroMiTorre);
    } else {
      moverPiezas(pisos-1, (6-procedencia-dondeQuieroMiTorre), procedencia);
      System.out.println("Mover aro " + pisos + " a torre " + dondeQuieroMiTorre);
      moverPiezas(pisos-1, dondeQuieroMiTorre, (6-procedencia-dondeQuieroMiTorre));
    }
  }
}
