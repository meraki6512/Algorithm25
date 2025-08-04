// package Algorithm.Algorithm25.Java.BOJ1039;

/*
1039. 교환 (2s, 128MB)
https://www.acmicpc.net/problem/1039

 */

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    private static int N, K, M, ans = -1;
    private static final int INF = 1_000_001;

    private static class Node{
        int num, cnt;
        Node(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        N = Integer.parseInt(s);
        K = sc.nextInt();
        M = s.length();

        bfs();
        System.out.println(ans);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[INF][K+1];
        q.add(new Node(N, 0));
        visited[N][0] = true;

        while (!q.isEmpty()){
            Node cur = q.poll();
            if (cur.cnt == K){
                ans = Math.max(ans, cur.num);
                continue;
            }

            for (int i=0; i<M; i++){
                for (int j=i+1; j<M; j++) {
                    int nxtNum = swap(cur.num, i, j);

                    if (nxtNum != -1 && !visited[nxtNum][cur.cnt + 1]){
                        q.add(new Node(nxtNum, cur.cnt + 1));
                        visited[nxtNum][cur.cnt + 1] = true;
                    }
                }
            }
        }
    }

    private static int swap(int n, int i, int j){
        char[] numArr = String.valueOf(n).toCharArray();
        if (i==0 && numArr[j] == '0') return -1;

        char tmp = numArr[i];
        numArr[i] = numArr[j];
        numArr[j] = tmp;

        return Integer.parseInt(new String(numArr));
    }
}
