package domain;

import java.util.HashMap;
import java.util.Map;

public class Bill {
    private final Map<Product, Integer> products;
    private int finalPrice;

    public Bill() {
        this.products = new HashMap<>();
        this.finalPrice = 0;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public int getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(int newPrice) {
        this.finalPrice = newPrice;
    }

    @Override
    public String toString() {
        StringBuilder billDetails = new StringBuilder("Bill Details:\n");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            billDetails.append(String.format("Product: %s, Quantity: %d, Price per unit: %d\n",
                    product.getName(), quantity, product.getPrice()));
        }
        billDetails.append(String.format("Total Price: %d", finalPrice));
        return billDetails.toString();
    }
}
