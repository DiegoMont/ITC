public class Card implements CardPrinter{
    int value;
    
    public Card(int value) {
        this.value = value;
    }

    public void print() {
        System.out.println("The value of the card is " + this.value);
    }
}
