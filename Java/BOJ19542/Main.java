package Algorithm.Algorithm25.Java.BOJ19542;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 19542. 전단지 돌리기
 * https://www.acmicpc.net/problem/19542
 * 현재 노드를 기준으로 D칸까지 전단지 던질 수 있음.
 * N = 100_000
 *
 * 최대 자식 거리를 저장해야할듯. dp tree 느낌?
 * 1 2 3 4 5 6
 * 4 3 2 0 1 0
 * 그 다음에 D보다 크거나 같은 노드만 방문 탐색하면서 depth 체크를 해볼까.
 */
public class Main {

    private static int N, S, D, ans = 0;
    private static ArrayList<Integer>[] tree;
    private static boolean[] visited;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 노드 수
        S = Integer.parseInt(st.nextToken());   // 케니소프트 위치
        D = Integer.parseInt(st.nextToken());   // 힘
        tree = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();
        visited = new boolean[N+1];
        dp = new int[N+1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        getDpDfs(S);
        visited = new boolean[N+1];
        dfs(S);

        // 또는
        // dfsOnce(S, 0);

        System.out.println(ans * 2);
    }

    private static void getDpDfs(int cur){
        visited[cur] = true;

        for (int n: tree[cur]) {
            if (!visited[n]) {
                getDpDfs(n);
                dp[cur] = Math.max(dp[cur], dp[n]+1);
            }
        }
    }

    private static void dfs(int cur){
        visited[cur] = true;

        for (int n: tree[cur]) {
            if (!visited[n] && dp[n] >= D) {
                dfs(n);
                ans ++;
            }
        }
    }

    private static int dfsOnce(int cur, int parent){
        int maxDepth = 0;
        for (int n : tree[cur]) {
            if (n != parent) {
                int child = dfsOnce(n, cur);
                if (child >= D) ans ++;
                maxDepth = Math.max(maxDepth, child + 1);
            }
        }
        return maxDepth;
    }
}

