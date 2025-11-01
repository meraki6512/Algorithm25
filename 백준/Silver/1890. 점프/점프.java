// package Algorithm.Algorithm25.Java.BOJ1890;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/1890">1890. 점프</a>
 * 각 칸에 현재 칸에서 이동할 거리가 적혀 있는 게임판
 * 우하측으로만 한 번에 한 방향으로 이동
 * 0 종착점
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] board = new int[N+1][N+1];

		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		long[][] dp = new long[N+1][N+1];
		dp[1][1] = 1;

		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				// 경로가 있고, 도착 지점이 아닐 경우에만
				if (dp[i][j] != 0 && !(i == N && j == N)) {
					int v = board[i][j];
					if (i + v <= N) dp[i + v][j] += dp[i][j];
					if (j + v <= N) dp[i][j + v] += dp[i][j];
				}
			}
		}

		System.out.println(dp[N][N]);
	}
}
