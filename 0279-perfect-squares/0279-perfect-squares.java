class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n+1];

        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int k = 2; k*k <= i; k++) {
                dp[i] = Math.min(dp[i], 1 + dp[i-k*k]);
            }
        }

        return dp[n];
    }
}