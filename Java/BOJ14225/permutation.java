package Algorithm.Algorithm25.Java.BOJ14225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 부분수열의 합 (2s, 512MB)
 * https://www.acmicpc.net/problem/14225
 *
 * 수열 S가 주어졌을 떄 수열 S의 부분 수열의 합으로 나올 수 없는 가장 작은 자연수를 구하라.
 *
 * -> dfs-순열 방식으로 풀면, O(N*N!)의 시간복잡도를 가진다. 20!는 2경이 넘는 수.
 * -> bitmask-부분집합 방식을 사용하면 O(N*2^N)의 시간복잡도를 가진다. 2^20은 100만 정도의 수.
 */
public class permutation {

    private static int N, INF = 100_001, T = 0;
    private static int[] S;
    private static boolean[] visited;
    private static boolean[] num;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());   // 20
        S = new int[N];
        visited = new boolean[INF];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            S[i] = Integer.parseInt(st.nextToken());    // 10^5
            T += S[i];
        }

        num = new boolean[T+2];
        dfs(0);

        for (int i=1; i<=T+1; i++){
            if (!num[i]) {
                System.out.println(i);
                return;
            }
        }
    }

    private static void dfs(int sum){

        num[sum] = true;

        for (int i=0; i<N; i++){
            if (!visited[i]) {
                visited[i] = true;
                dfs(sum + S[i]);
                visited[i] = false;
            }
        }
    }
}
