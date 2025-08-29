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
 * -> 틀림... 63%쯤.
 * if (!visited[cur_xr][cur_yr][cur_xb][cur_yb] && cnt < 10) 으로 cnt 체크하지 않고
 * if (cur.cnt >= 10) continue; 으로 체크하니까 성공함..
 */
public class Wrong63_2_Fixed {

    // 상하좌우
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

                int nxt_xr = cur.xr, nxt_yr = cur.yr;
                int nxt_xb = cur.xb, nxt_yb = cur.yb;

                // 우선 R, B 중에 먼저 굴릴 것 찾기
                // dx, dy: 상하좌우 (0123)
                // 상: x좌표가 작은 것 먼저
                // 하: x좌표가 큰 것 먼저
                // 좌: y좌표가 작은 것 먼저
                // 우: y좌표가 큰 것 먼저

                boolean rFirst = false;
                if (i == 0) rFirst = (cur.xr < cur.xb);         // 상
                else if (i == 1) rFirst = (cur.xr > cur.xb);    // 하
                else if (i == 2) rFirst = (cur.yr < cur.yb);    // 좌
                else if (i == 3) rFirst = (cur.yr > cur.yb);    // 우

                if (rFirst) {
                    // R 굴리기
                    int[] rPos = roll(nxt_xr, nxt_yr, i);
                    nxt_xr = rPos[0];
                    nxt_yr = rPos[1];

                    // B 굴리기
                    int[] bPos = roll(nxt_xb, nxt_yb, i);
                    if (holeIn(bPos[0], bPos[1])) continue;
                    if (nxt_xr == bPos[0] && nxt_yr == bPos[1]) {
                        // B가 R을 지난다면
                        if(!holeIn(nxt_xr, nxt_yr)){    // R이 구멍에 빠지지 않았을 때만
                            nxt_xb = nxt_xr - dx[i];    // 한 칸 전으로 보정
                            nxt_yb = nxt_yr - dy[i];
                        }
                    } else{
                        nxt_xb = bPos[0];
                        nxt_yb = bPos[1];
                    }
                }
                else{
                    // B 굴리기
                    int[] bPos = roll(nxt_xb, nxt_yb, i);
                    if (holeIn(bPos[0], bPos[1])) continue;
                    nxt_xb = bPos[0];
                    nxt_yb = bPos[1];

                    // R 굴리기
                    int[] rPos = roll(nxt_xr, nxt_yr, i);
                    if (nxt_xb == rPos[0] && nxt_yb == rPos[1]) {
                        // R이 B를 지난다면
                        if(!holeIn(nxt_xb, nxt_yb)){    // B가 구멍에 빠지지 않았을 때만
                            nxt_xr = nxt_xb - dx[i];    // 한 칸 전으로 보정
                            nxt_yr = nxt_yb - dy[i];
                        }
                    } else {
                        nxt_xr = rPos[0];
                        nxt_yr = rPos[1];
                    }
                }

                // 이동 후 결과 확인
                boolean bInHole = holeIn(nxt_xb, nxt_yb);
                boolean rInHole = holeIn(nxt_xr, nxt_yr);

                // 파란 구슬이 빠지면 실패이므로 이번 건 무시
                if (bInHole) continue;

                // 빨간 구슬만 빠지면 성공! 1 출력 후 종료
                if (rInHole) {
                    System.out.println(1);
                    return;
                }

                // R, B 모두 구멍에 안 들어갔으면 방문체크, cnt 체크하고 다음 탐색
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
        return new int[]{nx, ny};
    }

}
