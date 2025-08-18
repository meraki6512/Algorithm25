// package Algorithm.Algorithm25.Java.BOJ1949;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] num;
    private static int[][] dp;
    private static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        num = new int[N+1];
        dp = new int[N+1][2];
        tree = new ArrayList[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            tree[i] = new ArrayList<>();
        }

        for (int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    private static void dfs(int cur, int parent){
        dp[cur][1] += num[cur];

        for (int nxt : tree[cur]){
            if (nxt != parent){
                dfs(nxt, cur);
                dp[cur][0] += Math.max(dp[nxt][0], dp[nxt][1]);
                dp[cur][1] += dp[nxt][0];
            }
        }
    }
}
