public class S_Box { //输入一个半字节数如 8A 输出 60 (十六进制96 就是0110 0000)
    private static final int[][] Box = {
            { 9, 4, 10, 11 },
            { 13, 1, 8, 5 },
            { 6, 2, 0, 3 },
            { 12, 14, 15, 7 }
    };

    private static final int[][] Box_1 = {
            { 10, 5, 9, 11 },
            { 1, 7, 8, 15 },
            { 6, 0, 2, 3 },
            { 12, 4, 13, 14 }
    };

    public static int s_box(int input) {
        // 提取输入的高位和低位
        int highNibble = (input & 0xF0) >> 4;
        int lowNibble = input & 0x0F;

        // 使用查找表进行替代
        int substitutedHighNibble = Box[highNibble >> 2][highNibble & 0x03];
        int substitutedLowNibble = Box[lowNibble >> 2][lowNibble & 0x03];

        // 合并替代后的结果
        int output = (substitutedHighNibble << 4) | substitutedLowNibble;

        return output;
    }

    public static int s_box_1(int input) {
        // 提取输入的高位和低位
        int highNibble = (input & 0xF0) >> 4;
        int lowNibble = input & 0x0F;

        // 使用查找表进行替代
        int substitutedHighNibble = Box_1[highNibble >> 2][highNibble & 0x03];
        int substitutedLowNibble = Box_1[lowNibble >> 2][lowNibble & 0x03];

        // 合并替代后的结果
        int output = (substitutedHighNibble << 4) | substitutedLowNibble;
        return output;
    }
}
