package Algorithm.Algorithm25.Java.BOJ1738;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <a href="https://www.acmicpc.net/problem/1738">1738. 골목길</a>
 * 벨만 포드; 최장 경로; 양수 사이클; 역추적
 */
public class Main {
    private static int n, m;
    private static final int INF = 20_000_001;
    private static class Way {
        int u, v, w;
        public Way(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
    private static List<Way> ways = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            ways.add(new Way(u, v, w));
        }

        System.out.println(bellman());
    }

    private static String bellman() {

        // StringBuilder[] ans = new StringBuilder[n+1];
        // ans[1] = new StringBuilder("1");
        int[] parents = new int[n+1];

        int[] dist = new int[n+1];
        Arrays.fill(dist, -INF);
        dist[1] = 0;

        // 벨만-포드: (N-1)번 돌고, 사이클 확인을 위해 1번 더 돔 (총 N번)
        // -> 사이클 전파를 위해 (N)번 이상 반복 (총 N+1)번
        // + 반대로 양수 사이클 확인해야 함
        for (int i = 1; i <= n; i++) {
            for (Way way : ways) {
                
                if (dist[way.u] == -INF) continue;
                
                // 사이클이 있는 지점(u)이라면 나가는 경로(v)도 INF로 전파
                if (dist[way.u] == INF) {
                    dist[way.v] = INF;
                    continue;
                }

                // 갱신 (더 작을 경우)
                if (dist[way.v] < dist[way.u] + way.w) {
                    dist[way.v] = dist[way.u] + way.w;

                    // ans[way.v] = new StringBuilder(ans[way.u]).append(" ").append(way.v);   // * 주의: 새 객체 생성해야 함
                    parents[way.v] = way.u;

                    // (N)번째에서 갱신이 일어난다 == (양수) 사이클
                    if (i == n) {
                        dist[way.v] = INF;
                    }
                }
            }
        }

        // 양수 사이클에 있거나, 도착 자체를 못한 경우: 존재X
        // return (dist[n] == INF || dist[n] == -INF) ? "-1" : ans[n].toString();
        if (dist[n] == INF || dist[n] == -INF) return "-1";

        // 경로 역추적
        StringBuilder sb = new StringBuilder();
        int cur = n;
        Stack<Integer> stack = new Stack<>();

        while (cur != 0) {
            stack.push(cur);
            if (cur == 1) break;
            cur = parents[cur];
        }

        while (!stack.isEmpty()) sb.append(stack.pop()).append(" ");
        return sb.toString().trim();
    }
}
