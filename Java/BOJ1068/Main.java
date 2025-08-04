package Algorithm.Algorithm25.Java.BOJ1068;

import java.util.*;

public class Main {

    private static int N;
    private static int[] parent;

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();                           // 50
        parent = new int[N];
        int root = 0;
        for (int i=0; i<N; i++) {
            parent[i] = sc.nextInt();
            if (parent[i] == -1) root = i;
        }
        int rmv = sc.nextInt();

        if (parent[rmv] == -1) {
            System.out.println(0);
            return;
        }

        // 지우기
        remove(rmv);

        // 리프노드 세기
        int[] children = new int[N];
        for (int i=0; i<N; i++){
            if (parent[i] >= 0){
                // 자식 수 세기
                children[parent[i]]++;
            }
        }
        
        int ans = 0;
        for (int i=0; i<N; i++){
            // 삭제되지 않은 것들 중에, 자식이 없는 것
            if (parent[i] >= -1 && children[i] == 0) ans ++;
        }
        System.out.println(ans);
    }

    private static void remove(int rmv){
        parent[rmv] = -2;

        for (int i=0; i<N; i++){
            if (parent[i] == rmv){
                remove(i);
            }
        }
    }
}

// 4
//-1 3 0 2
//2

// 0 1 2 3
// X 3 0 2
// X 3 - -
// X - - -

//    0
//   2
//  3
// 1