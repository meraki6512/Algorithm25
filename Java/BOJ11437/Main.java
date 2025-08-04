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

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        tree = new List[N+1];
        for (int i=0; i<N+1; i++) tree[i] = new ArrayList<>();
        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            tree[v].add(u);
            tree[u].add(v);
        }

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

    private static Integer getLCA(int v, int u){
        boolean[][] visited = new boolean[2][N+1];
        visited[0][v] = true;
        visited[1][u] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, v});
        q.add(new int[]{1, u});

        while (!q.isEmpty()){
            int[] cur = q.poll();
            int l = cur[0];
            int i = cur[1];
            if (visited[l^1][i]) return i;

            for (int ni : tree[i]){
                if (!visited[l][ni]){
                    visited[l][ni] = true;
                    q.add(new int[]{l, ni});
                }
            }
        }

        return null;
    }
}

