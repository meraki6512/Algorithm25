import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {

	public int solution(String[] friends, String[] gifts) {
		int answer = 0;

		// 50
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < friends.length; i++) {
			map.put(friends[i], i);
		}

		// 10_000
		int[][] cur = new int[friends.length][friends.length];
		StringTokenizer st;
		for (String gift : gifts) {
			st = new StringTokenizer(gift, " ");
			int from = map.get(st.nextToken());
			int to = map.get(st.nextToken());
			cur[from][to]++;
		}

		// 50 * 50
		int[] presentIdx = new int[friends.length];
		for (int i = 0; i < friends.length; i++) {
			for (int j = 0; j < friends.length; j++) {
				presentIdx[i] += (cur[i][j] - cur[j][i]);
			}
		}

		// 50 * 50
		int[] nxt = new int[friends.length];
		for (int i = 0; i < friends.length; i++) {
			for (int j = i+1; j < friends.length; j++) {
				if (cur[i][j] > cur[j][i]) { // i->j가 더 많으면 다음에 i가 받음
					nxt[i]++;
				}
				else if (cur[i][j] < cur[j][i]) { // j->i가 더 많으면 다음에 j가 받음
					nxt[j]++;
				}
				else if (presentIdx[i] > presentIdx[j]) { // i의 선물지수가 더 높으면 다음에 i가 받음
					nxt[i]++;
				}
				else if (presentIdx[i] < presentIdx[j]) { // j의 선물지수가 더 높으면 다음에 j가 받음
					nxt[j]++;
				}
			}
		}

		// 50
		for (int n : nxt){
			answer = Math.max(answer, n);
		}

		return answer;
	}
}