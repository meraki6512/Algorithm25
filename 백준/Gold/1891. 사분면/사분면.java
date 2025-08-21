// package Algorithm.Algorithm25.Java.BOJ1891;

import java.util.Scanner;

/**
 * 사분면
 * https://www.acmicpc.net/problem/1891
 * 1. num -> R,C
 * 2. R,C move (-y, x)
 * 3. R, C -> num
 */

public class Main {

    private static int d;
    private static String num;
    private static long size;
    private static long x, y;
    private static long R, C;
    private static StringBuilder ans = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        d = sc.nextInt();
        num = sc.next();
        x = sc.nextLong();
        y = sc.nextLong();
        size = (long) Math.pow(2, d);

        findRCByNum(size);
        // 우:x+, 좌:x-, 상:y+, 하:y- (y값은 좌표랑 반대)
        R -= y;
        C += x;
        
        if (R<0||R>=size||C<0||C>=size) System.out.println(-1);
        else findNumByRC(R, C, size);
    }

    private static void findRCByNum(long size){
        for (int i=0; i<num.length(); i++){
            size /= 2;
            switch (num.charAt(i)-'0'){
                case 1: C += size; break;
                case 3: R += size; break;
                case 4: C += size; R += size;
            }
        }
    }

    private static void findNumByRC(long r, long c, long size){
        if (size == 1){
            System.out.println(ans);
            return;
        }
        
        long nSize = size/2;
        
        if (r<nSize && c>=nSize){
            ans.append(1); // 1사분면
            findNumByRC(r, c-nSize, nSize);
        }
        else if (r<nSize && c<nSize){
            ans.append(2); // 2사분면
            findNumByRC(r, c, nSize);
        }
        else if (r>=nSize && c<nSize){
            ans.append(3); // 3사분면
            findNumByRC(r-nSize, c, nSize);
        }
        else if (r>=nSize && c>=nSize){
            ans.append(4); // 4사분면
            findNumByRC(r-nSize, c-nSize, nSize);
        }
    }
}
