package Algorithm.Algorithm25.Java.BOJ1219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N, s, e, M;
    private static int[] earnings;
    private static ArrayList<Edge> edges = new ArrayList<>();
    private static final int INF = 50_000_001;
    private static class Edge {
        int a, b, c;
        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        earnings = new int[N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) earnings[i] = Integer.parseInt(st.nextToken());
        System.out.println(bellman());
    }

    private static String bellman() {
        long[] cost = new long[N];
        Arrays.fill(cost, -INF);
        cost[s] = earnings[s];

        // 반복 횟수를 넉넉하게 N + N 번 (최장 거리 판단 + 양수 사이클 전파) 수행
        for (int i = 0; i < N + N; i++) {
            for (Edge edge : edges) {

                if (cost[edge.a] == -INF) continue;
                if (cost[edge.a] == INF) {
                    cost[edge.b] = INF;
                    continue;
                }

                // 갱신
                if (cost[edge.b] < cost[edge.a] + earnings[edge.b] - edge.c) {
                    cost[edge.b] = cost[edge.a] + earnings[edge.b] - edge.c;

                    // N-1번째 이후 갱신 == 양수 사이클
                    if (i >= N-1) {
                        cost[edge.b] = INF;
                    }
                }
            }
        }

        return (cost[e] == INF) ? "Gee" : (cost[e] == -INF) ? "gg" : String.valueOf(cost[e]);
    }
}
