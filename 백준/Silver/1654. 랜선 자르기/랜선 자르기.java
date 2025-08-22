// package Algorithm.Algorithm25.Java.BOJ1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1654. 랜선 자르기
 * https://www.acmicpc.net/problem/1654
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());   // 10_000
        int N = Integer.parseInt(st.nextToken());   // 1_000_000
        long ans = 0;
        int[] lan = new int[K]; // 랜선의 길이 <= 2^31-1
        for (int i=0; i<K; i++) lan[i]=Integer.parseInt(br.readLine());
        Arrays.sort(lan);

        // 이분탐색: 기준=길이
        long l = 1;
        long r = lan[K-1];
        while (l <= r){
            long m = (l+r)/2;

            long cnt = 0;
            for (int k:lan) cnt += k/m;

            if (cnt < N) r = m - 1;
            else {l = m + 1; ans = m;}
        }

        System.out.println(ans);
    }
}
