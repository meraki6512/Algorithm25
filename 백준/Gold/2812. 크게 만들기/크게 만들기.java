import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * <h3>812. 크게 만들기</h3>
 * <a href="https://www.acmicpc.net/problem/2812">...</a>
 * N자리 숫자가 주어졌을 때 숫자 K개를 지워서 얻을 수 있는 가장 큰 수를 구하라. (1 <= K < N <= 500,000)
 */
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		char[] num = sc.next().toCharArray(); 	// 0으로 시작하지 않는 N자리 숫자

		Solution solution = new Solution();
		// System.out.println(Solution.wrongSol(N, K, num));
		System.out.println(solution.sol(N, K, num));
	}
}

class Solution {
	/**
	 * 스택 + 그리디 (O(N))
	 * 1. 결과를 담을 스택 생성
	 * 2. 왼쪽부터 차례대로 순회하면서, 현재 숫자를 스택의 top과 비교
	 * 	   top < 현재 숫자: pop(), K--
	 * 	   K가 0이 되거나, top이 현재 숫자보다 크거나 같아질 때까지 반복
	 * 3. K가 남아있다면, 남은 K만큼 스택에서 pop
	 * 4. 스택에 남은 숫자들을 순서대로 반환
	 */
	public String sol(int N, int K, char[] num){
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < N; i++) {	// 500_000
			while (K > 0 && !stack.isEmpty() && stack.peek() < num[i]) {
				stack.pop();
				K--;
			}
			stack.push(num[i]);
		}

		while (K-- > 0) stack.pop();

		return stack.stream().map(String::valueOf).collect(Collectors.joining());
	}

	/**
	 * 그리디
	 * 1. 각각의 자릿수의 숫자에 (큰 순서대로) 순서번호 order를 매김
	 * 2. 앞에서부터 차례대로 루프: 각 자릿수를 아래의 로직으로 확인함; 남은 제거할 문자 수 cnt = K에서 시작
	 * 		1) 순서번호가 K 순위 밖이면, 제거할 문자: cnt--
	 * 		2) 순서번호가 K 순위 내면, sb.append(현재문자)
	 * 		- cnt가 0이면 더이상 제거할 문자가 남지 않았으므로 남은 문자들을 순서대로 sb.append(현재문자)
	 */
	public String wrongSol(int N, int K, char[] num){
		// 순서 구하기
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i< N; i++) 				// 500_000
			pq.add(new Node(num[i]-'0', i));

		Node[] order = new Node[N];
		for (int i = 0; i < N && !pq.isEmpty(); i++) {	// 500_000
			Node n = pq.poll();
			order[n.i] = new Node(n.v, i);
		}

		// K 순위 밖의 문자를 앞에서부터 K개 제외하기
		StringBuilder sb = new StringBuilder();
		int cnt = K;
		for (int i = 0; i < N; i++){			// 500_000
			if (cnt > 0) {
				if (order[i].i >= K) cnt--;
				else sb.append(num[i] - '0');
			}
			else {
				sb.append(num[i] - '0');
			}
		}

		return sb.toString();
	}

	private static class Node implements Comparable<Node> {
		int v, i;
		public Node(int v, int i) {
			this.v = v;
			this.i = i;
		}

		@Override
		public int compareTo(Node o) {
			return (o.v==v) ? (i - o.i) : (o.v-v);
		}
	}
}