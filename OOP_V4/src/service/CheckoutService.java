

public class CheckoutService {

    public void checkout(Cart cart, Payment payment) {
        double total = cart.calculateTotal();
        System.out.println("Calculating total...");
        System.out.println("Items in cart:");
        for (ClothingItem item : cart.getItems()) {
            System.out.println("- " + item.getName() + ": $" + item.applyDiscount(0.0));
        }
        System.out.println("Total amount: $" + total);

        payment.pay(total); // polymorphism
        System.out.println("Checkout successful!");
    }
}