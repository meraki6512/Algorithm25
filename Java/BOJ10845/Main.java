package Algorithm.Algorithm25.Java.BOJ10845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 구현? 아님 Deque class?

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());   // 10^4
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            switch (s.charAt(1)){
                case 'u':   // push
                    dq.add(Integer.parseInt(st.nextToken()));
                    break;
                case 'o':   // pop
                    if (dq.isEmpty()) System.out.println(-1);
                    else System.out.println(dq.pollFirst());
                    break;
                case 'i':   // size
                    System.out.println(dq.size());
                    break;
                case 'm':   // empty
                    if (dq.isEmpty()) System.out.println(1);
                    else System.out.println(0);
                    break;
                case 'r':   // front
                    if (dq.isEmpty()) System.out.println(-1);
                    else System.out.println(dq.getFirst());
                    break;
                case 'a':   // back
                    if (dq.isEmpty()) System.out.println(-1);
                    else System.out.println(dq.getLast());
                    break;
            }
        }
    }
}
