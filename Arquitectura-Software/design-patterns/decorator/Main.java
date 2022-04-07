public class Main {
    public static void main(String[] args) {
        CardPrinter card = new Card(10);
        card.print();
        card = new CardHtmlPrinter(10);
        card.print();
    }
}
