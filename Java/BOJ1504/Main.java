package Algorithm.Algorithm25.Java.BOJ1504;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <a href="https://www.acmicpc.net/problem/1504">1504. 특정한 최단 경로</a>
 * 1번 정점에서 v1, v2를 거쳐 N번 정점으로 이동하는 최단 거리 = ?
 */
public class Main {

    private static final int INF = 200_000_001;
    private static int N, v1, v2;
    private static List<Node>[] graph;
    private static class Node implements Comparable<Node>{
        int x, c;
        public Node(int x, int c){
            this.x = x;
            this.c = c;
        }

        @Override
        public int compareTo(Node o) {
            return c - o.c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for(int i = 1; i <= N; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int v1_to_v2 = dijkstra(v1, v2);
        int dist = Math.min(
                dijkstra(1, v1) + dijkstra(v2, N),
                dijkstra(1, v2) + dijkstra(v1, N))
                + v1_to_v2;
        System.out.println(dist >= INF ? -1 : dist);
    }

    private static int dijkstra(int stt, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(stt, 0));
        int[] cost = new int[N + 1];
        Arrays.fill(cost, INF);
        cost[stt] = 0;

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if (cur.x == end) continue;
            if (cost[cur.x] < cur.c) continue;

            for (Node n : graph[cur.x]){
                if (cost[cur.x] + n.c < cost[n.x]) {
                    cost[n.x] = cost[cur.x] + n.c;
                    pq.offer(new Node(n.x, cost[n.x]));
                }
            }
        }

        return cost[end];
    }
}
