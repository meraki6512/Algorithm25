//package Algorithm.Algorithm25.Java.BOJ1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N, S;
    private static int[] nums;
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) nums[i] = Integer.parseInt(st.nextToken());

        dfs(0, 0);
        System.out.println(S == 0 ? ans -1 : ans);
    }

    // 2^20
    private static void dfs(int idx, int sum){
        if (idx == N){
            if (sum == S) ans ++;
            return;
        }
        dfs(idx + 1, sum + nums[idx]);
        dfs(idx + 1, sum);
    }
}
