import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 다리 만들기 2
* https://www.acmicpc.net/problem/17472
* 지도의 세로 크기 N과 가로 크기 M (각10)
* 지도의 정보: 0 또는 1 (0은 바다, 1은 땅)
* 다리 길이는 섬과 섬 칸의 거리
* 모든 섬을 연결하는 다리를 만드려고 할 때, 다리가 휘면 안되고, 길이가 2이상이어야 하고, 양끝이 인접해야함
* 다리끼리 교차하는 경우 다리 길이는 일정
* 모든 섬을 연결하는 다리 길이의 최솟값을 출력한다. 모든 섬을 연결하는 것이 불가능하면 -1을 출력
*/

public class Main {

    private static int[][] graph;
    private static int N, M;
    private static Queue<Point> q;
    private static boolean[][] visited;
    private static final int[] dx = new int[]{0, 0, 1, -1};
    private static final int[] dy = new int[]{1, -1, 0, 0};
    private static PriorityQueue<Edge> edges;
    private static int[] parent;
    private static int root;
    private static final int INF = 101;

    private static class Edge implements Comparable<Edge>{

        int idx1, idx2;
        int cost;

        public Edge(int idx1, int idx2, int cost) {
            this.idx1 = idx1;
            this.idx2 = idx2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    private static class Point implements Comparable<Point>{
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return (x == o.x) ? y - o.y : x - o.x;
        }


        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point p = (Point) obj;
            return x == p.x && y == p.y;
        }
    }

    // 섬 그루핑
    private static TreeSet<Point> bfs(Point p){

        q = new LinkedList<>();
        visited[p.x][p.y] = true;
        q.add(p);
        TreeSet<Point> set = new TreeSet<>();
        set.add(p);

        while(!q.isEmpty()){
            Point cur = q.poll();

            for (int i=0; i<4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < M){
                    if (!visited[nx][ny] && graph[nx][ny] == 1){
                        visited[nx][ny] = true;
                        q.offer(new Point(nx, ny));
                        set.add(new Point(nx, ny));
                    }
                }
            }
        }

        return set;
    }

    private static int find(int x){
        if (x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a < b) parent[b] = a;
        else if (a > b) parent[a] = b;
    }

    private static int kruskal(int n){
        int ans = 0;
        root = n;

        while (!edges.isEmpty()){
            Edge e = edges.poll();

            if (find(e.idx1) != find(e.idx2)){
                union(e.idx1, e.idx2);
                ans += e.cost;
                root --;
            }
        }

        return ans;
    }

//    // 상하 - y값 범위가 겹치는게 있으면 dist = x 큰거 - x 작은거 - 1
//    private static int checkHorizontal(Island ai, Island bi){
//        int res = -1;
//
//        // ai.top이 bi의 top과 bottom 사이에 있거나
//        // bi.top이 ai의 top과 bottom 사이에 있으면
//        if ((bi.leftTop.y <= ai.leftTop.y && ai.leftTop.y <= bi.rightBottom.y) ||
//            (ai.leftTop.y <= bi.leftTop.y && bi.leftTop.y <= ai.rightBottom.y))
//        {
//            // 그 간격만큼 리턴
//            res = Math.max(bi.leftTop.x - ai.rightBottom.x,
//                     ai.leftTop.x - bi.rightBottom.x) - 1;
//        }
//
//        return res;
//    }
//
//    // 좌우 - x값 범위가 겹치는게 있으면 dist = y 큰거 - y 작은거 - 1
//    private static int checkVertical(Island ai, Island bi){
//        int res = -1;
//
//        // ai의 left가 bi의 left와 right 사이에 있거나
//        // bi의 left가 ai의 left와 right 사이에 있으면
//        if ((bi.leftTop.x <= ai.leftTop.x && ai.leftTop.x <= bi.rightBottom.x) ||
//            (ai.leftTop.x <= bi.leftTop.x && bi.leftTop.x <= ai.rightBottom.x))
//        {
//            // 그 간격만큼 리턴
//            res = Math.max(bi.leftTop.y-ai.rightBottom.y,
//                    ai.leftTop.y-bi.rightBottom.y) - 1;
//        }
//
//        return res;
//    }

    private static int getDistBetween(TreeSet<Point> ai, TreeSet<Point> bi){
        int res = INF;

        for (Point p : ai){
            // 섬 ai에서 출발해 가까운 섬 좌표 찾기
            
            for (int i = 0; i < 4 ; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (graph[nx][ny] != 0) continue;

                // 현재 방향으로 움직이면서 가까운 섬과의 거리 계산하기
                int len = 1;
                int cx = nx + dx[i];
                int cy = ny + dy[i];
                while (!(cx<0 || cx>=N || cy<0 || cy>=M)){
                    if (graph[cx][cy] == 0) {   // 바다일 때만
                        len++;                  // 움직임
                        cx += dx[i];            // 움직이던 방향으로 계속 이동
                        cy += dy[i];
                    } else {
                        // 도착한 곳이 bi 섬이라면, 여태 움직인 길이가 2 이상일 경우, 다리 길이 업데이트
                        if (bi.contains(new Point(cx, cy)) && len >= 2) {
                            res = Math.min(res, len);
                        }
                        break;
                    }
                }
                
            }
        }

        return res == INF ? -1 : res;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 세로 크기 (행의 개수)
        M = Integer.parseInt(st.nextToken());   // 가로 크기 (열의 개수)

        List<Point> points = new ArrayList<>();
        graph = new int[N][M];
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 1) points.add(new Point(i, j));
            }
        }

        // bfs -> 섬의 개수를 먼저 구하자
        // (동시에 섬의 위치 Points도 저장)
        List<TreeSet<Point>> islands = new ArrayList<>();
        visited = new boolean[N][M];
        for (Point p : points) {
            if (!visited[p.x][p.y]) {
                islands.add(bfs(p));
            }
        }

        // 섬의 idx를 노드로 잡고 (최대 6개)
        int n = islands.size();
        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        // 엣지를 가능한 경우만큼 만들어서
        edges = new PriorityQueue<>();
        int dist;
        for (int i = 0; i < n; i++){
            TreeSet<Point> ai = islands.get(i);

            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                TreeSet<Point> bi = islands.get(j);

                // 거리가 2이상인 서로 닿는 길이 있으면 엣지로 추가
                dist = getDistBetween(ai, bi);
                if (dist > 0) edges.add(new Edge(i, j, dist));
            }
        }

        // 엣지가 있으면 MST를 계산하자
        if (edges.isEmpty()) {
            System.out.println(-1);
            return;
        }
        int t = kruskal(n);
        System.out.println(root == 1 ? t : -1);

    }

}

/*
* 수직 수평만 고려하는 경우
* : 예제 입력 3과 같은 경우에서 무조건 틀림 (처음에 섬도 안 휘어지는 줄 알고 풀었음)
* : 섬은 휘어질 수 있음.
        *
        * -> 섬의 points 별로 bfs 한 번 더 해야 함
* 각 섬 points 간 바다 거리를 계산해서, 그게 2 이상일 경우만 고려해, 최단 거리를 찾아내야 함
* : 그게 곧 mst를 찾는 edges가 될 것임
* */