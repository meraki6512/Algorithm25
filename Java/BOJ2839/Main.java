package Algorithm.Algorithm25.Java.BOJ2839;

/*
* 2839. 설탕배달
* https://www.acmicpc.net/problem/2839
* N 배달 (3kg, 5kg 봉지로)
* 봉지의 최소 수 = ?
* */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int dp(int N){

        int[] dp = new int[N+1];
        Arrays.fill(dp, N);

        for (int i = 3; i <= N; i++) {

            if (i == 3 || i == 5) dp[i] = 1;
            if (i == 4) continue;

            // greedy (5먼저하고3)
            if (i % 5 == 0) dp[i] = Math.min(dp[i-5]+1, dp[i]);
            else dp[i] = Math.min(dp[i-3]+1, dp[i]);
        }
        return dp[N] == N? -1 : dp[N];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 5000

        System.out.println(dp(N));
    }
}
