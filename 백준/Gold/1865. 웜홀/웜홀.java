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

public class Main {

    static final int INF = 10_000_000;

    static boolean bellmanFord(int N, ArrayList<Node> nodes){

        int[] time = new int[N+1];
        Arrays.fill(time, INF);
        boolean updated = false;

        for (int i=0; i<N-1; i++){
            for(Node node : nodes){
                if (node.s != INF && time[node.s] + node.t < time[node.e]){
                    time[node.e] = time[node.s] + node.t;
                    updated = true;
                }
            }

            if (!updated) return false;
        }

//        Queue<Integer> affected = new LinkedList<>();
        for (Node node : nodes){
            if (node.s != INF && time[node.s] + node.t < time[node.e]){
//                affected.add(node.e);
                return true;
            }
        }

        return false;

    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int t=0; t<TC; t++){

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

            boolean ans = bellmanFord(N, nodes);

            if (ans) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}
