import domain.Operations;
import domain.Polynomials;

import java.util.concurrent.ExecutionException;

public class Main {

    private static Polynomials regularSequential(Polynomials a, Polynomials b) {
        long startTime = System.currentTimeMillis();
        Polynomials result1 = Operations.sequentialRegularMultiplication(a, b);
        long endTime = System.currentTimeMillis();
        System.out.println("Simple sequential multiplication: time: " + ((endTime - startTime) / 1000.0) + " seconds");
        return result1;
    }

    private static Polynomials regularThreaded(Polynomials a, Polynomials b) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Polynomials result2 = Operations.threadedRegularMultiplication(a, b);
        long endTime = System.currentTimeMillis();
        System.out.println("Simple parallel multiplication: time: " + ((endTime - startTime) / 1000.0) + " seconds");
        return result2;
    }

    private static Polynomials karatsubaSequential(Polynomials a, Polynomials b) {
        long startTime = System.currentTimeMillis();
        Polynomials result3 = Operations.karatsubaSequential(a, b);
        long endTime = System.currentTimeMillis();
        System.out.println("Karatsuba sequential multiplication: time: " + ((endTime - startTime) / 1000.0) + " seconds");
        return result3;
    }

    private static Polynomials karatsubaThreaded(Polynomials a, Polynomials b) throws ExecutionException,
            InterruptedException {
        long startTime = System.currentTimeMillis();
        Polynomials result4 = Operations.karatsubaThreaded(a, b, 1);
        long endTime = System.currentTimeMillis();
        System.out.println("Karatsuba parallel multiplication: time: " + ((endTime - startTime) / 1000.0) + " seconds");
        return result4;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Polynomials a = new Polynomials(150);
        Polynomials b = new Polynomials(150);

        System.out.println("First:" + a);
        System.out.println("Second:" + b);
        System.out.println("\n");

        regularSequential(a, b);
        regularThreaded(a, b);
        karatsubaSequential(a, b);
        karatsubaThreaded(a, b);
    }
}
