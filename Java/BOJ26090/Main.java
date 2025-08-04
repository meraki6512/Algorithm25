package Algorithm.Algorithm25.Java.BOJ26090;

/*
26090. 완전한 수열
https://www.acmicpc.net/problem/26090
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        // 연속 부분 수열 중에서
        // 소수 길이인 수열들을 먼저 필터링하고
        // 그 합이 소수면 cnt ++

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i=0; i<N; i++){
            nums[i] = sc.nextInt();
        }
        
        // 부분합 구하기
        int[] p = new int[N+1];
        for (int i=1; i<=N; i++){
            p[i] = p[i-1] + nums[i-1];
        }

        // 소수 구하기
        int m = 2000 * N;
        boolean[] prime = new boolean[m+1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        for (int i=2; i*i<=m; i++){
            if (!prime[i]) continue;
            for (int j=i*i; j<=m; j+=i)
                prime[j] = false;
        }

        // 길이 소수 구하기
        boolean[] len_prime = new boolean[N+1];
        for (int i=2; i<=N; i++){
            if (prime[i]) len_prime[i] = true;
        }
         
        // 수열의 안정도 계산하기
        int ans = 0;
        for (int i=0; i<N; i++){
            for (int j=i+1; j<=N; j++){
                if (!len_prime[j-i]) continue;
                if (prime[p[j]-p[i]]) ans ++;
            }
        }
        System.out.println(ans);
    }
}
