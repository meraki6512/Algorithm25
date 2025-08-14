package Algorithm.Algorithm25.Java.BOJ2961;
import java.util.*;

/**
 * 재료 N개 중 최소 1개 사용     10
 * 신맛 S 곱                   1,000,000,000
 * 쓴맛 B 합                   1,000,000,000
 * 가장 작은 신맛과 쓴맛의 차이
 */

public class Main{

    private static class Ingradient {
        int s, b;
        Ingradient(int s, int b){
            this.s = s;
            this.b = b;
        }
    }

    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Ingradient[] ingradients = new Ingradient[n];
        for (int i = 0; i< n; i++) ingradients[i] = new Ingradient(sc.nextInt(), sc.nextInt());

        long ans = Long.MAX_VALUE;

        // 2^N개에 대해 체크 (bit: 각 재료를 사용하냐 안하냐)
        for (int i = 1; i < (1<<n) ; i++){

            long S = 1, B = 0;
            int check = i;
            int idx = 0;
            while (check > 0){
                if ((check & 1) == 1){
                    Ingradient inc = ingradients[idx];
                    S *= inc.s;
                    B += inc.b;
                }
                check >>= 1;
                idx ++;
            }

            ans = Math.min(ans, Math.abs(S-B));

        }

        System.out.println(ans);
    }
}

/**
 * 2^N개의 케이스에 대해 검사하는 방식으로 풀음.
 * -> dfs 로 각 재료를 선택하는 경우와 선택하지 않는 경우로 분기 처리해 풀 수도 있다!
 */