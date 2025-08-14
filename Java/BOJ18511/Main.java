package Algorithm.Algorithm25.Java.BOJ18511;

// 큰 수 구성하기 (1s, 256MB)
// https://www.acmicpc.net/problem/18511
// N>=자연수 중에서 집합 K의 원소로만 구성된 가장 큰 수를 출력하라
// K: 1~9 자연수로만 구성, 1~3개의 원소
// N: 10~10^8

import java.util.Scanner;

public class Main {

    private static int N, K, max = 0;
    private static int[] elements;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        elements = new int[K];
        for (int i=0; i<K; i++) elements[i] = sc.nextInt();

        dfs(0);
        System.out.println(max);

    }

    private static void dfs(int num){

        for (int i=0; i<K; i++){
            int nxt = num * 10 + elements[i];

            if (nxt <= N) {
                max = Math.max(max, nxt);
                dfs(nxt);
            }
        }

    }
}
