//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 
//'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 题目数据保证答案肯定是一个 32 位的整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "12"
//输出：2
//解释：它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2： 
//
// 
//输入：s = "226"
//输出：3
//解释：它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
//
// 示例 3： 
//
// 
//输入：s = "0"
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：s = "1"
//输出：1
// 
//
// 示例 5： 
//
// 
//输入：s = "2"
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s 只包含数字，并且可能包含前导零。 
// 
// Related Topics 字符串 动态规划 
// 👍 576 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            if (s.charAt(0) == '0') {
                return 0;
            } else {
                return 1;
            }
        }
        char[] chars = s.toCharArray();
        int dp0 = 1;
        int dp1 = 1;
        if (chars[1] == '0') {
            if (Integer.parseInt(new String(chars, 0, 2)) <= 26) {
                dp1 = 1;
            }else {
                return 0;
            }
        }else {
            if (Integer.parseInt(new String(chars, 0, 2)) <= 26) {
                dp1 = 2;
            } else {
                dp1 = 1;
            }
        }
        for (int i = 2; i < chars.length; i++) {
            if (chars[i] == '0') {
                if (chars[i - 1] == '1' || chars[i - 1] == '2') {
                    int temp = dp0;
                    dp0 = dp1;
                    dp1 = temp;
                } else {
                    return 0;
                }
            } else {
                if (chars[i - 1] != '0' && Integer.parseInt(new String(chars, i - 1, 2)) <= 26) {
                    int temp = dp1;
                    dp1 = dp0 + dp1;
                    dp0 = temp;
                } else {
                    dp0 = dp1;
                }
            }
        }
        return dp1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
