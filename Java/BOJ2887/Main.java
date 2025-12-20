package Algorithm.Algorithm25.Java.BOJ2887;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <a href="https://www.acmicpc.net/problem/2887">2887. 행성 터널</a>
 * 추후 다시 풀기
 *
 * 모든 행성 사이에 간선을 만들어주면 O(N^2)로 시간 초과. N(N-1)/2개의 간선 저장해야하므로 메모리 초과.
 * 3*(N-1)개의 간선만 봐도 MST를 항상 만들 수 있음.
 * 축을 분리해서 보기.
 */
public class Main {

    private static int[] parent, rank;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        Point[] xPoints = new Point[N];
        Point[] yPoints = new Point[N];
        Point[] zPoints = new Point[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            xPoints[i] = new Point(i, Integer.parseInt(st.nextToken()));
            yPoints[i] = new Point(i, Integer.parseInt(st.nextToken()));
            zPoints[i] = new Point(i, Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(xPoints);
        Arrays.sort(yPoints);
        Arrays.sort(zPoints);

        ArrayList<Edge> edges = new ArrayList<>();
        Point p1, p2;
        for (int i = 0; i < N - 1; i++) {
            p1 = xPoints[i];
            p2 = xPoints[i+1];
            edges.add(new Edge(p1.idx, p2.idx, Math.abs(p1.val - p2.val)));

            p1 = yPoints[i];
            p2 = yPoints[i+1];
            edges.add(new Edge(p1.idx, p2.idx, Math.abs(p1.val - p2.val)));

            p1 = zPoints[i];
            p2 = zPoints[i+1];
            edges.add(new Edge(p1.idx, p2.idx, Math.abs(p1.val - p2.val)));
        }
        Collections.sort(edges);

        parent = new int[N];
        rank = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;
        long ans = 0;
        int cnt = 0;

        for (Edge edge : edges) {
            if (union(edge.u, edge.v)) {
                ans += edge.cost;
                cnt ++;
                if (cnt == N-1) break;
            }
        }

        System.out.println(ans);
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return false;

        if (rank[x] < rank[y]) parent[x] = y;
        else if (rank[x] > rank[y]) parent[y] = x;
        else {
            parent[y] = x;
            rank[x] ++;
        }

        return true;
    }

    private static void _union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) parent[y] = x;
    }

    private static class Point implements Comparable<Point> {
        int idx, val;
        public Point(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
        @Override
        public int compareTo(Point p) {
            return this.val - p.val;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int u, v, cost;
        public Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static class _Planet {
        int idx;
        int x, y, z;
        public _Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
