package Algorithm.Algorithm25.Java.BOJ14940;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 깃헙 액션 테스트용
 * 14940. 쉬운 최단거리
 * https://www.acmicpc.net/problem/14940
 */
public class Main {

    private static int n, m, sx, sy;
    private static int[][] map, dist;
    private static final int[] dx = { -1, 1, 0, 0 };
    private static final int[] dy = { 0, 0, -1, 1 };
    private static boolean[][] visited;

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    sx = i;
                    sy = j;
                }
            }
        }

        dist = new int[n][m];
        visited = new boolean[n][m];
        visited[sx][sy] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    dist[nx][ny] = dist[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && dist[i][j] == 0) {
                    dist[i][j] = -1;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
