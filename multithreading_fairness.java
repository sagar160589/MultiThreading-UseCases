/*With Fairness of threads - almost similar time
        ThreadThread-3 completed in: 173245
        ThreadThread-6 completed in: 173044
        ThreadThread-0 completed in: 173332
        ThreadThread-9 completed in: 172568
        ThreadThread-1 completed in: 173349
        ThreadThread-7 completed in: 172902
        ThreadThread-5 completed in: 173157
        ThreadThread-2 completed in: 173306
        ThreadThread-8 completed in: 172793
        ThreadThread-4 completed in: 173202
        Total prime numbers 5761455 with time 173375*/

import java.util.concurrent.atomic.AtomicInteger;

class MultithreadingFairness implements Runnable {

    static AtomicInteger primeCount = new AtomicInteger(0);
    static int Concurrency = 10;
    static int MAX_INT = 100000000;
    static AtomicInteger counter = new AtomicInteger(2);
    public static void main(String[] args) throws InterruptedException {

        Thread t = null;
        Thread[] threads = new Thread[Concurrency];
        long start = System.currentTimeMillis();
        for(int i=0; i<Concurrency;i++){
            t = new Thread(new MultithreadingFairness());
            threads[i] = t;
            t.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("Total prime numbers "+primeCount+" with time"+elapsedTime);
    }



    @Override
    public void run() {

        int numberToCheck;
        long start = System.currentTimeMillis();
        while ((numberToCheck = counter.getAndIncrement()) <=MAX_INT) {
            checkPrime(numberToCheck);
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("Thread" + Thread.currentThread().getName() + " completed in: "+elapsedTime);
    }

    static boolean checkPrime(int n) {

        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
        primeCount.getAndIncrement();
        return true;
    }
}