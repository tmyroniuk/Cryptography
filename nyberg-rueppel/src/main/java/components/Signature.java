package components;

import java.math.BigInteger;

public class Signature {
    public BigInteger getA() {
        return a;
    }

    public BigInteger getB() {
        return b;
    }

    BigInteger a, b;

    public Signature(BigInteger a, BigInteger b) {
        this.a = a;
        this.b = b;
    }
}
