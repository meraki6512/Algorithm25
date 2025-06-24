package Algorithm.Algorithm25;

import java.util.HashSet;
import java.util.Scanner;

/*
* https://www.acmicpc.net/problem/14425
* Tree set 스터디 차례여서 골랐는데, 그냥 Hash set 문제
* */

public class BOJ14425 {
    public static void main(String[] args) {

        int N, M;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();       // 10^4
        M = sc.nextInt();       // 10^4

        HashSet<String> set = new HashSet<>(N);
        for (int i = 0; i<N; i++){
            set.add(sc.next()); // 길이: 500 이하, 알파벳 소문자로만 구성됨
        }

        int ans = 0;
        for (int i=0; i<M; i++){
            String check = sc.next();
            if (set.contains(check)) ans ++;
        }

        System.out.println(ans);

    }
}
