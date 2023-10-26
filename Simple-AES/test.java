public class test {
    public static void main(String[] args) {
        // //测试S-BOX和逆S-BOX
        // int a = 0x8A;
        // int b;
        // b = S_Box.s_box(a);
        // System.out.println(Integer.toHexString(a));
        // System.out.println(Integer.toHexString(b));
        // System.out.println(Integer.toHexString(S_Box.s_box_1(b)));

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

        // //测试列混淆和逆列混淆
        // String a = "0110110001000000";
        // String b = Round_Function.Column_Confusion(a);
        // System.out.println(a);
        // System.out.println(b);
        // System.out.println(Round_Function.Column_Confusion_1(b));

        //测试加密  结果好像不太对
        String PlainText = "0110111101101011";
        String Key = "1010011100111011"; 
        System.out.println("明文："+PlainText);
        System.out.println("密文：" + Key);
        String result = Cipher.cipher(PlainText, Key);
        System.out.println("加密结果：" + result);
        String add = Round_Key_Addition.key_addition(PlainText, Key);
        System.out.println("密钥加：" + add);
        String r1 = Cipher.Round_1(add, Key);
        System.out.println("第一轮加密结果：" + r1);
        String r2 = Cipher.Round_2(r1, Key);
        System.out.println("第二轮加密结果：" + r2);
    }
}
