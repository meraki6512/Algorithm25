package Algorithm.Algorithm25.Java.BOJ1158;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 1158. 요세푸스 문제
 * https://www.acmicpc.net/problem/1158
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) q.add(i);

        int[] ans = new int[N];
        int cnt = 0;
        while (!q.isEmpty()) {
            for (int i = 0; i < K-1; i++) q.add(q.poll());
            ans[cnt++] = q.poll();
        }

        StringBuilder sb = new StringBuilder("<");
        sb.append(ans[0]);
        for (int i = 1; i < N; i++) {
            sb.append(", ").append(ans[i]);
        }
        sb.append(">");
        System.out.println(sb);

    }
}
