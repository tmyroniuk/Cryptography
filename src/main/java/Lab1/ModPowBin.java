package Lab1;

import java.math.BigInteger;

public class ModPowBin {
    public static BigInteger modPow(BigInteger base, BigInteger exponent, BigInteger mod) {
        if (exponent.equals(BigInteger.ZERO)) {
            return BigInteger.ONE.mod(mod);
        }
        if (exponent.getLowestSetBit() == 0) {
            return base.multiply(modPow(base, exponent.subtract(BigInteger.ONE), mod)).mod(mod);
        } else {
            return modPow(base, exponent.shiftRight(1), mod).pow(2).mod(mod);
        }
    }
}
