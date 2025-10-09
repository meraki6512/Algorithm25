package Algorithm.Algorithm25.Java.BOJ2251;

import java.util.*;

/**
 * 2251. 물통
 * https://www.acmicpc.net/problem/2251
 *
 * 최적화 가능 사항들
 * 1. 상태 노드 및 visited 배열 3차원 -> 2차원으로 관리
 * 2. 6가지 종류 일일이 나열 -> from, to로 for 루프 처리
 */
public class Main {

    private static final int INF = 201;
    private static boolean[] ans;
    private static boolean[][][] visited;

    private static class Node {
        int a, b, c;
        public Node(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 200
        int A = sc.nextInt(); // 0
        int B = sc.nextInt(); // 0
        int C = sc.nextInt(); // 가득

        ans = new boolean[INF];
        visited = new boolean[A+1][B+1][C+1];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,C));

        while(!q.isEmpty()){
            Node cur = q.poll();
            visited[cur.a][cur.b][cur.c] = true;
            if (cur.a == 0) {
                ans[cur.c] = true;
            }

            int na, nb, nc;

            // a->b
            na = cur.a + cur.b > B ? cur.a + cur.b - B : 0;
            nb = Math.min(cur.a + cur.b, B);
            if (!visited[na][nb][cur.c]){
                q.add(new Node(na,nb,cur.c));
            }

            // b->a
            na = Math.min(cur.a + cur.b, A);
            nb = cur.a + cur.b > A ? cur.a + cur.b - A : 0;
            if (!visited[na][nb][cur.c]){
                q.add(new Node(na,nb,cur.c));
            }

            // a->c
            na = cur.a + cur.c > C ? cur.a + cur.c - C : 0;
            nc = Math.min(cur.a + cur.c, C);
            if (!visited[na][cur.b][nc]){
                q.add(new Node(na,cur.b,nc));
            }

            // c->a
            na = Math.min(cur.a + cur.c, A);
            nc = cur.a + cur.c > A ? cur.a + cur.c - A : 0;
            if (!visited[na][cur.b][nc]){
                q.add(new Node(na,cur.b,nc));
            }

            // b->c
            nb = cur.b + cur.c > C ? cur.b + cur.c - C : 0;
            nc = Math.min(cur.b + cur.c, C);
            if (!visited[cur.a][nb][nc]){
                q.add(new Node(cur.a,nb,nc));
            }

            // c->b
            nb = Math.min(cur.b + cur.c, B);
            nc = cur.b + cur.c > B ? cur.b + cur.c - B : 0;
            if (!visited[cur.a][nb][nc]){
                q.add(new Node(cur.a,nb,nc));
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<ans.length; i++){
            if (ans[i]) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }
}
