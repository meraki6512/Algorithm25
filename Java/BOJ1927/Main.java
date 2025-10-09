package Algorithm.Algorithm25.Java.BOJ1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 최소 힙
 * 연산의 개수 N: 100_000
 * x == 0이면 배열에서 가장 작은 값 출력하고 제거
 * 자연수면 배열에 값 추가
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        while (N-- > 0) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                Integer p = pq.poll();
                if (p == null) sb.append("0\n");
                else sb.append(p).append("\n");
            }
            else{
                pq.add(x);
            }
        }

        System.out.println(sb);
    }
}
