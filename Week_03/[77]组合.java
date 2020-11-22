//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 442 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(0, n, k, new ArrayList<>());
        return res;

    }

    public void dfs(int curIndex, int n, int k, List<Integer> list) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        if (curIndex == n) {
            return;
        }
        if (n - curIndex < k - list.size()) {
            return;
        }
        dfs(curIndex + 1, n, k, list);
        list.add(curIndex + 1);
        dfs(curIndex + 1, n, k, list);
        list.remove(list.size() - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
