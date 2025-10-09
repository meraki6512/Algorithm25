package Algorithm.Algorithm25.Java.PRGMS_KAKAO_KakaoTalkPresent;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		String[] friends1 = {"muzi", "ryan", "frodo", "neo"};
		String[] gifts1 = {"muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"};

		String[] friends2 = {"joy", "brad", "alessandro", "conan", "david"};
		String[] gifts2 = {"alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"};

		String[] friends3 = {"a", "b", "c"};
		String[] gifts3 = {"a b", "b a", "c a", "a c", "a c", "c a"};

		Solution solution = new Solution();
		System.out.println(solution.solution(friends1, gifts1));
		System.out.println(solution.solution(friends2, gifts2));
		System.out.println(solution.solution(friends3, gifts3));
	}
}

/**
 * 이번 달까지 선물 더 많이 준 사람 -> 더 적게 준 사람에게 줌
 * 같다면, 선물 지수(친구들에게 선물 준 - 받은 수)가 더 높은 사람
 * 같다면, 주고받지 않음.
 * 가장 선물을 많이 받을 친구가 받을 선물의 수 = ?
 *
 * friends: 친구들 이름(10): 50
 * gifts: A B: 10_000
 */
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