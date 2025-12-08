import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <a href="https://www.acmicpc.net/problem/1939">1939. 중량제한</a>
 * N개의 섬 (10,000), M개의 다리 (100,000)
 * 다리마다 중량제한이 있음 (초과하지 않도록)
 * 공장은 두 개
 * 한 공장에서 다른 공장으로 한 번 이동할 때 중량의 최댓값 = ?
 * 
 * 다익스트라로 풀었지만
 * 이진 탐색으로도 풀 수 있음
 */
public class Main {

    private static final int INF = 1_000_000_001;
    private static int N;
    private static List<Node>[] graph;

    private static class Node implements Comparable<Node>{
        int to, d;
        public Node(int to, int d) {
            this.to = to;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {  
            return o.d - this.d;            // desc 주의 (Max Heap으로)
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new List[N+1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A =  Integer.parseInt(st.nextToken());
            int B =  Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        st = new StringTokenizer(br.readLine());
        System.out.println(bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }

    private static int bfs(int stt, int end){

        int[] cost = new int[N + 1];
        cost[stt] = INF;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(stt, INF));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.d < cost[cur.to]) continue; // 현재 꺼낸 중량이 이미 기록된 중량보다 작으면 최적 경로가 아니므로 무시
            if (cur.to == end) continue;

            for (Node n : graph[cur.to]) {

                int m = Math.min(cur.d, n.d);
                if (cost[n.to] >= m) continue;

                cost[n.to] = m;
                pq.offer(new Node(n.to, cost[n.to]));

            }
        }

        return cost[end];
    }
}
