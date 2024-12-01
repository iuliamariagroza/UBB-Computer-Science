package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Operations {
    public static int thread_count = 10;
    public static int max_depth = 4;

    public static Polynomials sequentialRegularMultiplication(Polynomials a, Polynomials b){
        int resultSize = a.polynomialDegree + b.polynomialDegree + 1;

        List<Integer> coefficients = new ArrayList<>();

        for(int i=0;i<resultSize;i++){
            coefficients.add(0);
        }

        int coeffA = a.getCoefficientCount();
        int coeffB = b.getCoefficientCount();

        for(int i=0;i<coeffA;i++){
            for(int j=0;j<coeffB;j++){
                int index = i + j;
                int value = a.polynomialCoefficients.get(i) * b.polynomialCoefficients.get(j);
                coefficients.set(index, coefficients.get(index) + value);
            }
        }
        return new Polynomials(coefficients);
    }

    public static Polynomials threadedRegularMultiplication(Polynomials a, Polynomials b) throws InterruptedException {
        int resultSize = a.polynomialDegree + b.polynomialDegree + 1;
        List<Integer> coefficients = new ArrayList<>();

        for(int i=0;i<resultSize;i++){
            coefficients.add(0);
        }

        Polynomials c = new Polynomials(coefficients);
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(thread_count);
        int stepSize = c.getCoefficientCount()/thread_count;

        if(stepSize == 0){
            stepSize = 1;
        }

        for(int i=0;i<c.getCoefficientCount(); i = i + stepSize){
            int aux = i + stepSize;
            Task task = new Task(i, aux, a, b, c);
            executor.execute(task);
        }

        executor.shutdown();
        executor.awaitTermination(50, TimeUnit.SECONDS);

        return c;
    }

    public static Polynomials karatsubaSequential(Polynomials a, Polynomials b){
        if(a.polynomialDegree < 2  || b.polynomialDegree < 2){
            return sequentialRegularMultiplication(a, b);
        }

        int len = Math.max(a.polynomialDegree, b.polynomialDegree) / 2;
        Polynomials lowPart1 = new Polynomials(a.polynomialCoefficients.subList(0, len));
        Polynomials highPart1 = new Polynomials(a.polynomialCoefficients.subList(len, a.getCoefficientCount()));
        Polynomials lowPart2 = new Polynomials(b.polynomialCoefficients.subList(0, len));
        Polynomials highPart2 = new Polynomials(b.polynomialCoefficients.subList(len, b.getCoefficientCount()));

        Polynomials p1 = karatsubaSequential(lowPart1, lowPart2);
        Polynomials p2 = karatsubaSequential(Polynomials.add(lowPart1, highPart1), Polynomials.add(lowPart2, highPart2));
        Polynomials p3 = karatsubaSequential(highPart1, highPart2);

        Polynomials highDegreeTerms = Polynomials.addZeros(p3, 2*len);
        Polynomials middleDegreeTerms = Polynomials.addZeros(Polynomials.subtract(Polynomials.subtract(p2, p3),p1), len);
        return Polynomials.add(Polynomials.add(highDegreeTerms, middleDegreeTerms), p1);
    }

    public static Polynomials karatsubaThreaded(Polynomials a, Polynomials b, int currentDepth) throws ExecutionException, InterruptedException {
        if(currentDepth > max_depth){
            return karatsubaSequential(a, b);
        }

        if(a.polynomialDegree < 2  || b.polynomialDegree < 2){
            return sequentialRegularMultiplication(a, b);
        }

        int len = Math.max(a.polynomialDegree, b.polynomialDegree) / 2;
        Polynomials lowPartA = new Polynomials(a.polynomialCoefficients.subList(0, len));
        Polynomials highPartA = new Polynomials(a.polynomialCoefficients.subList(len, a.getCoefficientCount()));
        Polynomials lowPartB = new Polynomials(b.polynomialCoefficients.subList(0, len));
        Polynomials highPartB = new Polynomials(b.polynomialCoefficients.subList(len, b.getCoefficientCount()));

        ExecutorService executor = Executors.newFixedThreadPool(thread_count);
        Future<Polynomials> f1 = executor.submit(() -> karatsubaThreaded(lowPartA, lowPartB, currentDepth + 1));
        Future<Polynomials> f2 = executor.submit(() -> karatsubaThreaded(Polynomials.add(lowPartA, highPartA), Polynomials.add(lowPartB, highPartB), currentDepth + 1));
        Future<Polynomials> f3 = executor.submit(() -> karatsubaThreaded(highPartA, highPartB, currentDepth + 1));

        executor.shutdown();

        Polynomials q1 = f1.get();
        Polynomials q2 = f2.get();
        Polynomials q3 = f3.get();

        executor.awaitTermination(60, TimeUnit.SECONDS);

        Polynomials r1 = Polynomials.addZeros(q3, 2 * len);
        Polynomials r2 = Polynomials.addZeros(Polynomials.subtract(Polynomials.subtract(q2, q3), q1), len);
        return Polynomials.add(Polynomials.add(r1, r2), q1);
    }
}
