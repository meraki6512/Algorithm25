package Algorithm.Algorithm25.Java.BOJ14938;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/14938">14938. 서강그라운드</a>
 *
 * 전체 nxn 행렬에 대해 최단 거리를 모두 구해서
 * 수색 범위 m 이내의 모든 지점의 아이템 수를 더해서
 * 그 합의 최댓값을 구하면 되는 문제
 */
public class Main {

    private static final int INF = 2000;
    private static int n, m, r;
    private static int[] items;

    public static void main(String[] args) throws IOException {
        // O(N^3): O(10^6)
        solve_floyd();

        // dijkstra 1회 실행 비용: O((V+E) log V)
        // O(N * ((N+R) log N)): O(10^4)
        solve_dijkstra();
    }

    /**
     * Floyd-Warshall
     */
    
    private static int[][] cost;
    
    private static void solve_floyd() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) items[i] = Integer.parseInt(st.nextToken());

        cost = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            
            // 중복 간선 처리: 더 짧은 거 저장
            cost[a][b] = Math.min(cost[a][b], l);
            cost[b][a] = Math.min(cost[b][a], l);
        }

        // k: 경유지
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                for (int b = 1; b <= n; b++) {
                    if (cost[a][k] != INF && cost[b][k] != INF) {
                        cost[a][b] = Math.min(cost[a][b], cost[a][k] + cost[k][b]);
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            int res = 0;
            for (int j = 1; j <= n; j++) {
                if (cost[i][j] <= m) {
                    res += items[j];
                }
            }
            ans = Math.max(ans, res);
        }

        System.out.println(ans);
    }

    /**
     * Dijkstra
     */

    private static ArrayList<Edge>[] graph;
    private static int ans = 0;

    private static class Edge implements Comparable<Edge> {
        int x, w;
        public Edge(int x, int w) {
            this.x = x;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }

    private static void solve_dijkstra() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) items[i] = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, l));
            graph[b].add(new Edge(a, l));
        }

        for (int i = 1; i <= n; i++) {
            dijkstra(i);
        }

        System.out.println(ans);
    }

    private static void dijkstra(int st) {

        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[st] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(st, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (dist[cur.x] < cur.w) continue;
            if (cur.w > m) continue;

            for (Edge nxt : graph[cur.x]) {
                if (dist[nxt.x] > dist[cur.x] + nxt.w) {
                    dist[nxt.x] = dist[cur.x] + nxt.w;
                    pq.offer(new Edge(nxt.x, dist[nxt.x]));
                }
            }
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] <= m) {
                res += items[i];
            }
        }
        ans = Math.max(ans, res);
    }
}
