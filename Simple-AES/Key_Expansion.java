public class Key_Expansion {
    static int rcon1 = 0b10000000;
    static int rcon2 = 0b00110000;

    public static int shift(int w) { //八位w
        int w_next;
        //交换w左右格位置
        w_next = (w & 0x0F) << 4 | (w & 0xF0) >> 4;
        return w_next;
    }

    public static int sub_byte(int w) {  //S-box
        int w_next;
        w_next = S_Box.s_box(w);
        return w_next;
    }

    public static int g1(int w) { //g()
        int w_next;
        w_next = shift(w);
        w_next = sub_byte(w_next);
        w_next = w_next ^ rcon1;
        return w_next;
    }

    public static int g2(int w) {
        int w_next;
        w_next = shift(w);
        w_next = sub_byte(w_next);
        w_next = w_next ^ rcon2;
        return w_next;
    }

    public static int[] key_Expansion(int Key) {
        int[] roundKeys = new int[6]; // 轮密钥，每个轮密钥有2个半字节
        //获取Key前后八位
        int p1 = (Key & 0xFF00) >> 8;//左
        int p2 = Key & 0x00FF;//右
        roundKeys[0] = p1;
        roundKeys[1] = p2;
        roundKeys[2] = roundKeys[0] ^ g1(roundKeys[1]);
        roundKeys[3] = roundKeys[1] ^ roundKeys[2];
        roundKeys[4] = roundKeys[2] ^ g2(roundKeys[3]);
        roundKeys[5] = roundKeys[3] ^ roundKeys[4];
        return roundKeys;//每两个八位二进制数组成一轮密钥
    }
}
