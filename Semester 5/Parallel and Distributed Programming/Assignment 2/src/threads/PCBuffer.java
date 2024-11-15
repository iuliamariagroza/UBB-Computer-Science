package threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PCBuffer {
    private static final int capacity = 1;
    private final Queue<Integer> queue = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition conditionVariable = lock.newCondition();

    public void addProductsToTheQueue(int value) throws InterruptedException {
        lock.lock();
        try{
            while (queue.size() == capacity) {
                conditionVariable.await();
                System.out.println(Thread.currentThread().getName() + "Queue is full");
            }
            queue.add(value);
            System.out.printf("%s added %d into the queue %n", Thread.currentThread().getName(), value);
            conditionVariable.signal();
        }finally {
            lock.unlock();
        }
    }

    public int get() throws InterruptedException {
        lock.lock();
        try{
            while(queue.isEmpty()){
                System.out.println(Thread.currentThread().getName()
                        + ": Buffer is empty, waiting");
                conditionVariable.await();
            }
            Integer value = queue.poll();
            if(value != null){
                System.out.printf("%s consumed %d from the queue %n", Thread
                        .currentThread().getName(), value);

                conditionVariable.signal();
            }
            return value;
        }finally {
            lock.unlock();
        }
    }
}
