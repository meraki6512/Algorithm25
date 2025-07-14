/*
* 1005. ACM Craft
* https://www.acmicpc.net/problem/1005
* 먼저 지어야할 건물이 있음. (-> 위상정렬)
* 특정 건물을 지을 때까지 걸리는 최소 시간을 구하라.
*
* 테스트 케이스 T
* 1. 건물 개수 N (10^3), 건설 규칙 K (10^5)
* 2. 각 건물의 건설 시간 D1~DN (10^5)
* 3. 건설 규칙 X -> Y
* 4. 타겟 건물 번호 W
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{

    private static final int INF = 100_000_000;

    static class Node implements Comparable<Node>{
        int cur, prv, d;
        Node (int cur, int prv, int d){
            this.cur = cur;
            this.prv = prv;
            this.d = d;
        }

        @Override
        public int compareTo(Node o) {
            return d - o.d;
        }
    }

    private static int sol(int N, int W, int[] D, List<Integer>[] after, int[] degree){

        int[] dp = new int[N+1];                                // 각 건물의 최소 건설 시간

        Queue<Integer> q = new ArrayDeque<>();
        for (int i=1; i<=N; i++) {
            if (degree[i] == 0) {                               // 앞에 건설할 빌딩이 없으면
                q.add(i);                                       // q에 넣고
                dp[i] = D[i];                                   // 건설 시간으로 초기화
            }
        }

        while (!q.isEmpty()){
            int cur = q.poll();                                 // cur 건설

            for (int nxt : after[cur]){
                dp[nxt] = Math.max(dp[nxt], dp[cur] + D[nxt]);  // nxt 까지 걸리는 최소 시간 update
                degree[nxt]--;                                  // cur 건설했으니 nxt의 차수는 줄여줌
                if (degree[nxt] == 0) q.add(nxt);               // nxt degree가 0이면 건설 가능
            }
        }

        return dp[W];
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        int test_case = Integer.parseInt(br.readLine());
        for (int t = 0; t < test_case; t++){

            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] D = new int[N+1];
            for (int i=1; i<=N; i++) D[i] = Integer.parseInt(st.nextToken());

            // degree (차수), after (그래프) 배열 init
            int[] degree = new int[N+1];
            List[] after = new List[N+1];
            for (int i=0; i<=N; i++) after[i] = new ArrayList<>();
            for (int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                after[X].add(Y);    // X 다음에 Y 건설 가능
                degree[Y]++;        // Y 이전에 건설할 건물 개수
            }

            int W = Integer.parseInt(br.readLine());
            sb.append(sol(N, W, D, after, degree)).append("\n");
        }

        System.out.print(sb);
    }
}
