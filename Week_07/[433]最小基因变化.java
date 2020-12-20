//一条基因序列由一个带有8个字符的字符串表示，其中每个字符都属于 "A", "C", "G", "T"中的任意一个。 
//
// 假设我们要调查一个基因序列的变化。一次基因变化意味着这个基因序列中的一个字符发生了变化。 
//
// 例如，基因序列由"AACCGGTT" 变化至 "AACCGGTA" 即发生了一次基因变化。 
//
// 与此同时，每一次基因变化的结果，都需要是一个合法的基因串，即该结果属于一个基因库。 
//
// 现在给定3个参数 — start, end, bank，分别代表起始基因序列，目标基因序列及基因库，请找出能够使起始基因序列变化为目标基因序列所需的最少变
//化次数。如果无法实现目标变化，请返回 -1。 
//
// 注意: 
//
// 
// 起始基因序列默认是合法的，但是它并不一定会出现在基因库中。 
// 所有的目标基因序列必须是合法的。 
// 假定起始基因序列与目标基因序列是不一样的。 
// 
//
// 示例 1: 
//
// 
//start: "AACCGGTT"
//end:   "AACCGGTA"
//bank: ["AACCGGTA"]
//
//返回值: 1
// 
//
// 示例 2: 
//
// 
//start: "AACCGGTT"
//end:   "AAACGGTA"
//bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//返回值: 2
// 
//
// 示例 3: 
//
// 
//start: "AAAAACCC"
//end:   "AACCCCCC"
//bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//返回值: 3
// 
// 👍 63 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minMutation(String start, String end, String[] bank) {
        char[] elements = new char[]{'A', 'C', 'G', 'T'};
        Set<String> bankSet = new HashSet<>();
        for (String s : bank) {
            bankSet.add(s);
        }
        if (!bankSet.contains(end)) return -1;
        Set<String> startSet = new HashSet<>();
        startSet.add(start);
        bankSet.remove(start);
        Set<String> endSet = new HashSet<>();
        endSet.add(end);
        int times = 0;
        while (!startSet.isEmpty() && !endSet.isEmpty()) {
            times++;
            Set<String> nextSet = new HashSet<>();
            for (String word : startSet) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char cc = chars[i];
                    for (int j = 0; j < elements.length; j++) {
                        chars[i] = elements[j];
                        String sN = String.valueOf(chars);
                        if (endSet.contains(sN)) {
                            return times;
                        }
                        if (bankSet.contains(sN)) {
                            nextSet.add(sN);
                            bankSet.remove(sN);
                        }
                    }
                    chars[i] = cc;
                }
            }
            startSet = nextSet.size() <= endSet.size() ? nextSet : endSet;
            endSet = nextSet.size() <= endSet.size() ? endSet : nextSet;
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
