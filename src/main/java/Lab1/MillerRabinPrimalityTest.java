package Lab1;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class MillerRabinPrimalityTest {
    private final static Random rand = new Random();

    private static String blue(String str){ return (char) 27 + "[34m" + str + (char) 27 + "[0m"; }
    private static String yellow(String str){ return (char) 27 + "[33m" + str + (char) 27 + "[0m"; }
    private static String red(String str){ return (char) 27 + "[31m" + str + (char) 27 + "[0m"; }

    /**
     * @return random BigInteger in range [2, n-2]
     */
    private static BigInteger randomBase(BigInteger n) {
        BigInteger res;
        do {
            res = new BigInteger(n.bitLength(), rand);
            // while res is not in [2, n - 2]
        } while (res.compareTo(BigInteger.TWO) < 0 || res.compareTo(n.subtract(BigInteger.TWO)) > 0);
        return res;
    }

    public static boolean isProbablyPrime(BigInteger n, int maxIterations) {
        if(n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException();
        }
        if (n.compareTo(BigInteger.ONE) <= 0) {
            // n is 0 or 1
            return false;
        }
        if (n.compareTo(BigInteger.valueOf(3)) <= 0) {
            // n is 2 or 3
            return true;
        }
        if (n.getLowestSetBit() != 0) {
            // n is even
            return false;
        }
        // n > 3 and odd

        // Write n as d * 2^r + 1
        BigInteger d = n.subtract(BigInteger.ONE).divide(BigInteger.TWO);
        int r = 1;
        while (d.getLowestSetBit() != 0) {
            // While m is even
            d = d.divide(BigInteger.TWO);
            ++r;
        }

        // Main loop
        for (int i = 0; i < maxIterations; ++i) {
            BigInteger a = randomBase(n);
            BigInteger x = a.modPow(d, n);
            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                // x = 1 or x = n - 1
                continue;
            }
            int j = 0;
            while (j < r - 1 && !x.equals(n.subtract(BigInteger.ONE))) {
                x = x.modPow(BigInteger.TWO, n);
                ++j;
            }
            if(x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String exit = "exit";
        int iterations = 10;
        String str;

        System.out.println(blue("Enter number to test, or ") + yellow(exit) + blue(" to exit"));

        while (true) {
            str = sc.nextLine();
            if (str.equals(exit)) {
                break;
            }
            try {
                BigInteger n = new BigInteger(str);
                if (isProbablyPrime(n, iterations)) {
                    System.out.println(blue(n + " is probably prime"));
                } else {
                    System.out.println(blue(n + " is not prime"));
                }
            } catch (IllegalArgumentException e) {
                System.out.println(red("Invalid input"));
            }
        }
    }
}
