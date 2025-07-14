import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 1912. 연속합
* https://www.acmicpc.net/problem/1912
* */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        int[] nums =  new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) nums[i] =  Integer.parseInt(st.nextToken());

        // n 10^5
        // -> minus값 때문에 2pointer 사용X
        // -> dp
        int[] dp = new int[n];
        dp[0] = nums[0];
        int ans = nums[0];

        for (int i=1; i<n; i++) {
            int cur = dp[i-1] + nums[i];

            // 현재를 포함한게 더 작은 경우
            if (cur < dp[i-1]){
                // dp[i] = 현재까지 더한값으로 계속 (누적합이 계속 계산되어야 함)
                // ans = 이전까지 더한거 vs ans vs 현재값
                dp[i] = cur;
                ans = Math.max(ans, dp[i-1]);
                ans = Math.max(ans, nums[i]);
            }
            // 현재를 포함한게 더 큰 경우
            else {
                // 현재까지 더한거 (이어서) vs 현재값 (새로)
                dp[i] = Math.max(cur, nums[i]);
                ans = Math.max(dp[i], ans);
            }
        }

        System.out.println(ans);

    }
}
