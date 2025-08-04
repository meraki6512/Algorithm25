package Algorithm.Algorithm25.Java.BOJ10994;

import java.util.*;

public class Main {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 100
        System.out.println(star(N));

        // 아니면 board[4n-3][4n-3] ?
    }

    private static String star(int n){
        if (n==1) return "*";

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<4*n-3; i++) sb.append("*");
        sb.append("\n");
        sb.append("*");
        for (int i=0; i<4*n-5; i++) sb.append(" ");
        sb.append("*\n");

        String prv = star(n-1);
        StringTokenizer st = new StringTokenizer(prv, "\n");
        for (int i=0; i<4*n-7; i++){
            sb.append("* ").append(st.nextToken()).append(" *\n");
        }

        sb.append("*");
        for (int i=0; i<4*n-5; i++) sb.append(" ");
        sb.append("*\n");
        for (int i=0; i<4*n-3; i++) sb.append("*");

        return sb.toString();
    }
}
