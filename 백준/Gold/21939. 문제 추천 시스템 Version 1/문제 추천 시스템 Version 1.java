/*
* 문제 추천 시스템 Version1
* https://www.acmicpc.net/problem/21939
* 1s, 512MB
* N, P: 10^5, M: 10^4, L:100
* 
* recommend x: x가 1이면 가장 어려운 문제 출력 (어려운 문제가 여러 개면 문제 번호 큰 것)
*              x가 -1이면 가장 쉬운 문제 출력 (쉬운 문제가 여러 개면 문제 번호 작은 것)
* add P L: 난이도가 L인 문제 번호 P를 추가 (같은 문제 P에 대해 난이도 update 가능)
* solved P: 문제 P를 제거
* 
* -> 중요한 건 문제 번호도 정렬되어야 하는데 난이도도 정렬되어야하는 것을 고려해야 함
* 단순히 TreeMap을 쓰면 해결되지 않음
* 삭제 연산을 할 때, 정확한 Key Object를 remove 매개변수에 전달할 수 없기 때문.
* TreeSet + HashMap 조합을 사용해야 함
* */

import java.util.*;

public class Main {

    static class Problem implements Comparable<Problem>{
        int P, L;
        Problem(int P, int L){
            this.P = P;
            this.L = L;
        }

        @Override
        public int compareTo(Problem o) {
            // 난이도 먼저 비교 -> 문제 번호 비교
            return this.L != o.L ? this.L - o.L : this.P - o.P;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 기존 problems init
        int N = sc.nextInt();
        TreeSet<Problem> problems = new TreeSet<>();
        Map<Integer, Problem> map = new HashMap<>();

        for (int i=0; i<N; i++){
            int P = sc.nextInt();
            int L = sc.nextInt();
            Problem problem = new Problem(P, L);
            problems.add(problem);
            map.put(P, problem);
        }
        
        // 명령 처리
        int M = sc.nextInt();

        for (int i=0; i<M; i++){
            char cmd = sc.next().charAt(0);

            if (cmd == 'a'){
                int P = sc.nextInt();
                int L = sc.nextInt();
                Problem problem = new Problem(P, L);
                problems.add(problem);
                map.put(P, problem);
            }
            else if (cmd == 'r'){
                int x = sc.nextInt();
                System.out.println(x==1? problems.last().P : problems.first().P);
            }
            else if (cmd == 's') {
                int P = sc.nextInt();
                problems.remove(map.get(P));
            }
        }
    }
}
