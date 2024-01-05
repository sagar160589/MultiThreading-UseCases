//Wrong Result - Total numbers added to queue are 9985

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class MultiThreading_Mutex_WithoutLock implements Runnable{

    private final int number;

    public MultiThreading_Mutex_WithoutLock(int number) {
        this.number = number;
    }
    static Queue<Integer> numberQueue = new PriorityQueue<>();
    public static void main(String args[]) throws InterruptedException {
        addQueue(numberQueue);
    }

    private static void addQueue(Queue<Integer> numberQueue) throws InterruptedException {
        Thread t = null;
        Thread[] threads = new Thread[10000];
        for(int i = 0; i<10000; i++){
            t = new Thread(new MultiThreading_Mutex_WithoutLock(1));
            threads[i] = t;
            t.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Total numbers added to queue are "+numberQueue.size());
    }

    @Override
    public void run() {
        numberQueue.add(number);
    }
}
