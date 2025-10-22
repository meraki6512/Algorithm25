// package Algorithm.Algorithm25.Java.BOJ1947;

import java.util.Scanner;

public class Main {

	private static final long MOD = 1_000_000_000L;
	private static long[] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		dp = new long[N + 2];
		dp[1] = 0;
		dp[2] = 1;

		for (int i = 3; i <= N; i++) {
			dp[i] = ((dp[i-1] + dp[i-2]) * (i-1)) % MOD;
		}

		System.out.println(dp[N]);
	}
}
