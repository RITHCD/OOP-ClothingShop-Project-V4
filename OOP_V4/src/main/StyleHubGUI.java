import java.awt.*;
import java.util.List;
import javax.swing.*;



public class StyleHubGUI extends JFrame {
    private StoreManager store;
    private JTextArea displayArea;
    private PaymentService paymentService;

    public StyleHubGUI() {
        store = new StoreManager();
        paymentService = new PaymentService();
        Cart cart = new Cart();

        // sample data
        
        store.addItem(new Shirt("Oversize T-Shirt", 15.0, "M", 5, "Short Sleeve"));
        store.addItem(new Pants("Wide Leg Jeans", 25.0, "L", 3, "Loose Fit"));

        store.addItem(new Shirt("Boxy", 30.0, "M", 5, "Short Sleeve"));
        store.addItem(new Pants("Baggy Jeans", 25.0, "L", 3, "Loose Fit"));

        store.addItem(new Shirt("Suit", 15.0, "M", 5, "Short Sleeve"));
        store.addItem(new Pants("Jackets", 25.0, "L", 3, "Loose Fit"));


        setTitle("StyleHub - Buy and Sell Clothes System");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
 
        JButton showBtn = new JButton("Show Products");
        JButton buyBtn = new JButton("Buy Product");
        JButton sellBtn = new JButton("Sell Product");
        JButton sortBtn = new JButton("Sort by Price");
        JButton availableBtn = new JButton("Available Only");
        JButton addToCartBtn = new JButton("Add to Cart");
        JButton checkoutBtn = new JButton("Checkout");
        panel.add(addToCartBtn);
        panel.add(checkoutBtn);

        displayArea = new JTextArea(20, 55);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(displayArea);

        panel.add(showBtn);
        panel.add(buyBtn);
        panel.add(sellBtn);
        panel.add(sortBtn);
        panel.add(availableBtn);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        showBtn.addActionListener(e -> displayArea.setText(store.showAllItems()));

        buyBtn.addActionListener(e -> {
        String productName = JOptionPane.showInputDialog(this, "Enter product name to buy:");

                if (productName != null && !productName.trim().isEmpty()) {
                    Customer customer = new Customer("Student Customer");
                    ClothingItem item = store.findItemByName(productName);

                    if (item != null) {

                        //   Choose payment method
                        String[] options = {"Cash", "Card"};
                        int choice = JOptionPane.showOptionDialog(
                                this,
                                "Choose payment method:",
                                "Payment",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                options,
                                options[0]
                        );

                        Payment payment;

                        //  Create payment object
                        if (choice == 1) {
                            String cardNumber = JOptionPane.showInputDialog(this, "Enter card number:");
                            payment = new CardPayment(item.getPrice(), cardNumber);
                        } else {
                            payment = new CashPayment(item.getPrice());
                        }

                        // Process payment
                        paymentService.processPayment(payment);

                        // Buy item
                        String result = customer.buyItem(item);

                        displayArea.setText(
                                result +
                                "\nPayment Method: " + payment.getPaymentType() +
                                "\n\n" + store.showAllItems()
                        );

                    } else {
                        displayArea.setText("Product not found.");
                    }
                }
            });
        addToCartBtn.addActionListener(e -> {
                String name = JOptionPane.showInputDialog("Enter product name:");

                ClothingItem item = store.findItemByName(name);

                if (item != null) {
                    cart.addItem(item);
                    displayArea.setText(name + " added to cart.");
                } else {
                    displayArea.setText("Product not found.");
                }
            });
        
        checkoutBtn.addActionListener(e -> {
            double total = cart.calculateTotal();

            String[] options = {"Cash", "Card"};
            int choice = JOptionPane.showOptionDialog(this, "Choose payment", "Payment",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            Payment payment;

            if (choice == 1) {
                String card = JOptionPane.showInputDialog("Enter card number:");
                payment = new CardPayment(total, card);
            } else {
                payment = new CashPayment(total);
            }

            paymentService.processPayment(payment);

            displayArea.setText( "Item Names in Cart:\n");
            for (ClothingItem item : cart.getItems()) {
                displayArea.append("- " + item.getName() + "\n");
            }
                displayArea.append("Total: $" + total + "\n");
                displayArea.append("Payment Method: " + payment.getPaymentType() + "\n");
                    
        });

        sellBtn.addActionListener(e -> {
            try {
                String type = JOptionPane.showInputDialog(this, "Enter type (Shirt/Pants):");
                String name = JOptionPane.showInputDialog(this, "Enter product name:");
                double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter price:"));
                String size = JOptionPane.showInputDialog(this, "Enter size:");
                int stock = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter stock:"));

                Seller seller = new Seller("Student Seller");

                if (type.equalsIgnoreCase("Shirt")) {
                    String sleeveType = JOptionPane.showInputDialog(this, "Enter sleeve type:");
                    ClothingItem shirt = new Shirt(name, price, size, stock, sleeveType);
                    displayArea.setText(seller.addItem(store, shirt) + "\n\n" + store.showAllItems());
                } else if (type.equalsIgnoreCase("Pants")) {
                    String fitType = JOptionPane.showInputDialog(this, "Enter fit type:");
                    ClothingItem pants = new Pants(name, price, size, stock, fitType);
                    displayArea.setText(seller.addItem(store, pants) + "\n\n" + store.showAllItems());
                } else {
                    displayArea.setText("Invalid product type.");
                }

            } catch (NumberFormatException ex) {
                displayArea.setText("Invalid number input.");
            } catch (IllegalArgumentException ex) {
                displayArea.setText(ex.getMessage());
            }
        });

        sortBtn.addActionListener(e -> {
            List<ClothingItem> sortedItems = store.sortByPrice();
            StringBuilder sb = new StringBuilder("Products Sorted by Price:\n");
            for (ClothingItem item : sortedItems) {
                sb.append(item).append("\n");
            }
            displayArea.setText(sb.toString());
        });

        availableBtn.addActionListener(e -> {
            List<ClothingItem> availableItems = store.getAvailableItems();
            StringBuilder sb = new StringBuilder("Available Products:\n");
            for (ClothingItem item : availableItems) {
                sb.append(item).append("\n");
            }
            displayArea.setText(sb.toString());
        });

        JButton detailBtn = new JButton("Show Details");
        panel.add(detailBtn);

        detailBtn.addActionListener(e -> {
        displayArea.setText(store.showItemDetails());
});
    }
}