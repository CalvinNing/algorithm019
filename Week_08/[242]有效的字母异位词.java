//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 321 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;
        int[] freq = new int[26];
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        int length = ss.length;
        for (int i = 0; i < length; i++) {
            freq[ss[i] - 'a']++;
            freq[tt[i] - 'a']--;
        }
        boolean flag = true;
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
