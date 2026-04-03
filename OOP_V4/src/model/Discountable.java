   
   // 14/ "Interface defines a contract... class must implement"
public interface Discountable {
    double applyDiscount(double percent);
    
class Shirt implements Discountable {
    public double applyDiscount(double price) { 
        return price * 0.9;  // 10% off
    }
}
}