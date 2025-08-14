// package Algorithm.Algorithm25.Java.BOJ9372;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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
