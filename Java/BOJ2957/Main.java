package Algorithm.Algorithm25.Java.BOJ2957;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

/*
* 이진 탐색 트리
* https://www.acmicpc.net/problem/2957
* 1s, 128MB
*
* https://www.acmicpc.net/problem/1539 이진 검색 트리와 유사하지만
* root의 depth == 0이라는 점과 메모리 초과를 주의할 것. -> Scanner, Sout 사용 주의
* */

public class Main {
    public static void main(String[] args) throws IOException {

//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());    //3*10^5

        long C = 0;
        TreeSet<Integer> set = new TreeSet<>();     // data
        int[] depth = new int[N + 1];               // depth (값을 인덱스로: 1~N)

        for (int i=0; i<N; i++){
//            int n = sc.nextInt();
            int n = Integer.parseInt(br.readLine());

            Integer low = set.lower(n);
            Integer high = set.higher(n);

            int d = Math.max(
                    low != null ? depth[low] : -1,
                    high != null ? depth[high] : -1
            ) + 1;

            set.add(n);
            depth[n] = d;

            C += d;
//            System.out.println(C);
            sb.append(C).append("\n");
        }

        System.out.println(sb);
    }
}
