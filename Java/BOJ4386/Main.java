package Algorithm.Algorithm25.Java.BOJ4386;

/*
* 4386. 별자리 만들기
* https://www.acmicpc.net/problem/4386
* 별자리를 만드는 최소 비용
* 별의 개수 n (100)
* x, y좌표 실수 형태 (1000)
* */

import java.util.*;

public class Main {

    private static int n;
    private static List<Edge>[] graph;

    private static class Point{
        double x, y;
        public Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int idx;
        double cost;

        public Edge(int start, double cost) {
            this.idx = start;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return (int) (cost - o.cost);
        }
    }

    private static double prim(int start){

        double res = 0;
        boolean[] visited = new boolean[n+1];
        Arrays.fill(visited, false);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()){
            Edge e = pq.poll();

            if (visited[e.idx]) continue;
            visited[e.idx] = true;
            res += e.cost;

            for (Edge nxt : graph[e.idx]){
                if (!visited[nxt.idx])
                    pq.offer(nxt);
            }
        }

        return res;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        Point[] stars = new Point[n+1];
        for (int i = 1; i <= n; i++) {
            stars[i] = new Point(sc.nextDouble(), sc.nextDouble());
        }

        graph = new List[n+1];
        for (int i=0; i<=n; i++) graph[i] = new ArrayList<>();

        for (int i=1; i<=n; i++) {
            Point p = stars[i];
            for (int j = i+1; j<=n; j++) {
                Point q = stars[j];
                double c = Math.sqrt(Math.pow((p.x - q.x), 2) + Math.pow((p.y - q.y), 2));
                graph[i].add(new Edge(j, c));
                graph[j].add(new Edge(i, c));
            }
        }

        System.out.printf("%.2f\n",prim(1));
    }
}
