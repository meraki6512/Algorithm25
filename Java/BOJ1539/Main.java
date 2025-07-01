package Algorithm.Algorithm25.Java.BOJ1539;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;

/*
* 이진 검색 트리
* 2s, 256MB
*
* N: 2.5 * 10^5
* 0~N-1의 정수가 중복 없이 채워져 있는 배열 P
* P로 이진 검색 트리를 만들었을 때, 모든 노드의 높이의 합을 출력하라.
* (높이 = 루트에서의 거리 + 1)
*
* 만드는 경우: 최악의 경우 O(N^2)
* 만들지 않고 높이를 계산하는 방법이 있을까?
* 리프 노드만 고려한다? 꺾이는 수를 계산한다? inorder 정렬된 채로 고려
* -> 수가 삽입될 때 BST의 모습을 고려해보자. (정렬된 채로 어느 부분에 삽입되나)
* -> TreeSet의 lower()과 higher()을 이용한다! (python bisect 느낌)
* (TreeSet의 메소드: first(), last(), lower(), higher(), floor(), ceiling())
*
* -> 이런 문제 유형을 lower bound 문제라고 한다고 한다.
* 
* 주의: ans NC2까지 가능하므로 overflow 가능함 -> long 타입 선언하기
* */
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] P = new int[N];
        for (int i=0; i<N; i++){
            P[i] = sc.nextInt();
        }

        long ans = 0;
        TreeSet<Integer> set = new TreeSet<>();             // data
        HashMap<Integer, Integer> map = new HashMap<>();    // data, depth

        for (int i=0; i<N; i++){

            Integer lower = set.lower(P[i]);
            Integer higher = set.higher(P[i]);
            int depth = Math.max(
                    (lower != null)? map.getOrDefault(lower, 0): 0,
                    (higher != null)? map.getOrDefault(higher, 0) : 0
            ) + 1;

            set.add(P[i]);
            map.put(P[i], depth);
            ans += depth;
        }

        System.out.println(ans);
    }

}
