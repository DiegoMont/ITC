public class Main {
  public static void main(String[] args) {
    LinkedList<Integer> lista = new LinkedList<>();
    lista.get(0);
    lista.insertarAlFinal(3);
    lista.insertarAlInicio(2);
    lista.insertAt(1, 5);
    lista.insertAt(1, 8);
    lista.insertAt(4, 9);
    lista.imprimir();
    System.out.println(lista.remove(1));
    lista.imprimir();
  }
}
