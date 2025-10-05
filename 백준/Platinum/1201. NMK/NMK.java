// package Algorithm.Algorithm25.Java.BOJ1201;

import java.util.Scanner;

/**
 * <h1><a href="https://www.acmicpc.net/problem/1201">1201. NMK</a></h1>
 * 1~N을 한 번씩 이용해
 * 가장 긴 증가하는 부분 수열 LIS의 길이가 M,
 * 가장 긴 감소하는 부분 수열 LDS의 길이가 K인 수열을 출력하라. (없으면 -1 출력)
 * <ul><li>1 ≤ N ≤ 500</li><li>1 ≤ M, K ≤ N</li></ul>
 * <h2> Erdos-Szekeres 이론 </h2>
 * <p>길이가 M*K+1인 수열엔 반드시 길이가 M+1인 LIS 또는 길이가 K+1인 LDS가 존재한다.</p>
 * <ul><li>(M-1)+1+(K-1) <= N <= M*K</li></ul>
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();

		Solution sol = new Solution();
		System.out.println(sol.solution(N, M, K));
	}
}

class Solution {
	public String solution(int N, int M, int K) {

		if ((M + K - 1 > N) || (N > M * K))  return "-1"; // (M-1)+1+(K-1) <= N <= M*K

		StringBuilder sb = new StringBuilder();
		int cnt = 0;

		while (true) {
			for (int i = K; i > 0; i--) sb.append(cnt+i).append(" ");

			N-=K;		// 숫자 K개 처리 완료
			cnt += K;
			M--;		// 그룹 1개 처리 완료
			if (M == 0) break;

			K = N/M;	// 다음 그룹의 수 개수 K 구하기
		}

		return sb.toString();
	}
}
