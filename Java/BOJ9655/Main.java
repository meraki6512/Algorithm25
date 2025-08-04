package Algorithm.Algorithm25.Java.BOJ9655;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/9655
 * N개를 SK와 CY가 번갈아가며 가져감
 * 1개 또는 3개씩 가져갈 때, 마지막 돌을 가져가는 사람은 누구?
 *
 * 1 SK1
 * 2 SK1 CY1
 * 3 SK1 CY1 SK1 || SK3
 * 4 SK1 CY3 || SK3 CY1 || SK1 CY1 SK1 CY1
 *
 * -> 홀수면 SK, 짝수면 CY
 */

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 1000
        System.out.println((N&1) == 1 ? "SK" : "CY");
    }
}
