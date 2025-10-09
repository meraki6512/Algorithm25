package Algorithm.Algorithm25.Java.PRGMS_KAKAO_Delivery;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * n개의 집 (택배창고:0, 집들 i~j, 거리 1씩) (100,000)
 * 트럭에 최대 cap개 상자 (50)
 * 각 집마다 배달(deliveries), 수거(pickups) 개수 리스트가 주어짐
 * 최소 이동 거리 = ?
 */
public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();

		// 16
		int cap = 4;
		int n = 5;
		int[] deliveries = {1, 0, 3, 1, 2};
		int[] pickups = {0, 3, 0, 4, 0};
		System.out.println(solution.solution(cap, n, deliveries, pickups));

		// 30
		int cap2 = 2;
		int n2 = 7;
		int[] deliveries2 = {1, 0, 2, 0, 1, 0, 2};
		int[] pickups2 = {0, 2, 0, 1, 0, 2, 0};

		System.out.println(solution.solution(cap2, n2, deliveries2, pickups2));
	}
}

/**
 * 가장 멀리서부터 cap만큼 처리 (배달 한 번, 수거 한 번) (제일 멀리 간 위치의 2배만큼 ans에 더해주면 됨)
 * 갈 때 배달
 * 올 때 수거
 */
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