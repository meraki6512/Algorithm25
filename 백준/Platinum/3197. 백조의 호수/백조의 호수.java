// package Algorithm.Algorithm25.Java.BOJ3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 3197. 백조의 호수
 * https://www.acmicpc.net/problem/3197
 *
 * 주의: '.'이 주어지지 않는 경우는 없는건가 ? -> 질문게시판 "백조도 물의 공간임"
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
                    q.add(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        q.add(L1);
        q.add(L2);

        bfs();
        System.out.println(findMaxMin());
    }

    private static int findMaxMin(){
        PriorityQueue<Point> pq = new PriorityQueue<>();
        int tmp = time[L1.x][L1.y] == INF ? 0 : time[L1.x][L1.y];
        pq.add(new Point(L1.x, L1.y, tmp));
        boolean[][] visited = new boolean[R][C];
        int ans = 0;

        while (!pq.isEmpty()){
            Point c = pq.poll();
            ans = Math.max(ans, c.t);

            for (int i=0; i<4; i++){
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];
                if (0<=nx && nx<R && 0<=ny && ny<C && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    if (nx == L2.x && ny == L2.y) return ans;
                    int t = time[nx][ny] == INF ? 0 : time[nx][ny];
                    pq.add(new Point(nx, ny, t));
                }
            }
        }
        return -1;
    }

    private static void bfs(){
        while (!q.isEmpty()){
            Point c = q.poll();

            for (int i=0; i<4; i++){
                int nx = c.x + dx[i];
                int ny = c.y + dy[i];

                if (0<=nx && nx <R && 0<=ny && ny <C && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    if (board[nx][ny] == 'X') {
                        board[nx][ny] = '.';
                        q.add(new Point(nx, ny, c.t + 1));
                        time[nx][ny] = Math.min(time[nx][ny], c.t + 1);
                    }
                }
            }
        }
    }
}