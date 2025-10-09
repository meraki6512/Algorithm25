package Algorithm.Algorithm25.Java.BOJ10830;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 10830. 행렬제곱 (1, 256)
 * https://www.acmicpc.net/problem/10830
 *
 * N*N 행렬 A가 주어질 때, A^B = ? (각 원속를 1_000으로 나눈 나머지를 출력)
 *
 * -> 1629 곱셈 문제와 비슷하게 분할정복?
 * : 행렬에서도 A^p * A^q = A^(p+q)가 성립하기 때문에 할 수 있을듯..
 *
 */

public class Main {

    private static int N;
    private static final int MOD = 1000;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();   // 2~5
        long B = sc.nextLong(); // 100_000_000_000
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        int[][] ans = powMatrix(matrix, B);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                sb.append(ans[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static int[][] powMatrix(int[][] matrix, long B) {
        if (B == 1){
            int[][] result = new int[N][N];
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    result[i][j] = matrix[i][j] % MOD;
                }
            }
            return result;
        }

        int[][] tmp = powMatrix(matrix, B/2);
        int[][] tmpTmp = multiplyMatrix(tmp, tmp);

        if (B%2 == 1) return multiplyMatrix(tmpTmp, matrix);
        else return tmpTmp;
    }

    private static int[][] multiplyMatrix(int[][] X, int[][] Y) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result[i][j] = (X[i][j] * Y[j][i]) % MOD;
            }
        }

        return result;
    }
}


/**
 ({{1, 2}, {3, 4}})
 {{7, 10}, {15, 22}}
 ({{37, 54}, {81, 118}})
 {{199, 290}, {435, 634}}
 {{1069, 1558}, {2337, 3406}} ->  {{69, 558}, {337, 406}}
 */