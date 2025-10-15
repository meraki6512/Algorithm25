// package Algorithm.Algorithm25.Java.BOJ2195;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String S = sc.nextLine(), P = sc.nextLine();

		int idx = 0, ans = 1;
		for (int i = 0; i < P.length(); i++) {
			if (S.contains(P.substring(idx, i + 1)))
				continue;
			idx = i;
			ans++;
		}

		System.out.println(ans);
	}

}
