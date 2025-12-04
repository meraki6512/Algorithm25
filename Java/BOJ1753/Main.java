package Algorithm.Algorithm25.Java.BOJ1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static final int INF = 5_000_000;
    private static int V, E, stt;
    private static ArrayList<Edge>[] graph;
    private static int[] dist;

    private static class Edge  implements Comparable<Edge>{
        int x, w;
        public Edge(int x, int w){
            this.x = x;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        stt = Integer.parseInt(br.readLine());
        dist = new int[V+1];
        Arrays.fill(dist, INF);
        dist[stt] = 0;

        graph = new ArrayList[V+1];
        for (int i=0; i<=V; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i=1; i<=E; i++){
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[u].add(new Edge(v, w));
        }

        dijkstra();

        for(int i=1; i<=V; i++){
            if (i==stt) System.out.println('0');
            else if (dist[i]==INF) System.out.println("INF");
            else System.out.println(dist[i]);
        }
    }

    private static void dijkstra(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(stt, 0));

        while (!pq.isEmpty()){
            Edge cur = pq.poll();

            if (dist[cur.x] < cur.w) continue;

            for (Edge nxt : graph[cur.x]){
                if (dist[nxt.x] > cur.w + nxt.w) {
                    dist[nxt.x] = cur.w + nxt.w;
                    pq.add(new Edge(nxt.x, dist[nxt.x]));
                }
            }
        }
    }
}