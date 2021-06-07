package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IdeaKey {
    private final byte[] data;

    public IdeaKey(byte[] data) {
        assert data.length == 16;

        this.data = data.clone();
    }

    public IdeaKey getNextKey() {
        IdeaKey res = new IdeaKey(this.data);
        for (int i = 0; i < 16; ++i) {
            res.data[i] = (byte) (Byte.toUnsignedInt(data[(i+3) % 16]) << 1 | Byte.toUnsignedInt(data[(i+4) % 16]) >>> 7);
        }
        /*for(byte i : res.data) {
            System.out.print(String.format("%8s",Integer.toBinaryString(0xFF & i)).replaceAll(" ", "0"));
        }
        System.out.println("\n");*/
        return res;
    }

    public List<Block16Bit> getSubKeys(int n) {
        List<Block16Bit> keys = new ArrayList<>(8);
        for (int i = 0; i < n; ++i) {
            keys.add(new Block16Bit(Arrays.copyOfRange(data, i*2, (i+1)*2)));
        }
        return keys;
    }
}
