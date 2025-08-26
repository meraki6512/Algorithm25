// package Algorithm.Algorithm25.Java.BOJ16398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 최소스패닝트리. 분할.
 * -> 다시 풀기, prim 으로도 구현해보기
 */

public class Main {

    private static class Edge implements Comparable<Edge> {
        int v1, v2, weight;

        Edge(int v1, int v2, int weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    private static class Node implements Comparable<Node> {
        int to, weight;
        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }

    private static int N;
    private static int[] parent;
    private static int[][] costs;

    public static void main(String[] args) throws IOException {

        // In
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        parent = new int[N +1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        costs = new int[N+1][N+1];
        for(int i = 1; i<= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<= N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken()); // 100,000,000, Cij = Cji, Cii = 0
            }
        }

        // Sol
        // * 간선 E = n^2 = 1_000_000
        // 1. prim pq (인접 리스트) : ElogE = n^2logn ~~ 10_000_000
        prim_pq();
        // 2. prim adj arr (인접 행렬) : n^2 ~~ 1_000_000 (10배 정도 빠름)
        // prim();
        // 3. kruskal : ElogE = n^2logn ~~ 10_000_000
        // kruskal();
    }

    private static void prim_pq(){

        // 인접 리스트 생성
        ArrayList<Node>[] graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i!=j) graph[i].add(new Node(j, costs[i][j]));
            }
        }

        // 방문 체크하면서 최소 가중치에 해당하는 정점을 방문하고 가중치 합 누적
        boolean[] visited = new boolean[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1, 0));   // 1번에서 시작한다고 가정, 가중치 0

        long weight = 0;
        int cnt = 0;

        while(!pq.isEmpty() && cnt < N) {
            Node cur = pq.poll();

            if (visited[cur.to]) continue;
            visited[cur.to] = true;
            weight += cur.weight;
            cnt++;

            for (Node n : graph[cur.to]) {
                if (!visited[n.to]) {
                    pq.offer(n);
                }
            }
        }

        System.out.println(weight);
    }

    private static void prim(){
        boolean[] visited = new boolean[N+1];
        int[] min = new int[N+1];
        Arrays.fill(min, Integer.MAX_VALUE);
        min[0] = min[1] = 0;    // 1번에서 시작한다고 가정

        long weight = 0;
        for (int i = 1; i <= N; i++) {

            // 아직 not visited 중에 최솟값 찾기

            int u = -1; // 최소 위치
            int m = Integer.MAX_VALUE;  // 최소 값

            for(int j = 1; j <= N; j++) {
                if (!visited[j] && min[j] < m) {
                    m = min[j];
                    u = j;
                }
            }

            // 최소 비용 정점 선택
            visited[u] = true;  // 방문 처리
            weight += m;        // 가중치 누적

            // 선택한 정점 기준으로 u->v 인접 정점 최소 간선 비용 갱신
            for (int v = 1; v <= N; v++) {
                if (!visited[v]){
                    min[v] = Math.min(min[v], costs[u][v]); // 더 작은 값으로 갱신
                }
            }
        }

        System.out.println(weight);
    }

    private static void kruskal() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            for (int j=i+1; j<=N; j++) {
                pq.add(new Edge(i, j, costs[i][j]));
            }
        }

        long weight = 0; // 1000 * 100_000_000
        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (find(cur.v1)!=find(cur.v2)){
                union(cur.v1, cur.v2);
                weight += cur.weight;
            }
        }

        // Out
        System.out.println(weight);
    }

    private static void union(int v1, int v2) {
        v1 = find(v1);
        v2 = find(v2);
        if (v1 < v2) parent[v2] = v1;
        else parent[v1] = v2;
    }

    private static int find(int v) {
        if(parent[v] == v) return v;
        return parent[v] = find(parent[v]);
    }

}
