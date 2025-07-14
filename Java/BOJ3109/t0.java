package Algorithm.Algorithm25.Java.BOJ3109;

/*
3109. 빵집
https://www.acmicpc.net/problem/3109
1s, 256MB

R*C 격자 (첫번째 열: 다른 가게 가스관, 마지막 열: 빵집)
가스관과 빵집을 연결하는 파이프를 설치하자.
- 사이에 건물('X')이 있는 경우에는 파이프를 놓을 수 없다.
- 첫째열에서 시작해 오른쪽옆, 대각선으로 이동해 마지막 열에서 끝나야 한다.

파이프를 여러 개 설치할 때, 각 칸을 지나는 파이프는 1개여야 한다.
-> 파이프라인의 최대 개수를 구하라.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class t0 {

    private static class Point{
        int r, c;
        Point(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    private static void print(int R, int C, int[][] graph, boolean[][] visited){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++){
                if (graph[i][j]==1 || visited[i][j]) sb.append("x ");
                else sb.append(". ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int ans = 0;
        boolean[][] visited = new boolean[R][C];
        int[][] graph = new int[R][C];

        // 0: empty, 1: building, pipe
        for (int i = 0; i< R; i++){
            char[] s = br.readLine().toCharArray();
            for (int j = 0; j < C; j++){
                if (s[j] == 'x') graph[i][j] = 1;
            }
        }

        // 위에서부터 시작해서, 대각선위->옆->대각선아래 greedy 이동
        // 마지막에 도달할 수 있으면 개수++
        for (int row=0; row<R; row++){
            Stack<Point> stack = new Stack<>();
            Stack<Point> track = new Stack<>();
            Point start = new Point(row, 0);
            stack.push(start);
            track.push(start);

            while (!stack.isEmpty()){
                Point p = stack.pop();

                if (p.c == C-1){
                    if (!visited[p.r][p.c]) {
                        while (!track.isEmpty()) {
                            Point tmp = track.pop();
                            visited[tmp.r][tmp.c] = true;
                        }
                        ans++;
                    }
                    break;
                }

                if (p.r-1 >=0 && graph[p.r-1][p.c+1] == 0 && !visited[p.r-1][p.c+1]) {
                    Point nxt = new Point(p.r-1, p.c+1);
                    stack.push(nxt);
                    track.push(nxt);
                }
                else if (graph[p.r][p.c+1] == 0 && !visited[p.r][p.c+1]) {
                    Point nxt = new Point(p.r, p.c+1);
                    stack.push(nxt);
                    track.push(nxt);
                }
                else if (p.r+1 < R && graph[p.r+1][p.c+1] == 0 && !visited[p.r+1][p.c+1]) {
                    Point nxt = new Point(p.r+1, p.c+1);
                    stack.push(nxt);
                    track.push(nxt);
                }
                else {
                    visited[p.r][p.c] = true;
                    if (!track.isEmpty()) stack.push(track.pop());
                }
            }
//            print(R, C, graph, visited);
        }

        System.out.println(ans);
    }
}
