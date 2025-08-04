package Algorithm.Algorithm25.Java.BOJ2529;

import java.util.Scanner;

public class Main {

    private static int k;
    private static long min = 9876543210L, max = -1L;
    private static char[] signs;
    private static boolean[] visited = new boolean[10];

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        k = sc.nextInt();   // 2~9
        signs = new char[k];
        for (int i=0; i<k; i++) signs[i] = sc.next().charAt(0);

        for (int i=0; i<10; i++) {
            visited[i] = true;
            dfs(0, i, i);
            visited[i] = false;
        }

        System.out.println(max);
        System.out.println(min < Math.pow(10, k) ? "0"+min : min);

    }

    private static void dfs(int idx, int cur, long num){
        if (idx == k){
            min = Math.min(min, num);
            max = Math.max(max, num);
            return;
        }

        if (signs[idx] == '>'){
            for (int i=cur-1; i>=0; i--){
                if (!visited[i]){
                    visited[i] = true;
                    dfs(idx+1, i, num*10+i);
                    visited[i] = false;
                }
            }
        }
        else {
            for (int i=cur+1; i<10; i++){
                if (!visited[i]){
                    visited[i] = true;
                    dfs(idx+1, i, num*10+i);
                    visited[i] = false;
                }
            }
        }
    }
}
