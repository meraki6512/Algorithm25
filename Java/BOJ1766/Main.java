package Algorithm.Algorithm25.Java.BOJ1766;

/*
1 ~ N 까지 문제, 1: 쉬움, N: 어려움

1) N개의 문제는 모두 풀어야 한다.
2) 먼저 푸는 것이 좋은 문제가 있는 문제는, 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 한다.
3) 가능하면 쉬운 문제부터 풀어야 한다.

예를 들어서 네 개의 문제가 있다고 하자.
4번 -> 2번, 3번 -> 1번
=> 3-1-4-2

항상 문제를 모두 풀 수 있는 경우만 입력으로 주어진다.
문제 풀 순서대로 빈칸으로 띄어 출력하라.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());       // 32,000
        int M = Integer.parseInt(st.nextToken());       // 100,000

        // 인접 리스트: 각 문제마다 "먼저 풀어야 하는 문제"의 정보를 저장
        // list[A]에는 A번 문제를 풀고 나서 풀 수 있는 문제들의 번호가 저장됨
        List<Integer>[] list = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) list[i] = new ArrayList<>();
        int[] degree = new int[N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // A -> B
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list[A].add(B);

            // 진입 차수 배열:
            // degree[i]는 i번 문제를 풀기 전에 반드시 풀어야 하는 문제의 수
            degree[B]++;
        }

        // 차수가 0인 것부터 pq에 넣음 (앞에 풀어야할 문제 없는 것들)
        // 같은 차수일 경우, 숫자 크기 오름차순으로 출력 -> PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                pq.add(i);
            }
        }

        // 위상 정렬
        // `ACM Craft` - dp + 위상정렬
        // pq에서 꺼내서 연결된 차수 줄여주고 차수가 0이면 pq에 넣음
        List<Integer> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            int idx = pq.poll();
            ans.add(idx);

            // 방금 푼 문제를 풀고 나서 풀 수 있는 문제들의 degree를 하나씩 줄임
            // idx -> t (idx를 선행으로 갖는 문제 t)
            for (int k = 0; k <list[idx].size(); k++) {
                int t = list[idx].get(k);
                degree[t]--;
                
                // 줄인 차수가 0이면 (앞에 풀어야만 하는 문제가 X면) 바로 처리
                if (degree[t] == 0) {
                    pq.add(t);
                }
            }
        }

        // Output
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i)).append(" ");
        }
        System.out.println(sb.toString().strip());
    }
}
