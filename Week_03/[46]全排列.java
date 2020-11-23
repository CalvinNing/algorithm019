//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 1008 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        if (nums == null) {
            return res;
        }
        dfs(0, nums, new ArrayList<>());
        return res;
    }

    public void dfs(int curIndex, int[] nums, List<Integer> list) {
        if (curIndex == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = curIndex; i < nums.length; i++) {
            //交换
            int temp = nums[i];
            nums[i] = nums[curIndex];
            nums[curIndex] = temp;
            list.add(nums[curIndex]);
            //下探
            dfs(curIndex + 1, nums, list);
            //换回来
            temp = nums[i];
            nums[i] = nums[curIndex];
            nums[curIndex] = temp;
            list.remove(list.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
