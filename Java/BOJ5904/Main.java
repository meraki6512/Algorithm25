package Algorithm.Algorithm25.Java.BOJ5904;

import java.util.Scanner;

/**
 * S(k) = S(k-1)과 o가 k+2개인 수열 moooo...와 S(k-1)을 합쳐서 만들 수 있다.
 *
 * S(0) = moo                               // 3          // 0*2+3=3
 * S(1) = moo + mooo + moo                  // o3개 mooo  // 3*2+4=10
 * S(2) = moomooomoo + moooo + moomooomoo   // o4개 moooo // 10*2+5=25
 *
 * moo 수열을 무한대로 만들 수 있을 때, n번째 글자를 구하라.
 * 문자열 자체 계산 -> 메모리 오버 N: 10^9 (128MB)
 *
 * 대칭...
 */

public class Main {

    private static int N;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        getMoo(1, 3);
    }

    private static void getMoo(int k, int len){

        if (N <= 3){
            if (N == 1) System.out.println("m");
            else System.out.println("o");
            return;
        }

        // cur+MOO+cur 꼴
        int nLen = 2*len + k+3;

        // N이 다음 S 길이보다 클 땐 k++ 해서 반복
        if (nLen < N) getMoo(k+1, nLen);
        // N이 다음 S 길이보다 작을 땐
        else{
            // cur+MOO+cur 꼴에서 ...
            int nxt = len + k + 3; // cur+MOO

            // N이 MOO 내에 있을 때
            if (len < N && N <= nxt){
                if (N - len == 1) System.out.println("m");  // 시작하는 점이면 m
                else System.out.println("o"); 
            }
            // N이 뒤 cur 내에 있을 때
            else {
                N -= nxt; // 앞 cur 위치로 대응
                getMoo(1, 3); // 앞쪽에서 다시 찾기
            }
        }
    }
}
