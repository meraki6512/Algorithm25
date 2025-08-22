// package Algorithm.Algorithm25.Java.BOJ13325;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 13325. 이진 트리 (1, 512)
 * 포화이진트리 - 가중치 k, 높이 k, 리프 2^k, 총 2^(k+1)-1
 * 모든 리프까지의 거리(가중치 총합)가 같고, 그 총합이 최소화가 되도록 특정 엣지들의 가중치를 증가시키자.
 * Out: 증가시킨 후의 가중치 총합
 *
 * dfs
 * 더해주는 값: 같은 자식끼리 차이만큼
 * ret: 큰 값
 */

public class Main {

    private static int k, n;
    private static long ans = 0;
    private static int[] w;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());    // 20
        n = (int) (Math.pow(2, k+1)) - 1;       // 노드 개수는 200만정도
        w = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=2; i<n+1; i++) w[i] = Integer.parseInt(st.nextToken()); // 엣지 end 노드에 값 저장

        dfs(1);
        System.out.println(ans);
    }

    private static long dfs(int i){
        // 리프 노드면: 현재 노드에 저장된 값만 리턴
        if (2*i > n) {
            ans += w[i];
            return w[i];
        }

        // 아니면 자식 노드까지의 총합 (큰 값으로) 구하기
        long l = dfs(2*i);   // 왼쪽 총합
        long r = dfs(2*i+1); // 오른쪽 총합

        ans += w[i] + Math.abs(r-l);    // ans에 현재 자신 값 + 양측 자식 트리 총합의 차이만큼 더 더해주면 됨
        return w[i] + Math.max(l, r);   // 리턴 값은 현재 자신 값 + 양측 자식 트리 총합 중 큰 값
    }
}

/**
 *           1
 *      2(2)   3(2)
 *  4(2) 5(1)  6(1) 7(3)
 *
 *           1
 *      2(2)   3(2)         -> +1
 *  4(2) 5(1) // 6(1) 7(3)  -> +1 // +2
 */
