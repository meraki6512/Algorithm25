package Algorithm.Algorithm25.Java.BOJ2133;

import java.util.Scanner;

/**
 * <a href="https://www.acmicpc.net/problem/2133">2133. 타일 채우기</a>
 */

// 일단 홀수는 안됨
// dp[N] = ... dp[N-2] 상태에서 3x2짜리가 하나 더 생길 경우
// 기본 3x2 -> 3개

// N=4부터 특수한 케이스가 발생 -> 이 특수한 케이스가 무조건 두 경우씩밖에 안 나타남 (* 이게 포인트)

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] dp = new int[N+1];

		if (N%2 != 0) {
			System.out.println(0);
			return;
		}

		dp[0] = 1;	// 라인이 없는 경우 1가지
		dp[2] = 3;	// 기본값 3x2: 한 번에 추가될 때 2라인씩 추가됨

		for (int i = 4; i <= N; i+=2) {

			// 1) 기본 3x2가 추가되는 경우
			dp[i] = dp[i-2] * dp[2];

			// 2) 특수한 케이스가 추가되는 경우
			for (int j = i-4; j >= 0; j-=2) {
				dp[i] += dp[j] * 2;
			}

		}

		System.out.println(dp[N]);
	}
}