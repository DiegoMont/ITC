public class Queue<T> {
  private Nodo<T> inicial;

  public Queue() {
    super();
  }

  public Nodo<T> getInicial(){
    return this.inicial;
  }

  public void setInicial(Nodo<T> inicial){
    this.inicial = inicial;
  }

  public void enqueue(T elemento){
    if (inicial == null) {
      inicial = new Nodo<T>(elemento);
    } else {
      Nodo<T> temp = inicial;
      while (temp.getSiguiente() != null) temp = temp.getSiguiente();
      temp.setSiguiente(new Nodo<T>(elemento));
    }
  }

  public T dequeue(){
    if (inicial == null) {
      System.out.println("La lista esta vacia");
      return null;
    } else {
      T temp = inicial.getElemento();
      setInicial(inicial.getSiguiente());
      return temp;
    }
  }

  public T peek(){
    if (inicial != null) return inicial.getElemento();
    System.out.println("La lista esta vacia");
    return null;
  }

}
