package Algorithm.Algorithm25.Java.BOJ11725;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 11725. 트리의 부모 찾기
 * https://www.acmicpc.net/problem/11725
 */

public class Main {

    private static int N;
    private static int[] parent;
    private static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    // 10^5
        graph = new List[N+1];
        parent = new int[N+1];
        for (int i=0; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for (int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1);

        for (int i=2; i<=N; i++){
            System.out.println(parent[i]);
        }
    }

    private static void dfs(int idx){
        for (int n : graph[idx]){
            if (parent[n] == 0) {
                parent[n] = idx;
                dfs(n);
            }
        }
    }
}
