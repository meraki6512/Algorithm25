package Algorithm.Algorithm25.Java.BOJ1541;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * <a href="https://www.acmicpc.net/problem/1541">1541. 잃어버린 괄호</a>
 * +, -로만 이뤄진 식에 적절히 괄호를 쳐서 식의 값을 최소로 만들어라.
 * -> -가 나오는 부분에서 끊어서 모두 빼주면 됨
 * 여러개가 나오면 여러번 끊어주면 됨
 * 예를 들어, 10+10-10+10+10-10-10+10 = (10+10)-(10+10+10)-(10)-(10+10)
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String exp = sc.nextLine();

		String[] subExp = exp.split("-");
		Queue<Integer> sumList = new LinkedList<>();
		for (String s : subExp) sumList.add(Arrays.stream(s.split("\\+")).mapToInt(Integer::parseInt).sum());

		int ans = sumList.poll();
		while (!sumList.isEmpty()) ans -= sumList.poll();
		System.out.println(ans);
	}
}
