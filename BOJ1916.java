package Algorithm.Algorithm25;

// JAVA로 다시 풀기

import java.util.*;

public class BOJ1916 {

    static class Node implements Comparable<Node> {
        int pos, cost;
        public Node(int pos, int cost){
            this.pos = pos;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;  // ASC
        }
    }

    public static int Solution(int start, int end, int N, ArrayList<Node>[] graph){

        int[] costs = new int[N];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            // 이미 값이 더 작으면 PASS
            if (costs[cur.pos] < cur.cost) continue;
            
            // 아니면 다음 노드들 중에서 탐색
            for (Node next : graph[cur.pos]) {
                if (costs[next.pos] > cur.cost + next.cost){
                    costs[next.pos] = cur.cost + next.cost;
                    pq.offer(new Node(next.pos, costs[next.pos]));
                }
            }
        }

        return costs[end];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();   // 10^3
        int M = sc.nextInt();   // 10^5

        ArrayList<Node>[] graph = new ArrayList[N+1];
        for (int i = 1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<M; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            int c = sc.nextInt();

            graph[s].add(new Node(e, c));
        }
        
        int start = sc.nextInt();
        int end = sc.nextInt();

        // Output
        System.out.println(Solution(start, end, N, graph));

    }

}
