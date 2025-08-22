// package Algorithm.Algorithm25.Java.BOJ2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 2110. 공유기 설치
 * https://www.acmicpc.net/problem/2110
 *
 * 수직선 위에 집 N개 (x1, ..., xN)가 있다.
 * C개의 공유기를 N개의 집에 적당히 설치해
 * 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하라.
 * Out: 최대 거리
 */

public class Main {

    public static void main(String[] args) throws IOException {
        // In
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 200_000
        int C = Integer.parseInt(st.nextToken());
        long ans = 0;

        long[] houses = new long[N];
        for (int i = 0; i < N; i++) houses[i] = Long.parseLong(br.readLine());
        Arrays.sort(houses);

        // Sol
        // 이분 탐색: 기준은 두 집 사이의 거리
        // l (최소 거리: 1), r (최대 거리: 양 끝 집 거리)
        long l = 1;
        long r = houses[N-1]-houses[0];
        if (C==2) {System.out.println(r); return;}

        while (l <= r){
            long m = (l+r)/2;

            // cnt: houses 1번 집인 곳부터 거리가 m 이상인 집에 설치할 때 총 설치 공유기 수
            int cnt = 1;
            long prev = houses[0];
            for (int i=1; i<N; i++){
                // 거리 차가 m 이상인 경우만 공유기 설치
                if (houses[i]-prev>=m){
                    cnt++;
                    prev = houses[i];
                }
            }

            // cnt (설치한 공유기 수), C (설치할 공유기 수)
            if (cnt < C) r = m - 1;
            else {l = m+1; ans=m;}
        }

        // Out
        System.out.println(ans);
    }
}