package Algorithm.Algorithm25.Java.BOJ11724;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 연결 요소의 개수
 * https://www.acmicpc.net/problem/11724
 * dfs
 */

public class Main {
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 1000
        int M = Integer.parseInt(st.nextToken());   // 500_000
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        int ans = 0;
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i);
                ans++;
            }
        }

        System.out.println(ans);
    }

    private static void dfs(int i) {
        visited[i] = true;
        for(int j : graph[i]) {
            if (!visited[j]) {
                dfs(j);
            }
        }
    }
}
