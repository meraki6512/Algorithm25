// package Algorithm.Algorithm25.Java.BOJ1722;

import java.util.Scanner;

/**
 * 1722. 순열의 순서 (2, 128)
 * https://www.acmicpc.net/problem/1722
 *
 * 3
 * XXXX
 * 1-> 3!
 * 2-> 3!
 * 3-> 3!
 * 4-> 3!
 *
 * 12 -> 2!
 * 13 -> 2!
 * 14 -> 2!
 *
 * N=5
 * XXXXX
 * (N-1)!
 * K가 (N-1)!*i보다 작거나 같으면 i로 고정
 *
 */

public class Main {

    private static int N;
    private static long k;
    private static int[] num;
    private static long[] facto;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        facto = new long[N+1];
        facto[0] = facto[1] = 1;
        for (int i = 2; i < N+1; i++) facto[i] = facto[i-1] * i;

        switch(sc.nextInt()){
            case 1:
                k = sc.nextLong();
                getNumList();
                break;
            case 2:
                num = new int[N+1];
                for (int i=1; i<=N; i++){
                    num[i] = sc.nextInt();
                }
                getK();
                break;
        }
    }

    private static void getNumList(){
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[N+1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (visited[j]) continue;

                if (k <= facto[N-i]){
                    sb.append(j).append(" ");
                    visited[j] = true;
                    break;
                }
                else k-= facto[N-i];
            }
        }

        System.out.println(sb);
    }

    private static void getK(){
        long sum = 1;   // 순열 순서=1부터 
        boolean[] visited = new boolean[N+1];
        
        for (int i=1; i<=N; i++){   // 현재 위치 i에 대해 num[i]
            for (int j=1; j<num[i]; j++){   // 가능한 숫자 중 num[i]보다 작은 수
                if (!visited[j]) sum += facto[N-i]; // (N-i)!만큼 순열이 존재
            }
            visited[num[i]] = true; // num[i]는 처리 완료 표시
        }
        System.out.println(sum);
    }
}