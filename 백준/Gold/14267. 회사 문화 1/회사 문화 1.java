// package Algorithm.Algorithm25.Java.BOJ14267;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 회사 문화 1 (2s, 512MB)
 * https://www.acmicpc.net/problem/14267
 *
 */

public class Main {

    private static List<Integer>[] subs;
    private static int[] comp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n, m;   // 100_000
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 직원 n명의 직속 상사의 번호 입력
        st = new StringTokenizer(br.readLine());
        subs = new List[n+1];
        for (int i=0; i<n+1; i++) subs[i] = new ArrayList<>();
        st.nextToken(); // 사장은 제외하고 2번 직원부터 부하들 저장
        for (int i=2; i<=n; i++){
            subs[Integer.parseInt(st.nextToken())].add(i);
        }

        // 직속 상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w
        comp = new int[n+1]; // 각 직원이 받은 칭찬 수치를 담을 배열
        for (int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            comp[Integer.parseInt(st.nextToken())] += Integer.parseInt(st.nextToken());
        }

        // 사장님부터 내리 칭찬
        dfs(1);

        // 출력
        StringBuffer sb = new StringBuffer();
        for (int i=1; i<=n; i++) sb.append(comp[i]).append(' ');
        System.out.println(sb);

    }

    private static void dfs(int cur){
        for (int sub : subs[cur]){
            comp[sub] += comp[cur];
            dfs(sub);
        }
    }
}
