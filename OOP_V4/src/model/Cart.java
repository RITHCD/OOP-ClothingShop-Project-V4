

import java.util.ArrayList;

public class Cart {
    private ArrayList<ClothingItem> items = new ArrayList<>();

    public void addItem(ClothingItem item) {
        items.add(item);
    }

    public ArrayList<ClothingItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0;
        for (ClothingItem item : items) {
            total += item.applyDiscount(0.0); // polymorphism
        }
        return total;
    }
}