public class Middle_Attack {
    public static String attack(String PlainText, String CipherText) {
        //把Key1,Key2转化为二进制字符串，然后进行中间相遇攻击
        int Key1 = 0;
        int Key2 = 0;
        String K1 = new String();
        String K2 = new String();
        String[] Mid1 = new String[65536];
        String[] Mid2 = new String[65536];
        String out1 = new String();
        String out2 = new String();
        String out = new String();

        for (int i = 0; i < 65536; i++) {
            int digits = 16; // 指定位数
            K1 = String.format("%" + digits + "s", Integer.toBinaryString(Key1)).replace(' ', '0');
            Key1++;
            Mid1[i] = Cipher.cipher(PlainText, K1);
        }
        for (int i = 0; i < 65536; i++) {
            int digits = 16; // 指定位数
            K2 = String.format("%" + digits + "s", Integer.toBinaryString(Key2)).replace(' ', '0');
            Key2++;
            Mid2[i] = Decipher.decipher(CipherText, K2);
        }
        //当Mid1[i]=Mid2[j]时，找到密钥i,j(二进制)
        outer_loop: for (int i = 0; i < Mid1.length; i++) {
            for (int j = 0; j < Mid2.length; j++) {
                if (Mid1[i].equals(Mid2[j])) {
                    int digits = 16; // 指定位数
                    out1 = String.format("%" + digits + "s", Integer.toBinaryString(i)).replace(' ', '0');
                    out2 = String.format("%" + digits + "s", Integer.toBinaryString(j)).replace(' ', '0');
                    out = out1 + out2;
                    break outer_loop;
                }
            }
        }
        return out;
    }
    public static String attackASCII(String PlainText, String CipherText) {
        //把Key1,Key2转化为二进制字符串，然后进行中间相遇攻击
        int Key1 = 0;
        int Key2 = 0;
        String K1 = new String();
        String K2 = new String();
        String[] Mid1 = new String[65536];
        String[] Mid2 = new String[65536];
        String out1 = new String();
        String out2 = new String();
        String out = new String();

        for (int i = 0; i < 65536; i++) {
            int digits = 16; // 指定位数
            K1 = String.format("%" + digits + "s", Integer.toBinaryString(Key1)).replace(' ', '0');
            Key1++;
            Mid1[i] = ASCII.asciiEncipher(PlainText, K1);
        }
        for (int i = 0; i < 65536; i++) {
            int digits = 16; // 指定位数
            K2 = String.format("%" + digits + "s", Integer.toBinaryString(Key2)).replace(' ', '0');
            Key2++;
            Mid2[i] = ASCII.asciiDecipher(CipherText, K2);
        }
        //当Mid1[i]=Mid2[j]时，找到密钥i,j(二进制)
        outer_loop:for (int i = 0; i < Mid1.length; i++) {
            for (int j = 0; j < Mid2.length; j++) {
                if (Mid1[i].equals(Mid2[j])) {
                    int digits = 16; // 指定位数
                    out1 = String.format("%" + digits + "s", Integer.toBinaryString(i)).replace(' ', '0');
                    out2 = String.format("%" + digits + "s", Integer.toBinaryString(j)).replace(' ', '0');
                    out = out1 + out2;
                    break outer_loop;
                }
            }
        }
        return out;
    }
}