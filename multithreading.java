/*Without threads
        Total prime numbers 5761455 with time 418602*/

class Multithreading {

    static int primeCount = 0;
    public static void main(String[] args) {
        int MAX_INT = 100000000;

        long start = System.currentTimeMillis();
        for (int i = 2; i <= MAX_INT; i++) {
            boolean primeValue = checkPrime(i);

        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("Total prime numbers "+primeCount+" with time "+elapsedTime);
    }

    static boolean checkPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++)
            if (n % i == 0)
                return false;
        primeCount ++;
        return true;
    }

}