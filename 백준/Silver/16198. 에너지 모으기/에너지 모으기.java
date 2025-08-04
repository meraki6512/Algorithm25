//package Algorithm.Algorithm25.Java.BOJ16198;

import java.util.Scanner;

public class Main {

    private static int N, ans = 0;
    private static int[] W, M;
    private static boolean[] visited;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        W = new int[N+1];
        visited = new boolean[N+1];
        for (int i=1; i<=N; i++) W[i] = sc.nextInt();
        dfs(0, 0);
        System.out.println(ans);

    }

    private static void dfs(int cnt, int wSum){

        if (cnt == N-2){
            ans = Math.max(ans, wSum);
            return;
        }

        for (int i=2; i<N; i++){
            if (!visited[i]){
                visited[i] = true;

                int li = i-1;
                while (visited[li]) li--;
                int ri = i+1;
                while (visited[ri]) ri++;

                dfs(cnt + 1, wSum + W[li]*W[ri]);
                visited[i] = false;
            }
        }
    }

}