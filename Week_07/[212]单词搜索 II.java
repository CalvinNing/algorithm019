//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法 
// 👍 298 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private final int[][] direct = new int[][]{{-1, 0}, {0, 1}, {0, -1}, {1, 0}};

    public List<String> findWords(char[][] board, String[] words) {
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return new ArrayList<>();
        }
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }
        //获取root
        TrieNode root = trie.getRoot();
        int rows = board.length;
        int columns = board[0].length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                dfs(ans, i, j, rows, columns, board, root);
                if (ans.size() == words.length) {
                    return ans;
                }
            }
        }
        return ans;
    }

    private void dfs(List<String> ans, int i, int j, int rows, int columns, char[][] board, TrieNode node) {
        //System.out.println("i =" + i);
        //System.out.println("j =" + j);
        //System.out.println("lastWord = " + curWord);
        if (i < 0 || j < 0 || i >= rows || j >= columns || board[i][j] == '#') {
            return;
        }
        if (!node.containKey(board[i][j])) {
            //System.out.println("不存在前缀");
            return;
        }
        //System.out.println("存在前缀");
        TrieNode nextNode = node.get(board[i][j]);
        if (nextNode.isEnd) {
            ans.add(nextNode.getWord());
            nextNode.setEnd(false);
            nextNode.setWord(null);
        }
        char temp = board[i][j];
        board[i][j] = '#';
        for (int a = 0; a < direct.length; a++) {
            dfs(ans, i + direct[a][0], j + direct[a][1], rows, columns, board, nextNode);
        }
        board[i][j] = temp;
        if (nextNode.isEmpty()) {
            node.romove(board[i][j]);
        }
    }

    static class Trie {
        private TrieNode root;

        public TrieNode getRoot() {
            return root;
        }

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            if (word == null || word.length() == 0) return;
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                node = node.insert(word.charAt(i));
            }
            node.setEnd(true);
            node.setWord(word);
        }
    }

    static class TrieNode {
        private TrieNode[] links = new TrieNode[26];
        private boolean isEnd;
        private String word;
        public boolean isEmpty(){
            boolean flag = true;
            for (int i = 0; i < 26; i++) {
                if (links[i]!=null) {
                    flag = false;
                }
            }
            return flag;
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd(boolean end) {
            isEnd = end;
        }

        public TrieNode insert(char ch) {
            if (links[ch - 'a'] == null) links[ch - 'a'] = new TrieNode();
            return links[ch - 'a'];

        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void romove(char ch) {
            links[ch - 'a'] = null;
        }

        public boolean containKey(char ch) {
            return links[ch - 'a'] != null;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
