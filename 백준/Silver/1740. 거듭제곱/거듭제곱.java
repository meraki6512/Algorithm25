// package Algorithm.Algorithm25.Java.BOJ1740;

// 거듭제곱 (2s, 128MB)
// https://www.acmicpc.net/problem/1740
// 서로 다른 3의 제곱수의 합으로 표현되는 수 중 N번째로 작은 수를 구하라.

// PQ 또는 BitMask

import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong(); // 500,000,000,000

        /**
         * 1: 0001 -> 3^0 = 1
         * 2: 0010 -> 3^1 = 3
         * 3: 0011 -> 3^0 + 3^1 = 4
         * 4: 0100 -> 3^2 = 9
         * 5: 0101 -> 3^0 + 3^2 = 10
         * ...
         * N: ... -> 3^ + 3^ ...
         */

        long sum = 0;
        long p = 1;

        while (N > 0) {
            if ((N&1)==1) sum += p;
            N>>=1;
            p*=3;
        }

        System.out.println(sum);
    }

}


/**
 *

 int digit = 0;
 while (Math.pow(2, digit) <= N){
 digit ++;
 }

 // 1100011111010000000101001100000101010111111100101011 1001
 // 1100011111010000000101001100000101010111111100101011 1100

 long sum = 0;
 for (int i=0; i < digit; i++){
 if ((N & (1L<<i)) != 0) {
 sum += (long) Math.pow(3, i);
 }
 }
 System.out.println(sum);

 */