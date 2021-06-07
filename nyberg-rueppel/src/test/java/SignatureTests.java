import components.*;
import encryption.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigInteger;

public class SignatureTests {
    static BigInteger msg1, msg2, msg3;
    static Key key1, key2, key3;
    static Signature msgS1, msgS2, msgS3;

    @BeforeClass
    public static void init() {
        msg1 = BigInteger.valueOf(25467);
        msg2 = BigInteger.valueOf(4325);
        msg3 = BigInteger.valueOf(234);

        key1 = new Key(new PrivateKey(BigInteger.valueOf(3), BigInteger.valueOf(101)), new PublicKey(BigInteger.valueOf(607), BigInteger.valueOf(601), BigInteger.valueOf(391)));
        key2 = new Key(new PrivateKey(BigInteger.valueOf(5), BigInteger.valueOf(17)), new PublicKey(BigInteger.valueOf(443), BigInteger.valueOf(134), BigInteger.valueOf(43)));
        key3 = new Key(new PrivateKey(BigInteger.valueOf(8), BigInteger.valueOf(251)), new PublicKey(BigInteger.valueOf(503), BigInteger.valueOf(321), BigInteger.valueOf(231)));

        msgS1 = Encryptor.encrypt(key1, msg1);
        msgS2 = Encryptor.encrypt(key2, msg2);
        msgS3 = Encryptor.encrypt(key3, msg3);
    }

    @Test
    public void basicTest() {
        Assert.assertTrue( Encryptor.verify(msg1, msgS1, key1.getPublicKey()));
    }
    @Test
    public void verificationKeyTest1() {
        Assert.assertFalse(Encryptor.verify(msg1, msgS1, key2.getPublicKey()));
        Assert.assertFalse(Encryptor.verify(msg1, msgS1, key3.getPublicKey()));
    }
    @Test
    public void verificationKeyTest2() {
        Assert.assertFalse(Encryptor.verify(msg2, msgS2, key1.getPublicKey()));
        Assert.assertFalse(Encryptor.verify(msg2, msgS2, key3.getPublicKey()));
    }
    @Test
    public void verificationKeyTest3() {
        Assert.assertFalse(Encryptor.verify(msg3, msgS3, key1.getPublicKey()));
        Assert.assertFalse(Encryptor.verify(msg3, msgS3, key2.getPublicKey()));
    }
}
