public class Customer extends User {

    public Customer(String name) {
        super(name);
    }

    public String buyItem(ClothingItem item) {
        if (item.getStock() > 0) {
            item.setStock(item.getStock() - 1);
            return getName() + " bought: " + item.getName();
        } else {
            return item.getName() + " is out of stock.";
        }
    }
}