// package Algorithm.Algorithm25.Java.BOJ15681;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N, R, Q; // 100000
    private static List<Integer>[] edges;
    private static List<Integer>[] children;
    private static int[] size;
    private static int[] subCnt;

    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 정점
        R = Integer.parseInt(st.nextToken());   // 루트
        Q = Integer.parseInt(st.nextToken());   // 쿼리
        edges = new List[N+1];
        children = new List[N+1];
        size = new int[N+1];
        subCnt = new int[N+1];
        for (int i=0; i<=N; i++) edges[i] = new ArrayList<>();
        for (int i=0; i<=N; i++) children[i] = new ArrayList<>();

        for (int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            edges[U].add(V);
            edges[V].add(U);
        }

        int[] queries = new int[Q];
        for (int i=0; i<Q; i++){
            queries[i] = Integer.parseInt(br.readLine());
        }

        // Sol
        makeTree(R, -1);

        for (int q : queries){
            if (subCnt[q] != 0) size[q] = subCnt[q];
            else countSubtree(q);
            System.out.println(size[q]);
        }
    }

    private static void makeTree(int cur, int parent){
        for (int n : edges[cur]){
            if (n != parent){
                children[cur].add(n);
                makeTree(n, cur);
            }
        }
    }


    private static void countSubtree(int cur){
        size[cur] = 1; // 자기 자신 포함

        for (int child : children[cur]){
            countSubtree(child);
            size[cur] += size[child];
        }

        subCnt[cur] = size[cur];
    }
}
