import misc.IdeaKey;

public class IdeaDecryptor extends Idea {
    public IdeaDecryptor(IdeaKey key) {
        super(key);
    }

    public void decrypt(byte[] data) {
        for (byte val : data) {
            inputBuffer.add(val);
        }
        while (inputBuffer.size() >= 8) {
            compute(decryptionKeys);
        }
    }
}
