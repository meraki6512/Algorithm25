package Algorithm.Algorithm25.Java.BOJ17255;

/*
* 시작점을 (0~N-1번까지 전부 순회하며) 정해서 
* 각각의 시작점으로부터 만들 수 있는 모든 숫자를 set에 넣는 방식
* */

import java.util.HashSet;
import java.util.Scanner;

public class t1 {

    static char[] N;
    static HashSet<String> set = new HashSet<>();

    static void dfs(int L, int R, String s, String path){
        if (L==0 && R==N.length-1){
            set.add(path);
            return;
        }
        if (L-1 >= 0) dfs(L-1, R, N[L-1] + s, path + " " + N[L-1] + s);
        if (R+1 < N.length) dfs(L, R+1, s + N[R+1], path + " " + s + N[R+1]);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.next().toCharArray();

        for (int i=0; i<N.length; i++){
            dfs(i, i, String.valueOf(N[i]), String.valueOf(N[i]));
        }

        System.out.println(set.size());
    }
}
