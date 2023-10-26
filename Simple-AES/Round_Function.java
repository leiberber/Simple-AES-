public class Round_Function {   //第一轮轮密钥
    public static String Half_Byte_Replace(String a){    //a是16位二进制数  D2.2半字节代替
        String k = bin2hex(a); //转换为4位16进制数
        String C = k.substring(0,2);
        String D = k.substring(2,4);
        Integer Ci = Integer.parseInt(C,16);
        Integer Di = Integer.parseInt(D,16);
        int c = Ci.intValue();
        int d = Di.intValue();
        int c2 = S_Box.s_box(c);
        int d2 = S_Box.s_box(d);
        StringBuffer out = new StringBuffer();
        out.append(c2);
        out.append(d2);
        return out.toString();  //跑得通，不知道对不对
    }

    public static String bin2hex(String input) {
        StringBuilder sb = new StringBuilder();
        int len = input.length();
        System.out.println("原数据长度：" + (len / 8) + "字节");

        for (int i = 0; i < len / 4; i++) {
            //每4个二进制位转换为1个十六进制位
            String temp = input.substring(i * 4, (i + 1) * 4);
            int tempInt = Integer.parseInt(temp, 2);
            String tempHex = Integer.toHexString(tempInt).toUpperCase();
            sb.append(tempHex);
        }

        return sb.toString();
    }

    //行移位，也是逆行位移
    public static String Line_Shift(String a) {
        String[] a1 = a.split("");
        String temp = a1[1];
        a1[1] = a1[3];
        a1[3] = temp;
        return a1.toString();
    }

    // 列混淆
    public static String Column_Confusion(String a) { 
        // 将十六位二进制字符串转化为四个整数
        int[] state = new int[4];
        for (int i = 0; i < 4; i++) {
            String nibble = a.substring(i * 4, (i + 1) * 4);
            state[i] = Integer.parseInt(nibble, 2);
        }

        // 创建一个新的状态数组，用于存储列混淆后的结果
        int[] newState = new int[4];

        // 执行列混淆，使用GF(2^4)上的乘法
        newState[0] = state[0] ^ multiplyGF2_4(0x04, state[1]);
        newState[1] = state[1] ^ multiplyGF2_4(0x04, state[0]);
        newState[2] = state[2] ^ multiplyGF2_4(0x04, state[3]);
        newState[3] = state[3] ^ multiplyGF2_4(0x04, state[2]);

        // 将列混淆后的结果转化为16位二进制字符串
        String result = "";
        for (int i = 0; i < 4; i++) {
            //System.out.println(newState[i]);//测试
            result += String.format("%4s", Integer.toBinaryString(newState[i])).replace(' ', '0');
        }
        return result;
    }

    // 逆列混淆
    public static String Column_Confusion_1(String a) { 
        // 将十六位二进制字符串转化为四个整数
        int[] state = new int[4];
        for (int i = 0; i < 4; i++) {
            String nibble = a.substring(i * 4, (i + 1) * 4);
            state[i] = Integer.parseInt(nibble, 2);
        }

        // 创建一个新的状态数组，用于存储列混淆后的结果
        int[] newState = new int[4];

        // 执行逆列混淆，使用GF(2^4)上的乘法和逆列混淆矩阵
        newState[0] = multiplyGF2_4(0x09, state[0]) ^ multiplyGF2_4(0x02, state[1]);
        newState[1] = multiplyGF2_4(0x09, state[1]) ^ multiplyGF2_4(0x02, state[0]);
        newState[2] = multiplyGF2_4(0x09, state[2]) ^ multiplyGF2_4(0x02, state[3]);
        newState[3] = multiplyGF2_4(0x09, state[3]) ^ multiplyGF2_4(0x02, state[2]);

        // 将列混淆后的结果转化为16位二进制字符串
        String result = "";
        for (int i = 0; i < 4; i++) {
            //System.out.println(newState[i]);//测试
            result += String.format("%4s", Integer.toBinaryString(newState[i])).replace(' ', '0');
        }
        return result;
    }

    // GF(2^4)上的乘法
    public static int multiplyGF2_4(int a, int b) {
        int result = 0;
        int bitMask = 1;
        for (int i = 0; i < 4; i++) {
            if ((b & bitMask) != 0) {
                result ^= a;
            }
            boolean carry = (a & 0x08) != 0;
            a <<= 1;
            if (carry) {
                a ^= 0x03; // 异或 x^3 + x + 1 (0x03) 以处理溢出
            }
            bitMask <<= 1;
        }
        return result & 0x0F; // 确保结果是 4 位
    }

    //第一轮加密
    public static String Round_1(String a) {
        String h = Half_Byte_Replace(a);//半字节代替
        String l = Line_Shift(h);//行位移
        String c = Column_Confusion(l);//列混淆

        //获取轮密钥1
        int Key = 0b0010110101010101;
        int[] w = Key_Expansion.key_Expansion(Key);
        String w2 = Integer.toBinaryString(w[2]);
        String w3 = Integer.toBinaryString(w[3]);
        while (w2.length() < 8) {//位数不够八位补0 
            w2 = "0" + w2;
        }
        while (w3.length() < 8) {
            w3 = "0" + w3;
        }
        String Key_1 = w2 + w3;

        String out = Round_Key_Addition.key_addition(c, Key_1);
        return out;
    }
    
    //第二轮加密
    public static String Round_2(String a) {
        String h = Half_Byte_Replace(a);//半字节代替
        String l = Line_Shift(h);//行位移

        //获取轮密钥2
        int Key = 0b0010110101010101;
        int[] w = Key_Expansion.key_Expansion(Key);
        String w4 = Integer.toBinaryString(w[4]);
        String w5 = Integer.toBinaryString(w[5]);
        while (w4.length() < 8) {//位数不够八位补0 
            w4 = "0" + w4;
        }
        while (w5.length() < 8) {
            w5 = "0" + w5;
        }
        String Key_2 = w4+w5;

        String out = Round_Key_Addition.key_addition(l,Key_2);
        return out;
    }
}
