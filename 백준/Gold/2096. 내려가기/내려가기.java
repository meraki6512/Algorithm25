// package Algorithm.Algorithm25.Java.BOJ2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/2096">2096. 내려가기</a>
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N+1][4];
		int[][] max_dp = new int[N+1][4];
		int[][] min_dp = new int[N+1][4];

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= 3; i++) {
			max_dp[0][i] = 0;
			min_dp[0][i] = 0;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= 3; j++) {
				max_dp[i][j] = max_dp[i-1][j];
				min_dp[i][j] = min_dp[i-1][j];
				if (j-1 > 0) {
					max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i-1][j-1]);
					min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i-1][j-1]);
				}
				if (j+1 <= 3) {
					max_dp[i][j] = Math.max(max_dp[i][j], max_dp[i-1][j+1]);
					min_dp[i][j] = Math.min(min_dp[i][j], min_dp[i-1][j+1]);
				}

				max_dp[i][j] += arr[i][j];
				min_dp[i][j] += arr[i][j];
			}
		}

		int max = max_dp[N][1];
		int min = min_dp[N][1];
		for (int i = 2; i <= 3; i++) {
			max = Math.max(max, max_dp[N][i]);
			min = Math.min(min, min_dp[N][i]);
		}
		System.out.println(max + " " + min);
	}
}
