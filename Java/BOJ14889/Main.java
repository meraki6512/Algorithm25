package Algorithm.Algorithm25.Java.BOJ14889;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 스타트와 링크
 * https://www.acmicpc.net/problem/14889
 *
 * 짝수인 N명.
 * S[i][j] = i가 j와 같은 팀에 속할 때 팀에 더해지는 능력치. (S[i][j]와 S[j][i]는 다를 수 있음)
 * 두 팀의 능력치의 차이가 최소가 되도록 2개의 팀으로 나누자.
 */

public class Main {

    private static int[][] S;
    private static int N, ans = 100;
    private static boolean[] visited;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();                       // 4~20 짝수
        S = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                S[i][j] = sc.nextInt();         // 1~100
            }
        }
        visited = new boolean[N+1];
        dfs(1, 0);
        System.out.println(ans);
    }

    private static void dfs(int idx, int cnt) {
        if (cnt == N/2) {
            int stt = 0, lnk = 0;
            for (int i=1; i<=N; i++){
                for (int j=1; j<=N; j++){
                    if (i==j) continue;
                    if (visited[i] && visited[j]) stt += S[i][j];
                    if (!visited[i] && !visited[j]) lnk += S[i][j];
                }
            }
            ans = Math.min(ans, Math.abs(stt-lnk));
            return;
        }

        for (int i=idx; i<=N; i++){
            visited[i] = true;
            dfs(i+1, cnt+1);
            visited[i] = false;
        }
    }
}