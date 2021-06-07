package components;

import java.math.BigInteger;
import java.util.Random;

public class Randomizer {
    private static final Random rand = new Random();

    public static BigInteger randomBigInt(BigInteger upperLimit) {
        BigInteger res;
        do {
            res = new BigInteger(upperLimit.bitLength(), rand);
        } while (res.compareTo(upperLimit) >=0 || res.compareTo(BigInteger.ONE) <= 0);
        return res;
    }

    public static BigInteger randomBigInt(int bitLength) {
        return new BigInteger(bitLength, rand);
    }

    public static BigInteger randomBigInt(int bitLength, int certainty) {
        return new BigInteger(bitLength, certainty, rand);
    }
}
