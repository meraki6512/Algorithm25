package Algorithm.Algorithm25.Java.BOJ1513;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/1513">1513. 경로 찾기</a>
 * 집 (1,1)
 * 학원 (N,M)
 * 오락실 C개
 * 우하측으로만 이동 가능, 오락실 번호 증가하는 순서대로만 이동 가능
 * 오락실을 0, 1, ..., C개 방문하는 경로의 경우의 수 = ?
 * ...
 * -> 설명 들은 대로 구현함. 추후 다른 방법 생각해보기 + 찾아보기
 */
public class Main {

	private static final int MOD = 1_000_007;

	private static class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int C = sc.nextInt();

		// dp[i][j][k][prev]: x, y 위치이고 k개 방문, 직전 방문한 위치는 prev
		int[][][][] dp = new int[N + 1][M + 1][C + 1][C + 1];
		Point[] arcades = new Point[C + 1];
		boolean[][] isArcade = new boolean[N + 1][M + 1];
		for (int k = 1; k <= C; k++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			arcades[k] = new Point(i, j);
			isArcade[i][j] = true;
		}

		// 1. 0개 방문하는 경우
		dp[1][0][0][0] = 1;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (!isArcade[i][j]) {
					dp[i][j][0][0] = (dp[i - 1][j][0][0] + dp[i][j - 1][0][0]) % MOD;
				}
			}
		}

		// 2. k개(1개 이상) 방문하는 경우: 먼저 k개 방문 처리 후 오락실로부터 도착지점까지 0개 방문 처리
		for (int k = 1; k <= C; k++) { // 방문 오락실 개수
			for (int p = k; p <= C; p++) { // 직전 방문한 오락실 번호 (>=k)

				Point arcade = arcades[p];
				int x = arcade.x;
				int y = arcade.y;

				// 1) 먼저 k개 방문함
				// dp[x][y][k개][직전p] = dp[x-1][y][k-1][k-1~p-1] + dp[x][y-1][k-1][k-1~p-1]
				for (int i = k - 1; i < p; i++){
					dp[x][y][k][p] += (dp[x-1][y][k-1][i] + dp[x][y-1][k-1][i]) % MOD;
				}

				// 2) 이후 0개 방문함
				for (int i = x; i <= N; i++){
					for (int j = y; j <= M; j++){
						if (!isArcade[i][j]) {
							dp[i][j][k][p] = (dp[i - 1][j][k][p] + dp[i][j - 1][k][p]) % MOD;
						}
					}
				}
			}
		}

		// 출력
		StringBuilder sb = new StringBuilder();
		for (int k = 0; k <= C; k++){ // 0, 1, ..., C개에 대해 출력
			int sum = 0;
			for (int p = k; p <= C; p++) { //: 각각, 모든 마지막 오락실 번호의 dp 값을 더함
				sum = (sum + dp[N][M][k][p]) % MOD;
			}
			sb.append(sum).append(" ");
		}
		System.out.println(sb);
	}
}
