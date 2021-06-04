import Lab1.ExtendedEuclideanAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class ExtendedEuclideanTests {
    @Test
    public void test1() {
        BigInteger[] result = ExtendedEuclideanAlgorithm.gcdExtended(
                new BigInteger("180234567898765432123456786543245678765432456"),
                new BigInteger("150234567890987654323456783456789034567898765432345678976")
        );

        Assert.assertEquals(BigInteger.valueOf(8), result[0]);
        Assert.assertEquals(new BigInteger("1229954313815509585227806282539030464679998834377949985"),
                result[1]);
        Assert.assertEquals(new BigInteger("-1475561100203085651023995393195990040638327"),
                result[2]);
    }
}
