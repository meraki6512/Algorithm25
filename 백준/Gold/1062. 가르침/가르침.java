// package Algorithm.Algorithm25.Java.BOJ1062;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 가르침 (1s, 128MB)
 * https://www.acmicpc.net/problem/1062
 *
 */

public class Main {

    private static int N, K, ans = 0;
    private static char[][] words;
    static boolean[] learned = new boolean[26];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();   // 1~50
        K = sc.nextInt();   // 0~26
        words = new char[N][];
        for (int i = 0; i < N; i++) {
            words[i] = sc.next().toCharArray(); // 8~15
        }

        if (K < 5) {
            // 'a', 'c', 'i', 'n', 't' 반드시 포함하기
            System.out.println(0);
            return;
        }
        else if (K == 26){
            System.out.println(N);
            return;
        }

        learned['a' - 'a'] = true;
        learned['c' - 'a'] = true;
        learned['i' - 'a'] = true;
        learned['n' - 'a'] = true;
        learned['t' - 'a'] = true;

        dfs(0, 0);

        System.out.println(ans);
    }

    /**
     *
     * @param idx: 현재 알파벳 인덱스 (0~25)
     * @param cnt: 현재까지 배운 문자 수 (필수 문자 제외해서 더함)
     * 25C(K-5)로 5_000_000정도의 값에 대해서만 탐색하게 됨
     */
    private static void dfs(int idx, int cnt){

        if (cnt == K - 5) {
            int r = 0;
            outer:
            for (char[] word: words){
                for (char c : word){
                    if (!learned[c-'a']){
                        continue outer;
                    }
                }
                r ++;
            }
            ans = Math.max(ans, r);
            return;
        }

        if (idx == 26) {
            return;
        }

        if (learned[idx]) {
            dfs(idx + 1, cnt);
        }
        else {
            learned[idx] = true;
            dfs(idx + 1, cnt + 1);

            learned[idx] = false;
            dfs(idx + 1, cnt);
        }
    }
}
