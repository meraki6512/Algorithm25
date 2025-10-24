import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

class N플러스1카드게임 {
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

