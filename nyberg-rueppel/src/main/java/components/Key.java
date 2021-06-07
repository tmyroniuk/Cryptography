package components;

import static components.Randomizer.*;

import java.math.BigInteger;

public class Key {
    private final PrivateKey privateKey;
    private final PublicKey publicKey;


    public Key(PrivateKey privateKey, PublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public static Key generateKey(int keyLength) {
        BigInteger p = randomBigInt(keyLength, 90);
        BigInteger q = largePrimeFactor(p.subtract(BigInteger.ONE), keyLength << 2);
        BigInteger x = randomBigInt(q.subtract(BigInteger.ONE));
        BigInteger g = p.shiftLeft(1).nextProbablePrime().mod(p);
        BigInteger y = g.modPow(x, p);

        //System.out.println("p:" + p + " x:" + x + " q:" + q + " g:" + g + " y:" + y);
        return new Key(new PrivateKey(x, q), new PublicKey(p, g, y));
    }

    private static BigInteger largePrimeFactor(BigInteger p, int bitLength) {
        assert p.compareTo(BigInteger.ONE) > 0;

        BigInteger largePrime = BigInteger.ONE;
        while (p.getLowestSetBit() != 0) {
            largePrime = BigInteger.TWO;
            p = p.shiftRight(1);
        }
        for (BigInteger i = BigInteger.valueOf(3); i.compareTo(p.sqrt()) <= 0; i = i.add(BigInteger.TWO)) {
            while (p.remainder(i).equals(BigInteger.ZERO)) {
                largePrime = i;
                if(largePrime.bitLength() >= bitLength) {
                    return largePrime;
                }
                p = p.divide(i);
                if(p.isProbablePrime(90)) {
                    return p;
                }
            }
        }
        if (p.compareTo(BigInteger.TWO) > 0) {
            largePrime = p;
        }
        return largePrime;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
