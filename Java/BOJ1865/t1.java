package Algorithm.Algorithm25.Java.BOJ1865;

/*
* N개의 지점                            N 500
* 사이에 M개의 도로와                     M 2500
* W개의 웜홀 (방향O): 시간이 거꾸로 감      W 200
* 각 시간 T 0~10^4
* */

import java.util.*;

class Node{
    int s, e, t;
    Node(int s, int e, int t){
        this.s = s;
        this.e = e;
        this.t = t;
    }
}

/*
* 모든 노드와 연결되어 있는 0번 노드가 있다고 가정하고 0번부터 start해 푼 방식
* */

public class t1 {

    static final int INF = 10_000_000;

    static boolean bellmanFord(int start, int N, ArrayList<Node> nodes){

        int[] time = new int[N+1];
        Arrays.fill(time, INF);
        time[start] = 0;
        boolean updated = false;

        // (* N-1번 (X)) N번 반복 (0번 노드가 있다고 가정하는 중)
        for (int i=0; i<N; i++){
            for(Node node : nodes){
                if (time[node.s] != INF && time[node.s] + node.t < time[node.e]){
                    time[node.e] = time[node.s] + node.t;
                    updated = true;
                }
            }

            if (!updated) return false;
        }

        // 음수 사이클 있는지 판별
        for (Node node : nodes){
            if (time[node.s] != INF && time[node.s] + node.t < time[node.e]){
                return true;
            }
        }

        return false;

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int t=0; t<TC; t++){

            boolean yes = false;
            int N = sc.nextInt();
            int M = sc.nextInt();
            int W = sc.nextInt();
            ArrayList<Node> nodes = new ArrayList<>();

            for (int i=0; i<M; i++){
                int S = sc.nextInt();
                int E = sc.nextInt();
                int T = sc.nextInt();
                nodes.add(new Node(S, E, T));
                nodes.add(new Node(E, S, T));
            }
            for (int i=0; i<W; i++) {
                int S = sc.nextInt();
                int E = sc.nextInt();
                int T = sc.nextInt();
                nodes.add(new Node(S, E, -T));
            }

            // 0번 노드가 있다고 가정: 0번에서 모든 노드에 0초로 도달 가능하도록 설정
            for (int i=1; i<=N; i++) nodes.add(new Node(0, i, 0));
            System.out.println(bellmanFord(0, N, nodes)? "YES":"NO");
        }
    }
}
