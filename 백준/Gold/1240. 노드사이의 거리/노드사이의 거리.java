// package Algorithm.Algorithm25.Java.BOJ1240;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1240. 노드사이의 거리
 * https://www.acmicpc.net/problem/1240
 * tree 어렵다
 */

public class Main {

    private static int N, M, ans = 0;
    private static List<Node>[] tree;
    private static boolean[] visited;

    private static class Node{
        int i, d;
        Node(int i, int d){
            this.i = i;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 1000
        M = Integer.parseInt(st.nextToken());   // 1000
        visited = new boolean[N+1];
        tree = new List[N+1];
        for (int i=0; i<=N; i++) tree[i] = new ArrayList<>();

        for (int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            tree[a].add(new Node(b, d));
            tree[b].add(new Node(a, d));
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ans = 0;
            Arrays.fill(visited, false);
            visited[a] = true;
            dfs(a, b, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int a, int target, int d){
        if (a == target){
            ans = d;
            return;
        }

        for (Node n : tree[a]){
            if (!visited[n.i] && ans==0){
                visited[n.i] = true;
                dfs(n.i, target, d + n.d);
            }
        }

    }
}
