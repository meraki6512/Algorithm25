package Algorithm.Algorithm25.Java.BOJ17255;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

/*
* 거꾸로 양끝에서 하나씩 pop하면서 개수를 세는 방식
* */

public class t0 {

    static int count(Deque<Character> deq){

        if (deq.size() == 0) return 0;
        else if (deq.size() == 1) return 1;

        HashSet<String> set = new HashSet<>();

        char first = deq.pollFirst();
        set.add(deq.toString());            // StringBuilder를 더 추천하지만 이렇게 해도 상관X
        int a = count(deq);
        deq.addFirst(first);

        char last = deq.pollLast();
        int b = 0;
        if (!set.contains(deq.toString())) b = count(deq);
        deq.addLast(last);

        return a + b;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        char[] N = sc.next().toCharArray();

        Deque<Character> deq = new LinkedList<>();
        for (int i=0; i<N.length; i++){
            deq.add(N[i]);
        }

        System.out.println(count(deq));
    }
}