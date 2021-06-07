package misc;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

public class Block16Bit {
    private short data;

    public Block16Bit(byte[] data) {
        assert data.length == 2;


        this.data = (short) Byte.toUnsignedInt(data[0]);
        this.data <<= 8;
        this.data |= (short) Byte.toUnsignedInt(data[1]);


    }

    public Block16Bit(short data) {
        this.data = data;
    }

    public List<Byte> getBytes() {
        List<Byte> res = new LinkedList<>();
        res.add((byte) (data >>> 8));
        res.add((byte) (data));
        return res;
    }

    public short getShort() {
        return data;
    }

    public Block16Bit xor(Block16Bit other) {
        return new Block16Bit((short) (Short.toUnsignedLong(this.data) ^ Short.toUnsignedLong(other.data)));
    }

    public Block16Bit add(Block16Bit other) {
        long operand1 = Short.toUnsignedLong(this.data);
        long operand2 = Short.toUnsignedLong(other.data);

        return new Block16Bit((short) (operand1 + operand2));
    }

    public Block16Bit multiply(Block16Bit other) {
        long operand1 = Short.toUnsignedLong(this.data);
        long operand2 = Short.toUnsignedLong(other.data);

        if (operand1 == 0) {
            operand1 = 1<<16;
        }
        if(operand2 == 0) {
            operand2 = 1<<16;
        }

        return new Block16Bit((short) ((operand1 * operand2) % 65537));
    }

    public Block16Bit inverseMultiplicative() {
        return new Block16Bit(BigInteger.valueOf(Short.toUnsignedLong(data)).modInverse(BigInteger.valueOf(65537)).shortValue());
    }

    public Block16Bit inverseAdditive() {
        return new Block16Bit((short) -this.data);
    }
}
