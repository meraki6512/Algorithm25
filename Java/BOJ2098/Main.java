package Algorithm.Algorithm25.Java.BOJ2098;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 외판원 순회 TSP
 * https://www.acmicpc.net/problem/2098
 *
 * 한 외판원이 어느 한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 순회 경로
 * 단순 dfs: N*N!
 * Bitmask + DP: N*(2^N)
 * -> 다시 풀기
 */

public class Main {

    private static int N;
    private static final int INF = 1_000_000_000; // 10^6 * 16
    private static int[][] costs;
    private static int[][] dp; // dp[cur][visited] = 현재 cur이고, visited 집합만큼 방문했을 때의 남은 최소 경로값

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        costs = new int[N][N];
        dp = new int[N][1<<N];  // N*2^N ... 10^6

        for (int i=0; i<N; i++){
            for (int j =0 ; j<N; j++){
                costs[i][j] = sc.nextInt();
            }
        }

        for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);

        // 시작 도시를 임의로 0번으로 가정하고 실행
        // : 모든 도시 한 번씩 방문 후 출발지로 다시 돌아오기 때문에 최적 경로 비용은 모두 같음
        System.out.println(TSP(0, 1)); // 현재 0에서 시작, visited = 0번 도시만 (1<<0) (0b0..01)

    }

    /**
     * TSP (dp + bitmask dfs)
     * @param cur       현재 위치
     * @param visited   지금까지 방문한 도시 집합 2^N
     * @return          min cost
     */
    private static int TSP(int cur, int visited){
        if (visited == (1<<N) - 1) return costs[cur][0] > 0 ? costs[cur][0] : INF;
        if (dp[cur][visited] != -1) return dp[cur][visited];

        // 현재 위치로부터 가능한 모든 도시에 대해 비용 계산
        int min = INF;
        for (int n = 0; n<N; n++){
            if ((visited & 1<<n)!=0 || costs[cur][n] == 0) continue;    // 이미 방문 || 경로 없음
            min = Math.min(min, TSP(n, visited | (1<<n)) + costs[cur][n]); // cur -> n 이동 계산
        }

        // 최적 비용 저장
        return dp[cur][visited] = min;
    }
}
