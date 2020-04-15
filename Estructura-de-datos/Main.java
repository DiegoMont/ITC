public class Main {
  public static void main(String[] args) {
    BinaryTree<Integer> arbolito = new BinaryTree<>();
    arbolito.traverseInOrder();
    arbolito.insertar(9);
    arbolito.insertar(5);
    arbolito.insertar(15);
    arbolito.insertar(13);
    arbolito.insertar(12);
    arbolito.insertar(14);
    arbolito.insertar(17);
    arbolito.insertar(16);
    arbolito.traverseInOrder();
  }
}
