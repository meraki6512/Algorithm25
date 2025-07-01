package Algorithm.Algorithm25.Java;

import java.util.HashMap;
import java.util.Scanner;

/*
* https://www.acmicpc.net/problem/2015
* 오버플로우 항상 고려합시다..
* */

public class BOJ2015 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 2 * 10^5
        int K = sc.nextInt();   // 정수 범위
        int[] A = new int[N+1];
        for (int i=1; i<=N; i++){
            A[i] = sc.nextInt();
        }

        // 부분합 배열 생성
        long[] p = new long[N+1];
        for (int i=1; i<=N; i++){
            p[i] = p[i-1] + A[i];
        }

        // 부분합의 개수 = N×(N+1)/2: 4 * 100억개까지 가능이라서 overflow 가능성 있음 -> Long으로 설정
        // |K| ≤ 2,000,000,000 라서 underflow 가능성 있음 -> Long으로 설정
        long ans = 0;
        HashMap<Long, Long> hash = new HashMap<>();
        hash.put(0L, 1L); // hash.put을 ans+=X보다 더 나중에 하므로 0인 값을 1로 먼저 초기화
        for (int i=1; i<=N; i++){

            // p[x] - p[i] = K
            // 지금까지 해시 맵에 포함된 부분합 값들 중에 합이 K인 값이 있으면 그 개수만큼 더해줌 (중복 가능)
            ans += hash.getOrDefault(p[i]-K, 0L);
            
            // 현재 부분합 hash에 있으면 개수를 늘려주고, 없으면 개수=1로 설정
            hash.put(p[i], hash.getOrDefault(p[i], 0L) + 1);
        }

        System.out.println(ans);

    }
}
