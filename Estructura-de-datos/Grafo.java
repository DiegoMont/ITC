public class Grafo<T> {
  private LinkedList<Vertice<T>> vertices;
  private Queue<Vertice<T>> verticesQueue;
  private Stack<Vertice<T>> verticesStack;

  public Grafo() {
    vertices = new ArrayList<>();
    
  }

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
      Arista<T> arista = new Arista<>(temp1, temp2);
      temp1.agregarArista(arista);
      temp2.agregarArista(arista);
    }
  }

  public void recorrerAnchura() {
    if(vertices.get() == null)
      System.out.println("");
    else {
      verticesQueue.add(vertices.get(0));
      while(!verticesQueue.isEmpty()) {
        Vertice<T> aExplorar = verticesQueue.poll();
        if(aExplorar.isVisitado()) {
          System.out.println(aExplorar.getElemento().toString());
        }
        aExplorar.setVisitado(true);
        List<Arista<T>> aristasAVisitar = aExplorar.getAristas();
        for(Arista<T> arista: aristasAVisitar) {
          if(!arista.getVertice1().isVisitado())
            verticesQueue.add(arista.getVertice1());
          if(!arista.getVertice2().isVisitado())
            verticesQueue.add(arista.getVertice2());
        }
      }
    }
  }
}
