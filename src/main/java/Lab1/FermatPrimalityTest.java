package Lab1;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class FermatPrimalityTest {
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
            //while res is not in [1, n - 1]
        } while (res.compareTo(BigInteger.ONE) < 0 || res.compareTo(n.subtract(BigInteger.ONE)) > 0);
        return res;
    }

    public static boolean isPrime(BigInteger n, int maxIterations)
    {
        if(n.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException();
        }

        //base case
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return false;
        //test for two
        if (n.equals(BigInteger.TWO))
            return true;
        //easy test for even numbers
        if (n.getLowestSetBit() != 0)
            return false;

        for (int i = 0; i < maxIterations; ++i)
        {
            BigInteger a = randomBase(n);
            a = a.modPow(n.subtract(BigInteger.ONE), n);
            if(!a.equals(BigInteger.ONE)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream
        String exit = "exit";
        int iterations = 10;
        String str;

        System.out.println(blue("Enter number to test, or ") + yellow(exit) + blue(" to exit"));

        while(true) {
            str = sc.nextLine();
            if(str.equals(exit)) {
                break;
            }
            try {
                BigInteger n = new BigInteger(str);
                if(isPrime(n, iterations)) {
                    System.out.println(blue(n + " is prime"));
                } else {
                    System.out.println(blue(n + " is composite"));
                }
            } catch (IllegalArgumentException e) {
                System.out.println(red("Invalid input"));
            }
        }
    }
}
