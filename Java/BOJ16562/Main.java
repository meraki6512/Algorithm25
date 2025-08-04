package Algorithm.Algorithm25.Java.BOJ16562;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
16562. 친구비 (2s, 512MB)
https://www.acmicpc.net/problem/16562
union-find 로 풀면 될 듯
 */

public class Main {

    private static int[] A;
    private static int[] parent;

    private static int find(int x){
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b){
        a = find(a);
        b = find(b);

        if (a != b){
            if (A[a] <= A[b]){
                A[b] = 0;
                parent[b] = a;
            }
            else {
                A[a] = 0;
                parent[a] = b;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int ans = 0;

        st = new StringTokenizer(br.readLine());
        A = new int[N+1];
        for (int i=1; i<=N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        parent = new int[N+1];
        for (int i=0; i<=N; i++) parent[i] = i;

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v, w);
        }

        for (int i=1; i<=N; i++) ans += A[i];
        System.out.println(ans > k ? "Oh no" : ans);
    }
}

// 10 10 20 20 30
// 10 10  0 20 30
// 10 10  0  0 30
// 10 10  0  0  0


// 아니면 visited[N+1] 만들어서 greedy하게 풀어도 될듯.