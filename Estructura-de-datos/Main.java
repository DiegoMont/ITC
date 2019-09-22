public class Main {
  public static void main(String[] args) {
    Stack<Integer> pila = new Stack<Integer>();
    pila.push(5);
    pila.push(6);
    System.out.println(pila.pop());
    System.out.println(pila.top());
    System.out.println(pila.pop());
    System.out.println(pila.pop());
  }
}
