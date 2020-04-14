import java.util.ArrayList;
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

  public String toString() {
    int niveles = getNiveles();
    if(niveles == 0)
      return "This binary tree is empty";
    int sizeElemento = biggestString(raiz);
    int largoHorizontal = (int)(Math.pow(2, niveles-1)*2 -1) * sizeElemento;
    String texto = addEspacios((largoHorizontal-sizeElemento)/2) + completarIzquierda(raiz.getElemento().toString(), sizeElemento) + "\n";
    for(int level = 2; level < niveles+1; level++) {
      int elementosDelNivel = (int) Math.pow(2, level-1);
      int numEspacios = (int)(largoHorizontal - ((2 * elementosDelNivel - 1)) * sizeElemento) / (elementosDelNivel * 2);
      Object[] elementosNuevos = new Object[elementosDelNivel];
      elementosEnNivel(raiz, level, elementosNuevos, 1, 0, elementosDelNivel);
      for(int i = 0; i < elementosDelNivel; i++) {
        boolean isNull = elementosNuevos[i] == null;
        String palabra = addEspacios(numEspacios);
        if (isNull)
          palabra += addEspacios(sizeElemento);
        else
          palabra += i%2 == 0 ? completarIzquierda(elementosNuevos[i].toString(), sizeElemento): completarDerecha(elementosNuevos[i].toString(), sizeElemento);
        palabra += addEspacios(numEspacios + sizeElemento);
        texto += palabra;
      }
      texto += "\n";
    }
    return texto;
  }

  private void elementosEnNivel(Nodo<T> temp, int nivel, Object[] nuevosElementos, int nivelActual, int rangoMin, int rangoMax) {
    if (temp.getIzquierdo() != null) {
      elementosEnNivel(temp.getIzquierdo(), nivel, nuevosElementos, nivelActual+1, rangoMin, rangoMax-(rangoMax-rangoMin)/2);
    }
    if (temp.getDerecho() != null) {
      elementosEnNivel(temp.getDerecho(), nivel, nuevosElementos, nivelActual+1, rangoMin + (rangoMax-rangoMin)/2, rangoMax);
    }
    if (nivel == nivelActual+1) {
      nuevosElementos[rangoMin] = temp.getIzquierdo() == null? null: temp.getIzquierdo().getElemento();
      nuevosElementos[rangoMax-1] = temp.getDerecho() == null? null: temp.getDerecho().getElemento();
    }

  }

  private String completarIzquierda(String elemento, int minSize) {
    String texto = elemento;
    for (int i = minSize - texto.length(); i > 0; i--)
      texto = " " + texto;
    return texto;
  }

  private String completarDerecha(String elemento, int minSize) {
    String texto = elemento;
    for (int i = minSize - texto.length(); i > 0; i--)
      texto += " ";
    return texto;
  }

  private String addEspacios(int numeroEspacios) {
    String texto = "";
    for (int i = 0; i < numeroEspacios; i++)
      texto += " ";
    return texto;
  }

  private int contar(Nodo<T> temp, int nivel) {
    if(temp.getIzquierdo() != null && temp.getDerecho() != null){
      int derecho = contar(temp.getDerecho(), nivel+1);
      int izquierdo = contar(temp.getIzquierdo(), nivel+1);
      return derecho > izquierdo ? derecho : izquierdo;
    }
    if(temp.getDerecho() != null)
      return contar(temp.getDerecho(), nivel+1);
    if(temp.getIzquierdo() != null)
      return contar(temp.getIzquierdo(), nivel+1);
    else return nivel;
  }

  private int biggestString(Nodo<T> temp) {
    int tamanio1 = 0;
    int tamanio2 = 0;
    if(temp.getIzquierdo() != null)
      tamanio1 = biggestString(temp.getIzquierdo());
    if(temp.getDerecho() != null)
      tamanio2 = biggestString(temp.getDerecho());
    if(tamanio1 < tamanio2)
     tamanio1 = tamanio2;
     int lengthElemento = temp.getElemento().toString().length();
     return lengthElemento > tamanio1 ? lengthElemento: tamanio1;
  }

  private int getNiveles() {
    if(raiz == null)
      return 0;
    else
      return contar(raiz, 1);
  }
}
