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
        // q - large prime number
        BigInteger q = randomBigInt(keyLength, 100);
        BigInteger tempKey;
        // p - large prime number, q | (p-1)
        BigInteger p;
        do {
            tempKey = randomBigInt(keyLength);
            p = q.multiply(tempKey).add(BigInteger.ONE);
        } while(!p.isProbablePrime(100));
        // g - generator of the group (Z/QZ)
        BigInteger g;
        do {
            g = randomBigInt(p).modPow(tempKey, p);
        } while (g.equals(BigInteger.ONE));
        // x - private key; x is an element of (Z/QZ)
        BigInteger x = randomBigInt(q);
        // y - public key
        BigInteger y = g.modPow(x, p);

        return new Key(new PrivateKey(x), new PublicKey(p, q, g, y));
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
