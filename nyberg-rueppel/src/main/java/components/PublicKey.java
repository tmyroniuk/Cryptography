package components;

import java.math.BigInteger;

public class PublicKey {
    private final BigInteger p, q, g, y;

    public PublicKey(BigInteger p, BigInteger q, BigInteger g, BigInteger y) {
        this.p = p;
        this.q = q;
        this.g = g;
        this.y = y;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getY() {
        return y;
    }
}
