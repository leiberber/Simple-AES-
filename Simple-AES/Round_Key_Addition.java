public class Round_Key_Addition {
    public static String key_addition(String a, String key){   //D2.1密钥加,输入两个16位字符串逐位异或
        return OR(a,key);
    }

    public static String OR(String a,String b){   //异或函数,需要保证a,b长度相同
        int len = a.length();
        StringBuffer out = new StringBuffer();
        String []m = a.split("");
        String []n = a.split("");
        for(int i=0;i<len;i++){
            if(!(m[i].equals(n[i]))){
                out.append("1");
            }
            else {
                out.append("0");
            }
        }
        String Out = out.toString();
        return Out;
    }
}
