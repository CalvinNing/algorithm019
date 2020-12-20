//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树 
// 👍 486 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Trie {
    TrieNode root;

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
        char[] chars = word.toCharArray();
        int length = chars.length;
        TrieNode node = root;
        for (int i = 0; i < length; i++) {
            node = node.put(chars[i]);
        }
        node.setEnd(true);
    }

    public TrieNode searchNode(String word) {
        char[] chars = word.toCharArray();
        int length = chars.length;
        TrieNode node = root;
        for (int i = 0; i < length; i++) {
            node = node.get(chars[i]);
            if (node == null) {
                return null;
            }
        }
        return node;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEnd();
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = searchNode(prefix);
        return node != null;
    }

    static class TrieNode {
        private TrieNode[] links = new TrieNode[26];
        private boolean isEnd;

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public TrieNode put(char ch) {
            if (links[ch - 'a'] == null) {
                links[ch - 'a'] = new TrieNode();
            }
            return links[ch - 'a'];
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public boolean containKey(char ch) {
            return links[ch - 'a'] != null;
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)
