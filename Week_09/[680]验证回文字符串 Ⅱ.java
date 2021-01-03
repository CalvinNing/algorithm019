//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串 
// 👍 304 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean validPalindrome(String s) {
        if (s.length() <= 2) return true;
        char[] chars = s.toCharArray();
        int start = 0, end = chars.length - 1;
        for (; start < end && chars[start] == chars[end]; start++, end--) ;
        if (start >= end) {
            return true;
        } else {
            return validPalindrome(chars, start + 1, end) || validPalindrome(chars, start, end-1);
        }
    }

    private boolean validPalindrome(char[] chars, int start, int end) {
        for (; start < end && chars[start] == chars[end]; start++, end--) ;
        return start >= end;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
