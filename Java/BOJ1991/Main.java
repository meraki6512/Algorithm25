package Algorithm.Algorithm25.Java.BOJ1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 트리 순회
 * https://www.acmicpc.net/problem/1991
 *
 * 이진 트리
 * pre: root-left-right: ABDCEFG
 * in: left-root-right: DBAECFG
 * post: left-right-root: DBEGFCA
 *
 * 노드의 개수: N (26)
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

        }

    }

    private static void preOrder() {
    }

    private static void inOrder() {
    }

    private static void postOrder() {
    }
}
