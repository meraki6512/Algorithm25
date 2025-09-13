// package Algorithm.Algorithm25.Java.BOJ11967;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static ArrayList<Point>[][] graph;
    private static boolean[][] visited, possible, turnOn;
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1][N+1];
        visited = new boolean[N+1][N+1];
        possible = new boolean[N+1][N+1];
        turnOn = new boolean[N+1][N+1];
        for(int i=1; i<=N; i++) {
            for (int j=1; j<=N; j++) {
                graph[i][j] = new ArrayList<Point>();
            }
        }

        for (int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[x][y].add(new Point(a,b));
        }

        // 불 켤 수 있는 방만 탐색하기
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(1,1));
        turnOn[1][1] = true;
        visited[1][1] = true;
        boolean[][] canReach = new boolean[N+1][N+1];

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (Point n : graph[p.x][p.y]) {
                turnOn[n.x][n.y] = true;
                if (!visited[n.x][n.y] && canReach[n.x][n.y]) {
                    visited[n.x][n.y] = true;
                    q.add(new Point(n.x,n.y));
                }
            }
            
//            print(turnOn, "turnOn");
//            print(visited, "visited");
//            print(canReach, "canReach");

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (!inRange(nx, ny)) continue;
                canReach[nx][ny] = true;

                if (!visited[nx][ny] && turnOn[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Point(nx,ny));
                }
            }
        }

        // 실제로 불 켜는 방의 개수 세기
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (turnOn[i][j]) {
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    private static boolean inRange(int x, int y) {
        return 0<x && x<=N && 0<y && y<=N;
    }

    private static void print(boolean[][] arr, String name){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------");
        sb.append(name).append("-------------\n");
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sb.append(arr[i][j] ? '1' : '0');
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void printHashSet(HashSet<Point> set, String name){
        StringBuilder sb = new StringBuilder();
        sb.append("-------------");
        sb.append(name).append("-------------\n");
        for (Point p : set) {
            sb.append(p.x).append(" ").append(p.y).append("\n");
        }
        System.out.println(sb.toString());
    }
}
