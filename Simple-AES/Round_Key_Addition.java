public class Round_Key_Addition {
    public static String key_addition (String In, String Kn) {// 异或运算 
        StringBuffer Xor = new StringBuffer();
        int k = In.length();
        String[] In1 = In.split("");
        String[] Kn1 = Kn.split("");
        for (int i = 0; i < k; i++) {
            if (In1[i].equals(Kn1[i])) {
                Xor.append("0");
            } else {
                Xor.append("1");
            }
        }
        return Xor.toString();
    }
}
