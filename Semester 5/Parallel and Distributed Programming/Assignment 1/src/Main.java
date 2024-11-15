import domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Main {
    private static final int PRODUCT_COUNT = 3000;
    private static final int THREAD_COUNT = 3000;

    public static void main(String[] args) {
        List<Product> myProducts = generateProducts();

        Deposit myDeposit = new Deposit(generateProducts().size(), myProducts);
        SalesService mySales = new SalesService(myDeposit);

        Checker productChecker = new Checker(mySales);
        Timer timer = new Timer();
        timer.schedule(productChecker, 0, 1);

        List<Thread> myThreads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            myThreads.add(new Thread(mySales));
        }

        float start = System.nanoTime() / 1000000;

        for (Thread t : myThreads) {
            t.start();//start all transaction threads
        }

        for (Thread thread : myThreads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        timer.cancel();

        productChecker.run();//final product check

        float end = System.nanoTime() / 1000000;
        System.out.printf("\n%f seconds elapsed%n", (end - start) / 1000);
        int numberOfBills = mySales.getNumberOfBills();
        System.out.printf("Number of bills created: %d%n", numberOfBills);
    }

    private static List<Product> generateProducts() {
        List<Product> myProducts = new ArrayList<>();

        for (int i = 0; i < PRODUCT_COUNT; i++) {
            int productQuantity = (int) (Math.random() * 100);
            int productPrice = (int) (Math.random() * 50);
            String productName = "Product" + i;
            myProducts.add(new Product(productName, productPrice, productQuantity));
        }

        return myProducts;
    }
}