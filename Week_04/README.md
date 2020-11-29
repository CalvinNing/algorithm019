学习笔记
- 模拟行走机器人
```java
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        // 存储方向为北、东、南、西时，机器人走一步的坐标变化，按照顺时针存储
        // 如：向北走一步，则x + 0，y + 1；向东走一步，则x + 1，y + 0
        int[] dx = new int[]{0, 1, 0, -1};
        int[] dy = new int[]{1, 0, -1, 0};
        // 初始坐标：(0,0)，初始方向：正北
        int x = 0, y = 0, di = 0;

        // Encode obstacles (x, y) as (x+30000) * (2^16) + (y+30000)

        // 为什么long?
        // 因为-30000 <= obstacle[i][0] <= 30000 -30000 <= obstacle[i][1] <= 30000，
        // 而2^16=65536,所以obstacle[0] + 30000用16位存储足矣，
        // 高16位存储obstacle[0] + 30000，低16位存储obstacle[1] + 30000
        // Java int是32位，最高位是符号位，显然存不下两个16位，所以用long

        // 为什么用set呢？
        // 其实就是为了用HashSet，使用hash取值，时间复杂度是O(1),
        // 不然每次判断障碍物都需要遍历obstacles，复杂度是O(n)，
        // 而0 <= obstacles.length <= 10000，遍历的话，每一步就是10000次，所以官方题解说
        // "必须注意使用 集合 Set 作为对障碍物使用的数据结构，以便我们可以有效地检查下一步是否受阻。如果不这样做，我们检查 该点是障碍点吗 可能会慢大约 10000 倍。"
        Set<Long> obstacleSet = new HashSet();
        for (int[] obstacle : obstacles) {
            long ox = (long) obstacle[0] + 30000;
            long oy = (long) obstacle[1] + 30000;
            obstacleSet.add((ox << 16) + oy);
        }

        int ans = 0;
        for (int cmd : commands) {
            if (cmd == -2)  //left 左转，相当于右转三次
                di = (di + 3) % 4;
            else if (cmd == -1)  //right 右转
                di = (di + 1) % 4;
            else {
                for (int k = 0; k < cmd; ++k) {
                    // 计算下一步的坐标
                    int nx = x + dx[di];
                    int ny = y + dy[di];
                    // 此处下一步移动到的点的坐标用同样的方式计算code，判断是否是障碍物，不是则计算最大欧式距离的平方
                    long code = (((long) nx + 30000) << 16) + ((long) ny + 30000);
                    if (!obstacleSet.contains(code)) {
                        // 真正走到这一步
                        x = nx;
                        y = ny;
                        ans = Math.max(ans, x * x + y * y);
                    }
                }
            }
        }
        return ans;
    }
}
```

-  搜索旋转排序数组
```java
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        //如果target小于第一个但又大于最后一个，说明target一定不在nums数组中
        if (target < nums[0] && target > nums[nums.length - 1]) {
            return -1;
        }
        int start = 0;
        int end = nums.length - 1;
        int mid = 0;
        while (start <= end) {
            mid = start + (end - start) / 2;
            if (target >= nums[0]) {
                //target在前半段
                if (target == nums[mid]) {
                    return mid;
                } else if (target < nums[mid]) {
                    //此时mid一定在前半段范围内，因此end= mid - 1
                    end = mid - 1;
                } else {
                    //此时需要区分一下mid在前半段还是后半段
                    if (nums[mid] >= nums[0]) {
                        //前半段，则start = mid + 1
                        start = mid + 1;
                    } else {
                        //后半段，则前半段最长不会过了mid,因此end = mid-1
                        end = mid - 1;
                    }
                }
            } else {
                //后半段
                if (target == nums[mid]) {
                    return mid;
                } else if (target > nums[mid]) {
                    //此时mid一定在后半段范围内，因此start = mid + 1
                    start = mid + 1;
                } else {
                    //此时区分一下mid在前半段还是后半段
                    if (nums[mid] >= nums[0]) {
                        //前半段，则后半段的起始位置一定在mid之后，因此start = mid + 1
                        start = mid + 1;
                    } else {
                        //后半段，则end = mid - 1;
                        end = mid - 1;
                    }
                }
            }
        }
        return -1;
    }
}
```