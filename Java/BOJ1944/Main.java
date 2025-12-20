package Algorithm.Algorithm25.Java.BOJ1944;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <a href="https://www.acmicpc.net/problem/1944">1944. 복제 로봇</a>
 * S와 K에서만 복제를 할 수 있음에 유의한다.
 * MST를 위한 edge를 직접 구성하는 문제.
 */
public class Main {
    private static int N, M;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    private static Point start;
    private static ArrayList<Point> points = new ArrayList<>();
    private static char[][] board;
    private static int[][] indices;
    private static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 50
        M = Integer.parseInt(st.nextToken());   // 250
        board = new char[N][N];
        indices = new int[N][N];

        int idx = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'K' || board[i][j] == 'S') {
                    points.add(new Point(i, j));
                    indices[i][j] = idx++;
                }
            }
        }

        // getDist from each points (S<->K, K<->K)
        ArrayList<Edge> edges = new ArrayList<>();
        boolean[][] visited;
        for (Point p : points) {
            Queue<Point> q = new LinkedList<>();
            q.offer(new Point(p.x, p.y, 0));
            visited = new boolean[N][N];
            visited[p.x][p.y] = true;

            while (!q.isEmpty()) {
                Point cur = q.poll();

                if (board[cur.x][cur.y] == 'S' || board[cur.x][cur.y] == 'K') {
                    int u = indices[p.x][p.y];
                    int v = indices[cur.x][cur.y];
                    if (u != v) {
                        edges.add(new Edge(u, v, cur.dist));
                    }
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || board[nx][ny] == '1') continue;
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, cur.dist + 1));
                }
            }
        }

        Collections.sort(edges);

        int ans = 0, cnt = 0;
        parent = new int[M+1];
        rank = new int[M+1];
        for (int i = 0; i <= M; i++) parent[i] = i;

        for (Edge e : edges) {
            if (union(e.u, e.v)) {
                ans += e.dist;
                cnt ++;
                if (cnt == M) break;
            }
        }

        if (cnt != M) System.out.println(-1);
        else System.out.println(ans);
    }

    private static boolean union(int u, int v) {
        u = find(u);
        v = find(v);
        if (u == v) return false;

        if (rank[u] < rank[v]) parent[u] = v;
        else if (rank[u] > rank[v]) parent[v] = u;
        else {
            rank[u]++;
            parent[v] = u;
        }
        return true;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static class Point {
        int x, y;
        int dist;
        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int u, v, dist;
        public Edge(int u, int v, int dist) {
            this.u = u;
            this.v = v;
            this.dist = dist;
        }
        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
    }
}
