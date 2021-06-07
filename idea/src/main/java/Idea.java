import misc.Block16Bit;
import misc.IdeaKey;

import java.nio.BufferUnderflowException;
import java.util.*;

public class Idea {
    protected List<Block16Bit> encryptionKeys = new ArrayList<>(52);
    protected List<Block16Bit> decryptionKeys = new ArrayList<>(52);

    protected Queue<Byte> inputBuffer = new LinkedList<>();
    protected Queue<Byte> outputBuffer = new LinkedList<>();

    protected Idea(IdeaKey key) {
        for (int i = 0; i < 6; ++i) {
            encryptionKeys.addAll(key.getSubKeys(8));
            key = key.getNextKey();
        }
        encryptionKeys.addAll(key.getSubKeys(4));

        decryptionKeys.add(encryptionKeys.get(48).inverseMultiplicative());
        decryptionKeys.add(encryptionKeys.get(49).inverseAdditive());
        decryptionKeys.add(encryptionKeys.get(50).inverseAdditive());
        decryptionKeys.add(encryptionKeys.get(51).inverseMultiplicative());

        for(int i = 7;i >= 0; i--) {
            decryptionKeys.add(encryptionKeys.get(i*6 + 4));
            decryptionKeys.add(encryptionKeys.get(i*6 + 5));
            decryptionKeys.add(encryptionKeys.get(i*6).inverseMultiplicative());
            if (i == 0) {
                decryptionKeys.add(encryptionKeys.get(1).inverseAdditive());
                decryptionKeys.add(encryptionKeys.get(2).inverseAdditive());
            } else {
                decryptionKeys.add(encryptionKeys.get(i*6 + 2).inverseAdditive());
                decryptionKeys.add(encryptionKeys.get(i*6 + 1).inverseAdditive());
            }
            decryptionKeys.add(encryptionKeys.get(i*6 + 3).inverseMultiplicative());
        }
    }

    protected void compute(List<Block16Bit> keys) {
        if(inputBuffer.size() < 8) {
            return;
        }
        //Initial values
        Block16Bit[] D = new Block16Bit[4];
        for (int i = 0; i < 4; ++i) {
            byte[] temp = new byte[2];
            temp[0] = inputBuffer.poll();
            temp[1] = inputBuffer.poll();
            D[i] = new Block16Bit(temp);
        }
        //main loop
        for (int i = 0; i < 8; ++i) {
            Block16Bit a = D[0].multiply(keys.get(i*6));
            Block16Bit b = D[1].add(keys.get(i*6 + 1));
            Block16Bit c = D[2].add(keys.get(i*6 + 2));
            Block16Bit d = D[3].multiply(keys.get(i*6 + 3));
            Block16Bit e = a.xor(c);
            Block16Bit f = b.xor(d);

            D[0] = a.xor(f.add(e.multiply(keys.get(i*6 + 4))).multiply(keys.get(i*6 + 5)));
            D[1] = c.xor(f.add(e.multiply(keys.get(i*6 + 4))).multiply(keys.get(i*6 + 5)));
            D[2] = b.xor(e.multiply(keys.get(i*6 + 4)).add(f.add(e.multiply(keys.get(i*6 + 4))).multiply(keys.get(i*6 + 5))));
            D[3] = d.xor(e.multiply(keys.get(i*6 + 4)).add(f.add(e.multiply(keys.get(i*6 + 4))).multiply(keys.get(i*6 + 5))));
        }
        //final transformation
        Block16Bit temp = D[1];
        D[1] = D[2];
        D[2] = temp;
        D[0] = D[0].multiply(keys.get(48));
        D[1] = D[1].add(keys.get(49));
        D[2] = D[2].add(keys.get(50));
        D[3] = D[3].multiply(keys.get(51));

        outputBuffer.addAll(D[0].getBytes());
        outputBuffer.addAll(D[1].getBytes());
        outputBuffer.addAll(D[2].getBytes());
        outputBuffer.addAll(D[3].getBytes());
    }

    public byte[] getBlock() throws BufferUnderflowException {
        if (outputBuffer.isEmpty()) {
            throw new BufferUnderflowException();
        }
        byte[] res = new byte[Integer.min(8, outputBuffer.size())];
        for (int i = 0; i < res.length; ++i) {
            res[i] = outputBuffer.poll();
        }
        return res;
    }
}
