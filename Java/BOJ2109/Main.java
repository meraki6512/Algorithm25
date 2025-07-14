package Algorithm.Algorithm25.Java.BOJ2109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
2109. 순회강연 (2s, 128MB)
https://www.acmicpc.net/problem/2109

n개 대학(10^4)
d: deadline, p: price(각 10^4)
최대 p합 구하기

컵라면 문제와 같은 로직
d작고 p큰 순서로 정렬해서 price pq(오름차순) 
 */

public class Main {

    private static class Suggestion{
        int p, d;
        Suggestion(int p, int d){
            this.p = p;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Suggestion[] suggestions = new Suggestion[n];
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            suggestions[i] = new Suggestion(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(suggestions,
                (o1, o2)-> o1.d == o2.d? o2.p - o1.p : o1.d - o2.d);

        int ans = 0;
        PriorityQueue<Integer> selected = new PriorityQueue<>();
        for (int i=0; i<n; i++){
            Suggestion cur = suggestions[i];

            if (selected.size() < cur.d) {
                selected.add(cur.p);
                ans += cur.p;
            }
            else if (selected.peek() < cur.p) {
                ans -= selected.poll();
                selected.add(cur.p);
                ans += cur.p;
            }
        }

        System.out.println(ans);
    }
}
