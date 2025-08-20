// package Algorithm.Algorithm25.Java.BOJ11399;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] P = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) P[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(P);

        int ans = 0;
        for (int i=1; i<=N; i++){
            ans += P[i] * (N-i+1);
        }
        System.out.println(ans);
    }
}
