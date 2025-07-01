package Algorithm.Algorithm25.Java.BOJ23326;

/*
* 홍익 투어리스트
* https://www.acmicpc.net/problem/23326
* 원형으로 배치된 N개의 구역
* M개의 쿼리를 진행하라.
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q1 = Integer.parseInt(st.nextToken());
        TreeSet<Integer> attr = new TreeSet<>();

        // 명소인 곳만 TreeSet에 추가
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i<= n; i++){
            if (Integer.parseInt(st.nextToken())==1) attr.add(i);
        }

        // 쿼리 진행
        int cur = 0;
        for (int q = 0; q< q1; q++){                                // 10^5
            st = new StringTokenizer(br.readLine());

            switch (Integer.parseInt(st.nextToken())){
                case 1: // 1 i: i번 구역 명소 토글
                    int i = Integer.parseInt(st.nextToken());
                    if (attr.contains(i)) attr.remove(i);       // logN
                    else attr.add(i);
                    break;

                case 2: // 2 x: x만큼 이동 (10^9)
                    int x = Integer.parseInt(st.nextToken());
                    cur += x;
                    if (cur > n) cur %= n;
                    break;

                case 3: // 명소에 도달하기 위해 시계방향으로 최소 몇 칸 움직여야 하는지 출력 (명소가 없으면 -1)

                    if (attr.isEmpty()){
                        System.out.println(-1);
                        continue;
                    }
                    
                    // 가장 가까운 위치 찾기 (크거나 같은 것 중에 없으면 가장 작은 위치)
                    int cur_idx = cur + 1;
                    Integer nearest = attr.ceiling(cur_idx);      // logN
                    int pos = (nearest != null) ?  nearest : attr.first();
                    System.out.println((pos >= cur_idx) ? (pos - cur_idx) : (pos + n - cur_idx));
                    break;
            }

        }

    }
}
