/*With batch of threads - unfair
        ThreadThread-0 completed in: 67979
        ThreadThread-1 completed in: 103567
        ThreadThread-2 completed in: 122746
        ThreadThread-3 completed in: 135293
        ThreadThread-4 completed in: 144371
        ThreadThread-5 completed in: 151756
        ThreadThread-6 completed in: 157005
        ThreadThread-7 completed in: 162966
        ThreadThread-8 completed in: 165933
        ThreadThread-9 completed in: 167523
        Total prime numbers 5761455 with time 168525*/

import java.util.concurrent.atomic.AtomicInteger;

class MultithreadingUnFair implements Runnable {

    static AtomicInteger primeCount = new AtomicInteger(0);
    static int Concurrency = 10;
    static int MAX_INT = 100000000;
    static int batch_size = 0;
    private final int start_value;
    private final int currentEnd;

    public MultithreadingUnFair(int start_value, int currentEnd) {
        this.start_value = start_value;
        this.currentEnd = currentEnd;
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t = null;
        Thread[] threads = new Thread[Concurrency];
        int batch_size = MAX_INT / Concurrency;
        int start_value = 2;
        long start = System.currentTimeMillis();
        for(int i=0; i<Concurrency;i++){
            int currentEnd = start_value + batch_size;
            t = new Thread(new MultithreadingUnFair(start_value,currentEnd));
            threads[i] = t;
            System.out.println("Thread" + t.getName() + "gets" + start_value + " and "+currentEnd);
            t.start();
            start_value = currentEnd;

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
        long start = System.currentTimeMillis();
        int localStart = start_value;
        int localEnd = currentEnd;
       for(int n=localStart; n<localEnd;n++){
           checkPrime(n);
          // System.out.println("Thread" + Thread.currentThread().getName() + "running" + n);
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