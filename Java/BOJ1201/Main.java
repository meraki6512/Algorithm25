package Algorithm.Algorithm25.Java.BOJ1201;

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
		System.out.println(sol.solution_other(N, M, K));
	}
}

class Solution {

	// 동적으로 그룹 내 숫자 수 LDS 이내로 계산함
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

	// 첫 그룹으로 LDS 보장 후 남은 개수로 그룹 수 계산부터 하고 분배함
	public String solution_other(int N, int M, int K) {
		if ((M + K - 1 > N) || (N > M * K))  return "-1"; // (M-1)+1+(K-1) <= N <= M*K

		StringBuilder sb = new StringBuilder();
		for (int i = K; i > 0; i--) sb.append(i).append(" "); // 첫 그룹 먼저 만들기 (LDS: K)

		int rN = N - K;		// 남은 숫자 수
		int rM = M - 1;		// 남은 그룹 수
		int x = K + 1;		// 다음 시작 숫자

		if (rM > 0) {
			int grp = rN / rM;	// 균등하게 분배했을 때, 각 그룹의 숫자 수
			int rem = rN % rM;	// 나머지: 앞 그룹부터 하나씩 나눠줄 거

			for (int i = 0; i < rM; i++) {
				int curGrp = grp + (rem-- > 0 ? 1 : 0);	// (나머지가 있으면 개수 추가함) 현재 그룹의 숫자 수
				x += curGrp;
				for (int j = x - 1; j >= x - curGrp ; j--) sb.append(j).append(" "); // curGrp 크기만큼 감소하게 넣음
			}
		}

		return sb.toString();
	}
}
