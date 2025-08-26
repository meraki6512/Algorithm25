// package Algorithm.Algorithm25.Java.BOJ16398;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 최소스패닝트리. 분할.
 * -> 다시 풀기, prim으로도 구현해보기
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

    private static int[] parent;

    public static void main(String[] args) throws IOException {

        // In
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        parent = new int[N +1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        int[][] costs = new int[N+1][N+1];
        for(int i = 1; i<= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<= N; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken()); // 100,000,000, Cij = Cji, Cii = 0
            }
        }

        // Sol
        // prim - n^2: 1000_000 정도
        // kruskal - eloge: 10_000 정도
        // -> kruskal

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
