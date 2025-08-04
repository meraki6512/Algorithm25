package Algorithm.Algorithm25.Java.BOJ9461;

import java.util.*;

/**
 * 9461. 파도반 수열
 * https://www.acmicpc.net/problem/9461
 * int(x) long(o) 타입 주의 (N=100일 떄 888_855_064_897)
 */

public class Main {

    private static long[] P;

    private static long getP(int c){
        if (P[c] != 0) return P[c];
        return P[c] = getP(c-5) + getP(c-1);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        P = new long[101];
        long[] tmp = {1, 1, 1, 2, 2, 3, 4, 5, 7, 9};
        for (int i=0; i<10; i++) P[i+1] = tmp[i];

        for(int t=0; t<T; t++) System.out.println(getP(sc.nextInt()));
    }
}


// 규칙을 찾아봅세.
// i    Pi   from
// 1    1    1      //맨 첨에 있음 :1
// 2    1    1      // 1st
// 3    1    1      // 2nd
// 4    2    1+1 /  / 1st
// 5    2    2      // 2nd
//
// 6    3    1+2    // P[1] + P[c-1]
// 7    4    1+3    // P[2] + P[c-1]
// 8    5    1+4    // P[3] + P[c-1]
// 9    7    2+5    // ...
// 10   9    2+7
// 11   12   3+9
// 12   16   4+12
// 13   21   5+16
//                  // c>5: P[c] = p[c-5] + P[c-1]