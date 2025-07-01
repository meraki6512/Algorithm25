package Algorithm.Algorithm25.Java.BOJ2458;

/*
* 키 순서
* https://www.acmicpc.net/problem/2458
* 1s, 128MB
* 
* 서로 다른 키를 가진 1~N번 학생에 대해 (:500)
* M번 키를 비교할 때 (:NC2)
* 자신의 키가 몇 번째인지 알 수 있는 학생들이 모두 몇 명인지 출력하라.
* 
* -> A가 알 수 있다는 건 A에게로 오는 거 + A에게서 가는 거 == N-1개
* -> 최단 거리s 합
* 
* 플로이드 워셜: 모든 정점에서 다른 모든 지점까지의 최단 경로를 구하는 알고리즘
* DP, 음수 사이클 여부만, O(N^3)
* 
* */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int INF = 100;
    public static void main(String[] args) {

        // In
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] d = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            d[a][b] = 1;
        }

        // Sol
        // a->b로 갈 때 k를 거쳐 가는 경우를 고려하면서 그 최솟값을 모두 셈
        for (int k = 1; k <= N; k++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    d[a][b] = Math.min(d[a][b], d[a][k] + d[k][b]);
                }
            }
        }

        // Out
        // 해당 노드에 오는 또는 해당 노드로부터 가는 화살표 개수가 N-1이면 ans ++
        int ans = 0;
        for (int a=1; a<=N; a++){
            int tmp = 0;
            for (int x=1; x<=N; x++){
                if (d[a][x] != INF && d[a][x] > 0) tmp ++;      // 가는 거
                if (d[x][a] != INF && d[x][a] > 0) tmp ++;      // 오는 거
            }
            if (tmp == N-1) ans ++;
        }
        System.out.println(ans);
    }
}
