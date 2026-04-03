

public class CashPayment extends Payment {

    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using CASH");
    }

    @Override
    public String getPaymentType() {
        return "Cash";
    }
}