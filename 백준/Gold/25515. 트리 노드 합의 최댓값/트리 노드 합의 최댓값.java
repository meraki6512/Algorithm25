// package Algorithm.Algorithm25.Java.BOJ25515;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 25515. 트리 노드 합의 최댓값 (3, 512)
 * 0번(루트)부터 번호, n개의 정점, 가중치X 트리
 * 방문한 노드에 적힌 정수 합의 최댓값을 구하라.
 */

public class Main {

    private static long[] dp, num;  // -100,000 ≤ 노드에 부여된 값 ≤ 100,000
    private static ArrayList<Integer>[] children;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        children = new ArrayList[n];
        dp = new long[n];
        num = new long[n];
        for (int i = 0; i < n; i++) children[i] = new ArrayList<>();
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            children[p].add(c);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            dp[i] = num[i];
        }

        dfs(0);
        System.out.println(dp[0]);
    }

    private static void dfs(int cur){
        for (int n : children[cur]){
            dfs(n);
            dp[cur] += Math.max(dp[n], 0);
        }
    }
}
