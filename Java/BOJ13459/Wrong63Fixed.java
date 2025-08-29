package Algorithm.Algorithm25.Java.BOJ13459;

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
 *
 * -> 틀림.. 63%쯤.
 * if (!visited[cur_xr][cur_yr][cur_xb][cur_yb] && cnt < 10) 으로 cnt 체크하지 않고
 * if (cur.cnt >= 10) continue; 으로 체크하니까 성공함..
 */
public class Wrong63Fixed {

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
                // R 굴리기
                int cur_xr = cur.xr, cur_yr = cur.yr;
                int cnt_r = 0;
                boolean rInHole = false;
                while (true) {
                    int nxt_xr = cur_xr + dx[i], nxt_yr = cur_yr + dy[i];
                    if (board[nxt_xr][nxt_yr] == '#') break;
                    if (holeIn(nxt_xr, nxt_yr)) { // 굴리다가 도중에 R이 구멍에 들어간 경우
                        rInHole = true;
                        break;
                    }
                    cur_xr = nxt_xr;
                    cur_yr = nxt_yr;
                    cnt_r++;
                }
                // B 굴리기
                int cur_xb = cur.xb, cur_yb = cur.yb;
                int cnt_b = 0;
                boolean bInHole = false;
                while (true) {
                    int nxt_xb = cur_xb + dx[i], nxt_yb = cur_yb + dy[i];
                    if (board[nxt_xb][nxt_yb] == '#') break;
                    if (holeIn(nxt_xb, nxt_yb)) { // 굴리다가 도중에 B가 구멍에 들어간 경우
                        bInHole = true;
                        break;
                    }
                    cur_xb = nxt_xb;
                    cur_yb = nxt_yb;
                    cnt_b++;
                }

                // B가 구멍에 들어가면 0 출력 종료, B는 안들어가고 R만 들어가면 1 출력 종료
                if (bInHole) {
                    continue;
                }
                else if (rInHole){
                    System.out.println(1);
                    return;
                }

                // R, B가 같은 위치에 있다면
                // 늦게 온 것은 현재 방향의 반대 방향으로 한 칸 이동
                if (cur_xr == cur_xb && cur_yr == cur_yb) {
                    if (cnt_r > cnt_b) {
                        cur_xr -= dx[i];
                        cur_yr -= dy[i];
                    }
                    else if (cnt_r < cnt_b) {
                        cur_xb -= dx[i];
                        cur_yb -= dy[i];
                    }
                }

                // R, B 모두 구멍에 안 들어갔으면 방문체크, cnt 체크하고 다음 탐색
                if (!visited[cur_xr][cur_yr][cur_xb][cur_yb]) {
                    visited[cur_xr][cur_yr][cur_xb][cur_yb] = true;
                    q.add(new Node(cur_xr, cur_yr, cur_xb, cur_yb, cur.cnt + 1));
                }
            }
        }

        System.out.println(0);
    }

    private static boolean holeIn(int x, int y){
        return board[x][y] == 'O';
    }
}
