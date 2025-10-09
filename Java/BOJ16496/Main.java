package Algorithm.Algorithm25.Java.BOJ16496;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/16496">16496. 큰 수 만들기</a>
 * <p>N개의 수를 나열해 만들 수 있는 가장 큰 수를 구하라.</p>
 * <ul><li>1 <= N <= 1000</li><li>0 <= num <= 1_000_000_000</li></ul>
 */
public class Main {

	// -> 그냥 문자열 정렬 문제

	// Argument Exchange
	// a, b, c
	// a > b, a > c, b > c, ->  a > b > c
	// 귀납적 증명

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		String[] nums = new String[N];
		for(int i=0; i<N; i++) nums[i] = st.nextToken();

		Arrays.sort(nums, (s1, s2) -> (s2 + s1).compareTo(s1 + s2)); // DESC

		if (nums[0].equals("0")) System.out.println("0");
		else System.out.println(String.join("", nums));

	}
}

/*
// 위와 동일한 방식이지만 아래는 Node를 만들고 PriorityQueue 사용해 계산함
// 위의 방식이 람다식으로 조금 더 효율적인 코드. (시간복잡도는 동일)

public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Node> nums = new PriorityQueue<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) nums.add(new Node(st.nextToken()));

		StringBuilder sb = new StringBuilder();
		while(!nums.isEmpty()) sb.append(nums.poll().n);

		String ans = sb.toString();
		System.out.println(ans.charAt(0) == '0' ? "0" : ans);

	}
}

class Node implements Comparable<Node> {
	String n;
	public Node(String n) {
		this.n = n;
	}

	@Override
	public int compareTo(Node o) {
		StringBuilder sbAB = new StringBuilder(), sbBA = new StringBuilder();

		sbAB.append(n).append(o.n);
		sbBA.append(o.n).append(n);

		return sbBA.compareTo(sbAB); // DESC
	}
}
 */