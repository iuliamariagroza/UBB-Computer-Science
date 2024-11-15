package domain;

import java.util.TimerTask;

public class Checker extends TimerTask {
    private final SalesService transactionService;

    public Checker(SalesService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    synchronized public void run() {
        SalesService.priceCheckerLock.lock();
        System.out.println("--------- Started product checking ---------");
        System.out.printf("Total quantity: %d | Sold quantity: %d | Remaining quantity - %d%n", transactionService.getTotalQuantity(), transactionService.getSoldQuantity(), transactionService.getRemainingQuantity());

        if(transactionService.getTotalQuantity() != transactionService.getSoldQuantity() + transactionService.getRemainingQuantity()){
            System.err.println("Quantity check failed!");
        }

        if(transactionService.getBillsProfit() != transactionService.getProfit()){
            System.err.println("Profit check failed");
        }

        System.out.println("--------- Finished product checking ---------");
        SalesService.priceCheckerLock.unlock();
    }
}
