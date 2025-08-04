package Algorithm.Algorithm25.Java.BOJ11689;

/*
11689. GCD(n, k) = 1 (1s, 256MB)
https://www.acmicpc.net/problem/11689

자연수 n(10^12)이 주어졌을 때
GCD(n, k)=1을 만족하는 자연수 1<=k<=n의 개수를 구하라.

-> 오일러 피 (O(√n) -> 10^6)
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long res = n;

        for (int k = 2; k <= Math.sqrt(n); k++){
            if (n % k == 0){                    // 약수면 
                res -= res / k;                 // 배수개 만큼 빼줌
                while (n % k == 0) n /= k;      // 거듭제곱 모두 없애줌
            }
        }

        if (n > 1)          // n이 소수
            res -= res / n; // 마지막 소인수 처리 (배수개만큼 빼줌)

        System.out.println(res);
    }
}
