import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static class Point implements Comparable<Point> {
        int r, c, mirrors, dir;

        public Point(int r, int c, int mirrors, int dir) {
            this.r = r;
            this.c = c;
            this.mirrors = mirrors;
            this.dir = dir;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.mirrors, o.mirrors);
        }
    }

    private static int W, H;
    private static final int INF = 10_001;
    private static char[][] map;
    // cost[dir][r][c]: (r, c)에 dir 방향으로 도달한 최소 거울 개수
    private static int[][][] cost;
    private static PriorityQueue<Point> pq;
    private static Point c1, c2; // C 시작점과 끝점
    // 상하좌우
    private static final int[] dr = {0, -1, 1, 0, 0};
    private static final int[] dc = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'C') {
                    if (c1 == null) c1 = new Point(i, j, 0, 0);
                    else c2 = new Point(i, j, 0, 0);
                }
            }
        }

        System.out.println(solve());
    }

    private static int solve() {
        
        // 초기화
        cost = new int[5][H][W];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < H; j++) {
                Arrays.fill(cost[i][j], INF);
            }
        }
        pq = new PriorityQueue<>();
        for (int i = 1; i <= 4; i++)  updateNextPaths(c1, i, 0, 0);  // 시작 지점 c1에서 4방향으로 레이저 발사 (거울 0개)

        // bfs
        while (!pq.isEmpty()) {
            Point cur = pq.poll();

            if (cur.r == c2.r && cur.c == c2.c) return cur.mirrors;     // 끝점 c2에 도달 시 종료
            if (cost[cur.dir][cur.r][cur.c] < cur.mirrors) continue;    // 이미 더 좋은 탐색이 있었으면 스킵

            for (int n = 1; n <= 4; n++) {
                
                if ((cur.dir == 1 && n == 2) || (cur.dir == 2 && n == 1) || (cur.dir == 3 && n == 4) || (cur.dir == 4 && n == 3)) continue;  // 역방향 제외

                updateNextPaths(cur, n, 
                        cur.dir == n ? cur.mirrors : cur.mirrors + 1, 
                        cur.dir);
            }
        }

        // 최소 거울 수
        int res = INF;
        for (int i = 1; i <= 4; i++) {
            res = Math.min(res, cost[i][c2.r][c2.c]);
        }
        return res;
    }

    private static void updateNextPaths(Point cur, int d, int nextMirrors, int prevDir) {
        int nr = cur.r;
        int nc = cur.c;

        while (true) {
            nr += dr[d];
            nc += dc[d];

            // 맵을 벗어나거나 벽을 만날 경우 종료
            if (!isInRange(nr, nc) || isWall(nr, nc)) break;

            // c2 도착 시
            if (nr == c2.r && nc == c2.c) {
                if (cost[d][nr][nc] > nextMirrors) {
                    cost[d][nr][nc] = nextMirrors;
                    pq.offer(new Point(nr, nc, nextMirrors, d));
                }
                break;
            }

            // 빈 칸인 경우: 더 적은 거울 수로 경로를 찾았을 때만 갱신하고 pq에 추가
            if (cost[d][nr][nc] > nextMirrors) {
                cost[d][nr][nc] = nextMirrors;
                pq.offer(new Point(nr, nc, nextMirrors, d));
            }
        }
    }

    private static boolean isInRange(int r, int c) {
        return r >= 0 && r < H && c >= 0 && c < W;
    }

    private static boolean isWall(int r, int c) {
        return map[r][c] == '*';
    }
}