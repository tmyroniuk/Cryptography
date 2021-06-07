package components;

import java.math.BigInteger;

public class PrivateKey {
    private final BigInteger x, q;

    public PrivateKey(BigInteger x, BigInteger q) {
        this.x = x;
        this.q = q;
    }

    public BigInteger getX() {
        return x;
    }

    public BigInteger getQ() {
        return q;
    }
}
