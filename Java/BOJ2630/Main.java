package Algorithm.Algorithm25.Java.BOJ2630;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 색종이 만들기
 * https://www.acmicpc.net/problem/2630
 */

public class Main {

    private static int white = 0;
    private static int blue = 0;
    private static int[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(N, 0, 0);

        System.out.println(white);
        System.out.println(blue);
    }

    private static void divide(int len, int sX, int sY){
        if (checkColor(len, sX, sY)) return;

        int nl = len / 2;
        divide(nl, sX, sY);
        divide(nl, sX+nl, sY);
        divide(nl, sX, sY+nl);
        divide(nl, sX+nl, sY+nl);
    }

    private static boolean checkColor(int len, int sX, int sY){
        int sum = 0;
        for (int i=sX; i<sX+len; i++){
            for (int j=sY; j<sY+len; j++){
                sum += board[i][j];
            }
        }
        if (sum == 0) {white++; return true;}
        else if (sum == len*len) {blue++; return true;}
        else return false;
    }
}
