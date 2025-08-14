package Algorithm.Algorithm25.Java.BOJ14395;

import java.util.*;

// 4연산
// https://www.acmicpc.net/problem/14395

public class Main {

    // 반례:
    // 입력: 7 4
    // 오답: */+*
    // 출력: /+*

    private static final int INF = 1_000_000_001;
    private static Set<Integer> visited = new HashSet<>();

    private static class Node implements Comparable<Node>{
        int n;
        String path;
        Node(int n, String path){
            this.n = n;
            this.path = path;
        }

        @Override
        public int compareTo(Node o) {
            return path.compareTo(o.path);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int t = sc.nextInt();

        if (s==t) {
            System.out.println(0);
            return;
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(s, ""));
        visited.add(s);

        while (!q.isEmpty()){
            Node cur = q.poll();

            if (cur.n == t) {
                System.out.println(cur.path);
                return;
            }

            if (!(cur.n * cur.n > INF) && !visited.contains(cur.n * cur.n)) {
                visited.add(cur.n*cur.n);
                q.add(new Node(cur.n * cur.n, cur.path+"*"));
            }
            if (!(cur.n + cur.n > INF) && !visited.contains(cur.n + cur.n)) {
                visited.add(cur.n+cur.n);
                q.add(new Node(cur.n + cur.n, cur.path+"+"));
            }
            if (!visited.contains(0)) {
                visited.add(0);
                if (t != 0) continue;
                System.out.println(cur.path+"-");
            }
            if (cur.n != 0 && !visited.contains(1)) {
                visited.add(1);
                q.add(new Node(1, cur.path+"/"));
            }

        }

        System.out.println(-1);

    }
}
