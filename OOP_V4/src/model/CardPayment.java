

public class CardPayment extends Payment {

    private String cardNumber;

    public CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using CARD: " + cardNumber);
    }

    @Override
    public String getPaymentType() {
        return "Card";
    }
}