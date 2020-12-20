//编写一个程序，通过填充空格来解决数独问题。 
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// 提示： 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法 
// 👍 718 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void solveSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][] blocks = new int[9][9];
        List<int[]> spaces = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (Character.isDigit(board[i][j])) {
                    rows[i][board[i][j] - '1'] = 1;
                    columns[j][board[i][j] - '1'] = 1;
                    blocks[i / 3 * 3 + j / 3][board[i][j] - '1'] = 1;
                } else {
                    spaces.add(new int[]{i, j});
                }
            }
        }
        solve(rows, columns, blocks, board, spaces, 0);
    }

    private boolean solve(int[][] rows,
                          int[][] columns,
                          int[][] blocks,
                          char[][] board,
                          List<int[]> spaces,
                          int index) {
        if (index == spaces.size()) {
            return true;
        }
        int i = spaces.get(index)[0];
        int j = spaces.get(index)[1];
        for (char character = '1'; character <= '9'; character++) {
            if (rows[i][character - '1'] == 0
                    && columns[j][character - '1'] == 0
                    && blocks[i / 3 * 3 + j / 3][character - '1'] == 0) {
                board[i][j] = character;
                rows[i][character - '1'] = 1;
                columns[j][character - '1'] = 1;
                blocks[i / 3 * 3 + j / 3][character - '1'] = 1;
                if (solve(rows, columns, blocks, board, spaces, index + 1)) {
                    return true;
                }
                rows[i][character - '1'] = 0;
                columns[j][character - '1'] = 0;
                blocks[i / 3 * 3 + j / 3][character - '1'] = 0;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
