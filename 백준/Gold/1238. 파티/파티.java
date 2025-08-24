// package Algorithm.Algorithm25.Java.BOJ1238;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1238. 파티 (1, 128)
 * https://www.acmicpc.net/problem/1238
 * 단방향, 다시 돌아오는 것까지 고려해야 함
 */

public class Main {
    private static class Edge implements Comparable<Edge>{
        int i, t;
        public Edge(int i, int t) { // alt+insert
            this.t = t;
            this.i = i;
        }

        @Override
        public int compareTo(Edge o) {
            return t-o.t;
        }
    }

    private static int N, M, X, max = 0;
    private static ArrayList<Edge>[] graph;
    private static int[] cost, back;
    private static final int INF = 10_000_000;

    public static void main(String[] args) throws IOException { // live template: mainio
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 1000 // live template: stint
        M = Integer.parseInt(st.nextToken());   // 10000
        X = Integer.parseInt(st.nextToken());   // 100
        graph = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            graph[A].add(new Edge(B, T));
        }

        cost = new int[N+1];
        Arrays.fill(cost, INF);
        cost[X] = 0;
        dijkstra(X);
        back = cost.clone();

        for (int i = 1; i <= N; i++) {
            if (i==X) continue;
            cost = new int[N+1];
            Arrays.fill(cost, INF);
            cost[i] = 0;
            dijkstra(i);

            max = Math.max(cost[X] + back[i], max);
        }

        System.out.println(max);
    }

    private static void dijkstra(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge c = pq.poll();
            if (cost[c.i] < c.t) continue;

            for (Edge n : graph[c.i]) {
                if (n.t + c.t < cost[n.i]) {
                    cost[n.i] = n.t + c.t;
                    pq.add(new Edge(n.i, cost[n.i]));
                }
            }
        }
    }
}
