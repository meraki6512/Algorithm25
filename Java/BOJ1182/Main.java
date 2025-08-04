package Algorithm.Algorithm25.Java.BOJ1182;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분수열의 합
// https://www.acmicpc.net/problem/1182
// N개의 정수로 이뤄진 수열 -> 크기가 양수인 부분 수열 중 합이 S가 되는 경우의 수

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

    // 2^20 = 1,048,576
    private static void dfs(int idx, int sum){
        if (idx == N){
            if (sum == S) ans ++;
            return;
        }
        dfs(idx + 1, sum + nums[idx]);
        dfs(idx + 1, sum);
    }
}
