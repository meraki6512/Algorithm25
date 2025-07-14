package Algorithm.Algorithm25.Java.GoormTestJune;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int x, d;
    Node (int x, int d){
        this.x = x;
        this.d = d;
    }

    @Override
    public int compareTo(Node o){
        return this.d - o.d;
    }
}

class Number3 {

    private static final int INF = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // N, M, K, S
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        // P[i]: i번 집이 있는 지점
        int[] P = new int[K+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=K; i++){
            P[i] = Integer.parseInt(st.nextToken());
        }

        // M개의 줄에 A->B:D
        List<Node>[] graph = new List[N+1];
        for (int i=1; i<=N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, d));
            graph[b].add(new Node(a, d));
        }

        // S 지점으로부터의 최단거리
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);
        dist[S] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(S, 0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();
            if (cur.d > dist[cur.x]) continue;

            for (Node nxt : graph[cur.x]){
                if (dist[nxt.x] > dist[cur.x] + nxt.d){
                    dist[nxt.x] = dist[cur.x] + nxt.d;
                    pq.add(nxt);
                }
            }
        }

        // S 지점에서 시작해서 K개의 선물을 줄 수 있을까?
        int ans = 0;
        int max = -1;
        for (int i=1; i<=K; i++){
            int d = dist[P[i]];
            if (d >= INF) {
                System.out.println(-1);
                return;
            }

            max = Math.max(max, d);
            ans += 2*d;
        }
        System.out.println(ans - max);

    }
}

// -> 72 / 100 점