import java.util.LinkedList;
public class Vertice<T extends Comparable<T>> implements Comparable<T> {
  private T elemento;
  private LinkedList<Arista<T>> aristas;
  private boolean visitado;

  public Vertice(T elemento) {
    this.elemento = elemento;
    aristas = new LinkedList<>();
  }

  public T getElemento() {
    return elemento;
  }

  public void setElemento(T elemento) {
    this.elemento = elemento;
  }

  public LinkedList<Arista<T>> getAristas() {
    return aristas;
  }

  public void setAristas(LinkedList<Arista<T>> aristas) {
    this.aristas = aristas;
  }

  public void agregarArista(Vertice<T> vertice1, Vertice<T> vertice2) {
    aristas.push(new Arista<>(vertice1, vertice2));
  }

  public int compareTo(T element) {
    return 0;
  }
}
