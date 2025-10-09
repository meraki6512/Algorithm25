package Algorithm.Algorithm25.Java.PRGMS_KAKAO_Np1CardGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 프로그래머스 n + 1 카드게임
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/258707?language=java">...</a>
 * 1~n(6의 배수, 6~1000) 카드 뭉치와 coin(0~n)개의 동전
 * 1. n/3장을 뽑는다.
 * 2. 각 라운드 시작 시 카드 2장을 뽑는다. (뽑을 카드가 없다면 종료한다.)
 * 3. 뽑은 카드는 1:1로 동전 소모해 갖거나, 동전 소모하지 않고 버릴 수 있다.
 * 4. 두 카드의 합이 n+1이 되도록 2장을 제출하고 다음 라운드로 진행한다. (뽑을 카드가 없다면 종료한다.)
 * ex.
 * n = 12, coin = 4이고 [3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4] 순서대로 카드 뭉치가 있으면,
 * [3, 6, 7, 2] - 1, 10 - 코인2 - [3, 6, 7, 2, 1, 10] -> 3, 10
 * [1, 2, 6, 7] - 5, 9 - 코인0 - [1, 2, 6, 7] -> 6, 7
 * [1, 2] - 8, 12 - 코인1 - [1, 2, 12] -> 1, 12
 * [2] - 11, 4 - 코인1 - [2, 11] -> 2, 11
 * [] - 종료
 *
 * dfs
 */
public class Wrong {
	public static void main(String[] args) {
		Solution solution = new Solution();
		// int coin = 4;
		// int[] cards = {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4};

		// int coin = 3;
		// int[] cards = {1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12};

		int coin = 2;
		int[] cards = {5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7};

		System.out.println(solution.solution(coin, cards));
	}
}

class SolutionWrong {

	private int n, ans = 0;
	private StringBuilder sb = new StringBuilder();

	public int solution(int coin, int[] cards) {
		n = cards.length;

		Queue<Integer> cardQue = new LinkedList<>();
		for (int i=0; i<cards.length; i++) {
			cardQue.offer(cards[i]);
		}
		Set<Integer> hand = new HashSet<>();
		for (int i=0; i<n/3; i++) hand.add(cardQue.poll());

		Set<Integer> sumEqualsToNPlus1 = new HashSet<>();
		for (int i=0; i<n/3; i++) {
			for (int j=i+1; j<n/3; j++) {
				if (cards[i] + cards[j] == n + 1) {
					sumEqualsToNPlus1.add(Math.min(cards[i], cards[j]));
				}
			}
		}


		dfs(cardQue, hand, sumEqualsToNPlus1, coin, 1);
		System.out.println(sb);

		return ans;
	}

