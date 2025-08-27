// package Algorithm.Algorithm25.Java.BOJ7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 7569. 토마토 (1s, 256)
 * https://www.acmicpc.net/problem/7569
 */

public class Main {
    private static int M, N, H;
    private static int[][][] box;
    private static int[] dx = {0, 0, 0, 0, -1, 1};
    private static int[] dy = {0, 0, -1, 1, 0, 0};
    private static int[] dz = {-1, 1, 0, 0, 0, 0};
    private static Queue<Point> q = new LinkedList<>();

    private static class Point {
        int h, n, m;
        Point(int h, int n, int m) {
            this.h = h;
            this.n = n;
            this.m = m;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        box = new int[H+1][N+1][M+1];

        for(int i=1; i<=H; i++) {
            for (int j=1; j<=N; j++) {
                st = new StringTokenizer(br.readLine());
                for(int k=1; k<=M; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if (box[i][j][k] == 1) q.offer(new Point(i, j, k));
                }
            }
        }

        // 익히기
        bfs();

        // 토마토가 다 익은 후에 최소 일수 구하기
        int max = 0;
        for(int i=1; i<=H; i++) {
            for(int j=1; j<=N; j++) {
                for(int k=1; k<=M; k++) {
                    // 안 익은게 있으면 -1 출력 후 종료
                    if (box[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    // 익은 날짜 최댓값 업데이트
                    max = Math.max(max, box[i][j][k]);
                }
            }
        }

        // max == 1 ? 처음부터 다 익어있는 상태 -> 0 출력 : 아니면 1부터 시작했으므로 1빼주고 출력
        System.out.println(max == 1? 0: max-1);

    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=H; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= M; k++) {
                    sb.append(box[i][j][k]).append(" ");
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        while(!q.isEmpty()) {
            Point p = q.poll();
            int h = p.h, n = p.n, m = p.m;  // 높이, 행, 열

            for (int i=0; i<6; i++) {
                int nh = h + dx[i];
                int nn = n + dy[i];
                int nm = m + dz[i];

                if (inRange(nh, nn, nm) && box[nh][nn][nm] == 0) {
                    q.add(new Point(nh, nn, nm));
                    box[nh][nn][nm] = box[h][n][m] + 1;
                }
            }
        }
    }

    private static boolean inRange(int h, int n, int m) {
        return 1<=m && m<=M && 1<=n && n<=N && 1<=h && h<=H;
    }
}
