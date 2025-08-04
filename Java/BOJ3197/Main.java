package Algorithm.Algorithm25.Java.BOJ3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 3197. 백조의 호수
 * https://www.acmicpc.net/problem/3197
 *
 * 주의: '.'이 주어지지 않는 경우는 없는건가 ?
 * -> 질문게시판: "백조도 물의 공간임"
 */
public class Main {

    private static int R, C; // 1~1500
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static char[][] board;
    private static boolean[][] visited;
    private static int[][] time;
    private static Queue<Point> q;
    private static Point L1, L2;
    private static final int INF = 1501;

    private static class Point implements Comparable<Point> {
        int x, y, t;
        Point(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        @Override
        public int compareTo(Point o) {
            return this.t - o.t;
        }
    }

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s;

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];
        time = new int[R][C];
        for (int i=0; i<R; i++) Arrays.fill(time[i], INF);
        q = new LinkedList<>();

        for (int i = 0; i < R; i++) {
            s = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
                // L1, L2, . 체크
                if (board[i][j] == 'L' && L1 != null) L2 = new Point(i, j, 0);
                else if (board[i][j] == 'L') L1 = new Point(i, j, 0);
                else if (board[i][j] == '.') {
                    q.add(new Point(i, j, 0));    // 물의 공간 q에 넣기
                    visited[i][j] = true;
                }
            }
        }

        q.add(L1); // 백조들도 물의 공간에 포함시키기 (L은 visited 체크 생략해도 됨)
        q.add(L2);

        bfs();                                      // time[][] 구하기
        System.out.println(findMaxMin());           // L1 -> L2로 가장 빠른 경로로 갈 때 time의 최댓값 구하기

        // print: time[][] 확인용
        StringBuilder sb = new StringBuilder();
        int y1 = Math.min(L1.y, L2.y), y2 = L1.y + L2.y - y1;
        for (int i=L1.x; i<=L2.x; i++){
            for (int j = y1; j<=y2; j++){
                sb.append(time[i][j]).append("\t\t");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    /** L1 -> L2로 가장 빠른 경로로 갈 때 time의 최댓값 구하기*/
    private static int findMaxMin(){
        PriorityQueue<Point> pq = new PriorityQueue<>();            // time 소요값 적은 순부터: PQ
        int tmp = time[L1.x][L1.y] == INF ? 0 : time[L1.x][L1.y];
        pq.add(new Point(L1.x, L1.y, tmp));                         // L1에서 시작
        boolean[][] visited = new boolean[R][C];
        int ans = 0;

        // ans (최소의 최댓값) 구하기
        while (!pq.isEmpty()){
            Point c = pq.poll();
            ans = Math.max(ans, c.t);   // 이동할 때 ans update (기존 t vs 현재 t)

            for (int i=0; i<4; i++){
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if (0<=nx && nx<R && 0<=ny && ny<C && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    if (nx == L2.x && ny == L2.y) return ans;       // L2에서 종료
                    int t = time[nx][ny] == INF ? 0 : time[nx][ny]; // 애초에 물의 공간이었던 곳은 time = 0으로 계산
                    pq.add(new Point(nx, ny, t));
                }
            }
        }
        return -1; // 조건대로 들어오면 -1을 리턴할 일은 발생하지 않음.
    }

    /** time[][] 구하기 (물 공간으로부터 t++하면서 채워줌)
     */
    private static void bfs(){
        while (!q.isEmpty()){
            Point c = q.poll();

            for (int i=0; i<4; i++){
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];

                if (0<=nx && nx <R && 0<=ny && ny <C && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    if (board[nx][ny] == 'X') {               // 빙판 녹이고
                        board[nx][ny] = '.';
                        q.add(new Point(nx, ny, c.t + 1));  // 하루 지남 (t++)
                        time[nx][ny] = Math.min(time[nx][ny], c.t + 1); // time[][] update
                    }
                }
            }
        }
    }
}