package Algorithm.Algorithm25.Java.BOJ14225;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 부분수열의 합 (2s, 512MB)
 * https://www.acmicpc.net/problem/14225
 *
 * 수열 S가 주어졌을 떄 수열 S의 부분 수열의 합으로 나올 수 없는 가장 작은 자연수를 구하라.
 *
 * -> dfs-순열 방식으로 풀면, O(N*N!)의 시간복잡도를 가진다. 20!는 2경이 넘는 수.
 * -> bitmask-부분집합 방식을 사용하면 O(N*2^N)의 시간복잡도를 가진다. 2^20은 100만 정도의 수.
 * -> dfs-부분집합 방식: 시간복잡도도 적절하고(실제 계산 시 O(2^N)), 코드도 가장 직관적
 * 
 * 다시 풀기
 */
public class Main {

    private static int N, INF = 2_000_001;  // 20 * 100000
    private static int[] S;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        // In
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());   // 20
        S = new int[N];
        visited = new boolean[INF];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            S[i] = Integer.parseInt(st.nextToken());    // 10^5
        }

        // Sol
        // bitmask();
        dfs(0, 0);

        // Out
        for (int i=1; i<INF; i++){
            if (!visited[i]) {
                System.out.println(i);
                return;
            }
        }
    }

    /**
     * 모든 부분집합을 탐색해서, 조합 가능한 부분집합의 합을 visited에 체크
     * (bitmask 방식: i번째 원소 포함 여부를 비트로 표현, O(N * 2^N))
     *
     * 예를 들어,
     */
    private static void bitmask(){

        // S = [5, 1, 2]
        // N = 3 // 8 // 000~111
        // 부분집합의 개수는 2^N (공집합 제외하므로 1 ~ 2^N-1)
        for (int t = 1; t<Math.pow(2, N); t++){ // ;t<(1<<N);

            long sum = 0;
            for (int digit = 0; digit < N; digit++){
                if ((t & (1 << digit)) !=0)     // 해당 자리수가 1이면
                    sum += S[digit];            // 해당 값을 sum에 더함
            }

            visited[(int)sum] = true; // 현재 부분집합의 합을 visited에 체크
        }

    }

    /**
     * dfs(재귀)로 모든 부분집합의 합을 visited에 체크하는 함수: O(2^N) 호출
     * @param idx 현재 원소 인덱스
     * @param sum 현재까지의 부분집합 합
     */
    private static void dfs(int idx, int sum){
        if (idx == N) {
            visited[sum] = true; // 모든 원소를 다 고려했다면, 현재 sum 값을 체크
            return;
        }
        dfs(idx+1, sum + S[idx]); // idx 원소 포함하는 경우
        dfs(idx+1, sum); // idx 원소 포함하지 않는 경우
    }
}
