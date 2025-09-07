package Algorithm.Algorithm25.Java.BOJ1245;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1245.
 * https://www.acmicpc.net/problem/1245
 * 모든 점을 방문하면서, 같은 높이의 점들만 방문 체크를 하는 게 포인트.
 */

public class Main {

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static final int[] dx = {0, 0, -1, 1, -1, -1, 1, 1};
    private static final int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 100
        M = Integer.parseInt(st.nextToken());   // 70
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    if (bfs(i, j)) ans++;
                }
            }
        }
        System.out.println(ans);
    }

    private static boolean bfs(int x, int y) {
        boolean peak = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                // 더 높은 높이가 없을 때만 peak true 체크 (++)
                if (map[nx][ny] > map[x][y]) peak = false;

                // 같은 높이만 탐색
                if (!visited[nx][ny] && map[nx][ny] == map[x][y]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return peak;
    }
}
