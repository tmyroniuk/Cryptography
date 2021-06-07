import misc.Block16Bit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class Block16BitTests {
    Random rand = new Random();
    short num1, num2, num3;
    Block16Bit block1, block2, block3;

    @Before
    public void init() {
        num1 = (short) rand.nextInt();
        num2 = (short) rand.nextInt();
        num3 = (short) rand.nextInt();

        block1 = new Block16Bit((short) num1);
        block2 = new Block16Bit((short) num2);
        block3 = new Block16Bit((short) num3);
    }

    @Test
    public void xor() {
        Assert.assertEquals(block1.xor(block2).getShort(), (short) (num1 ^ num2));
        Assert.assertEquals(block2.xor(block3).getShort(), (short) (num2 ^ num3));
        Assert.assertEquals(block3.xor(block1).getShort(), (short) (num3 ^ num1));
    }
    @Test
    public void multiply() {
        Assert.assertEquals(block1.multiply(block2).getShort(), (short) (Short.toUnsignedLong(num1) * Short.toUnsignedLong(num2) % 65537));
        Assert.assertEquals(block2.multiply(block3).getShort(), (short) (Short.toUnsignedLong(num2) * Short.toUnsignedLong(num3) % 65537));
        Assert.assertEquals(block3.multiply(block1).getShort(), (short) (Short.toUnsignedLong(num3) * Short.toUnsignedLong(num1) % 65537));
    }
    @Test
    public void add() {
        Assert.assertEquals(block1.add(block2).getShort(), (short) (Short.toUnsignedLong(num1) + Short.toUnsignedLong(num2)));
        Assert.assertEquals(block2.add(block3).getShort(), (short) (Short.toUnsignedLong(num2) + Short.toUnsignedLong(num3)));
        Assert.assertEquals(block3.add(block1).getShort(), (short) (Short.toUnsignedLong(num3) + Short.toUnsignedLong(num1)));
    }
    @Test
    public void additiveInversion() {
        Assert.assertEquals(block1.inverseAdditive().add(block1).getShort(), 0);
        Assert.assertEquals(block2.inverseAdditive().add(block2).getShort(), 0);
        Assert.assertEquals(block3.inverseAdditive().add(block3).getShort(), 0);
    }
    @Test
    public void multiplicativeInversion() {
        Assert.assertEquals(block1.inverseMultiplicative().multiply(block1).getShort(), 1);
        Assert.assertEquals(block2.inverseMultiplicative().multiply(block2).getShort(), 1);
        Assert.assertEquals(block3.inverseMultiplicative().multiply(block3).getShort(), 1);
    }
}
