package Algorithm.Algorithm25.Java.PRGMS_KAKAO_TwoQueSum;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] queue1 = {3, 2, 7, 2};
		int[] queue2 = {4, 6, 5, 1};

		// int[] queue1 = {1, 2, 1, 2};
		// int[] queue2 = {1, 10, 1, 2};

		// int[] queue1 = {1, 1};
		// int[] queue2 = {1, 5};

		// int[] queue1 = {1, 1, 1, 1};
		// int[] queue2 = {1, 1, 7, 1};
		// answer : 9

		int ret = solution.solution(queue1, queue2);
		System.out.println(ret);
	}
}

/**
 * (pop + insert)를 반복해 각 큐의 합이 같도록 만드는 최소 횟수 = ? (불가능 시 -1)
 * 먼저 두 큐의 합의 평균 = 합 타겟 (큐의 길이: 300,000) (원소: 10^9)
 *
 */
class Solution {

	public int solution(int[] queue1, int[] queue2) {
		int answer = 0;
		long sum1 = Arrays.stream(queue1).asLongStream().sum(), sum2 = Arrays.stream(queue2).asLongStream().sum();
		Queue<Integer> q1 = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();
		q1.addAll(Arrays.stream(queue1).boxed().collect(Collectors.toList()));
		q2.addAll(Arrays.stream(queue2).boxed().toList());

		if (((sum1 + sum2) & 1) == 1) return -1;

		while (sum1 != sum2){
			if (sum1 > sum2) {
				int x = q1.poll();
				q2.add(x);
				sum1 -= x;
				sum2 += x;
			}
			else {
				int x = q2.poll();
				q1.add(x);
				sum2 -= x;
				sum1 += x;
			}
			answer++;
			if (answer > (queue1.length+queue2.length)*2) return -1;
		}

		return answer;
	}
}