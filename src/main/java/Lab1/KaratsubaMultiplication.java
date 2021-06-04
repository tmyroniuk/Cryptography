package Lab1;

import java.math.BigInteger;
import java.util.Scanner;

public class KaratsubaMultiplication {
    private static String blue(String str){ return (char) 27 + "[34m" + str + (char) 27 + "[0m"; }
    private static String yellow(String str){ return (char) 27 + "[33m" + str + (char) 27 + "[0m"; }
    private static String red(String str){ return (char) 27 + "[31m" + str + (char) 27 + "[0m"; }

    public static BigInteger multiply(BigInteger x, BigInteger y) {
        int n = Math.max(x.bitLength(), y.bitLength());

        // Direct multiplication for small numbers
        if (n <= 3)
            return x.multiply(y);

        // number of bits divided by 2, rounded up
        n = (n / 2) + (n % 2);

        // x = a * 2^n + b, y = c * 2^n + d
        BigInteger a = x.shiftRight(n);
        BigInteger b = x.subtract(a.shiftLeft(n));
        BigInteger c = y.shiftRight(n);
        BigInteger d = y.subtract(c.shiftLeft(n));

        BigInteger ac = multiply(a, c);
        BigInteger bd = multiply(b, d);
        BigInteger abcd = multiply(a.add(b), c.add(d)); // (a + b)(c + d)

        // ac * 2^2n + ((a+b)(c+d) - ac - bd) * 2^n + bd
        return bd.add(abcd.subtract(bd).subtract(ac).shiftLeft(n)).add(ac.shiftLeft(2 * n));
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        String exit = "exit";
        String str;

        System.out.println(blue("Enter two numbers, or ") + yellow(exit) + blue(" to exit"));

        while (true) {
            str = sc.nextLine();
            if (str.equals(exit)) {
                break;
            }
            try {
                String[] temp = str.split(" ");
                if (temp.length != 2) {
                    throw new IllegalArgumentException();
                }
                System.out.println(blue(multiply(new BigInteger(temp[0]), new BigInteger(temp[1])).toString()));
            } catch (IllegalArgumentException e) {
                System.out.println(red("Invalid input"));
            }
        }
    }
}
