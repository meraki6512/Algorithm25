import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
* 도시 분할 계획
* https://www.acmicpc.net/problem/1647
*
* */

public class Main {

    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> edges;
    static HashSet<Integer> set;

    static class Edge implements Comparable<Edge>{
        int s, e, c;
        Edge(int s, int e, int c){
            this.s = s;
            this.e = e;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return c - o.c;
        }

    }

    static int find(int x){
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        // 작은 값을 부모로
        if (x < y) {
            parent[y] = x;
            set.remove(y);
        }
        else if (x > y) {
            parent[x] = y;
            set.remove(x);
        }
    }

    public static int kruskal(){
        int ans = 0;
        set = new HashSet<>();

        parent = new int[N+1];                  // cycle 확인: union-find
        for (int i=1; i<=N; i++) {
            parent[i] = i;
            set.add(i);
        }

        while (!edges.isEmpty()){

            // 만약 parent가 두 개로만 나눠지면 종료
            if (set.size() <= 2) break;

            Edge cur = edges.poll();            // 제일 가중치가 작은 엣지가

            if (find(cur.s) != find(cur.e)){    // cyclic 하지 않을 때
                ans += cur.c;                   // mst에 포함
                union(cur.s, cur.e);
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new PriorityQueue<>();

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, e, c));
        }

        System.out.println(kruskal());
    }
}
