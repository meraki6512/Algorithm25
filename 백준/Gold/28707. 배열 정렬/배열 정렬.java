/*
 * 배열 정렬
 * https://www.acmicpc.net/problem/28707
 *
 * 길이가 N인 양의 정수로 이뤄진 배열 A (N: 2-8, 요소: 1-10)
 * 배열을 비내림차순으로 정렬하기 위해서 다음 M가지 조작을 순서, 횟수에 무관하게 원하는 만큼 할 수 있음
 *   [비용 c[i]를 들여 A의 l[i]번째 수와 r[i]번째 수를 바꿈] (M: 1-10, c[i]: 1-10)
 * 이때 필요한 비용 총합의 최솟값을 출력하라.
 * 불가능할 경우 -1
 * */

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static class Op{
        int l, r, c;
        Op(int l, int r, int c){
            this.l = l;
            this.r = r;
            this.c = c;
        }
    }

    static class Node implements Comparable<Node>{
        int[] num;
        int cost;
        Node(int[] num, int cost){
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    public static void main(String[] args) {

        // In
        int N, M;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] A = new int[N];
        for (int i = 0; i<N; i++) A[i] = sc.nextInt();
        int[] ordered = Arrays.stream(A).sorted().toArray();

        M = sc.nextInt();
        Op[] ops = new Op[M];
        for (int i=0; i<M; i++) {
            int l = sc.nextInt() -1;    // 0-based
            int r = sc.nextInt() -1;
            int c = sc.nextInt();
            ops[i] = new Op(l, r, c);
        }

        //Sol
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(A,0));
        Set<List<Integer>> set = new HashSet<>();

        while (!que.isEmpty()){
            Node cur = que.poll();

            List<Integer> curKey = Arrays.stream(cur.num).boxed().collect(Collectors.toList());
            if (set.contains(curKey)) continue;
            set.add(curKey);

            if (Arrays.equals(cur.num, ordered)) {
                System.out.println(cur.cost);
                return;
            }

            for (Op op : ops){
                int[] nxt = Arrays.copyOf(cur.num, N);
                int tmp = nxt[op.l];
                nxt[op.l] = nxt[op.r];
                nxt[op.r] = tmp;
                que.add(new Node(nxt, cur.cost + op.c));
            }
        }

        System.out.println(-1);

    }
}