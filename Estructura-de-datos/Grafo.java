public class Grafo<T> {
  private LinkedList<Vertice<T>> vertices;

  public void agregarVertice(T elemento) {
    boolean encontrado = false;
    for(Vertice<T> vertice: vertices) {
      if (vertice.getElemento().compareTo(elemento) == 0) {
        encontrado = true;
        break;
      }
    }
    if (!encontrado) {
      vertices.insertarAlUltimo(new Vertice<>(elemento));
    }
  }

  public void agregarArista(T elemento1, T elemento2) {
    Vertice<T> temp1 = null;
    Vertice<T> temp2 = null;
    for(Vertice<T> vertice: vertices) {
      if(vertice.getElemento().compareTo(elemento1) == 0)
        temp1 = vertice;
    }
    for(Vertice<T> vertice: vertices) {
      if(vertice.getElemento().compareTo(elemento2) == 0)
        temp2 = vertice;
    }
    if(temp1 != null && temp2 != null) {
      
      temp1
    }
  }
}
