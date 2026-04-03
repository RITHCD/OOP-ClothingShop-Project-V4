

public class PaymentService {

    public void processPayment(Payment payment) {
        payment.pay(payment.getAmount());
    }
}