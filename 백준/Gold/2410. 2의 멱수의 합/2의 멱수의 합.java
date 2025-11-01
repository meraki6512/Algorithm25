// package Algorithm.Algorithm25.Java.BOJ2410;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <a href="https://www.acmicpc.net/problem/2410">2410. 2의 멱수의 합</a>
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		final long MOD = 1_000_000_000;

		long[] dp = new long[N + 1];
		Arrays.fill(dp, 1);

		for (int x = 2; x <= N; x *= 2) {
			for (int i = 1; i <= N; i++) {
				if (i - x >= 0) {
					dp[i] = (dp[i] + dp[i-x]) % MOD;
				}
			}
		}

		System.out.println(dp[N]);
	}

	private static class SolutionWrong {
		private static void solve(int N) {

			final long MOD = 1_000_000_000;
			int E = (int)(Math.log10(N) / Math.log10(2));

			long[][] dp = new long[N+1][E+1];
			for (int i = 1; i <= N; i++) dp[i][0] = 1;	// k = 0이면 무조건 1

			for (int k = 1; k <= E; k++) {
				for (int i = 1; i <= N; i++) {
					dp[i][k] = (dp[i][k-1] % MOD);

					int x = (int)Math.pow(2, k);
					if (i - x >= 0) dp[i][k] = (dp[i][k] + dp[i - x][k]) % MOD;
				}
			}

			System.out.println(dp[N][E]);
		}
	}
}

