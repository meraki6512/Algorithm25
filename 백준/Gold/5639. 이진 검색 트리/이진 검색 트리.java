// package Algorithm.Algorithm25.Java.BOJ5639;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 이진 검색 트리 (1s, 256MB)
 * https://www.acmicpc.net/problem/5639
 * 이진 검색 트리의 전위 순회 결과가 주어졌을 때 후위 순회 결과를 출력하라.
 */
public class Main {

    private static class Node{
        int data;
        Node left, right;

        Node(int data){
            this.data = data;
        }

        void insert(int d){
            if (data < d){
                if (right == null) right = new Node(d);
                else right.insert(d);
            }
            else {
                if (left == null) left = new Node(d);
                else left.insert(d);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Input & MakeTree
        Node root = new Node(Integer.parseInt(br.readLine())); // <10^6
        String s;
        while ((s = br.readLine()) != null && !s.isBlank()) {   // isBlank()가 null까지 체크하는 건 백준 자바 (Java 11)에 지원되지 않는 기능
            root.insert(Integer.parseInt(s));
        }

        // PostOrder (left->right->root)
        dfs(root);

        System.out.println(sb);
    }

    private static StringBuilder sb = new StringBuilder();

    private static void dfs(Node cur){
        if (cur == null) return;

        dfs(cur.left);
        dfs(cur.right);
        sb.append(cur.data).append("\n");
    }
}
