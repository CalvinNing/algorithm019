//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 哈希表 字符串 
// 👍 341 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        Queue<Pair> queue = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            if (!map.containsKey(chars[i])) {
                map.put(chars[i], i);
                queue.offer(new Pair(chars[i], i));
            } else {
                map.put(chars[i], -1);
                while (!queue.isEmpty() && map.get(queue.peek().key) == -1) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 : queue.peek().index;
    }

    class Pair {
        char key;
        int index;

        public Pair(char key, int index) {
            this.key = key;
            this.index = index;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
