package Algorithm.Algorithm25.Java.BOJ17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 17471. 게리맨더링
 * https://www.acmicpc.net/problem/17471
 *
 * 생각해보자.
 * 우선은 두 그룹으로 나눠야 한다. => 모든 조합. 2^n가지. (comb/recurs/bitmask)
 * 찾은 모든 조합에 대해 유효성을 확인해야 한다. 각 그룹끼리 연결되어 있는가.
 * 연결되어 있는 경우에 한해서 두 그룹의 인구 수 차이를 계산한다.
 */
public class Main {
    private static ArrayList<Integer>[] graph;
    private static boolean[] visited;
    private static int[] population;
    private static int ans = Integer.MAX_VALUE;
    private static int res;
    private static int[] g;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    // 구역 수: 10
        population = new int[N+1];            // 구역의 인구 수: 100
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {population[i]=Integer.parseInt(st.nextToken());}

        graph = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= num; j++) {
                graph[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // 사실 (i == 0이나) i == 1<<N일 때와 같이 한 그룹에 모든 구역이 존재하는 경우는 두 그룹의 인구수 차이가 최소가 될 수 없으므로 고려하지 않아도 어느 정도 괜찮다.
        // 또 극단적으로 유효한 분할이 아예 없고 한 그룹에 모든 구역이 존재하는 경우만 존재한다면 안전하게 i = 1부터 i <= (1<<N-1)까지만 돌리는 게 맞지만
        // 유효한 분할이 없으면서 모든 구역이 한 그룹에만 존재하는 경우는 발생할 수 없다.
        // 현재 백준 테스트케이스에서도 i = 0부터 i <= 1<<N까지 돌려도 문제 없이 통과함.

        // 모든 조합에 대해 탐색 (1024)
        for (int i = 1; i <= (1<<N)-1; i++) {             // 1000     // 그룹핑
            g = new int[2]; // 그룹별 인구수 저장할 배열
            visited = new boolean[N+1];

            for (int k=0; k<=1; k++){                   // (0 또는 1 그룹)
                res = 0;    // 방문 그룹의 인구수 총합
                for (int j = 1; j <= N; j++) {          // 10
                    // j 점에서 시작해서 같은 그룹의 노드들만 탐색하기
                    if (((i>>(j-1)) & 1) == k){
                        dfs(j, i, k);
                        break;  // 맨 처음 하나의 노드에서 시작만 하고 더 이상 탐색하지 않음
                    }
                }
                g[k] = res;
            }

            // 모든 노드를 다 방문했다면, (유효하므로,) 두 그룹의 인구수 차이 업데이트
            int cnt = 0;
            for (int j = 1; j <= N; j++) if (visited[j]) cnt ++;
            if (cnt == N) ans = Math.min(ans, Math.abs(g[0]-g[1]));

        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    /**
     * 같은 그룹인 구역들을 전부 방문하고 인구수 합을 구한다. (10 + 55)
     * @param cur 현재 방문 구역
     * @param bitmask 그룹핑 내용 - 예) 000111 이면 1, 2, 3 / 4, 5, 6으로 그룹 나눔
     * @param k 0 그룹인지 1 그룹인지 구별용
     */
    private static void dfs(int cur, int bitmask, int k) {
        visited[cur] = true;
        res += population[cur];

        for (int n : graph[cur]){
            if (((bitmask >> (n-1)) & 1) == k && !visited[n]) {
                dfs(n, bitmask, k);
            }
        }
    }
}
