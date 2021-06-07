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

        key1 = Key.generateKey(128);
        key2 = Key.generateKey(128);
        key3 = Key.generateKey(128);

        msgS1 = Encryptor.encrypt(key1, msg1);
        msgS2 = Encryptor.encrypt(key2, msg2);
        msgS3 = Encryptor.encrypt(key3, msg3);
    }

    @Test
    public void basicValidationTest() {
        Assert.assertTrue( Encryptor.verify(msg1, msgS1, key1.getPublicKey()));
        Assert.assertTrue( Encryptor.verify(msg2, msgS2, key2.getPublicKey()));
        Assert.assertTrue( Encryptor.verify(msg3, msgS3, key3.getPublicKey()));
    }
    @Test
    public void basicDecryptionTest() {
        Assert.assertEquals(msg1, Encryptor.decrypt(msgS1, key1.getPublicKey()));
        Assert.assertEquals(msg2, Encryptor.decrypt(msgS2, key2.getPublicKey()));
        Assert.assertEquals(msg3, Encryptor.decrypt(msgS3, key3.getPublicKey()));
    }
    @Test
    public void validationKeyTest1() {
        Assert.assertFalse(Encryptor.verify(msg1, msgS1, key2.getPublicKey()));
        Assert.assertFalse(Encryptor.verify(msg1, msgS1, key3.getPublicKey()));
    }
    @Test
    public void validationKeyTest2() {
        Assert.assertFalse(Encryptor.verify(msg2, msgS2, key1.getPublicKey()));
        Assert.assertFalse(Encryptor.verify(msg2, msgS2, key3.getPublicKey()));
    }
    @Test
    public void validationKeyTest3() {
        Assert.assertFalse(Encryptor.verify(msg3, msgS3, key1.getPublicKey()));
        Assert.assertFalse(Encryptor.verify(msg3, msgS3, key2.getPublicKey()));
    }
}
