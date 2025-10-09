package Algorithm.Algorithm25.Java.BOJ2138;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 2138. 전구와 스위치
// https://www.acmicpc.net/problem/2138
// 어디서부터 바꾸든 최종 형태로 만드는 최소 횟수는 정해져있음
// 앞에서부터 처리하면 됨
// 이전 값을 보고 현재 값을 바꿀지 말지

public class Main {

	private static final int INF = Integer.MAX_VALUE;
	private static int N;
	private static int[] toBe;

	public static void main(String[] args) throws IOException {
		// in
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String sc = br.readLine();
		String st = br.readLine();
		int[] cur1 = new int[N], cur2 = new int[N];
		toBe = new int[N];
		for (int i = 0; i < N; i++) {
			cur1[i] = cur2[i] = sc.charAt(i) - '0';
			toBe[i] = st.charAt(i) - '0';
		}

		// 맨 처음 토글 안하는 경우와 하는 경우를 나눠 2개 케이스를 계산
		int res1 = solve(cur1, 0);
		toggle(cur2, 0);
		int res2 = solve(cur2, 1);

		// out
		int ans = Math.min(res1, res2);
		System.out.println(ans == INF ? -1 : ans);
	}

	private static int solve(int[] cur, int cnt) {
		// i-1 위치의 값이 다르면 toggle(cur, i)
		for (int i = 1; i < N; i++) {
			if (cur[i-1] != toBe[i-1]) {
				toggle(cur, i);
				cnt++;
			}
		}
		// N-1 위치의 값 (마지막 값)이 다르면 실패
		if (cur[N-1] != toBe[N-1]) return INF;
		else return cnt;
	}

	private static void toggle(int[] cur, int idx) {
		for (int i = idx-1; i <= idx+1; i++) {
			if (i >=0 && i < N){
				cur[i] ^= 1;
			}
		}
	}
}
