package Algorithm.Algorithm25.Java.BOJ1062;

import java.util.*;

/**
 * 가르침 (1s, 128MB)
 * https://www.acmicpc.net/problem/1062
 *
 */

public class t0 {

    private static int N, K, ans = 0;
    private static char[][] words;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();   // 1~50
        K = sc.nextInt();   // 0~26
        words = new char[N][];
        for (int i = 0; i < N; i++) {
            words[i] = sc.next().toCharArray(); // 8~15
        }

        if (K < 5) {
            // 'a', 'c', 'i', 'n', 't'
            System.out.println(0);
            return;
        }
        else if (K == 26){
            System.out.println(N);
            return;
        }

        dfs(0, 0, 0);

        for (int num : list){   // 10^7
            int cnt = 0;
            for (int i=0; i<N; i++) {   // 50
                boolean flag = true;
                for (char c : words[i]){    // 15
                    int ci = c - 'a' + 1;
                    if ((num & (1<<ci)) == 0) {
                        flag = false;
                        break;
                    }
                }
                if (flag) cnt ++;
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }

    /**
     * 26개 알파벳 중 K개를 선택했을 때 num을 list에 담는 함수
     * - 모든 가능한 경우의 수 int[]에 담기 - 최대 2^26: 60,000,000정도 탐색
     * - 26개 중 K개를 선택하는 경우의 수: 26CK - 최대 26C13: 10^7보다 작음
     */
    private static void dfs(int idx, int num, int cnt){
        if (cnt == K) {
            list.add(num);
            return;
        }

        if (idx == 26) {
            return;
        }

        dfs(idx + 1, num | (1<<idx), cnt + 1);
        dfs(idx + 1, num, cnt);
    }
}