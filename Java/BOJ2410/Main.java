package Algorithm.Algorithm25.Java.BOJ2410;

import java.util.Scanner;

/**
 * <a href="https://www.acmicpc.net/problem/2410">2410. 2의 멱수의 합</a>
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long ans = 0;
		final long MOD = 1_000_000_000;

		long[][] dp = new long[N+1][N+1];
		for (int i = 1; i <= N; i++) dp[i][0] = 1;	// k = 0이면 무조건 1

		int end = (int)Math.log(N);
		System.out.println(end);

		for (int k = 1; k <= end; k++) {
			for (int i = 1; i <= N; i++) {
				dp[i][k] = (dp[i][k-1] % MOD);

				int x = (int)Math.pow(2, k);
				if (i - x >= 0) dp[i][k] = (dp[i][k] + dp[i - x][k]) % MOD;
			}
		}

		System.out.println(dp[N][end]);
	}
}
