// package Algorithm.Algorithm25.Java.BOJ2533;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static ArrayList<Integer>[] tree;
    private static int[][] dp;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for (int i=0; i<=N; i++) tree[i] = new ArrayList<>();
        dp = new int[N+1][2];
        visited = new boolean[N+1];
        for (int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        // 시작 정점은 임의로 1로 설정
        dfs(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }
    
    // dp[cur][1]: 얼리어답터인 경우: 친구가 얼(1)이거나 얼이 아니거나(0) 중에 더 작은 수 선택
    // dp[cur][0]: 얼리어답터가 아닌 경우: 친구가 얼(1)인 경우 선택
    private static void dfs(int cur){
        visited[cur] = true;
        dp[cur][1] = 1; // 얼리어답터일 때 카운트

        for (int n : tree[cur]){
            if (!visited[n]){
                dfs(n);
                dp[cur][1] += Math.min(dp[n][1], dp[n][0]);
                dp[cur][0] += dp[n][1];
            }
        }
    }
}
