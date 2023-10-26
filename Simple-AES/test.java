public class test {
    public static void main(String[] args) {
        // //测试S-BOX
        // int a = 0x8A;
        // int b;
        // b = S_Box.s_box(a);
        // System.out.println(Integer.toHexString(b));

        // //测试密钥扩展
        // int Key = 0b0010110101010101; // 16位原始密钥2D55,修好了，没问题
        // System.out.println("原始密钥: " + Integer.toHexString(Key));
        // int[] w = Key_Expansion.key_Expansion(Key);

        // for (int i = 0; i < w.length; i++) {
        //     //roundKey不够8位补0
        //     String wk = Integer.toBinaryString(w[i]);
        //     while (wk.length() < 8) {
        //         wk = "0" + wk;
        //     }
        //     System.out.println("w" + i + ": " + wk);
        // }
        String a = "0110110001000000";
        String b = Round_Function.Column_Confusion(a);
        System.out.println(b);
    }
}
