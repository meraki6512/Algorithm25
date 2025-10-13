// package Algorithm.Algorithm25.Java.BOJ1670;

import java.util.Scanner;

/**
 * <h3><a href="https://www.acmicpc.net/problem/1670">1670. 정상 회담 2</a></h3>
 * <p>교차하지 않는 악수의 경우의 수 %987654321 = ?</p>
 * <ul><li>N <= 10_000, 짝수</li></ul>
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		final long MOD = 987654321L;
		final int INF = 10_001;

		long[] dp = new long[INF];
		dp[0] = 1;

		for(int i=1;i<=N;i++) {
			for (int k = 0; k < i; k++) {
				dp[i] = (dp[i] + dp[k] * dp[i-k-1]) % MOD;
			}
		}

		System.out.println(dp[N/2]);
	}
}

