import misc.IdeaKey;

public class IdeaEncryptor extends Idea {
    public IdeaEncryptor(IdeaKey key) {
        super(key);
    }

    public void encrypt(byte[] data) {
        for (byte val : data) {
            inputBuffer.add(val);
        }
        while (inputBuffer.size() >= 8) {
            compute(encryptionKeys);
        }
    }
}
