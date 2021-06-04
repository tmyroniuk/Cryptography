import Lab1.Montgomery;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class MontgomeryTests {
    @Test
    public void multiplyingTest1() {
        BigInteger a, b, n;
        a = new BigInteger("6544443325");
        b = new BigInteger("43876543243");
        n = new BigInteger("5498761");
        Assert.assertEquals(a.multiply(b).mod(n), Montgomery.multiply(a,b,n));
    }

    @Test
    public void multiplyingTest2() {
        BigInteger a, b, n;
        a = new BigInteger("6544443325");
        b = new BigInteger("44567645756");
        n = new BigInteger("5498761");
        Assert.assertEquals(a.multiply(b).mod(n), Montgomery.multiply(a,b,n));
    }

    @Test
    public void multiplyingTest3() {
        BigInteger a, b, n;
        a = new BigInteger("6544443325");
        b = new BigInteger("53465645");
        n = new BigInteger("5498761");
        Assert.assertEquals(a.multiply(b).mod(n), Montgomery.multiply(a,b,n));
    }
}
