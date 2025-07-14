/*
* 신나는 함수 실행 https://www.acmicpc.net/problem/9184
* 주의: Set을 key로 쓰면 안됨 (순서 보장X) -> 3차원 배열 사용
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][][] dp = new int[21][21][21];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) break;
            else System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a,b,c));
        }


    }

    static int w(int a, int b, int c){

        if (a<=0 || b<=0 || c<=0) return 1;
        if (a>20 || b>20 || c>20) return w(20, 20, 20);
        if (dp[a][b][c]!=0) return dp[a][b][c];

        if (a<b && b<c) dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        else dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
        return dp[a][b][c];
    }
}


