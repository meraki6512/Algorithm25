package Algorithm.Algorithm25.Java.BOJ2206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
2206. 벽 부수고 이동하기 (2s, 192MB)
https://www.acmicpc.net/problem/2206
- (1,1)에서 (N,M)으로 상하좌우 이동할 때, 최단 경로로 이동하고 싶다. (N,M:1000)
- 벽을 한 개까지 부수고 이동해도 된다.
최단경로를 구하라. 이동이 불가능하면 -1 출력.
0(길), 1(벽), 시작과 끝은 항상 0, 최단 경로 = 시작과 끝 칸 포함

시간 문제 때문에 bfs를 사용해야 하는 건 맞는데
status가 있어서 visited를 단순 2차원 배열 하나로 사용하면 관리가 어려움
2차원 visited 배열 2개를 쓰거나, 3차원 visited 배열을 써야 한다. (map * status)
 */

public class Main {

    private static int N, M;
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};
    private static int[][] map;
    private static boolean[][][] visited;   // 벽을 부셨는지 아닌지까지 관리해야 함

    private static boolean inRange(int x, int y){
        return 0<=x && x<N && 0<=y&& y<M;
    }

    private static class Node{
        int x, y, d, broken;
        Node(int x, int y, int d, int broken){
            this.x = x;
            this.y = y;
            this.d = d;
            this.broken = broken;
        }
    }

    private static int bfs(int x, int y, int d, int broken){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y, d, broken));
        visited[x][y][broken] = true;

        while (!q.isEmpty()){
            Node cur = q.poll();

            for (int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (inRange(nx, ny)){
                    if (nx == N-1 && ny == M-1){
                        return cur.d + 1;
                    }

                    // 이동 가능 시 (+ 현재 broken 상태로 방문 안했을 경우)
                    if (map[nx][ny] == 0 && !visited[nx][ny][cur.broken]){
                        q.add(new Node(nx, ny, cur.d + 1, cur.broken));
                        visited[nx][ny][cur.broken] = true;
                    }
                    // 이동 불가능 인데, 아직 !broken 일 시 (+ broken 상태로 방문 안했을 경우)
                    else if (map[nx][ny] == 1 && cur.broken == 0 && !visited[nx][ny][1]){
                        q.add(new Node(nx, ny, cur.d + 1, 1));
                        visited[nx][ny][1] = true;
                    }
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2];

        if (N == 1 && M == 1) {
            System.out.println(1);
            return;
        }

        for (int i=0; i<N; i++){
            String s = br.readLine();
            for (int j = 0; j<M; j++){
                map[i][j] = s.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0, 1, 0));
    }
}
