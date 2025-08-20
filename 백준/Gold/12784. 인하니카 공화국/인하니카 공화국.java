// package Algorithm.Algorithm25.Java.BOJ12784;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 인하니카 공화국 (1, 256)
 * https://www.acmicpc.net/problem/12784
 *
 * 두 섬을 연결하는 다리를 최소한의 개수로 만들어 모든 섬 간의 왕래가 가능하다 == 트리!
 * (트리 중) 다리가 하나밖에 없는 어느 섬 == 루트 노드 || 리프 노드
 */

public class Main {

    private static class Node{
        int x, size;
        Node(int x, int size){
            this.x = x;
            this.size = size;
        }
    }

    private static int N, M, INF = 20001;
    private static ArrayList<Node>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc<T; tc++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            tree = new ArrayList[N+1];
            for (int i = 0; i < N + 1; i++) tree[i] = new ArrayList<>();
            for (int m=0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                tree[a].add(new Node(b, s));
                tree[b].add(new Node(a, s));
            }

            // 예제를 기준으로
            // 2번을 끊냐, 한 depth 더 가서 3번과 4번을 끊냐 == 1 < 5(4+1)이니까 [1]선택
            // 5번을 끊냐, 한 depth 더 가서 6번과 7번을 끊냐 == 4 > 3(1+2)이니까 [3]선택
            // depth가 더 깊어질수록 계속 비교를 해야 한다..?
            // post order

            int res = dfs(1, 0, INF);
            System.out.println(res == INF ? 0 : res);   // 섬 1번만 있는 경우 예외 처리

        }
    }

    private static int dfs(int cur, int parent, int size){
        int sum = 0;
        for (Node n: tree[cur]){
            if (n.x!=parent){
                sum += dfs(n.x, cur, n.size);
            }
        }

        // 더한 값이 0이면 Leaf 노드: 해당 다리 dynamite size 리턴
        if (sum == 0) return size;
        // 아니면 연결된 전체 다리 dynamite size 합과 해당 다리 dynamite size 비교해 작은 값 리턴
        return Math.min(sum, size);
    }

}
