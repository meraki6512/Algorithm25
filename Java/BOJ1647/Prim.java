package Algorithm.Algorithm25.Java.BOJ1647;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 도시 분할 계획
* https://www.acmicpc.net/problem/1647
* MST - Prim (greedy, pq, bfs)
* */

public class Prim {

    static int N, M;
    static List<Edge>[] graph;

    static class Edge implements Comparable<Edge>{
        int n, c;
        Edge(int n, int c){
            this.n = n;
            this.c = c;
        }

        @Override
        public int compareTo(Edge o) {
            return c - o.c;
        }

    }

    public static int prim(int start){

        int ans = 0;
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(visited, false);
        PriorityQueue<Edge> q = new PriorityQueue<>();
        q.add(new Edge(start, 0));
        TreeSet<Edge> mst = new TreeSet<>();

        while (!q.isEmpty()){
            Edge cur = q.poll();                // 가중치가 가장 작은 엣지가
            if (visited[cur.n]) continue;       // 방문한 정점이 아니면 선택
            visited[cur.n] = true;
            ans += cur.c;
            mst.add(cur);

            for (Edge nxt : graph[cur.n]){      // 인접 노드에 대해 탐색
                if (!visited[nxt.n])            
                    q.add(nxt);
            }
        }

        // 마지막 노드 (가중치 가장 큰 노드) 하나는 다른 마을로 분리 가능
        ans -= mst.last().c;    

        return ans;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for (int i=0; i<=N; i++) graph[i] = new ArrayList<>();

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[s].add(new Edge(e, c));
            graph[e].add(new Edge(s, c));
        }

        System.out.println(prim(1));
    }
}
