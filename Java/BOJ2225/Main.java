package Algorithm.Algorithm25.Java.BOJ2225;

import java.util.Arrays;
import java.util.Scanner;

/*
* 합분해 https://www.acmicpc.net/problem/2225
* 2s, 128MB
*
* 0~N숫자 범위 K개 조합해 N을 만드는 경우의 수 (중복 순열)
* */

public class Main {

    public static void main(String[] args) {

        final int MOD = 1_000_000_000;

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();                               // 200
        int K = sc.nextInt();                               // 200

        int[][] dp = new int[K+1][N+1];
        Arrays.fill(dp[1], 1);                          // K=1일 때 1
        for  (int i = 1; i <= K; i++) dp[i][0] = 1;         // N=0일 때 1

        for (int i = 2; i <= K; i++) {                      // 1~K (1생략)
            for (int j = 1; j <= N; j++) {                  // 0~N (0생략)
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD; // 최대 (MOD-1)*2 < 21억
            }
        }

        System.out.println(dp[K][N]);

    }
}
