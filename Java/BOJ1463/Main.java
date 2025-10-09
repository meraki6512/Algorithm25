package Algorithm.Algorithm25.Java.BOJ1463;

import java.util.Scanner;

/**
 * 1463. 1로 만들기
 * https://www.acmicpc.net/problem/1463
 */
// 1 = 0
// 2 = 1
// 3 = 1
// 4 = 2
// 5 = [4] + 1 = 3
// 6 = 2
// 7 = [6] + 1 = 3
// 8 = 3
// ...
public class Main {
    private static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //10^6
        dp = new int[N+1];

        for (int i=2; i<=N; i++){
            dp[i] = dp[i-1] + 1;
            if (i % 2 == 0) dp[i] = Math.min(dp[i], dp[i/2] + 1);
            if (i % 3 == 0) dp[i] = Math.min(dp[i], dp[i/3] + 1);
        }

        System.out.println(dp[N]);
    }
}
