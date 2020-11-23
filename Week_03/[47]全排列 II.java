//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法 
// 👍 532 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        //排序
        Arrays.sort(nums);
        //放入双端队列中
        Deque<Integer> deque = new ArrayDeque<>();
        for (int num : nums) {
            deque.addLast(num);
        }
        dfs(deque, new ArrayList<>());
        return res;

    }

    public void dfs(Deque<Integer> deque, List<Integer> list) {
        //如果队列为空，则完成一次排列
        if (deque.isEmpty()) {
            res.add(new ArrayList<>(list));
            return;
        }
        //记录队列的长度，队列进出一定要提前记录队列的长度，否则随着poll offer，队列长度会发生变化
        int size = deque.size();
        //记录上一次选择的数，防止重复
        Integer last = null;
        for (int i = 0; i < size; i++) {
            //取出队列第一个元素
            Integer top = deque.poll();
            //如果这个元素与上次选择的元素相同，则直接放入队尾，防止重复
            if (last != null && top.equals(last)) {
                //恢复队列，原因同下面的addLast
                deque.addLast(top);
                continue;
            }
            //记录选择的元素，供下次循环判断相同元素是否已经选择过
            last = top;
            //当前层的选择
            list.add(top);
            //下探
            dfs(deque, list);
            //恢复
            list.remove(list.size() - 1);
            //恢复队列，此处的恢复，是为了本层不选择的元素，下一层还能选择，如果不恢复的话
            //假如本层是[1,1,3,4,5],本层选择的是3，则下探后下一层只剩下[4,5]了，恢复则下一层就是[4,5,1,1]
            deque.addLast(top);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
