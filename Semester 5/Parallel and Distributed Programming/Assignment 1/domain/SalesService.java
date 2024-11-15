package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class SalesService implements Runnable {
    private final Deposit myDeposit;
    private int profit;
    private final List<Bill> bills;
    public static final ReentrantLock priceCheckerLock = new ReentrantLock();

    public SalesService(Deposit deposit) {
        this.bills = new ArrayList<>();
        this.profit = 0;
        this.myDeposit = deposit;
    }

    @Override
    public void run(){
        priceCheckerLock.lock();
        Bill currentBill = new Bill();

        while(currentBill.getProducts().size() < 3){
            int productIndex = 0;
            int productQuantity = 0;

            while(productQuantity == 0 || findProductInBill(currentBill, myDeposit.getProductsList().get(productIndex))){
                productIndex = (int) (Math.random() * myDeposit.getProductsList().size());
                productQuantity = (int) (Math.random() * myDeposit.getProductsList().get(productIndex).getQuantity());
            }

            Product soldProduct = myDeposit.getProductsList().get(productIndex);
            soldProduct.productLock.lock();

            if(myDeposit.sellProduct(soldProduct.getName(), productQuantity)){
                currentBill.getProducts().put(soldProduct, productQuantity);
                currentBill.setFinalPrice(currentBill.getFinalPrice() + soldProduct.getPrice() * productQuantity);
            }
            soldProduct.productLock.unlock();
        }
        System.out.println(currentBill);
        System.out.printf("Deposit status:", myDeposit);
        bills.add(currentBill);
        profit += currentBill.getFinalPrice();
        priceCheckerLock.unlock();
    }

    public boolean findProductInBill(Bill bill, Product product){
        for(Map.Entry<Product,Integer> billProd: bill.getProducts().entrySet()){
            if(product == billProd.getKey()){
                return true;
            }
        }
        return false;
    }

    public int getProfit() {
        return this.profit;
    }

    public synchronized int getBillsProfit() {
        return this.bills.stream().map(Bill::getFinalPrice).reduce(0, Integer::sum);
    }

    public synchronized int getSoldQuantity() {
        int soldQuantity = 0;
        for (Bill b : bills) {
            for (Map.Entry<Product, Integer> billProd : b.getProducts().entrySet()) {
                soldQuantity += billProd.getValue();
            }
        }
        return soldQuantity;
    }

    public synchronized int getRemainingQuantity() {
        return myDeposit.getProductsList().stream().map(Product::getQuantity).reduce(0, Integer::sum);
    }

    public int getTotalQuantity(){
        return myDeposit.getTotalQuantity();
    }

    public int getNumberOfBills() {
        return this.bills.size();
    }
}
