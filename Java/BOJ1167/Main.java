package Algorithm.Algorithm25.Java.BOJ1167;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class Edge{
        int n, d;
        Edge(int n, int d){
            this.n = n;
            this.d = d;
        }
    }

    private static int V, f = 0, fi = 0;
    private static boolean[] visited;
    private static List<Edge>[] tree;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());    // 10^5
        visited = new boolean[V+1];
        tree = new List[V+1];
        for (int i=0; i<=V; i++) tree[i] = new ArrayList<>();

        for (int i=0; i<V; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int n, d;
            while (true) {
                n = Integer.parseInt(st.nextToken());
                if (n < 0) break;
                d = Integer.parseInt(st.nextToken());   // 10^4
                tree[v].add(new Edge(n, d));
            }
        }

        // 트리의 지름 구하기..
        // 모든노드로부터 거리가 0인 임의의 노드하나를 더 만들어서 전체에 대한 거리를 구해서 최대 거리를 구하든가.
        // 하나의 노드로부터 가장 떨어진 노드를 구하고 그 노드에서부터 다시 가장 먼 거리의 노드까지의 거리를 구하든가.
        visited[1] = true;
        bfs(1);

        Arrays.fill(visited, false);
        visited[fi] = true;
        bfs(fi);

        System.out.println(f);

    }

    private static void bfs(int st){
        Queue<Edge> q = new LinkedList<>();
        q.add(new Edge(st, 0));

        while (!q.isEmpty()){
            Edge cur = q.poll();

            if (f < cur.d){
                fi = cur.n;
                f = cur.d;
            }

            for (Edge x : tree[cur.n]){
                if (!visited[x.n]){
                    visited[x.n] = true;
                    q.add(new Edge(x.n, cur.d + x.d));
                }
            }
        }
    }


    private static void dfs(int i, int d){

        if (f < d){
            fi = i;
            f = d;
        }

        for (Edge x : tree[i]){
            if (!visited[x.n]){
                visited[x.n] = true;
                dfs(x.n, d + x.d);
            }
        }
    }
}
