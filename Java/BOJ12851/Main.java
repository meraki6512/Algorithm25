package Algorithm.Algorithm25.Java.BOJ12851;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 12581. 숨바꼭질 2
 */

public class Main {

    private static class Node{
        int x, t;
        Node(int x, int t){
            this.x = x;
            this.t = t;
        }
    }

    private static int N, K, minTime = Integer.MAX_VALUE, cnt = 0;
    private static final int INF = 100_001;
    private static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 수빈이 위치 [0, 100_000]
        K = sc.nextInt(); // 동생 위치 [0, 100_000]
        visited = new boolean[INF];

        PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.t - o2.t);
        q.add(new Node(N, 0));

        while(!q.isEmpty()){
            Node cur = q.poll();
            if (cur.t > minTime) continue;
            if (cur.x == K) {
                if (minTime > cur.t) {
                    minTime = cur.t;
                    cnt = 1;
                }
                else cnt++;
                continue;
            }
            visited[cur.x] = true;

            if (cur.x-1 >=0 && !visited[cur.x-1]) q.add(new Node(cur.x-1, cur.t + 1));
            if (cur.x+1 <INF && !visited[cur.x+1]) q.add(new Node(cur.x+1, cur.t + 1));
            if (cur.x*2 <INF && !visited[cur.x*2]) q.add(new Node(cur.x*2, cur.t + 1));
        }

        System.out.println(minTime);
        System.out.println(cnt);
    }
}
