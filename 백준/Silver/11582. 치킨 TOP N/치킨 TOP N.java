// package Algorithm.Algorithm25.Java.BOJ11582;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 치킨 TOP N
 * https://www.acmicpc.net/problem/11582
 *
 * 머지소트 개념 -> 실제로 머지소트를 구현할 필요는 X
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder ans = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) num[i] = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        int size = N/k;
        for (int i = 0; i < N; i+=size) Arrays.sort(num, i, i+size);

        for (int i = 0; i < N; i++) ans.append(num[i]).append(" ");
        System.out.println(ans);
    }
}
