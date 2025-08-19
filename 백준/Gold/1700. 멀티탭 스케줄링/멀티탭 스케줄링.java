// package Algorithm.Algorithm25.Java.BOJ1700;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1700. 멀티탭 스케줄링 (2, 128)
 * https://www.acmicpc.net/problem/1700
 *
 * Queue: 나중에 사용되는 순서로
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   // 멀티탭 구멍 수 100
        int K = Integer.parseInt(st.nextToken());   // 전기용품 총 사용 횟수 100
        st = new StringTokenizer(br.readLine());
        int ans = 0;

        int[] use = new int[K];
        for (int i=0; i<K; i++){
            use[i] = Integer.parseInt(st.nextToken());
        }
        
        // 번호별 사용 위치 인덱스 저장
        HashMap<Integer, Queue<Integer>> pos = new HashMap<>();
        for (int i=0; i<K; i++) {
            pos.putIfAbsent(use[i], new LinkedList<>());
            pos.get(use[i]).add(i);
        }

        HashSet<Integer> using = new HashSet<>();  // 멀티탭에 꽂힌 (사용 중인) 것

        for (int u : use){
            pos.get(u).poll();                  // 현재 사용 위치를 큐에서 제거
            if (using.contains(u)) continue;    // 이미 사용 중이면 패스
            
            // 멀티탭이 가득 찬 경우
            if (using.size() == N){
                
                // 사용 중인 제품 중에 가장 나중에 사용할 제품 찾아 제거
                int rmv = -1;
                int farthest = -1;

                for (int d: using){
                    Queue<Integer> q = pos.get(d);
                    int idx = (q.isEmpty())? Integer.MAX_VALUE: q.peek();
                    if (idx > farthest){
                        farthest = idx;
                        rmv = d;
                    }
                }

                using.remove(rmv);
                ans ++;
            }

            // 제거를 완료했거나 제거할 필요가 없으니 새롭게 사용할 제품을 사용 처리
            using.add(u);
        }

        System.out.println(ans);
    }
}
