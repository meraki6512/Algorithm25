// package Algorithm.Algorithm25.Java.BOJ14888;

import java.util.Scanner;

/**
 * 14888. 연산자 끼워넣기
 * https://www.acmicpc.net/problem/14888
 *
 * N개의 수의 수열 A (순서 고정)와 N-1개의 연산자.
 * 수와 수 사이에 연산자를 넣어 수식을 만들 때 Max, Min값을 구하라.
 *
 * 연산 범위는 int값 내, 연산자 우선순위 무시, 나눗셈은 몫만 취함
 *
 * backtracking 느낌.
 */

public class Main {

    private static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    private static int N;
    private static int[] A;
    private static int[] opNum;

    public static void main(String[] args){

        // Input
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();                           // 2 ≤ N ≤ 11
        A = new int[N+1];
        for (int i=1; i<=N; i++) A[i] = sc.nextInt();   // 1 ≤ A[i] ≤ 100
        opNum = new int[4];                       // 순서대로 +, -, x, / 의 개수
        for (int i=0; i<4; i++) opNum[i] = sc.nextInt();

        // Sol
        dfs(1, A[1]);
        System.out.println(max);
        System.out.println(min);

    }

    private static int operate(int operand, int operator, int opNum){
        switch(opNum){
            case 0:
                return operand + operator;
            case 1:
                return operand - operator;
            case 2:
                return operand * operator;
            case 3:
                return operand / operator;
        }
        return -1; // 실행할 일 없음
    }

    private static void dfs(int idx, int val){
        if (idx == N) {
            max = Math.max(max, val);
            min = Math.min(min, val);
            return;
        }

        for (int i=0; i<4; i++){
            if (opNum[i] > 0){
                opNum[i] -- ;
                if (idx + 1 <= N) {
                    int nxt = operate(val, A[idx + 1], i);
                    dfs(idx + 1, nxt);
                }
                opNum[i] ++ ;
            }
        }

    }
}
