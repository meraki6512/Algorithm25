package Algorithm.Algorithm25.Java.BOJ1630;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <a href="https://www.acmicpc.net/problem/1630">1630. 오민식</a>
 * N이 주어졌을 때, "자연수 X(1<=X<=N)로 나눠 떨어지는 수" 중 가장 작은 수는?
 */
public class Main {

    private static final int MOD = 987654321;
    private static long[] dp;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // 소수 구하기
        boolean[] prime = new boolean[N + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        
        for (int i = 2; i * i <= N; i++) {
            if (!prime[i]) continue;
            for (int j = i * i; j <= N; j += i) {
                prime[j] = false;
            }
        }

        // 제곱수 구하기
        int[] root = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            if (prime[i]) root[i] = i;
        }
        for (int i = 2; i * i <= N; i++) {
            if (!prime[i]) continue;
            for (int j = i * i; j <= N; j *= i) {
                root[j] = i;
            }
        }

        // 최소 오민식 수 구하기
        dp = new long[N + 1];
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            // 소수의 제곱수이면 *i
            if (prime[root[i]]) dp[i] = (dp[i-1] * root[i]) %MOD;
            else dp[i] = dp[i-1];
        }

        System.out.println(dp[N]);
    }
}
