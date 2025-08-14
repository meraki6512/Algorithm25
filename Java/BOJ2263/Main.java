package Algorithm.Algorithm25.Java.BOJ2263;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 트리의 순회 (5s, 128MB)
 * n개의 정점 이진 트리 (10^5)
 * inorder와 postorder가 주어졌을 때, preorder를 구하라
 * 
 * 
 * 1234567
 * inorder: LCR: 425[1]637 -> 루트를 기준으로 왼/오
 * postorder: LRC: 452/673[1] -> 가장 오른쪽값은 루트
 * preorder: CLR: 1
 * 
 * 그냥 베낌 -> 다시 풀기
 */

public class Main {

    private static int n;
    private static int[] inorder, postorder, preorder;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        inorder = new int[n+1];
        postorder = new int[n+1];
        preorder = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++){
            inorder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++){
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        getPreorder(1, n, 1, n);

        for (int n : preorder) {
            if (n==0) continue;
            bw.write(n + " ");
        }
        bw.flush();
    }

    private static int idx = 0;

    /**
     * @param is inorder start point
     * @param ie inorder end point
     * @param ps postorder start poinrt
     * @param pe postorder end point
     */
    private static void getPreorder(int is, int ie, int ps, int pe){

        if (is <= ie && ps <= pe){

            // 루트 노드 찾기 (post 배열에서 -> preorder의 root)
            preorder[idx++] = postorder[pe]; // 포스트 오더의 가장 오른쪽은 루트노드

            // 루트 노드 위치 찾기 (inorder 배열에서)
            int pos = is;
            for (int i=is; i<=ie;i++){
                if (inorder[i] == postorder[pe]){
                    pos = i;
                    break;
                }
            }

            // pos (루트 위치)를 기준으로 왼쪽 서브트리 탐색 후 -> 오른쪽 서브르리 탐색
            int leftSubtreeSize = pos - is;
            /**
             * 왼쪽 서브트리
             * inorder 범위: is ~ post - 1: pos - is
             * postorder 범위: ps ~ ps + lSS - 1
             */
            getPreorder(is, pos-1, ps, ps + leftSubtreeSize - 1);
            /**
             * 오른쪽 서브트리
             * inorder 범위: post + 1 ~ ie
             */
            getPreorder(pos+1 , ie, ps + leftSubtreeSize, pe - 1);

        }
    }
}
