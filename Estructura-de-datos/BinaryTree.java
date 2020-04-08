import binaryTree.Nodo;

public class BinaryTree<T extends Comparable<T>> {
  private Nodo<T> raiz;

  public Nodo getRaiz() {
    return raiz;
  }

  public void setRaiz(Nodo<T> raiz) {
    this.raiz = raiz;
  }

  public void insertar(T elemento) {
    if (raiz == null) {
      raiz = new Nodo<>(elemento);
    } else {
      insertarRecursivo(raiz, elemento);
    }
  }

  private void insertarRecursivo(Nodo<T> temp, T elemento) {
    if (elemento.compareTo(temp.getElemento()) > 0) {
      if (temp.getDerecho() == null) {
        temp.setDerecho(new Nodo<>(elemento));
      } else {
        insertarRecursivo(temp.getDerecho(), elemento);
      }
    } else if(elemento.compareTo(temp.getElemento()) < 0) {
      if (temp.getIzquierdo() == null) {
        temp.setIzquierdo(new Nodo<>(elemento));
      } else {
        insertarRecursivo(temp.getIzquierdo(), elemento);
      }
    }
  }

  public void traverseInOrder() {
    traverseInOrderRecursivo(raiz);
  }

  private void traverseInOrderRecursivo(Nodo<T> temp) {
    if(temp != null) {
      traverseInOrderRecursivo(temp.getIzquierdo());
      System.out.println(temp.getElemento());
      traverseInOrderRecursivo(temp.getDerecho());
    }
  }
}
