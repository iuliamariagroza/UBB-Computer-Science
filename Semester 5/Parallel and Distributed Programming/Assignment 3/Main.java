import domain.Matrix;
import threads.ColumnThread;
import threads.KThread;
import threads.RowThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final int rowCountMatrixA = 1000;
    private static final int columnCountMatrixA = 1000;
    private static final int rowCountMatrixB = 1000;
    private static final int columnCountMatrixB = 1000;
    private static final int threadCount = 100;

    public static RowThread initializeRow(int index, Matrix a, Matrix b, Matrix c, int threadCount) {
        int finalMatrixSize = c.rowCount * c.columnCount;
        int elementCount = finalMatrixSize / threadCount;

        int startRow = elementCount * index / c.columnCount;
        int startColumn = elementCount * index % c.columnCount;

        if (index == threadCount - 1) {
            elementCount += finalMatrixSize % threadCount;
        }

        return new RowThread(startRow, startColumn, elementCount, a, b, c);
    }

    public static void rowMultiplication(Matrix a, Matrix b, Matrix c, long[] times) {
        long start = System.nanoTime();
        runRowThreadPerTask(a, b, c);
        times[0] = System.nanoTime() - start;

        start = System.nanoTime();
        runRowThreadPool(a, b, c);
        times[1] = System.nanoTime() - start;
    }


    public static void runRowThreadPerTask(Matrix a, Matrix b, Matrix c) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            threads.add(initializeRow(i, a, b, c, threadCount));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Row:\n" + c);
    }

    public static void runRowThreadPool(Matrix a, Matrix b, Matrix c) {
        ExecutorService service = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            service.submit(initializeRow(i, a, b, c, threadCount));
        }

        service.shutdown();
        try {
            if (!service.awaitTermination(300, TimeUnit.SECONDS)) {
                service.shutdownNow();//forces tasks that did not complete execution to stop after reaching the 300 seconds limit
            }
            System.out.println("Row:\n" + c.toString());
        } catch (InterruptedException ex) {
            service.shutdownNow();
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static ColumnThread initializeColumn(int index, Matrix a, Matrix b, Matrix c, int threadCount) {
        int finalMatrixSize = c.rowCount * c.columnCount;
        int elementCount = finalMatrixSize / threadCount;

        int startRow = elementCount * index % c.rowCount;
        int startColumn = elementCount * index / c.rowCount;

        if (index == threadCount - 1) {
            elementCount += finalMatrixSize % threadCount;
        }

        return new ColumnThread(startRow, startColumn, elementCount, a, b, c);
    }

    public static void columnMultiplication(Matrix a, Matrix b, Matrix c, long[] times) {
        long start = System.nanoTime();
        runColumnThreadPerTask(a, b, c);
        times[0] = System.nanoTime() - start;

        start = System.nanoTime();
        runColumnThreadPool(a, b, c);
        times[1] = System.nanoTime() - start;
    }

    public static void runColumnThreadPerTask(Matrix a, Matrix b, Matrix c) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            threads.add(initializeColumn(i, a, b, c, threadCount));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Column: \n" + c);
    }

    public static void runColumnThreadPool(Matrix a, Matrix b, Matrix c) {
        ExecutorService service = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            service.submit(initializeColumn(i, a, b, c, threadCount));
        }

        service.shutdown();
        try {
            if (!service.awaitTermination(300, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
            System.out.println("Column :\n" + c.toString());
        } catch (InterruptedException ex) {
            service.shutdownNow();
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public static KThread initializeKThread(int index, Matrix a, Matrix b, Matrix c, int threadCount) {
        int finalMatrixSize = c.rowCount * c.columnCount;
        int elementCount = finalMatrixSize / threadCount;

        if (index < finalMatrixSize % threadCount) {
            elementCount++;
        }

        int startRow = index / c.columnCount;
        int startColumn = index % c.columnCount;

        return new KThread(startRow, startColumn, elementCount, threadCount, a, b, c);
    }

    public static void kTask(Matrix a, Matrix b, Matrix c, long[] times) {
        long start = System.nanoTime();
        runKhreadPerTask(a, b, c);
        times[0] = System.nanoTime() - start;

        start = System.nanoTime();
        runKThreadPool(a, b, c);
        times[1] = System.nanoTime() - start;
    }


    public static void runKhreadPerTask(Matrix a, Matrix b, Matrix c) {
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            threads.add(initializeKThread(i, a, b, c, threadCount));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("K-th element: \n" + c);
    }

    public static void runKThreadPool(Matrix a, Matrix b, Matrix c) {
        ExecutorService service = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            service.submit(initializeKThread(i, a, b, c, threadCount));
        }

        service.shutdown();
        try {
            if (!service.awaitTermination(300, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
            System.out.println("Thread Pool K-th element:\n" + c.toString());
        } catch (InterruptedException ex) {
            service.shutdownNow();
            ex.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

public static void main(String[] args) {
    Matrix a = new Matrix(rowCountMatrixA, columnCountMatrixA, 1);
    Matrix b = new Matrix(rowCountMatrixB, columnCountMatrixB, 0);

    System.out.println(a);
    System.out.println(b);

    if (columnCountMatrixA == rowCountMatrixB) {
        Matrix c = new Matrix(rowCountMatrixA, columnCountMatrixB, 1);

        long[] rowTimes = new long[2];
        long[] columnTimes = new long[2];
        long[] kTimes = new long[2];

        rowMultiplication(a, b, c, rowTimes);
        columnMultiplication(a, b, c, columnTimes);
        kTask(a, b, c, kTimes);

        System.out.println("Execution times (in seconds):");
        System.out.printf("Row - Threads: %.4f, Thread Pool: %.4f%n", rowTimes[0] / 1_000_000_000.0, rowTimes[1] / 1_000_000_000.0);
        System.out.printf("Column - Threads: %.4f, Thread Pool: %.4f%n", columnTimes[0] / 1_000_000_000.0, columnTimes[1] / 1_000_000_000.0);
        System.out.printf("KTask - Threads: %.4f, Thread Pool: %.4f%n", kTimes[0] / 1_000_000_000.0, kTimes[1] / 1_000_000_000.0);
    } else {
        System.err.println("Impossible to multiply matrices");
    }
}

}
