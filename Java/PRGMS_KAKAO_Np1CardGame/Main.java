package Algorithm.Algorithm25.Java.PRGMS_KAKAO_Np1CardGame;

import java.util.HashSet;
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
 * greedy
 */
public class Main {
	public static void main(String[] args) {
		Solution solution = new Solution();
		// int coin = 4;
		// int[] cards = {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4};

		// int coin = 3;
		// int[] cards = {1, 2, 3, 4, 5, 8, 6, 7, 9, 10, 11, 12};

		// int coin = 2;
		// int[] cards = {5, 8, 1, 2, 9, 4, 12, 11, 3, 10, 6, 7};

		int coin = 10;
		int[] cards = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18};

		System.out.println(solution.solution(coin, cards));
	}
}

class Solution {
	public int solution(int coin, int[] cards) {
		int n = cards.length;
		int round = 1;
		int ci = n/3;
		Set<Integer> hand = new HashSet<>();
		for (int i=0; i<ci; i++) hand.add(cards[i]);

		// 동전 가져와 사용할 수 있는 뭉치 (장바구니)
		Set<Integer> pending = new HashSet<>();

		while (ci < n){
			int card1 = cards[ci];
			int card2 = cards[ci+1];

			// pending에 넣어두기 -> 사용하지 않으면 계속 남아 있도록
			pending.add(card1);
			pending.add(card2);

			// coin 0: hand + hand
			boolean flag = false;
			int rm = -1;
			for (int handCard : hand) {
				if (handCard != (n+1)/2 && hand.contains(n + 1 - handCard)) { // self (x), hand (o)
					rm = handCard;
					flag = true;
					break;
				}
			}
			if (rm != -1) {
				hand.remove(rm);
				hand.remove(n+1 - rm); // 첫 카드덱에 n+1-handCard도 포함되어있을 수 있어서 같이 제거해야 함
				flag = true;
			}

			// greedy -> coin 0 써도 되면 0 쓰기
			// coin 1: hand + (card1||card2)
			if (!flag && coin > 0){
				rm = -1;
				for (int handCard : hand) {
					if (pending.contains(n + 1 - handCard)){
						rm = handCard;
						break;
					}
				}
				if (rm != -1) {
					hand.remove(rm);
					pending.remove(n+1 - rm);
					--coin;
					flag = true;
				}
			}

			// coin 2개 뽑으면 되는 경우: card1 + card2
			if (!flag && coin > 1){
				rm = -1;
				for (int pendingCard : pending) {
					if (pending.contains(n + 1 - pendingCard)){
						rm = pendingCard;
						break;
					}
				}
				if (rm != -1) {
					pending.remove(rm);
					pending.remove(n+1 - rm);
					coin -= 2;
					flag = true;
				}
			}

			if (flag) {
				round++;
				ci += 2;
			}
			else{
				break;
			}
		}

		return round;
	}
}

