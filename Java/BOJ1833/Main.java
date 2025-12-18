package Algorithm.Algorithm25.Java.BOJ1833;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int totalCost = 0;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        for (int i = 1; i <= N; i++) parent[i] = i;

        StringTokenizer st;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (j <= i) continue;
                if (cost < 0) {     // 이미 설치된 도로
                    union(i, j);    // 사이클 여부와 무관하게 무조건 연결
                    totalCost -= cost;
                }
                else if (cost > 0) { // 새로이 설치하려는 도로
                    pq.add(new Edge(i, j, cost));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (!pq.isEmpty()) {
            Edge e = pq.poll();
            if (find(e.u) != find(e.v)) {
                union(e.u, e.v);
                totalCost += e.cost;
                cnt ++;
                sb.append(e.u).append(" ").append(e.v).append("\n");
            }
        }

        System.out.println(totalCost + " " + cnt);
        System.out.println(sb.toString());
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }

    private static class Edge implements Comparable<Edge> {
        int u, v, cost;
        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }
}
