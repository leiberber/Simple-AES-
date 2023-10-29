public class ASCII {
    public static String asciiEncipher(String Begin,String Key){  //输入字符串以及16位密钥;
        int length = Begin.length();
        StringBuffer out = new StringBuffer();
        for(int i=0;i<length;i++){
            char change = Begin.charAt(i);
            int asciiValue = (int)change;
            String Binary = decimalToBinary(asciiValue);
            String Binary_en = Cipher.cipher(Binary,Key);
            int DecimalNumber = binaryToDecimal(Binary_en);
            char decimal = (char)(DecimalNumber);
            out.append(decimal);
        }
        return out.toString();
    }

    public static String asciiEncipher2(String Begin, String key){
        String key1 = key.substring(0,16);
        String key2 = key.substring(16,32);
        String middle = asciiEncipher(Begin,key1);
        String out = asciiEncipher(middle,key2);
        return out;
    }

    public static String asciiEncipher3(String input, String key){
        String key1 = key.substring(0,16);
        String key2 = key.substring(16,32);
        String key3 = key.substring(32,48);
        String middle1 = asciiEncipher(input,key1);
        String middle2 = asciiEncipher(middle1,key2);
        String out = asciiEncipher(middle2,key3);
        return out;
    }

    public static String asciiDecipher(String Begin,String Key){
        int length = Begin.length();
        StringBuffer out = new StringBuffer();
        for(int i=0;i<length;i++){
            char change = Begin.charAt(i);
            int asciiValue = (int)change;
            String Binary = decimalToBinary(asciiValue);
            String Binary_en = Decipher.decipher(Binary,Key);
            int DecimalNumber = binaryToDecimal(Binary_en);
            char decimal = (char)(DecimalNumber);
            out.append(decimal);
        }
        return out.toString();
    }

    public static String asciiDecipher2(String input, String key){
        String key1 = key.substring(0,16);
        String key2 = key.substring(16,32);
        String middle = asciiDecipher(input,key2);
        String out = asciiDecipher(middle,key1);
        return out;
    }

    public static String asciiDecipher3(String input, String key){
        String key1 = key.substring(0,16);
        String key2 = key.substring(16,32);
        String key3 = key.substring(32,48);
        String middle1 = asciiDecipher(input,key3);
        String middle2 = asciiDecipher(middle1,key2);
        String out = asciiDecipher(middle2,key1);
        return out;
    }

    public static String decimalToBinary(int decimalNumber) {
        int digits = 16; // 指定位数
        String binary = String.format("%" + digits + "s", Integer.toBinaryString(decimalNumber)).replace(' ', '0');
        return binary;
    }

    public static int binaryToDecimal(String binaryString) {
        int decimalNumber = Integer.parseInt(binaryString, 2);
        return decimalNumber;
    }
}
