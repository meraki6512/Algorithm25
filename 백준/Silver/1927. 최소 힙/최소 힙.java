// package Algorithm.Algorithm25.Java.BOJ1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

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
