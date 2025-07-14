package Algorithm.Algorithm25.Java.BOJ1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

//    https://blog.itcode.dev/posts/2021/06/09/a1007

    private static double ans;
    private static Point[] points;
    private static int N;
    private static boolean[] checked;

    private static class Point {
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static class Vector {
        int x, y;
        Vector (int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private static double getVector(){
        int x = 0, y = 0;
        for (int i=0; i<N; i++){
            if (checked[i]){
                x += points[i].x;
                y += points[i].y;
            }
            else{
                x -= points[i].x;
                y -= points[i].y;
            }
        }
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y,2));
    }

    private static void combination(int idx, int cnt){
        if (cnt == 0) ans = Math.min(ans, getVector());
        else{
            for (int i = idx; i < N; i++){
                checked[i] = true;
                combination(i  + 1, cnt - 1);
                checked[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int sum_x = 0 , sum_y = 0;

        for (int test_case = 0; test_case < T; test_case++){

            N = Integer.parseInt(br.readLine());    // 20
            ans = Double.MAX_VALUE;
            points = new Point[N];
            checked = new boolean[N];

            for (int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                sum_x += points[i].x;
                sum_y += points[i].y;
            }

            combination(0, N/2);
            System.out.println(ans);

        }

    }
}
