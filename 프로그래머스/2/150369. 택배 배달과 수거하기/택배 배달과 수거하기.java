import java.util.*;

class Solution {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		// 각 최대 10만개
		PriorityQueue<Integer> pqDel = new PriorityQueue<>((a, b) -> b - a);
		PriorityQueue<Integer> pqPick = new PriorityQueue<>((a, b) -> b - a);
		for (int i = 0; i < deliveries.length; i++) {
			for (int j = 0; j < deliveries[i]; j++) {
				pqDel.add(i+1);
			}
		}
		for (int i = 0; i < pickups.length; i++) {
			for (int j = 0; j < pickups[i]; j++) {
				pqPick.add(i+1);
			}
		}

		// 멀리서부터 처리
		while (!(pqDel.isEmpty() && pqPick.isEmpty())) {
			int tmp = 0;
			for (int i = 0; i < cap && !pqDel.isEmpty(); i++) {
				tmp = Math.max(tmp, pqDel.poll());
			}
			for (int i = 0; i < cap && !pqPick.isEmpty(); i++) {
				tmp = Math.max(tmp, pqPick.poll());
			}
			answer += tmp* 2L;
		}

		return answer;
	}
}