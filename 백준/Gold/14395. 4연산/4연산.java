// package Algorithm.Algorithm25.Java.BOJ14395;

import java.util.*;

// 4연산
// https://www.acmicpc.net/problem/14395

public class Main {

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
            return path.length() == o.path.length() ?
                    path.compareTo(o.path):
                    path.length() - o.path.length();
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
            int x = cur.n;

            if (x == t) {
                System.out.println(cur.path);
                return;
            }

            if (!((long)x*x > t) && !visited.contains(x*x)) {
                visited.add(x*x);
                q.add(new Node(x*x, cur.path+"*"));
            }
            if (!((long)x+x> t) && !visited.contains(x+x)) {
                visited.add(x+x);
                q.add(new Node(x+x, cur.path+"+"));
            }
            if (x != 0 && !visited.contains(1)) {
                visited.add(1);
                q.add(new Node(1, cur.path+"/"));
            }

        }

        System.out.println(-1);

    }
}
