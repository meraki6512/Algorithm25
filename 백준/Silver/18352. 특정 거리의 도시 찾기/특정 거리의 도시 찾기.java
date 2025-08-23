// package Algorithm.Algorithm25.Java.BOJ18352;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 18352. 특정 거리의 도시 찾기 (2, 256)
 * 1~N번 도시
 * M개 단방향 도로 (길이=1)
 * 도시 X로부터 최단 거리가 K인 도시들의 번호를 출력하라.
 */

public class Main {

    private static int N, M, K, X;
    private static ArrayList<Integer>[] graph;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 도시 수
        M = Integer.parseInt(st.nextToken());   // 도로 수
        K = Integer.parseInt(st.nextToken());   // 최단 거리
        X = Integer.parseInt(st.nextToken());   // 출발 도시
        dist = new int[N+1];
        Arrays.fill(dist, -1);
        graph = new ArrayList[N+1];
        for (int i=0; i<N+1; i++) graph[i] = new ArrayList<>();
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[A].add(B);
        }

        // X로 출발해 최단거리가 K인 모든 도시
        bfs();

        // 오름차순으로 한 줄에 하나씩 출력
        // 최단거리가 K인 도시가 하나도 없으면 -1
        print();
    }
    private static void print(){
        boolean found = false;
        for (int i=1; i<=N; i++){
            if (dist[i]==K) {
                System.out.println(i);
                found = true;
            }
        }
        if (!found) System.out.println(-1);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        dist[X] = 0;
        q.add(X);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int nxt: graph[cur]){
                if (dist[nxt] == -1){
                    dist[nxt] = dist[cur] + 1;
                    q.add(nxt);
                }
            }
        }
    }
}
