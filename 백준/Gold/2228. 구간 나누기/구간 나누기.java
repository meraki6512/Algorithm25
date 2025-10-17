// package Algorithm.Algorithm25.Java.BOJ2228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/2228">2228. 구간 나누기</a>
 */
public class Main {

	private static final int INF = 4_000_000;
	private static int N, M;
	private static int[] nums, pref;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		nums = new int[N+1];
		pref = new int[N+1];
		dp = new int[N+1][M+1];
		for (int i = 1; i <= N; i++) nums[i] = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) pref[i] = pref[i-1] + nums[i];

		for (int i = 0; i <= N; i++) {
			for (int j = 1; j <= M; j++) {	// 0 구간 선택 시 합은 0
				dp[i][j] = -INF;
			}
		}

		System.out.println(solve(N, M));
	}

	// dp[][] 정의: N개의 수, M개의 구간
	// -> i번째 수까지 사용했을 때, j개의 구간을 선택한 경우의 구간합의 최댓값

	// i번째 수 포함O: dp[i-1][j]
	// i번째 수 포함X: i번째 수를 j번째 구간의 끝으로 포함시켜야 함
				// 모든 가능한 k에 대해, k는 j번째 구간의 시작
				// sum(k, i): 구간합
				// k가 j번째 구간의 시작이라면 j-1번째 구간은 k-2 이전에 있음 (인접X)
				// -> dp[k-2][j-1] + sum(k, i)
				// 모든 가능한 k에 대해 최댓값을 구해야 함

	private static int solve(int N, int M) {

		// j: 구간 개수
		for (int j = 1; j <= M; j++) {
			int max = -INF;

			// i: 현재 숫자의 인덱스
			for (int i = 2*j-1; i <= N; i++) {
				// 포함O
				//: 구간 개수 유지
				dp[i][j] = dp[i-1][j];

				// 포함X
				//: 모든 가능한 k에 대해 최댓값을 구함
				// dp[k-2][j-1] + pref[i] - pref[k-1]
				if (i >= 2) max = Math.max(max, dp[i-2][j-1] - pref[i-1]);
				else max = Math.max(max, -pref[i - 1]); // i-2<0
				//: i번째 수를 j번째 구간의 끝으로 포함시킴: 언제 구간합이 최대가 되는지
				if (max != -INF) dp[i][j] = Math.max(dp[i][j], pref[i] + max);
			}
		}

		return dp[N][M];
	}

}

