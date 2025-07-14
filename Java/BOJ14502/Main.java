package Algorithm.Algorithm25.Java.BOJ14502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
14502. 연구소 (2s, 512MB)

 */

public class Main {

    private static List<Point> virus;
    private static boolean[][] visited;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int N, M;

    private static class Point{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static int dfs(int[][] map, Point p, int cnt){

        for (int i=0; i<4; i++){
            int nx = p.x + dx[i];
            int ny = p.y + dy[i];
            if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && map[nx][ny] == 0){
                visited[nx][ny] = true;
                cnt ++;
                cnt = dfs(map, new Point(nx, ny), cnt);
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        List<Point> zero = new ArrayList<>();
        virus = new ArrayList<>();
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    zero.add(new Point(i, j));
                }
                else if (map[i][j] == 2){
                    virus.add(new Point(i, j));
                }
            }
        }

        // 커봤자 10^5 * 2^8 정도니까 2초 안에 가능
        int safe = 0;
        int size = zero.size();

        for (int a = 0; a<size; a++){                   // 64 C 3 -> 4*10^4정도
            Point fp = zero.get(a);
            map[fp.x][fp.y] = 1;

            for (int b = a+1; b<size; b++){
                Point sp = zero.get(b);
                map[sp.x][sp.y] = 1;

                for (int c = b+1; c < size; c++){
                    Point tp = zero.get(c);
                    map[tp.x][tp.y] = 1;

//                    System.out.println("\n설치한 벽: ("+fp.x+", "+fp.y+"), ("+sp.x+", "+sp.y+"), ("+tp.x+", "+tp.y+")");

                    int cnt = 0;
                    visited = new boolean[N][M];
                    for (Point v : virus){              // 64 * 10
                        visited[v.x][v.y] = true;
                        cnt += dfs(map, v, 0);      // cnt: 이동 가능한 0의 개수 (virus 전염 가능한 수)
                    }
                    
                    // size - 3: 원래 0의 개수에서 새로운 벽 3개를 뺀 값
                    // 에서 cnt 만큼 빼면, 안전 구역의 수
                    // 의 최댓값
                    safe = Math.max(safe, size - 3 - cnt);

                    map[tp.x][tp.y] = 0;
                }

                map[sp.x][sp.y] = 0;
            }

            map[fp.x][fp.y] = 0;
        }

        System.out.println(safe);
    }
}
