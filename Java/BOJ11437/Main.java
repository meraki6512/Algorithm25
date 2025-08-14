package Algorithm.Algorithm25.Java.BOJ11437;

//https://www.acmicpc.net/problem/11437

import java.io.*;
import java.util.*;

/**
 * LCA (3s, 256MB)
 * 1번부터 N번까지 번호가 매겨진 N개의 정점으로 트리 (N: 2~50000)
 * 루트 = 1번
 * 두 노드의 쌍 M개(10000)
 * 두 노드의 가장 가까운 공통 조상은 몇 번 노드?
 */

public class Main {

    private static int N;
    private static List<Integer>[] tree;
    private static int[] parent, depth;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        tree = new List[N+1];
        parent = new int[N+1];
        depth = new int[N+1];
        for (int i=0; i<N+1; i++) tree[i] = new ArrayList<>();
        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            tree[v].add(u);
            tree[u].add(v);
        }

        parent[1] = -1;
        getParentAndDepth(1, 0);

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            sb.append(getLCA(v, u)).append("\n");
        }
        System.out.print(sb);

    }

    private static void getParentAndDepth(int i, int d){
        for (int n : tree[i]){
            if (parent[n] == 0){
                parent[n] = i;
                depth[n] = d + 1;
                getParentAndDepth(n, depth[n]);
            }
        }
    }

    private static int getLCA(int v, int u){

        // 같은 깊이로 맞춰준 후
        while (depth[v] > depth[u]) v = parent[v];
        while (depth[v] < depth[u]) u = parent[u];

        // 탐색 시작
        while (v != u){
            v = parent[v];
            u = parent[u];
        }

        return v;
    }
}

