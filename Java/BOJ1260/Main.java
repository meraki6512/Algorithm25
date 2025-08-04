package Algorithm.Algorithm25.Java.BOJ1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static int N;
    private static StringBuilder dfs_str, bfs_str;

    private static void dfs(int V){
        dfs_str.append(V).append(" ");

        for (int n : graph[V]){
            if (!visited[n]){
                visited[n] = true;
                dfs(n);
            }
        }
    }

    private static void bfs(int V){
        Queue<Integer> q = new LinkedList<>();
        q.add(V);
        visited = new boolean[N+1];
        visited[V] = true;
        bfs_str = new StringBuilder();

        while (!q.isEmpty()){
            int c = q.poll();
            bfs_str.append(c).append(" ");

            for (int n : graph[c]){
                if (!visited[n]){
                    visited[n] = true;
                    q.add(n);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 1000 정점
        int M = Integer.parseInt(st.nextToken());   // 10000 간선
        int V = Integer.parseInt(st.nextToken());   // 시작 정점 번호
        graph = new List[N+1];
        for (int i=0; i<=N; i++) graph[i] = new ArrayList<>();
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }
        for (int i=0; i<=N; i++) Collections.sort(graph[i]);

        dfs_str = new StringBuilder();
        visited = new boolean[N+1];
        visited[V] = true;
        dfs(V);
        bfs(V);

        System.out.println(dfs_str.toString().strip());
        System.out.println(bfs_str.toString().strip());
    }
}
