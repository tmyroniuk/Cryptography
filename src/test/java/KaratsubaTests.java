import Lab1.KaratsubaMultiplication;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Random;

public class KaratsubaTests {
    @Test
    public void multiplicationOnZeroShouldReturnZero() {
        Assert.assertEquals(BigInteger.ZERO,
                KaratsubaMultiplication.multiply(new BigInteger("2034954578765486095"), BigInteger.ZERO));
    }

    @Test
    public void negativeNumberShouldReturnNegativeWithMultiplyingOnPositive() {
        Assert.assertEquals(new BigInteger("-500000000000000"),
                KaratsubaMultiplication.multiply(new BigInteger("-5"), new BigInteger("100000000000000")));
    }

    @Test
    public void negativeNumberShouldReturnPositiveWithMultiplyingOnNegative() {
        Assert.assertEquals(new BigInteger("500000000000000"),
                KaratsubaMultiplication.multiply(new BigInteger("-5"), new BigInteger("-100000000000000")));
    }

    @Test
    public void resultsForMultiplicationShouldBeSameAsLibMultiplication() {
        BigInteger a, b;
        Random rand = new Random();

        for (int i = 0; i < 100; i++) {
            a = BigInteger.valueOf(Math.abs(rand.nextLong()));
            b = BigInteger.valueOf(Math.abs(rand.nextLong()));

            Assert.assertEquals(a.multiply(b), KaratsubaMultiplication.multiply(a, b));
        }
    }
}
