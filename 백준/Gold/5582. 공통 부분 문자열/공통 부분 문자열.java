// package Algorithm.Algorithm25.Java.BOJ5582;

import java.util.Scanner;

/**
 * 가장 긴 공통 부분 문자열의 길이를 출력하라
 * <a href="https://www.acmicpc.net/problem/5582">5582. 공통 부분 문자열</a>
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s1 = sc.nextLine(); // 4000
		String s2 = sc.nextLine(); // 4000

		int ans = 0;
		int[][] dp = new int[s1.length()+1][s2.length()+1];

		for(int i=1; i<=s1.length(); i++) {
			for(int j=1; j<=s2.length(); j++) {
				if(s1.charAt(i-1) == s2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
					ans = Math.max(ans, dp[i][j]);
				}
			}
		}

		System.out.println(ans);

	}
}
