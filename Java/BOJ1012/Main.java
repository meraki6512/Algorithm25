package Algorithm.Algorithm25.Java.BOJ1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 1012. 유기농 배추
 * https://www.acmicpc.net/problem/1012
 *
 *  궁금해서 test_dfs, dfs 둘 다 만들어봄.
 *  -> 이론적으로 빅오 시간복잡도는 둘 다 O(MN)이지만
 *  test_dfs가 매번 4방향 재귀 호출을 하므로 불필요한 계산이 더 많을 수 있다.
 *  -> 그러나 현재 실행 케이스 상에서는 큰 차이 없다. (10ms 정도?)
 *  대규모 데이터 상에서는 주의할 필요는 있을듯.
 */

public class Main {
    private static int M, N, K;
    private static boolean[][] map, visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new boolean[M][N];
            visited = new boolean[M][N];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[x][y] = true;
            }

            int cnt = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] && !visited[i][j]) {
                        dfs(i, j);
                        cnt++;
                    }
                }
            }

            System.out.println(cnt);

        }
                
    }

    private static void test_dfs(int x, int y) {
        if (!(0<=x && x<M && 0<=y && y<N && !visited[x][y] && map[x][y])) return;

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            test_dfs(nx, ny);
        }
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0<=nx && nx<M && 0<=ny && ny<N && !visited[nx][ny] && map[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }
}
