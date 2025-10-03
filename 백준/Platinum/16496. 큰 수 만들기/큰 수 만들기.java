// package Algorithm.Algorithm25.Java.BOJ16496;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N개의 숫자 리스트에서 수를 나열해 만들 수 있는 가장 큰 수를 구하라.
 * (1 <= N <= 1000), (0 <= num <= 1_000_000_000)
 */
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