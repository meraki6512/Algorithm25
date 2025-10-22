//package Algorithm.Algorithm25.Java.BOJ9465;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for(int tc=1;tc<=T;tc++) {

			int n = Integer.parseInt(br.readLine());
			int[][] scores = new int[2][n+1];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {
					scores[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[][] dp = new int[2][n+1];
			dp[0][1] = scores[0][1];
			dp[1][1] = scores[1][1];

			for (int j = 2; j <= n; j++) {
				for (int i = 0; i < 2; i++) {
					dp[i][j] = scores[i][j] + Math.max(dp[1-i][j-1], Math.max(dp[0][j-2], dp[1][j-2]));
				}
			}

			System.out.println(Math.max(dp[0][n], dp[1][n]));
		}
	}
}
