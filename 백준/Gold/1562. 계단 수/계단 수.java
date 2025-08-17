// package Algorithm.Algorithm25.Java.BOJ1562;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
* 계단 수 (2, 128)
* 길이가 N이면서 0~9까지 숫자가 모두 등장하는 계단 수의 개수 = ?
* 계단 수 = 인접한 모든 자리 수의 차이가 1이고, 0으로 시작하지 않는 수
* ex) N=10: 9876543210
*
* -> 단순 bfs는 시간이 너무 오래 걸림: 2^N (2^100 ... 10^30)
* -> dp 메모제이션 필요: 10 * N * 2^10 (10*100*1024 ... 10^6)
 */

public class Main {

    private static final int MOD = 1_000_000_000;
    private static final int VISITED = (1<<10)-1;
    private static int N; // 1~100
    private static long ans = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        if (N < 10) {
            System.out.println(0);
            return;
        }

        // DP를 사용해 불필요한 계산을 제거해주는 게 핵심.
        // dp[dgt][num][visited]:
        // 자릿수가 dgt, 현재 숫자가 num, visited한 숫자들이 있을 때, 경우의 수
        long[][][] dp = new long[N+1][10][1<<10];
        for (int i=1; i<10; i++) dp[1][i][1<<i] = 1; // 1~9로 시작 가능

        for (int dgt = 2; dgt<=N; dgt++){   // 100
            for (int num = 0; num<10; num++){   // 10
                for (int visited = 0; visited < (1<<10); visited++){  // 1000
                    if (dp[dgt-1][num][visited] == 0) continue; // 불필요한 계산 제외

                    for (int i=-1; i<2; i+=2){ // -1, 1
                        int nxt = num + i;
                        if (0<=nxt && nxt<10){
                            // nxt 방문 체크해주고, 이전 자릿수로부터 오는 경우의 수를 더해줌
                            dp[dgt][nxt][visited|(1<<nxt)]
                                    = (dp[dgt][nxt][visited|(1<<nxt)] + dp[dgt-1][num][visited]) % MOD;
                        }
                    }
                }
            }
        }

        for (int i=0; i<10; i++) ans = (ans + dp[N][i][VISITED] ) % MOD;
        System.out.println(ans);
    }


    /// ////////////////////////////////////////////////////////////////////////////
    // X

    private static class Node{
        int idx, cnt, check;
        Node(int idx, int cnt, int check){
            this.idx = idx;
            this.cnt = cnt;
            this.check = check;
        }
    }
    private static void bfs(int st){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(st, 1, 1<<st));

        while (!q.isEmpty()){
            Node cur = q.poll();
            if (cur.cnt == N){
                if (cur.check + 1 == 1<<10) {
                    ans += 1;
                }
                continue;
            }

            for (int i=-1; i<2; i+=2){
                int nxt = cur.idx + i;
                if (0<=nxt && nxt<10){
                    q.add(new Node(nxt, cur.cnt+1, cur.check|(1<<nxt)));
                }
            }
        }
    }

}
