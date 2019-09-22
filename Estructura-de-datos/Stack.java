public class Stack<T> {
  private Nodo<T> inicial;

  public Nodo<T> getInicial(){
    return this.inicial;
  }

  public void setInicial(Nodo<T> inicial){
    this.inicial = inicial;
  }

  public void push(T elemento){
    if (isEmpty()) inicial = new Nodo<T>(elemento);
    else {
      Nodo<T> temp = inicial;
      inicial = new Nodo<T>(elemento);
      inicial.setSiguiente(temp);
    }
  }

  public T pop(){
    if (isEmpty()) {
      System.out.println("La cola esta vacia");
      return null;
    }
    T temp = inicial.getElemento();
    inicial = inicial.getSiguiente();
    return temp;
  }

  public T top(){
    if (isEmpty()) {
      System.out.println("La cola esta vacia");
      return null;
    }
    return inicial.getElemento();
  }

  public boolean isEmpty(){
    return getInicial() == null;
  }
}
