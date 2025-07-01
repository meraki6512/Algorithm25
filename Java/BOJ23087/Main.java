package Algorithm.Algorithm25.Java.BOJ23087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
* 최단최단경로
* https://www.acmicpc.net/problem/23087
* : N개의 도시, M개의 편도 노선
* : 최단 경로면서 가장 적은 수의 노선을 사용하는 경로
* x -> y 경로가 아예 없으면 -1
* 
* 다시 풀어야 할 문제 - 해결 못함
* */

public class Main {

    static class Line implements Comparable<Line>{
        int x, w, m;
        Line (int x, int w, int m){
            this.x = x;
            this.w = w;
            this.m = m;
        }

        @Override
        public int compareTo(Line o) {
            return (w == o.w) ? m - o.m : w - o.w;
        }
    }

    public static void main(String[] args) throws IOException {

        final int INF = Integer.MAX_VALUE;
        final int MOD = 1_000_000_009;

        int N, M, x, y;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        ArrayList<Line>[] graph = new ArrayList[N+1];
        for (int i=0; i<=N; i++) graph[i] = new ArrayList<>();

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Line(v, w, 0));
        }

        int[] d = new int[N+1];
        int[] m = new int[N+1];
        Arrays.fill(d, INF);
        Arrays.fill(m, INF);
        d[x] = 0;
        m[x] = 0;
        long[] count = new long[N+1];
        count[x] = 1;
        PriorityQueue<Line> que = new PriorityQueue<>();
        que.offer(new Line(x, 0, 0));

        while (!que.isEmpty()){
            Line cur = que.poll();

            if (cur.w > d[cur.x]) continue;
            if (cur.w == d[cur.x] && cur.m > m[cur.x]) continue;

            for (Line next : graph[cur.x]){
                // 가중치 업데이트가 필요하면 업데이트 후 que에 곧바로 넣음
                if (d[next.x] > cur.w + next.w){
                    d[next.x] = cur.w + next.w;
                    m[next.x] = cur.m + 1;
                    count[next.x] = count[cur.x];
                    que.offer(new Line(next.x, d[next.x], cur.m + 1));
                }
                // 가중치가 같다면, 거친 간선 수 비교
                else if (d[next.x] == cur.w + next.w){
                    // 거친 간선 수 업데이트가 필요하면 업데이트 후 que에 넣음
                    if (m[next.x] > cur.m + 1){
                        m[next.x] = cur.m + 1;
                        count[next.x] = count[cur.x];
                        que.offer(new Line(next.x, d[next.x], m[next.x]));
                    }
                    // 거친 간선 수가 같다면, count 그만큼 늘려줌
                    else if (m[next.x] == cur.m + 1){
                        count[next.x] = (count[next.x] + count[cur.x]) % MOD;
                    }
                }
            }
        }

        if (d[y] == INF) System.out.println(-1);
        else {
            System.out.println(d[y]);
            System.out.println(m[y]);
            System.out.println(count[y] % MOD);
        }
    }
}
