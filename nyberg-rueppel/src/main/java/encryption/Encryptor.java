package encryption;

import components.*;
import static components.Randomizer.*;

import java.math.BigInteger;
import java.util.Random;

public class Encryptor {
    private static BigInteger func(BigInteger m, BigInteger p) {
        return m.add(m.multiply(BigInteger.valueOf(1 << 4))).mod(p);
    }

    private static BigInteger funcInv(BigInteger func_m, BigInteger p) {
        return func_m.divide(BigInteger.valueOf((1 << 4) + 1)).mod(p);
    }

    public static boolean verify(BigInteger msg, Signature msgS, PublicKey key) {
        BigInteger g = key.getG();
        BigInteger p = key.getP();
        BigInteger y = key.getY();
        BigInteger a = msgS.getA();
        BigInteger b = msgS.getB();

        // u1 = (g^b)(y^-a) = g^(b - ax) = g^k = r   (mod p)
        BigInteger u1 = g.modPow(b, p).multiply(y.modInverse(p).modPow(a, p)).mod(p);
        // u2 should equal func(msg, p)
        BigInteger u2 = a.multiply(u1.modInverse(p)).mod(p);

        return u2.equals(func(msg, p));
    }

    public static BigInteger decrypt(Signature msgS, PublicKey key) {
        BigInteger g = key.getG();
        BigInteger p = key.getP();
        BigInteger y = key.getY();
        BigInteger a = msgS.getA();
        BigInteger b = msgS.getB();

        // u1 = (g^b)(y^-a) = g^(b - ax) = g^k = r   (mod p)
        BigInteger u1 = g.modPow(b, p).multiply(y.modInverse(p).modPow(a, p)).mod(p);
        // u2 should equal func(msg, p)
        BigInteger u2 = a.multiply(u1.modInverse(p)).mod(p);

        return funcInv(u2, p);
    }

    public static Signature encrypt(Key key, BigInteger msg) {
        BigInteger x = key.getPrivateKey().getX();
        BigInteger q = key.getPublicKey().getQ();
        BigInteger g = key.getPublicKey().getG();
        BigInteger p = key.getPublicKey().getP();

        BigInteger k = randomBigInt(q);
        BigInteger r = g.modPow(k, p);
        BigInteger a = func(msg, p).multiply(r).mod(p);
        BigInteger b = x.multiply(a).add(k).mod(q);

        return new Signature(a, b);
    }
}
