package Algorithm.Algorithm25.Java.BOJ1715;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// 1715. 카드 정렬하기
// https://www.acmicpc.net/problem/1715

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // greedy (작은 것부터 선택)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<N; i++) pq.add(Integer.parseInt(br.readLine()));

        // 2개씩 꺼내서 더해 넣음
        int ans = 0;
        while (pq.size() > 1){
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a+b);
            ans += a+b;
        }

        System.out.println(ans);
    }
}
