package Algorithm.Algorithm25.Java.BOJ4963;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 섬의 개수
 * https://www.acmicpc.net/problem/4963
 * w, h 주의
 */
public class Main {

    private static boolean[][] visited;
    private static int[][] map;
    private static final int[] dx = {0, 0, 1, -1, 1, -1, 1, -1};
    private static final int[] dy = {1, -1, 0, 0, 1, 1, -1, -1};
    private static int w, h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int ans;

        while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken()); // 50
            h = Integer.parseInt(st.nextToken()); // 50
            if (w == 0 && h == 0) break;
            ans = 0;

            map = new int[h][w];
            for(int i=0; i<h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<w; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        dfs(i, j);
                        ans ++;
                    }
                }
            }

            System.out.println(ans);
        }
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return 0<=x && x<h && 0<=y && y<w;
    }
}
