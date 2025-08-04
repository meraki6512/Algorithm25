package Algorithm.Algorithm25.Java.BOJ14500;

import java.util.Scanner;

/**
 * 14500. 테트로미노
 * https://www.acmicpc.net/problem/14500
 *
 */

public class Main {

    private static int N, M;        // 500
    private static int[][] board;   // 1000
    private static boolean[][] visited;   // 1000
    private static int ans = 0;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                board[i][j] = sc.nextInt();
            }
        }

        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                dfs(i, j, 0, 0);
            }
        }

        System.out.println(ans);
    }

    private static boolean inRange (int x, int y){
        return 0<= x && x<N && 0<=y && y<M;
    }

    private static void dfs(int x, int y, int cnt, int res){
        if (cnt == 3){
            if (inRange(x-1, y) && inRange(x-2, y) && visited[x-1][y] && visited[x-2][y]){
                if (inRange(x-1, y+1)) ans = Math.max(ans, res + board[x-1][y+1]);
                if (inRange(x-1, y-1)) ans = Math.max(ans, res + board[x-1][y-1]);
                return;
            }
            else if (inRange(x, y-1) & inRange(x, y-2) && visited[x][y-1] && visited[x][y-2]){
                if (inRange(x-1, y-1)) ans = Math.max(ans, res + board[x-1][y-1]);
                if (inRange(x+1, y-1)) ans = Math.max(ans, res + board[x+1][y-1]);
                return;
            }
        }
        else if (cnt == 4){
            ans = Math.max(ans, res);
            return;
        }

        for (int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (inRange(nx, ny) && !visited[nx][ny]){
                visited[nx][ny] = true;
                dfs(nx, ny, cnt + 1, res + board[nx][ny]);
                visited[nx][ny] = false;
            }
        }
    }

}
