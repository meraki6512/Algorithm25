class Solution {
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;
        boolean[] deleted = new boolean[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        final int INF = 10_001;
        int[][] dp = new int[INF][2];
        for (int i = 1; i < INF; i++) {
            if (map.containsKey(i)) {
                dp[i][1] = dp[i-1][0] + map.get(i) * i;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
        }

        return Math.max(dp[INF-1][0], dp[INF-1][1]);
    }
}