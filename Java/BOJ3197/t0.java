package Algorithm.Algorithm25.Java.BOJ3197;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class t0 {

    private static int R, C; // 1~1500
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static char[][] board;
    private static int[][] time;
    private static Point L1, L2;
    private static boolean[][] visited;
    private static final int INF = 1501;

    private static class Point implements Comparable<Point> {
        int x, y;
        int time;
        Point(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            return this.time - o.time;
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
        for (int i=0; i<R; i++){
            Arrays.fill(time[i], INF);
        }

        for (int i=0; i<R; i++){
            s = br.readLine();
            for (int j=0; j<C; j++){
                board[i][j] = s.charAt(j);

                if (board[i][j] == 'L') {
                    if (L1 != null) L2 = new Point(i, j, 0);
                    else L1 = new Point(i, j, 0);
                }
            }
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.add(L1);
        pq.add(L2);
        visited[L1.x][L1.y] = visited[L2.x][L2.y] = true;

        while (!pq.isEmpty()){
            Point cur = pq.poll();

            for (int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (0 <= nx && nx < R && 0 <= ny && ny < C){
                    if (!visited[nx][ny]){
                        visited[nx][ny] = true;

                        if (board[nx][ny] == '.'){
                            time[nx][ny] = Math.min(cur.time, time[nx][ny]);
                            pq.add(new Point(nx, ny, time[nx][ny]));
                        }
                        else if (board[nx][ny] == 'X'){
                            time[nx][ny] = Math.min(cur.time + 1, time[nx][ny]);
                            pq.add(new Point(nx, ny, time[nx][ny]));
                        }
                    }
                }
            }
        }

//        for (int i=0; i<R; i++){
//            System.out.println(Arrays.toString(time[i]));
//        }

        System.out.println(getDist());
    }

    private static int getDist(){
        int ans = 0;
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.add(new Point(L1.x, L1.y, time[L1.x][L1.y]));
        for (int i=0; i<R; i++){
            Arrays.fill(visited[i], false);
        }
        visited[L1.x][L1.y] = true;

        while (!q.isEmpty()){
            Point cur = q.poll();

            if (!(cur.x == L1.x && cur.y == L1.y)) ans = Math.max(ans, cur.time);

//            StringBuilder sb = new StringBuilder();
//            for (int i=0; i<R; i++){
//                for (int j=0; j<C; j++){
//                    sb.append(visited[i][j] ? "1" : "0");
//                }
//                sb.append("\n");
//            }
//            System.out.println(sb.append("\n"));

            for (int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (0<=nx && nx<R && 0<=ny && ny <C && !visited[nx][ny]){
                    if (nx == L2.x && ny == L2.y) return ans;
                    else {
                        visited[nx][ny] = true;
                        q.add(new Point(nx, ny, time[nx][ny]));
                    }
                }
            }
        }

        return -1;
    }

}

// L부터 시작하지 말고
// .부터 시작해서 계산하는 걸로 다시 짜보기

/**3 7 (2)
 XXXXXXX
 XX.XXXX
 LXXXXXL

 XX.XXXX
 X...XXX
 LX.XXXL

 X...XXX
 .....XX
 L...XXL
 */

/**8 2 (1)
 .L
 X.
 .X
 XX
 ..
 XX
 ..
 .L

 .L
 ..
 ..
 ..
 ..
 ..
 ..
 .L
 */