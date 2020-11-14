//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 118 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);
        while (nodes.size() > 0) {
            List<Node> nextLevelNodes = new ArrayList<>();
            List<Integer> curValue = new ArrayList<>();
            for (int i = 0; i < nodes.size(); i++) {
                Node node = nodes.get(i);
                curValue.add(node.val);
                if (node.children != null && node.children.size() > 0) {
                    nextLevelNodes.addAll(node.children);
                }
            }
            res.add(curValue);
            nodes = nextLevelNodes;
        }
        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
