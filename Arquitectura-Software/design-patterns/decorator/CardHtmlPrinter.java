public class CardHtmlPrinter implements CardPrinter{
    Card card;

    public CardHtmlPrinter(int value) {
        card = new Card(value);
    }

    public CardHtmlPrinter(Card card) {
        this.card = card;
    }

    public void print() {
        System.out.println("<html><body><h1>" + card.value + "</h1></body></html>");
    }
}
