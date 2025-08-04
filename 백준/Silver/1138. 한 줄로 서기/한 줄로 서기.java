// package Algorithm.Algorithm25.Java.BOJ1138;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int cnt;
        int[] info = new int[N+1];
        int[] ans = new int [N+1];
        for (int i=1; i<=N; i++) info[i] = sc.nextInt();

        for (int i=1; i<=N; i++){
            cnt = 0;
            for (int j=1; j<=N; j++){
                if (cnt == info[i]){
                    while (ans[j] != 0) j++;
                    ans[j] = i;
                    break;
                }
                if (ans[j]==0) cnt ++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++){
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString().strip());

    }
}
