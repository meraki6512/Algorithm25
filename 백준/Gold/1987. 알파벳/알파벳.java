// package Algorithm.Algorithm25.Java.BOJ1987;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    private static int R, C;                    // 20
    private static char[][] board;
    private static Set<Character> visited = new HashSet<>();
    private static int ans = 0;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        board = new char[R][C];

        for (int i=0; i<R; i++){
            String s = sc.next();
            for (int j=0; j<C; j++){
                board[i][j] = s.charAt(j);      // 알파벳 대문자
            }
        }

        visited.add(board[0][0]);
        dfs(0, 0);
        System.out.println(ans);

    }

    private static void dfs(int x, int y){
        if (visited.size() == R*C){
            ans = visited.size();
            return;
        }

        for (int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (0<=nx && nx<R && 0<= ny && ny <C){
                if (visited.contains(board[nx][ny])){
                    ans = Math.max(ans, visited.size());
                }
                else{
                    visited.add(board[nx][ny]);
                    dfs(nx, ny);
                    visited.remove(board[nx][ny]);
                }
            }
        }
    }
}
