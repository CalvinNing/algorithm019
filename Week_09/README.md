学习笔记
- 不同路径 2 这道题目的状态转移方程
```java
    dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
    if (obstacleGrid[i][j] == 1) {
        dp[j] = 0;
    } else {
        if (j >= 1) {
            dp[j] = dp[j - 1] + dp[j];
        }
    }
```
