/*
* 그래프 -> 최소 스패닝 트리를 구하라
* V(10^4), E(10^5)
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int[] parent;
    private static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge>{

        int a, b, c;
        public Edge(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return c - o.c;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else if (b < a) parent[a] = b;
    }

    private static int kruskal(int V){

        int ans = 0, edge = 0;
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {parent[i]=i;}

        while (!pq.isEmpty() && edge != V-1){
            Edge e = pq.poll();

            if (find(e.a) != find(e.b)){
                ans += e.c;
                union(e.a, e.b);
                edge ++;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();

        for (int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a, b, c));
        }

        System.out.println(kruskal(V));

    }
}
