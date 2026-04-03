import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        new StyleHubGUI().setVisible(true);


// Example payments
// Cart cart = new Cart();
// cart.addItem(new Shirt("T-Shirt", 20.0, "M", 10, "Short Sleeve"));
// cart.addItem(new Pants("Jeans", 40.0, "L", 15, "Denim"));

// Payment payment = new CashPayment(0); // amount will be passed later

// CheckoutService checkoutService = new CheckoutService();
// checkoutService.checkout(cart, payment);

//    ClothingItem item = new Shirt("", 0.0, "", 0, "Long Sleeve");
//    item.getCategory(); 
//    System.out.println("Category: " + item.getCategory());
   
//    14/ "Interface defines a contract... class must implement"
//     Discountable shirt = new Shirt("Long Sleeve Shirt", 25.0, "M", 10, "Long Sleeve");
//     double discountedPrice = shirt.applyDiscount(10.0); // Applying 10% discount
//     System.out.println("Discounted Price: $" + discountedPrice);


//Polomorphic behavior with Discountable interface
// Discountable shirt = new Shirt("Long Sleeve Shirt", 25.0 , "M", 10, "Long Sleeve");      // 10% off shirts
// Discountable pants = new Pants("Jeans", 30.0, "L", 15, "Denim");      // 15% off pants
// Discountable jacket = new Jacket("Winter Jacket", 50.0, "XL", 20, "Insulated");    // 20% off jackets (future!)
// //we use polymorphism to treat all items as Discountable, allowing us to apply discounts without worrying about the specific type 
// List<Discountable> items = List.of(shirt, pants, jacket);
// double shirtDiscountedPrice = shirt.applyDiscount(10.0);
// double pantsDiscountedPrice = pants.applyDiscount(15.0);
// double jacketDiscountedPrice = jacket.applyDiscount(20.0);
// System.out.println("Shirt Discounted Price: $" + shirtDiscountedPrice);
// System.out.println("Pants Discounted Price: $" + pantsDiscountedPrice);
// System.out.println("Jacket Discounted Price: $" + jacketDiscountedPrice);



    }
}