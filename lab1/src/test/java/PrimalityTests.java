import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class PrimalityTests {
    final static int iterations = 100;

    @Test
    public void test1() {
        Assert.assertFalse(FermatPrimalityTest.isProbablyPrime(BigInteger.ONE, iterations));
        Assert.assertFalse(MillerRabinPrimalityTest.isProbablyPrime(BigInteger.ONE, iterations));
    }
    @Test
    public void test2() {
        Assert.assertTrue(FermatPrimalityTest.isProbablyPrime(BigInteger.TWO, iterations));
        Assert.assertTrue(MillerRabinPrimalityTest.isProbablyPrime(BigInteger.TWO, iterations));
    }
    @Test
    public void rest3() {
        Assert.assertTrue(FermatPrimalityTest.isProbablyPrime(BigInteger.valueOf(3), iterations));
        Assert.assertTrue(MillerRabinPrimalityTest.isProbablyPrime(BigInteger.valueOf(3), iterations));
    }
    @Test
    public void rest4() {
        Assert.assertFalse(FermatPrimalityTest.isProbablyPrime(BigInteger.valueOf(8), iterations));
        Assert.assertFalse(MillerRabinPrimalityTest.isProbablyPrime(BigInteger.valueOf(8), iterations));
    }
    @Test
    public void rest5() {
        Assert.assertTrue(FermatPrimalityTest.isProbablyPrime(new BigInteger("178933406622112350005292883899574764047"), iterations));
        Assert.assertTrue(MillerRabinPrimalityTest.isProbablyPrime(new BigInteger("178933406622112350005292883899574764047"), iterations));
    }
}
