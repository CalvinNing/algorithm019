//给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。 
//
// 
//
// 示例： 
//
// 输入："Let's take LeetCode contest"
//输出："s'teL ekat edoCteeL tsetnoc"
// 
//
// 
//
// 提示： 
//
// 
// 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。 
// 
// Related Topics 字符串 
// 👍 262 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return s;
        char[] chars = s.toCharArray();
        int start = 0, left = start, right = left + 1;
        while (start < chars.length) {
            while (right < chars.length && chars[right] != ' ') right++;
            start = right + 1;
            right--;
            while (left <= right) {
                char tmp = chars[left];
                chars[left++] = chars[right];
                chars[right--] = tmp;
            }
            left = start;
            right = left + 1;
        }
        return new String(chars);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
