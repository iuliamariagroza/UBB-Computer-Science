package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Deposit {
    private List<Product> productsList = new ArrayList<>();
    private int totalQuantity = 0;

    public Deposit(int numberOfProducts, List<Product> prod) {
        for (int i = 0; i < numberOfProducts; i++) {
            productsList.add(prod.get(i));
            this.totalQuantity += prod.get(i).getQuantity();
        }
    }

    public boolean sellProduct(String name, int quantity) {
        Product soldProduct = this.productsList.stream().filter(prod -> Objects.equals(prod.getName(), name)).collect(Collectors.toList()).getFirst();
        if(soldProduct.getQuantity() - quantity >= 0) {
            System.out.printf("Product sold: %s  Quantity sold: %d | Quantity remaining: %d%n",
                    soldProduct.getName(),
                    quantity,
                    soldProduct.getQuantity()
            );
            soldProduct.setQuantity(soldProduct.getQuantity() - quantity);
            return true;
        }
        return false;
    }

    public List<Product> getProductsList() {
        return this.productsList;
    }

    public int getTotalQuantity() {
        return this.totalQuantity;
    }

    @Override
    public String toString() {
        StringBuilder depositDetails = new StringBuilder("Deposit Details:\n");
        depositDetails.append(String.format("Total Quantity: %d\n", totalQuantity));

        for (Product product : productsList) {
            depositDetails.append(String.format("Product: %s, Quantity: %d, Price: %d\n",
                    product.getName(),
                    product.getQuantity(),
                    product.getPrice()));
        }

        return depositDetails.toString();
    }
}
