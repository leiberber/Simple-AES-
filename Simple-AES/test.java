public class test {
    public static void main(String[] args) {
        // //测试S-BOX和逆S-BOX  没问题
        // int a = 0x8A;
        // int b;
        // b = S_Box.s_box(a);
        // System.out.println(Integer.toHexString(a));
        // System.out.println(Integer.toHexString(b));
        // System.out.println(Integer.toHexString(S_Box.s_box_1(b)));

        // //测试半字节替代     没问题
        // String text = "1000101000011100";
        // String h = Round_Function.Half_Byte_Replace(text);
        // String h1 = Round_Function.Half_Byte_Replace_1(h);
        // //将h,h1输出为十六进制数
        // System.out.println(text);
        // System.out.println(h);
        // System.out.println(h1);

        // //测试密钥扩展   没问题
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

        // //测试列混淆和逆列混淆   没问题
        // String a = "0110110001000000";
        // String b = Round_Function.Column_Confusion(a);
        // System.out.println(a);
        // System.out.println(b);
        // System.out.println(Round_Function.Column_Confusion_1(b));

        // //行位移 没问题
        // String text = "0110111101101011";
        // String l = Round_Function.Line_Shift(text);
        // String l1 = Round_Function.Line_Shift(l);
        // System.out.println(l);
        // System.out.println(l1);

        // //测试加密解密  没问题
        // String PlainText = "0110111101101011";
        // String Key = "1010011100111011"; 
        // System.out.println("明文："+PlainText);
        // System.out.println("密钥：" + Key);
        // String result = Cipher.cipher(PlainText, Key);
        // System.out.println("加密结果：" + result);
        // String decipher = Decipher.decipher(result, Key);
        // System.out.println("解密结果：" + decipher);

        //测试CBC加密解密  
        String PlainText = "abcdefgh";
        String Key = "1010011100111011"; 
        System.out.println("明文："+PlainText);
        System.out.println("密钥：" + Key);
        String result = ASCII.CBCcipher(PlainText, Key);
        System.out.println("CBC加密结果：" + result);
        String decipher = ASCII.CBCdecipher(result, Key);
        System.out.println("CBC解密结果：" + decipher);

        // //测试中间相遇攻击 //没问题
        // String Key = "10101010101010101111111100000000";
        // String Keylist = new String();
        // // String PlainText = "1010101010101010";
        // // String CipherText = Cipher.cipher2(PlainText, Key);
        // // String[] MK = Middle_Attack.attack(PlainText, CipherText);
        // String PlainText = "abccbcaa";
        // String CipherText = ASCII.asciiEncipher2(PlainText, Key);
        // String[] Mk = Middle_Attack.attackASCII(PlainText, CipherText);
        
        // for (int i = 0; i < Mk.length; i++) {
        //     if (Mk[i] != null) {
        //         Keylist += Mk[i] + "\n";
        //     }
        // }
        // System.out.println("明文：" + PlainText);
        // System.out.println("加密结果：" + CipherText);
        // System.out.println("密钥： "+ Key);
        // System.out.println("中间相遇攻击： "+Keylist);
    }
}
