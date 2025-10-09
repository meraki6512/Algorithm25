package Algorithm.Algorithm25.Java.BOJ12865;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <h3><a href="https://www.acmicpc.net/problem/12865">12865. 평범한 배낭</a></h3>
 * <ul><li>1 ≤ N ≤ 100</li><li>1 ≤ K ≤ 100,000</li><li>1 ≤ W ≤ 100,000</li></ul>
 */

// N개의 물건
// 각 물건은 무게 W와 가치 V를 가짐
// 최대 무게 수용량은 K

// 무게가 x일 때의 가치 dp[x]
// w, v -> dp[] = Math.max(
//					유지하는 경우의 가치,
// 					현재 물건을 넣어 가치를 늘리고 무게를 늘리는(수용량을 줄이는) 경우의 가치)

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); 	// 100
		int K = Integer.parseInt(st.nextToken());	// 100_000
		int[] dp = new int[K+1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken()); 	// w: 100_000
			int v = Integer.parseInt(st.nextToken());	// v: 1000

			// j 수용량의 가방의 가치
			for (int j = K; j >= w; j--) dp[j] = Math.max(dp[j], dp[j-w] + v);
		}

		System.out.println(dp[K]);
	}
}