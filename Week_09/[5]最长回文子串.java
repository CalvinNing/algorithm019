//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 3052 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] chars = s.toCharArray();
        int centerIndex = 0, maxLen = 1;
        for (int i = 0; i < chars.length; i++) {
            int len1 = expandAroundCenter(chars, i, i);
            int len2 = expandAroundCenter(chars, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > maxLen) {
                centerIndex = i;
                maxLen = len;
            }
        }
        if ((maxLen & 1) == 1) {
            return s.substring(centerIndex - maxLen / 2, centerIndex + maxLen / 2 + 1);
        } else {
            return s.substring(centerIndex - maxLen / 2 + 1, centerIndex + maxLen / 2 + 1);
        }
    }


    private int expandAroundCenter(char[] chars, int left, int right) {
        while (left >= 0 && right < chars.length && chars[left] == chars[right]) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}
//runtime:9 ms
//memory:38.2 MB

//leetcode submit region end(Prohibit modification and deletion)
