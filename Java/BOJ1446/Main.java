package Algorithm.Algorithm25.Java.BOJ1446;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.math.*;

public class Main {
        
    private static int N, D;
    private static ArrayList<Edge>[] graph;
    private static int[] dist;  // i까지의 최단거리
    
    private static class Edge implements Comparable<Edge>{
        int x, w;
        public Edge(int x, int w){
            this.x = x;
            this.w = w;
        }
        
        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        dist = new int[D+1];
        for (int i=1; i<=D; i++) dist[i] = i;
        
        graph = new ArrayList[D+1];
        for (int i=0; i<=D; i++){
            graph[i] = new ArrayList<>();
        }
        
        for (int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
           if (v <= D) graph[v].add(new Edge(u, w)); // 끝점을 기준으로 시작점과 가중치를 저장
        }
        
        for (int i=1; i<=D; i++){
            dist[i] = dist[i-1] + 1;
            
            for (Edge e : graph[i]){
                dist[i] = Math.min(dist[e.x] + e.w, dist[i]);
            }
        }
        
        System.out.println(dist[D]);
    }
}
