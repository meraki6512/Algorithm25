// package Algorithm.Algorithm25.Java.BOJ4803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n, m, T, tc = 1;
    private static boolean toPass;
    private static ArrayList<Integer>[] graph;
    private static HashSet<Integer> globalVisited;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());   // 정점 수
            m = Integer.parseInt(st.nextToken());   // 간선 수
            if (n==0 && m==0) break;

            graph = new ArrayList[n+1];
            globalVisited = new HashSet<>();
            for (int i=1; i<=n; i++) graph[i] = new ArrayList<>();
            for (int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            // 트리
            // = 사이클이 없는 연결 요소
            // = 정점이 k개일 때 간선이 k-1개
            // = 임의의 두 정점에 대해 유일한 경로
            T = 0;
            for (int i=1; i<=n; i++){
               if (!globalVisited.contains(i)){
                   toPass = false;
                   visited = new boolean[n+1];
                   visited[i] = true;
                   dfs(i, 0);
                   if (!toPass) T++;
               }
            }
            sb.append("Case ").append(tc++).append(": ");
            if (T==0) sb.append("No trees.\n");
            else if (T==1) sb.append("There is one tree.\n");
            else sb.append("A forest of ").append(T).append(" trees.\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int cur, int parent){
        visited[cur] = true;
        globalVisited.add(cur);
        for (int nxt : graph[cur]){
            if (nxt != parent && globalVisited.contains(nxt)) {
                toPass = true;
                return;
            }
            if (!globalVisited.contains(nxt) && !visited[nxt])
                dfs(nxt, cur);
        }
    }
}
