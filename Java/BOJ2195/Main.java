package Algorithm.Algorithm25.Java.BOJ2195;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.nextLine(), P = sc.nextLine();

		int prev = 0, ans = 1;
		for (int i = 0; i < P.length(); i++) {
			if (S.contains(P.substring(prev, i + 1))) continue;
			prev = i;
			ans++; // 이전부터 현재까지 연속된 값이 S에 없다면, 분리해서 만듦
		}

		System.out.println(ans);
	}

}
