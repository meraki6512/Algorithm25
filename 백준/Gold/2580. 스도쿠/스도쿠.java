// package Algorithm.Algorithm25.Java.BOJ2580;

// 스도쿠 (1s, 256MB)
// https://www.acmicpc.net/problem/2580
// - 다시 풀기

import java.sql.Time;
import java.util.*;

import static java.lang.System.exit;

public class Main {

    private static int[][] board = new int[9][9];

    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        for (int i=0; i<9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        sudoku(0, 0);
    }

    private static void printBoard(){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (j==8) sb.append(board[i][j]);
                else sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void sudoku(int row, int col) {

        // col이 끝까지 도달하면 다음 row에서 다시 시작
        if (col == 9) {
            sudoku(row + 1, 0);
            return;
        }

        // row가 끝까지 도달하면 출력 후 종료
        if (row == 9){
            printBoard();
            exit(0);
        }
        
        if (board[row][col] == 0){
            for (int i=1; i<=9; i++){
               if (isAvailable(row, col, i)){
                   board[row][col] = i;
                   sudoku(row, col+1);
               }
            }
            board[row][col] = 0;
            return;
        }

        sudoku(row, col + 1);
    }

    private static boolean isAvailable(int row, int col, int v){

        // 같은 row, col에서 겹치는 값이 있으면 false
        for (int i=0; i<9; i++){
            if (board[row][i] == v) return false;
        }
        for (int i=0; i<9; i++){
            if (board[i][col] == v) return false;
        }

        // 같은 cube에서 겹치는 값이 있으면 false
        int r = (row/3)*3, c = (col/3)*3; // v가 속한 cube의 첫 row, col 위치
        for (int i=r; i<r+3; i++){
            for (int j=c; j<c+3; j++){
                if (board[i][j] == v) return false;
            }
        }

        return true;
    }

}
