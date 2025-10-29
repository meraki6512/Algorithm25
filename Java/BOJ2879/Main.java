package Algorithm.Algorithm25.Java.BOJ2879;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/2879">2879. 코딩은 예쁘게</a>
 * abs(sub[i])가 min인 것부터 순차적으로 처리하면 됨
 * 언제까지? 모든 값이 0이 될 때까지
 */

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] sub = new int[N + 2];
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) sub[i] -= Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) sub[i] += Integer.parseInt(st.nextToken());

		int ans = 0;
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = 1; i <= N; i++) {
				if (sub[i] == 0) continue; // 모든 값이 0이 되면 최종 통과
				flag = true;
				int min = Math.abs(sub[i]);

				for (int j = i + 1; j <= N + 1; j++) { // i부터 다른 부호 직전까지 연속으로 처리
					if (sub[i] * sub[j] > 0) { // same sign
						min = Math.min(min, Math.abs(sub[j]));
					}
					else { // diff sign
						ans += min;
						min = sub[i] > 0 ? min : -min; // +였으면 빼줘야하고 -였으면 더해줘야하니까
						for (int k = i ; k < j; k++) sub[k] -= min;
						i = j-1;
						break;
					}
				}
			}
		}

		System.out.println(ans);
	}
}
