//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串 
// 👍 1398 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Trie trie = new Trie();
        for (String str : strs) {
            if (str.length() == 0) {
                return "";
            }
            trie.insert(str);
        }
        return trie.getCommonPrefix();
    }

    class Trie {
        private TrieNode root;

        public TrieNode getRoot() {
            return root;
        }

        /**
         * Initialize your data structure here.
         */
        public Trie() {
            root = new TrieNode();
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                node = node.insert(word.charAt(i));
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            TrieNode node = searchByPrefix(word);
            return node != null && node.isEnd;

        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            TrieNode node = searchByPrefix(prefix);
            return node != null;
        }

        public String getCommonPrefix() {
            StringBuilder res = new StringBuilder();
            TrieNode node = root;
            while (true) {
                if (node.size != 1) {
                    break;
                }
                if (node.isEnd) {
                    break;
                }
                for (int i = 0; i < 26; i++) {
                    if (node.linkNode[i] != null) {
                        res.append((char) (i + 'a'));
                        node = node.linkNode[i];
                        break;
                    }
                }
            }
            return res.toString();
        }

        private TrieNode searchByPrefix(String word) {
            if (word == null || word.length() == 0) return null;
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                node = node.get(word.charAt(i));
                if (node == null) {
                    break;
                }
            }
            return node;
        }
    }

    class TrieNode {
        TrieNode[] linkNode = null;
        int size;
        boolean isEnd;

        public TrieNode() {
            linkNode = new TrieNode[26];
            size = 0;
            isEnd = false;
        }

        public TrieNode insert(char c) {
            if (linkNode[c - 'a'] == null) {
                linkNode[c - 'a'] = new TrieNode();
                size++;
            }
            return linkNode[c - 'a'];
        }

        public TrieNode get(char c) {
            return linkNode[c - 'a'];
        }

        @Override
        public String toString() {
            return "size=" + size +
                    ", isEnd=" + isEnd;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
