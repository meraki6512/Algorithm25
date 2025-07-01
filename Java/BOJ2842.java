package Algorithm.Algorithm25.Java;

/*
2025-06-23-AlgorithmStudy

* 수평, 수직, 대각선으로 인접한 칸으로 이동
* 피로도 = alt 최대-최소값
* 최소 피로도를 출력하라.

* */

import java.util.*;

class Point{
    int x;
    int y;

    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class BOJ2842 {

    public static int Solution(int N, Point start, int[][] alt_map, HashSet<Point> houseList){

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int[] dx = {1, -1, 0, 0, 1, 1, -1, -1};
        int[] dy = {0, 0, 1, -1, 1, -1, 1, -1};
        boolean[][] visited = new boolean[N][N];
        visited[start.x][start.y] = true;

        Queue<Point> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()){
            Point cur = q.poll();

            if (houseList.isEmpty()) continue;
            if (visited[cur.x][cur.y]) continue;

            min = Math.min(min, alt_map[cur.x][cur.y]);
            max = Math.max(max, alt_map[cur.x][cur.y]);

            for (int i=0; i<8; i++){
                Point next = new Point(cur.x + dx[i], cur.y + dy[i]);
                if (next.x >= 0 && next.x <N && next.y>=0 && next.y <N && !visited[next.x][next.y]){

                    if (houseList.contains(next)) {
                        houseList.remove(next);
                    }

                    visited[next.x][next.y] = true;
                    q.offer(next);
                }
            }
            
        }

        return max - min;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();                   // N*N 행렬, 50

        // Input
        Point start = null;
        HashSet<Point> houseList = new HashSet<>();
        char[][] type_map = new char[N][];
        int[][] alt_map = new int[N][N];

        for (int i = 0; i<N; i++){
            type_map[i] = sc.next().toCharArray();
            for (int j = 0; j<N; j++){
                if (type_map[i][j] == 'P'){
                    start = new Point(i, j);
                }
                else if (type_map[i][j] == 'K'){
                    houseList.add(new Point(i, j));
                }
            }
        }

        for (int i = 0; i< N; i++){
            for (int j = 0; j<N; j++){
                alt_map[i][j] = sc.nextInt();
            }
        }

        System.out.println(Solution(N, start, alt_map, houseList));
    }
}

// 이게 왜 투포인터지.
// 우선순위 큐? dijkstra? 아무리봐도 bfs 느낌이 낭낭한디.
