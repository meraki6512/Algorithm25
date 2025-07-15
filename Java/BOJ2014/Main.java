package Algorithm.Algorithm25.Java.BOJ2014;

/*
2014. 소수의 곱 (2s, 128MB)
https://www.acmicpc.net/problem/2014

K개의 소수 (100)
소수들 중에서 몇 개를 곱해 얻게 되는 수 (자기자신포함)들을 오름차순으로 나열할 때,
N번 째 수를 구해보라. (10^5)
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int N = sc.nextInt();
        int[] given = new int[K];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++){
            given[i] = sc.nextInt();
            pq.add(given[i]);
        }

        // 그냥 pq에서 전부 곱할 경우 -> 중복 발생
        // pq                   // poll
        // 2 3 5 7              // 2    // 4 6 10 14
        // 3 4 5 6 7 10 14      // 3    // 6 9 15 21
                                        //-> 2*3과 3*2가 중복됨
        // 어차피 뒤에서 계산을 할 거라고 생각하고
        // 나눠 떨어지는 수까지만 곱해서 넣음
        // 2 3 5 7              // 2    // 4
        // 3 4 5 7              // 3    // 6 9
        // 4 5 6 7 9            // 4
        // 5 6 7 9              // 5    // 10 15 25
        // ...

        for (int i=0; i<N-1; i++){
            int cur = pq.poll();

            for (int j=0; j<K; j++){
                pq.add(cur * given[j]);
                if (cur % given[j] == 0) break;
            }
        }

        System.out.println(pq.poll());
    }
}

/*
2 3 5 7
2 3 5 7 /4 6 10 14 /8 12 20 28/ 16 24 40 56
 */
