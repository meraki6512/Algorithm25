package Algorithm.Algorithm25.Java.BOJ1781;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
1781. 컵라면
https://www.acmicpc.net/problem/1781
2s, 256MB

 */

public class Main {
    public static class Task{
        int deadline, cups;
        Task(int deadline, int cups){
            this.deadline = deadline;   // N 이하의 자연수
            this.cups = cups;             // int 범위 내의 자연수
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Task> tasks = new PriorityQueue<>(
                (o1, o2) -> o1.deadline == o2.deadline ?
                        o2.cups - o1.cups : o1.deadline - o2.deadline);

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            tasks.add(new Task(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        // before    // after
        // 1234567   // 2165347     // 문제 번호
        // 1133226   // 1122336     // deadline
        // 6721451   // 7654211     // cups
        
        // 컵라면 수 오름차순으로
        int ans = 0;
        PriorityQueue<Integer> selected = new PriorityQueue<>();
        while (!tasks.isEmpty()){
            Task cur = tasks.poll();
  
            // 풀은 문제 (일)수보다 현재 deadline이 더 크면 => 바로 select
            if (selected.size() < cur.deadline) {
                selected.add(cur.cups);
                ans += cur.cups;
            }
            // 풀은 문제 (일)수보다 현재 deadline이 더 작거나 같으면 => 더 풀 수 없음
            else {
                // 그치만 현재 문제가 만약 cups를 더 많이 준다면
                // 제일 적은 수를 빼고 select
                if (selected.peek() < cur.cups){
                    ans -= selected.poll();
                    selected.add(cur.cups);
                    ans += cur.cups;
                }
            }
        }

        System.out.println(ans);
    }
}

/*
* 난이도가 있는 문제 중에 PQ를 여러개 같이 쓰는 경우가 꽤 있는 것 같음
* */
