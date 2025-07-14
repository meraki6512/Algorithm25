package Algorithm.Algorithm25.Java.BOJ2014;

/*
2014. 소수의 곱 (2s, 128MB)
https://www.acmicpc.net/problem/2014

K개의 소수 (100)
소수들 중에서 몇 개를 곱해 얻게 되는 수 (자기자신포함)들을 오름차순으로 나열할 때,
N번 째 수를 구해보라. (10^5)
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] nums = new int[K];
        for (int i = 0; i < K; i++){
            nums[i] = sc.nextInt();
        }

    }
}

/*
2 3 5 7
2 3 5 7 /4 6 10 14 /8 12 20 28/ 16 24 40 56
 */