	private void dfs(Queue<Integer> cardQue, Set<Integer> hand, Set<Integer> sumEqualsToNPlus1, int coin, int round) {

		sb.append("\nCurrent Round: ").append(round).append("\n");
		sb.append("Current Coin: ").append(coin).append("\n");
		printCardQue(cardQue);
		printSet(hand, "Hand");
		printSet(sumEqualsToNPlus1, "SumEqualsToNPlus1");

		if (cardQue.isEmpty()) {
			ans = Math.max(ans, round);
			sb.append("Card Que is Empty: ans = ").append(ans).append("\n");
			return;
		}

		int card1 = cardQue.poll(), card2 = cardQue.poll();

		// 현재 핸드 카드만 선택함 (두 개 다 버림)
		printCardQue(cardQue);
		printSet(hand, "Hand");
		printSet(sumEqualsToNPlus1, "SumEqualsToNPlus1");
		for (int s : sumEqualsToNPlus1.stream().toList()) {
			// 합이 n+1인 것을 손에서 버리고
			hand.remove(s);
			hand.remove(n+1-s);
			sumEqualsToNPlus1.remove(s);
			sb.append("Add None\n");
			sb.append("뽑기0 두 개의 카드 제출하고 다음 라운드로 이동: ").append(s).append(", ").append(n+1-s).append("\n");
			sb.append("Current Coin: ").append(coin).append("\n");
			// 다음 라운드로 넘어감
			dfs(cardQue, hand, sumEqualsToNPlus1, coin, round + 1);
			sumEqualsToNPlus1.add(s);
			hand.add(n+1-s);
			hand.add(s);
		}

		// 3 6 7 2 1 10
		// {6, 7}, {3, 10}
		// 다음 카드들 중 한 개만 선택하고 합이 n+1인 조합 찾음
		if (coin < 1) {
			ans = Math.max(ans, round);
			return;
		}
		printCardQue(cardQue);
		printSet(hand, "Hand");
		printSet(sumEqualsToNPlus1, "SumEqualsToNPlus1");
		for (int card : new int[]{card1, card2}){
			sb.append("Add ").append(card).append("\n");
			for (int handCard : hand.stream().toList()) {
				// card 하나만 가져옴
				hand.add(card);

				// 1) 사용해서 다음 라운드로 진행
				if (handCard + card == n + 1) {
					hand.remove(handCard);
					hand.remove(card);
					sb.append("뽑기1 사용O 두 개의 카드 제출하고 다음 라운드로 이동: ").append(handCard).append(", ").append(card).append("\n");
					dfs(cardQue, hand, sumEqualsToNPlus1, coin - 1, round + 1);
					hand.add(card);
					hand.add(handCard);
				}

				// 2) 사용하지 않고 기존의 조합으로 다음 라운드로 진행
				printSet(sumEqualsToNPlus1, "SumEqualsToNPlus1");
				for (int s : sumEqualsToNPlus1.stream().toList()) {
					// 합이 n+1인 것을 손에서 버리고
					hand.remove(s);
					hand.remove(n+1-s);
					sumEqualsToNPlus1.remove(s);
					sb.append("뽑기1 사용X 두 개의 카드 제출하고 다음 라운드로 이동: ").append(s).append(", ").append(n+1-s).append("\n");
					// 다음 라운드로 넘어감
					if (handCard + card == n+1) sumEqualsToNPlus1.add(Math.min(handCard, card));
					dfs(cardQue, hand, sumEqualsToNPlus1, coin - 1, round + 1);
					sumEqualsToNPlus1.remove(Math.min(handCard, card));
					sumEqualsToNPlus1.add(s);
					hand.add(n+1-s);
					hand.add(s);
				}

				hand.remove(card);
			}
		}

		if (coin < 2) {
			ans = Math.max(ans, round);
			return;
		}
		printCardQue(cardQue);
		printSet(hand, "Hand");
		printSet(sumEqualsToNPlus1, "SumEqualsToNPlus1");
		hand.add(card1);
		hand.add(card2);
		sb.append("Add ").append(card1).append(", ").append(card2).append("\n");
		if (card1 + card2 == n + 1) {
			// 두 장 다 사용해서 다음 라운드로 진행하거나
			sb.append("뽑기2 사용O 두 개의 카드 제출하고 다음 라운드로 이동: ").append(card1).append(", ").append(card2).append("\n");
			dfs(cardQue, hand, sumEqualsToNPlus1, coin - 2, round + 1);
		}

		List<Integer> temp = new ArrayList<>();
		for (int handCard : hand.stream().toList()) {
			Integer tmp = null;
			if (handCard + card1 == n + 1) {
				tmp = Math.min(handCard, card1);
			}
			else if (handCard + card2 == n + 1) {
				tmp = Math.min(handCard, card2);
			}
			else if (card1 + card2 == n + 1) {
				tmp = Math.min(card1, card2);
			}
			if (tmp != null) {
				sumEqualsToNPlus1.add(tmp);
				temp.add(tmp);
			}
		}

		for (int s : sumEqualsToNPlus1.stream().toList()) {
			// 합이 n+1인 것을 손에서 버리고
			hand.remove(s);
			hand.remove(n+1-s);
			sumEqualsToNPlus1.remove(s);
			sb.append("뽑기2 사용X 두 개의 카드 제출하고 다음 라운드로 이동: ").append(s).append(", ").append(n+1-s).append("\n");
			// 다음 라운드로 넘어감
			if (card1 + card2 == n+1) sumEqualsToNPlus1.add(Math.min(card1, card2));
			dfs(cardQue, hand, sumEqualsToNPlus1, coin - 2, round + 1);
			sumEqualsToNPlus1.remove(Math.min(card1, card2));
			sumEqualsToNPlus1.add(s);
			hand.add(n+1-s);
			hand.add(s);
		}
		hand.remove(card1);
		hand.remove(card2);

		for (int t : temp){
			sumEqualsToNPlus1.remove(t);
		}
	}

	private void printSet(Set<Integer> set, String title) {
		sb.append("=============== ").append(title).append(" List ===============\n");
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(" ");
		}
		sb.append("\n");
	}

	private void printCardQue(Queue<Integer> cardQue) {
		sb.append("=============== Card List ===============\n");
		List<Integer> list = new ArrayList<>(cardQue);
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(" ");
		}
		sb.append("\n");
	}

}