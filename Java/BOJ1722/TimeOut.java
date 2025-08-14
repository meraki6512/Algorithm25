package Algorithm.Algorithm25.Java.BOJ1722;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

public class TimeOut {

    private static int N, k, count = 0;
    private static int[] num, cur;
    private static boolean[] visited;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        cur = new int[N];
        visited = new boolean[N];

        switch(sc.nextInt()){
            case 1:
                k = sc.nextInt();
                getNum(0);
                break;
            case 2:
                num = new int[N];
                for (int i=0; i<N; i++){
                    num[i] = sc.nextInt();
                }
                getK( 0);
                break;
        }

    }

    private static void getNum(int cnt){
        if (cnt == N){

            if (count == k-1) {
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<N; i++){
                    sb.append(cur[i]).append(" ");
                }
                System.out.println(sb.toString().strip());
                exit(0);
            }
            count ++;
            return;
        }

        for (int i=0; i<N; i++){
            if (!visited[i]){
                visited[i] = true;
                cur[cnt] = i + 1;
                getNum(cnt + 1);
                visited[i] = false;
            }
        }
    }

    private static void getK(int cnt){
        if (cnt == N){
            count ++;
            if (Arrays.equals(cur, num)) System.out.println(count);
            return;
        }

        for (int i=0; i<N; i++){
            if (!visited[i]){
                visited[i] = true;
                cur[cnt] = i + 1;
                getK(cnt + 1);
                visited[i] = false;
            }
        }
    }
}
