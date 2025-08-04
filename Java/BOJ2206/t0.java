package Algorithm.Algorithm25.Java.BOJ2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
2206. 벽 부수고 이동하기 (2s, 192MB)
https://www.acmicpc.net/problem/2206
- (1,1)에서 (N,M)으로 상하좌우 이동할 때, 최단 경로로 이동하고 싶다. (N,M:1000)
- 벽을 한 개까지 부수고 이동해도 된다.
최단경로를 구하라. 이동이 불가능하면 -1 출력.
0(길), 1(벽), 시작과 끝은 항상 0, 최단 경로 = 시작과 끝 칸 포함
dfs -> time out
 */

public class t0 {

    private static int N, M;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int[][] map;
    private static boolean[][] visited;
    private static boolean broken;
    private static int ans = Integer.MAX_VALUE;

    private static boolean inRange(int x, int y){
        return 0<=x && x<N && 0<=y&& y<M;
    }

    private static void dfs(int x, int y, int d){
        visited[x][y] = true;

        for (int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (inRange(nx, ny) && !visited[nx][ny]){
                if (nx == N-1 && ny == M-1){
                    ans = Math.min(ans, d + 1);
                    return;
                }

                if (map[nx][ny] == 0){     // 이동 가능하면
                    visited[nx][ny] = true;
                    dfs(nx, ny, d + 1);   // 이동
                    visited[nx][ny] = false;
                }
                else if (!broken){          // 벽인데 아직 하나도 안 부셨으면
                    visited[nx][ny] = true;
                    broken = true;          // 하나 부수고
                    dfs(nx, ny, d + 1);  // 이동
                    broken = false;
                    visited[nx][ny] = false;
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        for (int i=0; i<N; i++){
            String s = br.readLine();
            for (int j = 0; j<M; j++){
                map[i][j] = s.charAt(j) - '0';
            }
        }

         dfs(0, 0, 1);
         System.out.println(ans > 1_000_000 ? -1 : ans);

    }
}
