// package Algorithm.Algorithm25.Java.BOJ11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * workflow action test용
 * 구간합 구하기
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] num = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) num[i] = Integer.parseInt(st.nextToken());

        int[] p = new int[N+1];
        for (int i = 1; i <= N; i++) p[i] = p[i-1] + num[i];

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(p[b]-p[a-1]).append("\n");
        }

        System.out.println(sb);
    }
}
