// package Algorithm.Algorithm25.Java.BOJ1045;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * MST: 그래프의 모든 nodes를 연결하면서, edges의 cost 합이 최소가 되는 트리 구존
 */
public class Main {

    private static int N, M;
    private static char[][] adjMatrix;
    private static int[] ans;
    private static int[] parent;
    private static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        // In
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = new int[N];
        adjMatrix = new char[N][N];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
//                adjMatrix[i][j] = s.charAt(j);
                if (s.charAt(j) == 'Y') {
                    edges.add(new Edge(i, j));
                }
            }
        }

        // 정렬 1회
        Collections.sort(edges);

        // union - find
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        ArrayList<Edge> connectedEdges = new ArrayList<>();
        boolean[][] visited = new boolean[N][N];

        // 1. MST 만들기
        for (Edge edge : edges) {
            if (find(edge.u) != find(edge.v)) {
                union(edge.u, edge.v);
                connectedEdges.add(edge);
                visited[edge.u][edge.v] = visited[edge.v][edge.u] = true;
            }
        }

        // - 연결 그래프가 안 만들어지면 실패
        if (connectedEdges.size() < N - 1) {
            System.out.println(-1);
            return;
        }
        
        // 2. M개 채우기
        for (Edge edge : edges) {
            if (connectedEdges.size() == M) break;
            if (!visited[edge.u][edge.v]) {
                connectedEdges.add(edge);
                visited[edge.u][edge.v] = visited[edge.v][edge.u] = true;
            }
        }

        // - M개 못 채우면 실패
        if (connectedEdges.size() != M) {
            System.out.println(-1);
            return;
        }

        // Out
        for (Edge edge : connectedEdges) {
            ans[edge.u]++;
            ans[edge.v]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString());

    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u != v) parent[v] = u;
    }

    private static class Edge implements Comparable<Edge> {
        int u, v;
        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }
        @Override
        public int compareTo(Edge e) {
            // asc by idx of min
            int ai = Math.min(this.u, this.v);
            int bi = Math.min(e.u, e.v);
            return ai - bi;
        }
    }

    // ------------------------------------------------------------------------------------------------
    // ------------------------------------------------------------------------------------------------

    private static void wrong_sol_prim(){
        // M개를 못 채웠을 때 사이클 검출을 못하고 있음.
        boolean res = prim();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            if (ans[i] == 0) {
                res = false;
                break;
            }
            sb.append(ans[i]).append(" ");
        }
        System.out.println(res ? sb.toString() : -1);
    }

    private static boolean prim(){
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<WrongEdge> pq = new PriorityQueue<>();
        pq.offer(new WrongEdge(0,0));
        int cnt = -1;
        ans[0] = -2;

        while(!pq.isEmpty()){
            WrongEdge e = pq.poll();

            if (visited[e.parent][e.x]) continue;
            visited[e.parent][e.x] = visited[e.x][e.parent] = true;

            ans[e.parent]++;
            ans[e.x]++;

            if (++cnt == M) break;

            for (int i = 0; i < N; i++){
                if (adjMatrix[e.x][i] == 'Y') {
                    pq.offer(new WrongEdge(i, e.x));
                }
            }
        }

        return cnt == M;
    }

    private static class WrongEdge implements Comparable<WrongEdge>{
        int x;
        int parent;
        public WrongEdge(int x, int parent) {
            this.x = x;
            this.parent = parent;
        }
        @Override
        public int compareTo(WrongEdge e) {
            // asc by idx of min
            int ai = Math.min(this.x, this.parent);
            int bi = Math.min(e.x, e.parent);
            return ai - bi;
        }
    }
}
