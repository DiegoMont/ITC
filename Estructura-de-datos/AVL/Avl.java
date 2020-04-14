import java.util.ArrayList;
public class Avl<T extends Comparable<T>> {

	private Nodo<T> raiz;

	public Nodo<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo<T> raiz) {
		this.raiz = raiz;
	}

	public void insertar(T elemento) {
    if (raiz == null) {
      raiz = new Nodo<>(elemento);
    } else {
      raiz = insertarBalance(elemento, raiz);
    }
  }

	private Nodo<T> insertarBalance(T elemento, Nodo<T> raiz) {
		if(raiz==null) {
			raiz = new Nodo<T>(elemento);
		}else {
			if(elemento.compareTo(raiz.getElemento()) > 0) {
        raiz.setDerecho(insertarBalance(elemento, raiz.getDerecho()));
        //System.out.println("Factor de equilibrio: "+getFactorEquilibrio(raiz));
				if(getFactorEquilibrio(raiz) == -2) {
					if(elemento.compareTo(raiz.getDerecho().getElemento()) > 0) {
						raiz = rotacionSimpleALaIzquierda(raiz);
					}else {
						raiz = rotacionDobleALaIzquierda(raiz);
					}
				}
      }

      if(elemento.compareTo(raiz.getElemento()) < 0) {
        raiz.setIzquierdo(insertarBalance(elemento, raiz.getIzquierdo()));
        //System.out.println("Factor de equilibrio: " + getFactorEquilibrio(raiz));
        if(getFactorEquilibrio(raiz) == 2) {
          if(elemento.compareTo(raiz.getIzquierdo().getElemento()) < 0) {
            raiz = rotacionSimpleALaDerecha(raiz);
          }else {
            raiz = rotacionDobleALaDerecha(raiz);
          }
        }
      }
    }

    int altura = calcularAltura(raiz.getIzquierdo(), raiz.getDerecho());
    //System.out.println("Altura nodo: " + raiz.getElemento() + " " + altura);
    raiz.setAltura(altura + 1);

    return raiz;
	}

	public void borrar(T elemento) {
		Nodo<T> nodoPadre = encontrarNodoPadre(raiz, elemento);
		if (nodoPadre == null)
		  System.out.println("El elemento no existe en el arbol");
		else {
			System.out.println(nodoPadre.getElemento());
			Nodo<T> nodoABorrar = encontrarNodo(nodoPadre, elemento);
			ArrayList<T> hijos = new ArrayList<>();
			guardarHijos(nodoABorrar.getDerecho(), hijos);
			guardarHijos(nodoABorrar.getIzquierdo(), hijos);
			if (nodoPadre.getDerecho().getElemento().compareTo(elemento) == 0)
				nodoPadre.setDerecho(null);
			else nodoPadre.setIzquierdo(null);
			for (T hijo : hijos)
			  insertar(hijo);
		}
	}

	private void guardarHijos(Nodo<T> temp, ArrayList<T> arreglo) {
		if(temp == null) return;
		else {
			arreglo.add(temp.getElemento());
			guardarHijos(temp.getDerecho(), arreglo);
			guardarHijos(temp.getIzquierdo(), arreglo);
		}
	}

	private Nodo<T> encontrarNodo(Nodo<T> temp, T elemento) {
		if(temp == null)
		  return null;
		if(temp.getElemento().compareTo(elemento) == 0){
		  return temp;}
		else {
			Nodo<T> derecho = encontrarNodo(temp.getDerecho(), elemento);
			Nodo<T> izquierdo = encontrarNodo(temp.getIzquierdo(), elemento);
			return derecho == null ? izquierdo: derecho;
		}
	}

	private Nodo<T> encontrarNodoPadre(Nodo<T> temp, T elemento) {
		if(temp == null)
		  return null;
		if(temp.getDerecho() != null){
		  if(temp.getDerecho().getElemento().compareTo(elemento) == 0)
			  return temp;
			else {
				Nodo<T> derecho = encontrarNodoPadre(temp.getDerecho(), elemento);
				Nodo<T> izquierdo = encontrarNodoPadre(temp.getIzquierdo(), elemento);
				return derecho == null ? izquierdo: derecho;
			}
		}
		if(temp.getIzquierdo() != null) {
		  if(temp.getIzquierdo().getElemento().compareTo(elemento) == 0)
			  return temp;
			else {
				Nodo<T> derecho = encontrarNodoPadre(temp.getDerecho(), elemento);
				Nodo<T> izquierdo = encontrarNodoPadre(temp.getIzquierdo(), elemento);
				return derecho == null ? izquierdo: derecho;
			}
		}
		return null;
	}

	private int getFactorEquilibrio(Nodo<T> raiz) {
		return (raiz.getIzquierdo() == null? 0: raiz.getIzquierdo().getAltura()) - (raiz.getDerecho() == null? 0: raiz.getDerecho().getAltura());
	}

	private int calcularAltura(Nodo<T> izquierdo, Nodo<T> derecho) {
    int alturaIzquierda;
    int alturaDerecha;

    if(izquierdo==null) {
      alturaIzquierda = 0;
    }else {
      alturaIzquierda = izquierdo.getAltura();
    }
    if(derecho==null) {
      alturaDerecha = 0;
    }else{
      alturaDerecha = derecho.getAltura();
    }

    return Math.max(alturaIzquierda, alturaDerecha);
	}

	private Nodo<T> rotacionSimpleALaDerecha(Nodo<T> raiz){
		Nodo<T> temp= raiz.getIzquierdo();
		raiz.setIzquierdo(temp.getDerecho());
		temp.setDerecho(raiz);
		raiz.setAltura(calcularAltura(raiz.getIzquierdo(), raiz.getDerecho()) + 1);
		temp.setAltura(calcularAltura(raiz.getIzquierdo(), raiz) + 1);
		return temp;
	}
	private Nodo<T> rotacionSimpleALaIzquierda(Nodo<T> raiz){
		Nodo<T> temp= raiz.getDerecho();
		raiz.setDerecho(temp.getIzquierdo());
		temp.setIzquierdo(raiz);
		raiz.setAltura(calcularAltura(raiz.getIzquierdo(), raiz.getDerecho()) + 1);
		temp.setAltura(calcularAltura(temp.getDerecho(), raiz) + 1);
		return temp;
	}
	private Nodo<T> rotacionDobleALaDerecha(Nodo<T> raiz){
		raiz.setIzquierdo(rotacionSimpleALaIzquierda(raiz.getIzquierdo()));
		return rotacionSimpleALaDerecha(raiz);

	}
	private Nodo<T> rotacionDobleALaIzquierda(Nodo<T> raiz){
		raiz.setDerecho(rotacionSimpleALaDerecha(raiz.getDerecho()));
		return rotacionSimpleALaIzquierda(raiz);
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

	public void elementosEnNivelPublico(int level, Object[] elementosNuevos) {
		elementosEnNivel(raiz, level, elementosNuevos, 1, 0, elementosNuevos.length);
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

  public int getNiveles() {
    if(raiz == null)
      return 0;
    else
      return contar(raiz, 1);
  }
}
