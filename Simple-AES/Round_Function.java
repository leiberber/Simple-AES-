public class Round_Function {   //第一轮轮密钥
    public static String Half_Byte_Replace(String a) { //a是16位二进制数  
        //提取前后八位转化为字符串
        String a1 = a.substring(0, 8);
        String a2 = a.substring(8, 16);
        //转化为数字
        int a11 = Integer.parseInt(a1, 2);
        int a22 = Integer.parseInt(a2, 2);
        //半字节替代
        int s1 = S_Box.s_box(a11);
        int s2 = S_Box.s_box(a22);
        //转化为字符串
        String s11 = Integer.toBinaryString(s1);
        String s22 = Integer.toBinaryString(s2);
        //补0
        while (s11.length() < 8) {
            s11 = "0" + s11;
        }
        while (s22.length() < 8) {
            s22 = "0" + s22;
        }
        //拼接
        String out = s11 + s22;
        return out;
    }
    
    public static String Half_Byte_Replace_1(String a){    //a是16位二进制数  
        //提取前后八位转化为字符串
        String a1 = a.substring(0, 8);
        String a2 = a.substring(8, 16);
        //转化为数字
        int a11 = Integer.parseInt(a1, 2);
        int a22 = Integer.parseInt(a2, 2);
        //半字节替代
        int s1= S_Box.s_box_1(a11);
        int s2 = S_Box.s_box_1(a22);
        //转化为字符串
        String s11 = Integer.toBinaryString(s1);
        String s22 = Integer.toBinaryString(s2);
        //补0
        while (s11.length() < 8) {
            s11 = "0" + s11;
        }
        while (s22.length() < 8) {
            s22 = "0" + s22;
        }
        //拼接
        String out = s11 + s22;
        return out;
    }
    
    //行移位，也是逆行位移
    public static String Line_Shift(String a) {
        String []nibble = new String[4];
        //提取a的每四位
        for (int i = 0; i < 4; i++) {
            nibble[i] = a.substring(i * 4, (i + 1) * 4);
        }
        String out = nibble[0] + nibble[2] + nibble[3] + nibble[1];
        return out;
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
}
