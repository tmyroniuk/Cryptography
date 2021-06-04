package Lab1;

import java.math.BigInteger;
import java.util.Scanner;

public class Montgomery {
    private static String blue(String str){ return (char) 27 + "[34m" + str + (char) 27 + "[0m"; }
    private static String yellow(String str){ return (char) 27 + "[33m" + str + (char) 27 + "[0m"; }
    private static String red(String str){ return (char) 27 + "[31m" + str + (char) 27 + "[0m"; }

    private static BigInteger reduce(BigInteger a, BigInteger b, BigInteger n) {
        BigInteger r = BigInteger.TWO.shiftLeft(n.bitLength());
        BigInteger[] gcdExt = ExtendedEuclideanAlgorithm.gcdExtended(n, r);

        // u = (t + ( t ⋅ n′ mod r) ⋅ n) / r
        BigInteger t = a.multiply(b);
        BigInteger m = t.multiply(gcdExt[1].negate()).and(r.subtract(BigInteger.ONE));
        BigInteger u = t.add(m.multiply(n)).shiftRight(n.bitLength());

        if (u.compareTo(n) >= 0) {
            return u.subtract(n);
        } else {
            return u;
        }
    }

    public static BigInteger multiply(BigInteger a, BigInteger b, BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) <= 0 || n.getLowestSetBit() != 0) {
            throw new IllegalArgumentException("Modulo must be positive odd number");
        }
        BigInteger a1 = a.shiftLeft(n.bitLength()).mod(n);
        BigInteger b1 = b.shiftLeft(n.bitLength()).mod(n);
        return reduce(reduce(a1, b1, n), BigInteger.ONE, n);
    }

    public static BigInteger pow(BigInteger a, BigInteger e, BigInteger n) {
        if (n.compareTo(BigInteger.ZERO) <= 0 || n.getLowestSetBit() != 0) {
            throw new IllegalArgumentException("Modulo must be positive odd number");
        }
        BigInteger a1 = a.shiftLeft(n.bitLength()).mod(n);
        BigInteger x1 = BigInteger.ONE.shiftLeft(n.bitLength()).mod(n);
        for (int i = e.bitLength() - 1; i >= 0; --i) {
            x1 = reduce(x1, x1, e);
            if (e.testBit(i)) {
                x1 = reduce(x1, a1, e);
            }
        }
        return reduce(x1, BigInteger.ONE, n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String exit = "exit";
        String str;
        System.out.println(blue("Enter operation (") + yellow("*, ^") + blue(") and three numbers, or ") + yellow(exit) + blue(" to exit"));

        while (true) {
            str = sc.nextLine();
            if (str.equals(exit)) {
                break;
            }
            try {
                String[] temp = str.split(" ");
                if (temp.length != 4) {
                    throw new IllegalArgumentException("Incorrect number of arguments");
                }
                if (temp[0].equals("^")) {
                    System.out.println(blue(pow(new BigInteger(temp[1]), new BigInteger(temp[2]), new BigInteger(temp[3])).toString()));
                } else if (temp[0].equals("*")) {
                    System.out.println(blue(multiply(new BigInteger(temp[1]), new BigInteger(temp[2]), new BigInteger(temp[3])).toString()));
                } else {
                    throw new IllegalArgumentException("Unknown operations");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(red("Invalid input; " + e.getMessage()));
            }
        }
    }
}
