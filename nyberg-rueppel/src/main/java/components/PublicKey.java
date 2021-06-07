package components;

import java.math.BigInteger;

public class PublicKey {
    private final BigInteger p, g, y;


    public PublicKey(BigInteger p, BigInteger g, BigInteger y) {
        this.p = p;
        this.g = g;
        this.y = y;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getY() {
        return y;
    }
}
