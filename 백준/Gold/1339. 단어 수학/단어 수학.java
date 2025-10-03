// package Algorithm.Algorithm25.Java.BOJ1339;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <a href="https://www.acmicpc.net/problem/1339">1339. 단어 수학</a>
 * 알파벳 대문자로만 이뤄진 단어 N개 리스트가 있을 때,
 * 각 알파벳 대문자를 0부터 9까지의 숫자 중 하나로 바꾸고,
 * N개의 수의 합의 최댓값을 구하라.
 * : 문자열 비교할 지, 숫자로 비교할 지 방향성을 잘 잡아야 함 -> 숫자 비교가 훨씬 간단.
 */
public class Main {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		String[] words = new String[N];
		for(int i=0; i<N; i++) words[i] = sc.next();

		Map<Character, Integer> map = new HashMap<>(); // 각 Char에 얼마만큼의 값이 곱해지는지
		for(String word : words) {
			int len = word.length();

			for (int j=0; j<len; j++) {
				char c = word.charAt(j);
				map.put(c, map.getOrDefault(c, 0) + (int)Math.pow(10, len-1-j));
			}
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		pq.addAll(map.values());

		int num = 9, ans = 0;
		while (!pq.isEmpty()) ans += pq.poll() * num--;

		System.out.println(ans);
	}
}
