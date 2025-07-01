package Algorithm.Algorithm25.Java.BOJ1539;

import java.util.Scanner;

/*
* 이진 검색 트리
* 2s, 256MB
*
* N: 2.5 * 10^5
* 0~N-1의 정수가 중복 없이 채워져 있는 배열 P
* P로 이진 검색 트리를 만들었을 때, 모든 노드의 높이의 합을 출력하라.
*
* 만드는 경우: 최악의 경우 O(N^2)
* 만들지 않고 높이를 계산하는 방법이 있을까?
* 일단 만들어보자..
* */
public class t0 {

    static int ans = 0;

    static class Vertex{
        Vertex lChild = null, rChild = null;
        int data, depth;
        Vertex(int data, int depth){
            this.data = data;
            this.depth = depth;
        }
    }

    public static void insert(Vertex V, int X){
        if (X < V.data) { // 왼쪽으로 가야 하는데 (V.data가 더 큼)
            // 왼쪽에 자식이 있으면 재귀, 자식이 없으면 만듦
            if (V.lChild != null) insert(V.lChild, X);
            else {
                V.lChild = new Vertex(X, V.depth + 1);
                ans += V.depth + 1;
            }
        }
        else { // 오른쪽으로 가야하는데 (X가 더 큼)
            if (V.rChild != null) insert(V.rChild, X);
            else {
                V.rChild = new Vertex(X, V.depth + 1);
                ans += V.depth + 1;
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] P = new int[N];
        for (int i=0; i<N; i++){
            P[i] = sc.nextInt();
        }

        Vertex root = new Vertex(P[0], 1);
        ans = 1;
        for (int i=1; i<N; i++){
            insert(root, P[i]);
        }
        System.out.println(ans);
    }

}
