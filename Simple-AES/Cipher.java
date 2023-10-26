public class Cipher {
    public static String cipher(String input, String Key) {
        String S0 = Round_Key_Addition.key_addition(input, Key);
        String S1 = Round_1(S0, Key);
        String S2 = Round_2(S1, Key);
        return S2;
    }


    //第一轮加密
    public static String Round_1(String a,String Key) {
        String h = Round_Function.Half_Byte_Replace(a);//半字节代替
        String l = Round_Function.Line_Shift(h);//行位移
        String c = Round_Function.Column_Confusion(l);//列混淆

        //获取轮密钥1
        int K = Integer.parseInt(Key,2);
        int[] w = Key_Expansion.key_Expansion(K);
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
    public static String Round_2(String a,String Key) {
        String h = Round_Function.Half_Byte_Replace(a);//半字节代替
        String l = Round_Function.Line_Shift(h);//行位移

        //获取轮密钥2
        int K = Integer.parseInt(Key,2);
        int[] w = Key_Expansion.key_Expansion(K);
        String w4 = Integer.toBinaryString(w[4]);
        String w5 = Integer.toBinaryString(w[5]);
        while (w4.length() < 8) {//位数不够八位补0 
            w4 = "0" + w4;
        }
        while (w5.length() < 8) {
            w5 = "0" + w5;
        }
        String Key_2 = w4 + w5;

        String out = Round_Key_Addition.key_addition(l,Key_2);
        return out;
    }
}
