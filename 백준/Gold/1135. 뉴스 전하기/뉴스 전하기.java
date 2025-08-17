// package Algorithm.Algorithm25.Java.BOJ1135;

/*
* 뉴스 전하기 (2, 128)
* https://www.acmicpc.net/problem/1135
* Greedy + Tree
 */

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    private static ArrayList<Integer>[] sub;
    private static int[] dp;
    static List<Integer> subCnt = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sub = new ArrayList[N];
        dp = new int[N];
        for (int i=0; i<N; i++) sub[i] = new ArrayList<>();

        sc.nextInt();
        for (int i=1; i<N; i++){
            sub[sc.nextInt()].add(i);
        }

        System.out.println(dfs(0));
    }

    private static class Node implements Comparable<Node>{
        int idx, val;
        Node(int idx, int val){
            this.idx = idx;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return o.val-this.val;  // 내림차순
        }
    }

    private static int dfs(int cur){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int n : sub[cur]){
            pq.add(new Node(n, dfs(n)));
        }

        int cnt = 1, max = 0;
        while (!pq.isEmpty()){
            Node n = pq.poll();
            max = Math.max(max, n.val + cnt++);
        }

        return max;
    }

}
