public class LinkedList<T> {
  private Nodo<T> inicial;

  public Nodo<T> getInicial(){
    return inicial;
  }

  public void setInicial(Nodo<T> nodo){
    inicial = nodo;
  }

  public boolean isEmpty(){
    return inicial == null;
  }

  public void insertarAlInicio(T elemento){
    Nodo<T> temp = new Nodo<T>(elemento);
    temp.setSiguiente(inicial);
    inicial = temp;
  }

  public void insertarAlFinal(T elemento){
    if (isEmpty()) inicial = new Nodo<T>(elemento);
    else {
      Nodo<T> temp = inicial;
      while(temp.getSiguiente() != null) temp = temp.getSiguiente();
      temp.setSiguiente(new Nodo<T>(elemento));
    }
  }

  public int length(){
    if (isEmpty()) return 0;
    else {
      Nodo<T> temp = inicial;
      int counter = 1;
      while (temp.getSiguiente() != null) {
        temp = temp.getSiguiente();
        counter++;
      }
      return counter;
    }
  }

  public void insertAt(int index, T elemento){
    if (index == 0) insertarAlInicio(elemento);
    else if (index > length()) {
      System.err.println("Index out of bounds");
    } else {
      Nodo<T> temp = inicial;
      int counter = 0;
      while (counter + 1 < index) {
        temp = temp.getSiguiente();
        counter++;
      }
      Nodo<T> temp2 = temp.getSiguiente();
      temp.setSiguiente(new Nodo<T>(elemento));
      temp.getSiguiente().setSiguiente(temp2);
    }
  }

  public T get(int indice){
    if (indice >= length()){
      System.err.println("Null pointer exception");
      return null;
    } else {
      Nodo<T> temp = inicial;
      for(int i = 0; i < indice; i++) {
        temp = temp.getSiguiente();
      }
      return temp.getElemento();
    }
  }

  public T remove(int indice){
    int size = length();
    if (indice >= size){
      System.err.println("Null pointer exception");
      return null;
    } else if(0 == indice){
      T aRemover = inicial.getElemento();
      inicial = inicial.getSiguiente();
      return aRemover;
    } else {
      Nodo<T> temp = inicial;
      for (int i = 1; i < indice; i++) {
        temp = temp.getSiguiente();
      }
      T aRemover = temp.getSiguiente().getElemento();
      System.out.println(temp.getSiguiente().getSiguiente());
      temp = temp.getSiguiente().getSiguiente();
      return aRemover;
    }
  }

  public void imprimir(){
    if(!isEmpty())
    System.out.println("[" + imprimirRecursivo(inicial) + "]");
  }

  private String imprimirRecursivo(Nodo<T> lista){
    return " " + (lista.getSiguiente() == null ? lista.getElemento().toString() : lista.getElemento().toString() + imprimirRecursivo(lista.getSiguiente()));
  }
}
