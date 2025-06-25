/*
 * N개의 지점                            N 500
 * 사이에 M개의 도로와                     M 2500
 * W개의 웜홀 (방향O): 시간이 거꾸로 감      W 200
 * 각 시간 T 0~10^4
 * */

import java.util.*;

/*
 * start를 지정하지 않고 음수 간선이 있는지만 파악하는 방식
 * -> INF가 무한이 아닌 일반 큰 수라서 time만 비교하게 되고, update가 이뤄짐
 * -> 통과는 하지만 비추
 *
 * -> 아예 INF를 설정하지 않고 time을 0으로 초기화한 후, N번째에 update 여부만 확인하는 방법도 있다. (안정적)
 *
 * start를 0번으로 지정하지 않을 거면
 * for loop를 통해 모든 노드에 대해 고려하면서 true일 때 즉시 빠져나오는 방식으로 하는 게 정석적임 (시간은 더 오래 걸리긴 함)
 * */

public class Main {

//    static final int INF = 10_000_000;

    private static class Node{
        int s, e, t;
        Node(int s, int e, int t){
            this.s = s;
            this.e = e;
            this.t = t;
        }
    }

    static boolean bellmanFord(int N, ArrayList<Node> nodes){

        int[] time = new int[N+1];
//        Arrays.fill(time, INF);
        boolean updated = false;

        // N-1번 반복 (time[node.s] != INF 검사하지 않음)
        for (int i=0; i<N; i++){
            for(Node node : nodes){
                if (time[node.s] + node.t < time[node.e]){
                    time[node.e] = time[node.s] + node.t;
                    updated = true;
                }
            }

            if (!updated) return false;
        }

        // 음수 사이클 있는지 판별
        for (Node node : nodes){
            if (time[node.s] + node.t < time[node.e]){
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

            System.out.println(bellmanFord(N, nodes)? "YES":"NO");
        }
    }
}
