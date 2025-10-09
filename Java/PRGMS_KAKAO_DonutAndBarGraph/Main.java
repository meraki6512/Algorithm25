package Algorithm.Algorithm25.Java.PRGMS_KAKAO_DonutAndBarGraph;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {

		int[][] edges = {{2, 3}, {4, 3}, {1, 1}, {2, 1}}; // [2, 1, 1, 0]
		int[][] edges2 = {
			{4, 11}, {1, 12}, {8, 3}, {12, 7},
			{4, 2}, {7, 11}, {4, 8}, {9, 6},
			{10, 11}, {6, 10}, {3, 5}, {11, 1},
			{5, 3}, {11, 9}, {3, 8}
		}; // [4, 0, 1, 2]

		Solution solution = new Solution();
		System.out.println(Arrays.toString(solution.solution(edges)));
	}
}

class Solution {

	/**
	 * 1. 임의의 정점 v: 들어오는 간선이 0이고 나가는 간선만 2개 이상인 노드
	 * 2. 도넛 그래프의 수: v에서 나가는 간선의 개수 - 막대그래프 수, 8자그래프 수
	 * 3. 막대 그래프의 수: 들어오는 간선이 1이상이고 나가는 간선이 0인 노드 수 (tail 노드)
	 * 4. 8자 그래프의 수: 들어오는 간선이 2이상이고 나가는 간선도 2이상인 노드 수 (center 노드)
	 */
	public int[] solution(int[][] edges) {

		int INF = 1_000_001;
		int[] in = new int[INF];
		int[] out = new int[INF];
		// int n = 0;

		for (int[] edge : edges) {
			// n = Math.max(Math.max(edge[0], edge[1]), n);
			out[edge[0]]++;
			in[edge[1]]++;
		}

		int v = -1, bar = 0, eight = 0;
		// for (int i = 0; i <= n; i++) {
		for (int i = 0; i < INF; i++) {
			if (in[i] == 0 && out[i] >= 2) v = i;
			if (in[i] >= 1 && out[i] == 0) bar++;
			else if (in[i] >= 2 && out[i] == 2) eight++;
		}

		return new int[] {v, (out[v] - bar - eight), bar, eight};
	}
}
