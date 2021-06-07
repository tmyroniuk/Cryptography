package encryption;

import components.*;
import static components.Randomizer.*;

import java.math.BigInteger;
import java.util.Random;

public class Encryptor {
    private static final Random rand = new Random();



    private static BigInteger func(BigInteger m, BigInteger p) {
        return m.add(m.multiply(BigInteger.valueOf(1<<4))).mod(p);
    }

    public static boolean verify(BigInteger msg, Signature msgS, PublicKey key) {
        BigInteger g = key.getG();
        BigInteger p = key.getP();
        BigInteger y = key.getY();
        BigInteger a = msgS.getA();
        BigInteger b = msgS.getB();

        BigInteger u1 = g.modPow(b, p).multiply(y.modPow(a.negate(), p)).mod(p);
        BigInteger u2 = a.multiply(u1.modInverse(p)).mod(p);

        System.out.println("p:" + p + " g:" + g + " y:" + y + " u1:" + u1 + " u2:" + u2 + " msg:" + msg + " f(msg):" + func(msg, p));
        return u2.equals(func(msg, p));
    }

    public static Signature encrypt(Key key, BigInteger msg) {
        BigInteger x = key.getPrivateKey().getX();
        BigInteger q = key.getPrivateKey().getQ();
        BigInteger g = key.getPublicKey().getG();
        BigInteger p = key.getPublicKey().getP();

        BigInteger k = randomBigInt(q.subtract(BigInteger.ONE));
        BigInteger r = g.modPow(k, p);
        BigInteger a = func(msg, p).multiply(r).mod(p);
        BigInteger b = x.multiply(a).add(k).mod(q);

        System.out.println("p:" + p + " x:" + x + " q:" + q + " g:" + g + " k:" + k + " r:" + r + " a:" + a + " b:" + b + " msg:" + msg);

        return new Signature(a, b);
    }
}
