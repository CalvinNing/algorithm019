### 学习笔记
- 在本周的学习中，很明显感觉到了困难，前两周基本都是自己先做出来，再去看别人更优的解法，但是本周却突然就变成了看到题毫无头绪的状态。
- 但这也从另一个方面纠正了我之前学习的一个错误方式。之前虽然知道应该按照“五毒神掌”的方式去刷题，但是遇到题还是喜欢死磕到底，因为感觉这样更有成就感。但是简单题带来的成就感无疑使自己待在了舒适区中。
- 这周一下子感觉到了难度，在学习、理解别人题解的思路的时候，会感觉到很痛苦，但是当萦绕在脑海中的困惑逐渐清晰时，又会有一种学会的喜悦和成就。相比前两周，明显感觉到真正的学到了“不会的东西”。
- 本周刷题量有所减少，但是思考的更多了。

> 当你感觉痛苦时,其实成长已经悄然开始

### [47]全排列 Java 双端队列解法
本解法思路来源是`[46]全排列`，
- 当我采用相同的解法，数组内交换时，尽管我对连续的元素判断了重复，然后跳过，但是结果却是错误的。原因是中间的交换会导致原本排序后挨在一起的相同元素又分开了
- 46题交换的目的是节省空间，数组交换相对元素的后移操作，复杂度要低很多，所以46题选择的是交换元素
- 那能不能既达到46题的目的，又防止交换导致的原本挨在一起的相同元素分开呢？我想到了双端队列，当前层未选择的元素放到队列尾部，保证相同元素的连续性
```java
class Solution {
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        //排序
        Arrays.sort(nums);
        //放入双端队列中
        Deque<Integer> deque = new LinkedList<>();
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
```

