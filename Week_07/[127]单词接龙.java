//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
// 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索 
// 👍 666 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) return 0;
            Set<String> beginWords = new HashSet<>();
            Set<String> endWords = new HashSet<>();
            beginWords.add(beginWord);
            endWords.add(endWord);
            return bfs(beginWords, endWords, 0, wordSet);
        }

        private int bfs(Set<String> beginWords, Set<String> endWords, int level, Set<String> wordSet) {
            while (!beginWords.isEmpty() && !endWords.isEmpty()) {
                Set<String> set = beginWords.size() <= endWords.size() ? beginWords : endWords;
                Set<String> targetSet = beginWords.size() <= endWords.size() ? endWords : beginWords;
                int size = set.size();
                wordSet.removeAll(set);
                level++;
                Set<String> nextSet = new HashSet<>();
                for (String word : set) {
                    char[] chars = word.toCharArray();
                    for (int j = 0; j < chars.length; j++) {
                        char tmp = chars[j];
                        for (char c = 'a'; c <= 'z'; c++) {
                            chars[j] = c;
                            String sN = new String(chars);
                            if (wordSet.contains(sN)) {
                                if (targetSet.contains(sN)) {
                                    return level + 1;
                                }
                                nextSet.add(sN);
                            }
                        }
                        chars[j] = tmp;
                    }
                }
                beginWords = nextSet;
                endWords = targetSet;
            }
            return 0;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
