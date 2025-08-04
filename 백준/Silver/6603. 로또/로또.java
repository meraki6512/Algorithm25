// package Algorithm.Algorithm25.Java.BOJ6603;

import java.util.Scanner;

/**
 * 로또
 * https://www.acmicpc.net/problem/6603
 * 독일 로또 1~49에서 수 6개를 고른다.
 * 49가지 중 k개의 수를 골라 집합 S를 만든 다음 그 수로 번호를 선택하는 방법
 * k: 7 ~12
 * 
 * 그러니까 k개 중에 6개를 고르는 경우의 수를 구하는 문제 (순서 X)
 * -> kC6
 */

// 모든 스택 수열 느낌 ... -> 다시 풀어봐야겠다.
// 재귀로 가려면 백트래킹?

public class Main {

    private static StringBuilder sb = new StringBuilder();
    private static boolean[] visited;
    private static int[] nums;
    private static int k;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            k = sc.nextInt();
            if (k == 0) break;

            visited = new boolean[k];
            nums = new int[k];
            for (int i=0; i<k; i++){
                nums[i] = sc.nextInt();
            }

            backTracking(0, 0, "");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void backTracking(int idx, int depth, String path){
        if (depth == 6){
            sb.append(path.strip()).append("\n");
            return;
        }

        for (int i = idx; i<k; i++){
            if (!visited[i] && depth < 6){
                visited[i] = true;
                backTracking(i, depth + 1, path + nums[i] + " ");
                visited[i] = false;
            }
        }
    }

}
