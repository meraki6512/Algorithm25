// package Algorithm.Algorithm25.Java.BOJ1629;

import java.util.Scanner;

/**
 * 곱셈
 * https://www.acmicpc.net/problem/1629
 *
 * (A^B)%C //  A, B, C는 모두 2,147,483,647 이하의 자연수
 * B가 너무 클 경우 (50_000_000정도 이상일 경우) ... 가 문제다..
 *
 * -> 분할정복 (지수와 모듈러 성질을 활용)
 *
 * * 지수 법칙
 * a^(n+m) = a^n * a^m
 * ex.
 * 지수가 짝수일 경우: a^8 = a^4 * a^4 = (a^2*a^2)*(a^2*a^2) = ((a*a)*(a*a)) * ((a*a)*(a*a))
 * 지수가 홀수일 경우: a^9 = a * a^8 = ...
 *
 * * 모듈러 성질
 * * (증명: https://st-lab.tistory.com/19#%EB%AA%A8%EB%93%88%EB%9F%AC)
 * * (a*b)%c = (a%C * B%C)%C
 * ex.
 * (12*7)%5 = (12%5 * 7%5)%5
 * 84%5 = (2*2)%5
 * 4 = 4
 *
 */

public class Main {

    private static long C;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long A = sc.nextInt();
        long B = sc.nextInt();
        C = sc.nextInt();

        System.out.println(pow(A, B));
    }

    private static long pow(long x, long n){
        if (n == 1) return x % C;

        // a^(2n+1) = a^n * a^n * a
        // a^(2n) = a^n * a^n
        long tmp = pow(x, n/2);

        if (n%2 == 1) return ((tmp * tmp)%C * x%C) % C;
        else return (tmp * tmp) % C;
    }
}