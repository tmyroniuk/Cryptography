import java.math.BigInteger;
import java.util.Scanner;


public class ExtendedEuclideanAlgorithm {
    private static String blue(String str){ return (char) 27 + "[34m" + str + (char) 27 + "[0m"; }
    private static String yellow(String str){ return (char) 27 + "[33m" + str + (char) 27 + "[0m"; }
    private static String red(String str){ return (char) 27 + "[31m" + str + (char) 27 + "[0m"; }

    /**
     * @return BigInteger[3] {gcd(a, b), x, y}, where ax + by = gcd(a, b)
     */
    public static BigInteger[] gcdExtended(BigInteger a, BigInteger b)
    {
        if(b.equals(BigInteger.ZERO)) {
            return new BigInteger[] {a, BigInteger.ONE, BigInteger.ZERO};
        } else if (a.equals(BigInteger.ZERO)) {
            return new BigInteger[] {b, BigInteger.ZERO, BigInteger.ONE};
        }

        BigInteger[] res = gcdExtended(b.remainder(a), a);
        // x(i+1) = y(i) - (b / a) * x(i)
        return new BigInteger[] {res[0], res[2].subtract(b.divide(a).multiply(res[1])), res[1]};
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
                BigInteger[] res = gcdExtended(new BigInteger(temp[0]), new BigInteger(temp[1]));
                System.out.println(blue("gcd: " + res[0] + ", x: " + res[1] + ", y: " + res[2]));
            } catch (IllegalArgumentException e) {
                System.out.println(red("Invalid input"));
            }
        }
    }
}
