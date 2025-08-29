// package Algorithm.Algorithm25.Java.BOJ13459;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 13459. 구슬 탈출 (2, 512)
 *
 * 세로 N, 가로 M (3~10)
 * 구멍, 빨간 구슬, 파란 구슬 - 모두 1개씩
 * 파란 구슬을 구멍에 넣지 않으면서 빨간 구슬을 10번 이하로 기울여서(LRUD) 빼낼 수 있을까? 1:0
 * '.', '#', 'O', 'R', 'B'
 */
public class Main {

    // dx, dy: 상하좌우 (0123)
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static class Node{
        int xr, yr, xb, yb, cnt;
        Node(int xr, int yr, int xb, int yb, int cnt) {
            this.xr = xr;
            this.yr = yr;
            this.xb = xb;
            this.yb = yb;
            this.cnt = cnt;
        }
    }

    private static int N, M;
    private static char[][] board;
    private static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int xR = 0, yR = 0, xB = 0, yB = 0;
        board = new char[N][M];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'R') {
                    xR = i; yR = j;
                }
                else if (board[i][j] == 'B') {
                    xB = i; yB = j;
                }
            }
        }

        // SOL
        visited = new boolean[N][M][N][M];
        visited[xR][yR][xB][yB] = true;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(xR, yR, xB, yB, 0));

        while(!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.cnt >= 10) continue;

            // 해당 방향에 대해 장애물을 만나기 전까지 움직임
            for (int i = 0; i < 4; i++) {

                int[] rPos = roll(cur.xr, cur.yr, i);
                int[] bPos = roll(cur.xb, cur.yb, i);
                int nxt_xr = rPos[0], nxt_yr = rPos[1];
                int nxt_xb = bPos[0], nxt_yb = bPos[1];

                // B가 빠지면 어떤 경우든 실패
                if (holeIn(nxt_xb, nxt_yb)) continue;

                // B는 안 빠지고, R이 빠졌으면 성공
                if (holeIn(nxt_xr, nxt_yr)) {
                    System.out.println(1);
                    return;
                }

                // 둘 다 안 빠졌으면 다음 탐색

                // 우선 R, B 의 위치가 겹치면 위치 보정
                if (nxt_xr == nxt_xb && nxt_yr == nxt_yb) {
                    // 상: x좌표가 작은 것 먼저 // 하: x좌표가 큰 것 먼저
                    // 좌: y좌표가 작은 것 먼저 // 우: y좌표가 큰 것 먼저

                    if (i == 0) {           // 상
                        if (cur.xr < cur.xb) nxt_xb -= dx[i];   // B를 한 칸 내림
                        else nxt_xr -= dx[i];                   // R을 한 칸 내림
                    } else if (i == 1) {    // 하
                        if (cur.xr > cur.xb) nxt_xb -= dx[i];   // B를 한 칸 올림
                        else nxt_xr -= dx[i];                   // R을 한 칸 올림
                    } else if (i == 2) {    // 좌
                        if (cur.yr < cur.yb) nxt_yb -= dy[i];   // B를 한 칸 오른쪽으로
                        else nxt_yr -= dy[i];                   // R을 한 칸 오른쪽으로
                    } else if (i == 3) {    // 우
                        if (cur.yr > cur.yb) nxt_yb -= dy[i];   // B를 한 칸 왼쪽으로
                        else nxt_yr -= dy[i];                   // R을 한 칸 왼쪽으로
                    }
                }

                // 방문체크 다음 탐색
                if (!visited[nxt_xr][nxt_yr][nxt_xb][nxt_yb]) {
                    visited[nxt_xr][nxt_yr][nxt_xb][nxt_yb] = true;
                    q.add(new Node(nxt_xr, nxt_yr, nxt_xb, nxt_yb, cur.cnt + 1));
                }
            }
        }

        System.out.println(0);
    }

    private static boolean holeIn(int x, int y){
        return board[x][y] == 'O';
    }

    private static int[] roll(int x, int y, int dir) {
        int nx = x, ny = y;
        while (true) {
            int nnx = nx + dx[dir];
            int nny = ny + dy[dir];
            // 벽을 만나면 멈춤
            if (board[nnx][nny] == '#') {
                break;
            }
            // 구멍을 만나면 구멍 위치에서 멈춤
            if (board[nnx][nny] == 'O') {
                nx = nnx;
                ny = nny;
                break;
            }
            // 그 외에는 계속 이동
            nx = nnx;
            ny = nny;
        }
        return new int[]{nx, ny};   // 벽 직전 또는 구멍 위치를 반환하게 됨
    }

}
