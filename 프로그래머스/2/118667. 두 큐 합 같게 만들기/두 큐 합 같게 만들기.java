import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

class 두큐합같게만들기 {

	public int solution(int[] queue1, int[] queue2) {
		int answer = 0;
		long sum1 = Arrays.stream(queue1).asLongStream().sum(), sum2 = Arrays.stream(queue2).asLongStream().sum();
		Queue<Integer> q1 = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();
		q1.addAll(Arrays.stream(queue1).boxed().collect(Collectors.toList()));
		q2.addAll(Arrays.stream(queue2).boxed().collect(Collectors.toList()));

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