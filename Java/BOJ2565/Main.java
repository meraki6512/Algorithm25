package Algorithm.Algorithm25.Java.BOJ2565;

/*
* 전깃줄 https://www.acmicpc.net/problem/2565
* 교차하지 않도록 없애야 하는 전깃줄의 최소 개수 = ?
* 
* 전깃줄의 개수 N (100)
* 위치의 번호 (500)
* 한 위치에는 최대 하나의 전깃줄
* */

import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class Line implements Comparable<Line> {
        int a, b;
        Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line o) {
            return a - o.a;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        Line[] lines = new Line[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i] = new Line(a, b);
        }

        // a 기준 ASC 정렬
        Arrays.sort(lines);

        // a1 < a2이면서, b1 > b2이면 교차
        // : B의 연결 위치에서 LIS(최장 증가 부분 수열)의 길이를 구해서
        // LIS에 포함되지 않는 전깃줄을 제거하기
        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int lis = 1;

        // LIS: i번째 값보다 작은 값들로 끝나는 것들 중에서
        // 가장 긴 것에 i번째 값을 붙여 새로운 부분 수열을 만듦
        // dp[i] = max(dp[i], dp[j] + 1)
        // ex. [10, 30, 10, 40]
        // i = 1(30); 10<30;                dp[1] = max(1, 1+1) = 2
        // i = 2(10); <10인 값 X;            dp[2] = 1
        // i = 3(40); 10<40, 30< 40, 10<40; dp[3] = max(1, 2+1, 1+1) = 3
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (lines[j].b < lines[i].b) {              // 교차하지 않으면
                    dp[i] = Math.max(dp[i], dp[j] + 1);     // LIS 추가
                }
            }
            lis = Math.max(lis, dp[i]);                     // max: LIS의 길이
        }

        System.out.println(N - lis);

    }
}
