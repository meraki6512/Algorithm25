package Algorithm.Algorithm25.Java.BOJ14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/14719">14719. 빗물</a>
 */
public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] blocks = new int[W+1];
		for(int i=1;i<=W;i++) blocks[i] = Integer.parseInt(st.nextToken());

		// 고이는 빗물의 총량 = ?
		int ans = 0;
		Stack<Integer> stack = new Stack<>();

		for (int h = 1; h <= H; h++) {

			for (int w = 1; w <= W; w++) {
				if (blocks[w] >= h)
					stack.push(w);
			}

			if (stack.isEmpty()) continue;
			int cur = stack.pop();

			while (!stack.isEmpty()){
				int nxt = stack.pop();
				ans += cur - nxt - 1; 	// stack -> 인덱스 큰 것부터 들어감
				cur = nxt;
			}

		}

		System.out.println(ans);
	}
}
