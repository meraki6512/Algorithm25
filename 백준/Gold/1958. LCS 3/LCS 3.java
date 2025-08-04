// package Algorithm.Algorithm25.Java.BOJ1958;

/*
1958. LCS 3 (2s, 128MB)
https://www.acmicpc.net/problem/1958
3개 문자열의 LCS 길이를 구하라.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 각 길이 100
        Scanner sc = new Scanner(System.in);
        char[] s1 = sc.next().toCharArray();
        char[] s2 = sc.next().toCharArray();
        char[] s3 = sc.next().toCharArray();

        int L1 = s1.length;
        int L2 = s2.length;
        int L3 = s3.length;

        int[][][] dp = new int[L1+1][L2+1][L3+1];
        for (int i=1; i<=L1; i++){
            for (int j=1; j<=L2; j++){
                for (int k=1; k<=L3; k++){

                    if (s1[i-1] == s2[j-1] && s2[j-1] == s3[k-1]){
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    }
                    else {
                        int max = Math.max(dp[i-1][j][k], dp[i][j-1][k]);
                        max = Math.max(max, dp[i][j][k-1]);
                        dp[i][j][k] = max;
                    }

                }
            }
        }

        System.out.println(dp[L1][L2][L3]);
    }
}
