package Algorithm.Algorithm25.Java.BOJ3109;

/*
3109. 빵집
https://www.acmicpc.net/problem/3109
1s, 256MB

R*C 격자 (첫번째 열: 다른 가게 가스관, 마지막 열: 빵집)
가스관과 빵집을 연결하는 파이프를 설치하자.
- 사이에 건물('X')이 있는 경우에는 파이프를 놓을 수 없다.
- 첫째열에서 시작해 오른쪽옆, 대각선으로 이동해 마지막 열에서 끝나야 한다.

파이프를 여러 개 설치할 때, 각 칸을 지나는 파이프는 1개여야 한다.
-> 파이프라인의 최대 개수를 구하라.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static private int R, C;
    static private char[][] graph;
    static private int[] d = {-1, 0, 1};
    static private int ans = 0;

    private static void print(){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                sb.append(graph[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // graph는 성공 시에만 반영 -> 할 필요 없음
    // => 그냥 경로 따라 계속 변경 (greedy)
    private static boolean dfs(int r, int c){

        // 위에서부터 시작해서, 대각선위->옆->대각선아래 greedy 이동
        for (int i=0; i<3; i++){
            int nr = r + d[i];
            int nc = c + 1;

            if (nr >= 0 && nr < R && nc < C && graph[nr][nc]=='.'){
                graph[nr][nc] = '*';

                // 마지막에 도달할 수 있으면(성공하면) 개수++
                if (nc == C-1){
                    ans ++;
                    return true;
                }

                if (dfs(nr, nc)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        graph = new char[R][C];

        for (int i = 0; i< R; i++){
            char[] s = br.readLine().toCharArray();
            for (int j = 0; j < C; j++){
                graph[i][j] = s[j];
            }
        }

        // dfs
        for (int i=0; i<R; i++) {
            dfs(i, 0);
//            print();
        }
        System.out.println(ans);
    }
}
