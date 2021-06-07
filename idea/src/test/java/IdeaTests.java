import misc.IdeaKey;
import org.junit.Assert;
import org.junit.Test;

public class IdeaTests {
    @Test
    public void consistencyTest() {
        byte[] key = new byte[] {0x00, 0x01, 0x00, 0x02, 0x00, 0x03, 0x00, 0x04, 0x00, 0x05, 0x00, 0x06, 0x00, 0x07, 0x00, 0x08};
        byte[] data = new byte[] {0x00, 0x00, 0x00, 0x01, 0x00, 0x02, 0x00, 0x03};

        IdeaDecryptor decryptor = new IdeaDecryptor(new IdeaKey(key));
        IdeaEncryptor encryptor = new IdeaEncryptor(new IdeaKey(key));

        encryptor.encrypt(data);

        decryptor.decrypt(encryptor.getBlock());

        Assert.assertArrayEquals(data, decryptor.getBlock());
    }
}
