/*
18430. 무기 공학
2초, 256MB
https://www.acmicpc.net/problem/18430
부메랑 만들기의 최댓값 구하기
중심은 두 배 찬스
나무 형태 N, M (5), 값 K (100)
backtracking
 */

import java.util.Scanner;

public class Main {

    // 우하, 우상, 좌상, 좌하
    private static final int[][] dx = {{1, 0}, {1, 0}, {-1, 0}, {-1, 0}};
    private static final int[][] dy = {{0, 1}, {0, -1}, {0, -1}, {0, 1}};
    private static int[][] board;
    private static int ans = 0;
    private static int N, M;
    private static boolean[][] visited;

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void dfs(int x, int y, int sum){
        if (y == M) {
            x++;
            y = 0;
        }
        if (x == N){
            ans = Math.max(ans, sum);
            return;
        }

        if (!visited[x][y]){
            for (int d=0; d<4; d++){
                int cx = x + dx[d][0];
                int cy = y + dy[d][0];
                int lx = x + dx[d][1];
                int ly = y + dy[d][1];

                if (!inRange(cx, cy) || !inRange(lx, ly) || visited[cx][cy] || visited[lx][ly]) continue;

                visited[x][y] = visited[cx][cy] = visited[lx][ly] = true;
                dfs(x, y + 1, sum + board[x][y]*2 + board[cx][cy] + board[lx][ly]);
                visited[x][y] = visited[cx][cy] = visited[lx][ly] = false;

            }
        }

        // 현재 칸에 (중심)부메랑을 두지 않는 경우 (도 고려해야함)
        dfs(x, y + 1, sum);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        board = new int[N][M];
        visited = new boolean[N][M];

        if (N <= 1 || M <= 1) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                board[i][j] = sc.nextInt();

        dfs(0, 0, 0);
        System.out.println(ans);
    }
}
