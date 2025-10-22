// package Algorithm.Algorithm25.Java.BOJ2011;

import java.util.Scanner;

// 2011. 암호코드
// https://www.acmicpc.net/problem/2011
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String code = sc.nextLine();
		int len = code.length();
		final int MOD = 1000_000;

		// dp[i] = i번째 코드까지의 해석의 가짓수 (1-base)
		int[] dp = new int[len + 1];
		dp[0] = 1;

		for (int i = 1; i <= len; i++) {

			int cur = code.charAt(i - 1) - '0';

			// 따로 추가되는 경우 - ex) 52, 1
			if (cur != 0) dp[i] = (dp[i] + dp[i - 1]) % MOD;

			if (i == 1) continue;
			int last = code.charAt(i - 2) - '0';
			if (last == 0) continue;

			// 직전 값과 연결되는 경우 - ex) 5, 21
			int v = last * 10 + cur;
			if (10 <= v && v <= 26) dp[i] = (dp[i] + dp[i - 2]) % MOD;

		}

		System.out.println(dp[len]);
	}
}
