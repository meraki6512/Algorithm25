class Solution {

    private static class Edge{
        int a, b, d;
        Edge(int a, int b, int d){
            this.a = a;
            this.b = b;
            this.d = d;
        }
    }

    private static int[] parent;

    public int minCostConnectPoints(int[][] points) {
        
        int N = points.length;    // 10^3
        int[][] map = new int[N][N];
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2)-> e1.d - e2.d);

        for (int i=0; i<N; i++){
            for (int j=i+1; j<N; j++){
                map[i][j] = Math.abs(points[i][0]-points[j][0]) + Math.abs(points[i][1]-points[j][1]);
                pq.add(new Edge(i, j, map[i][j]));
            }
        }

        parent = new int[N];
        for (int i=0; i<N; i++) parent[i] = i;

        int ans = 0;
        while (!pq.isEmpty()){
            Edge cur = pq.poll();
            if (find(cur.a) != find(cur.b)){
                ans += cur.d;
                union(cur.a, cur.b);
            }
        }

        return ans;
    }

    private static int find(int x){
        if (x==parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a<b) parent[b] = a;
        else if (a>b) parent[a] = b;
    }
}