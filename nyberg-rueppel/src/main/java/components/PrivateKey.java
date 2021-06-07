package components;

import java.math.BigInteger;

public class PrivateKey {
    private final BigInteger x;

    public PrivateKey(BigInteger x) {
        this.x = x;
    }

    public BigInteger getX() {
        return x;
    }
}
