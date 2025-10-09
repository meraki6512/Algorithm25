package Algorithm.Algorithm25.Java.BOJ1328;

import java.util.Scanner;

/**
 * <h3><a href="https://www.acmicpc.net/problem/1328">1328. 고층 빌딩</a></h3>
 * <ul><li>1 <= N <= 100</li></ul>
 *
 * n이 추가될 때마다 높은 빌딩을 추가하는 방식으로 접근하면 경우의 수가 너무 많음
 * 반대로 가장 작은 빌딩을 추가하는 방식이 필요함
 * 즉, n번째 차례라고 할 때 높이가 n인 빌딩이 추가되는게 아니고 2~n짜리 빌딩이 있을 때 1짜리 빌딩이 추가되는 경우의 수를 체크하는 방식
 *
 * 어떤 분은 빌딩이 아닌 나무로 생각하면 더 쉽다고, 아래와 같은 예를 들어주셨음.
 * "나무들은 한 달 마다 길이가 1씩 자란다. 매달 길이가 1인 새로운 나무를 무작위 위치로 심는다."
 *
 * 빌딩이 n개고 왼쪽 l개 오른쪽 r개가 보일 때
 * dp[n][l][r]
 * 		= dp[n-1][l-1][r]		// 오른쪽에 가장 작은 빌딩을 추가하면 오른쪽 수(r)만 커지되 경우의 수는 그대로 가져올 수 있음
 * 		+ dp[n-1][l][r-1]		// 왼쪽에 가장 작은 빌딩을 추가하면 왼쪽 수(l)만 커지되 경우의 수는 그대로 가져올 수 있음
 * 		+ dp[n-1][l][r]*(n-2)	// 양 끝 두 개를 제외하고 사이에 가장 작은 빌딩을 추가히면, r이나 l이 변하지 않고 경우의 수도 그대로 가져올 수 있음
 * dp[3][2][2]
 * 		= dp[2][1][2]
 * 		+ dp[2][2][1]
 * 		+ dp[2][2][2] * (3-2)
 */

public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		final int INF = 101, MOD = 1_000_000_007;
		long[][][] dp = new long[INF][INF][INF];
		int N = sc.nextInt();						// 100까지
		int L = sc.nextInt();
		int R = sc.nextInt();

		dp[1][1][1] = 1;
		for (int h = 2; h <= N; h++) {
			dp[h][h][1] = dp[h][1][h] = 1; 			// 양끝에 1개만 보이는 경우는 무조건 1개 있음

			for (int l = 1; l <= L; l++) {
				for (int r = 1; r <= R; r++) {
					dp[h][l][r] = (dp[h-1][l-1][r] + dp[h-1][l][r-1] + dp[h-1][l][r]*(h-2)) % MOD;
				}
			}
		}

		System.out.println(dp[N][L][R]);
	}
}


