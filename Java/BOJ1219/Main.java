package Algorithm.Algorithm25.Java.BOJ1219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, s, e, M;
    private static int[] earnings;
    private static ArrayList<Edge> edges = new ArrayList<>();
    private static final int INF = 50_000_001;
    private static ArrayList<Integer>[] adj;
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
        adj = new ArrayList[N];
        for(int i=0; i<N; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
            adj[a].add(b);
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) earnings[i] = Integer.parseInt(st.nextToken());
        System.out.println(bellman());
    }

    // 방법 1. 넉넉하게 돌리기
    // 시간 복잡도: O(2NM)
    // 공간 복잡도: O(M)
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

    // 방법2. BFS로 양수 사이클 탐색하기
    // 시간 복잡도: O(NM) + O(N+M)
    // 공간 복잡도: O(M) + O(N+M) + O(N)
    private static String bellman2() {
        long[] cost = new long[N];
        Arrays.fill(cost, -INF);
        cost[s] = earnings[s];

        Queue<Integer> q = new LinkedList<>();
        boolean[] isGee = new boolean[N]; // 양수 사이클 영향권 체크

        // 1. 벨만-포드 수행 (N번)
        // N-1번은 최장 거리 갱신, 마지막 1번(N번째)은 사이클 검출용
        for (int i = 0; i < N; i++) {
            for (Edge edge : edges) {
                if (cost[edge.a] == -INF) continue;

                if (cost[edge.b] < cost[edge.a] + earnings[edge.b] - edge.c) {
                    cost[edge.b] = cost[edge.a] + earnings[edge.b] - edge.c;

                    // N번째 루프(i == N-1)에서 갱신된다는 건 사이클이란 뜻.
                    if (i == N - 1) {
                        if (!isGee[edge.a]) {
                            isGee[edge.a] = true;
                            q.offer(edge.a);
                        }
                        if (!isGee[edge.b]) {
                            isGee[edge.b] = true;
                            q.offer(edge.b);
                        }
                    }
                }
            }
        }

        // 도달 불가인가?
        if (cost[e] == -INF) return "gg";

        // 2. BFS 탐색
        while (!q.isEmpty()) {
            int curr = q.poll();

            // 인접 리스트(adj)를 사용해 O(V+E)로 전파
            for (int next : adj[curr]) {
                if (!isGee[next]) {
                    isGee[next] = true;
                    q.offer(next);
                }
            }
        }

        // 양수 사이클 영향권인가?
        if (isGee[e]) return "Gee";

        return String.valueOf(cost[e]);
    }
}
