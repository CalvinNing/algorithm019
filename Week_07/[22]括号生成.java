//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1481 👎 0


import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(0, 0, n, "", ans);
        return ans;
    }

    private void dfs(int leftCount, int rightCount, int n, String curStr, List<String> ans) {
        if (leftCount == n && rightCount == n) {
            ans.add(curStr);
            return;
        }
        if (leftCount < n) {
            dfs(leftCount + 1, rightCount, n, curStr + "(", ans);
        }
        if (rightCount < leftCount) {
            dfs(leftCount, rightCount + 1, n, curStr + ")", ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
