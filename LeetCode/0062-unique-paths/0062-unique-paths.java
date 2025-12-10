class Solution {

    private int[][] dp;

    public int uniquePaths(int m, int n) {
        dp = new int[m][n];
        return f(m-1, n-1);
    }

    private int f(int x, int y) {
        if (x == 0 || y == 0) return dp[x][y] = 1;

        int a = dp[x-1][y] == 0 ? f(x-1, y) : dp[x-1][y];
        int b = dp[x][y-1] == 0 ? f(x, y-1) : dp[x][y-1];

        return dp[x][y] = (a + b);
    }
}