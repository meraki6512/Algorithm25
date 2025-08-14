package Algorithm.Algorithm25.Java.BOJ9372;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 상근이의 여행
 * https://www.acmicpc.net/problem/9372
 * tree 구조, 연결됨, 모든 노드(N) 방문 -> 간선의 개수(N-1)를 구하는 문제
 */

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int N, M;
        for (int tc = 0; tc< T; tc++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int a, b;
            for (int m=0 ;m<M; m++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
            }

            System.out.println(N-1);
        }

    }
}
