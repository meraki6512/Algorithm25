package Algorithm.Algorithm25.Java.BOJ2229;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/2229">2229. 조 짜기</a>
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1], arr = new int[N+1];
		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1];
			for (int j = i - 1; j >= 1; j--){
				dp[i] = Math.max(dp[i], Math.abs(arr[i] - arr[j]) + dp[j-1]);
			}
		}

		System.out.println(dp[N]);

	}
}
