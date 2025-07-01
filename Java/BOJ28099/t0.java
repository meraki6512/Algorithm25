package Algorithm.Algorithm25.Java.BOJ28099;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
* 이상한 배열
* https://www.acmicpc.net/problem/28099
* 이상한 배열: 같은 값이 있을 때 그 사이의 값이 그 값보다 작거나 같은 배열
* 이상한 배열이면 Yes, 아니면 No를 출력하라.
*
* T가 2*10^5, N이 2*10^5 * -> 로그 시간으로 비교 처리해야 함 -> TreeSet
* */

public class t0 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());    // 2*10^5

        for (int test_case = 0; test_case < T; test_case++){

            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            boolean ans = true;

            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i=0; i<N; i++){
                int n = Integer.parseInt(st.nextToken());
//                System.out.println("n="+n+", i="+i);
                if (map.containsKey(n)){
//                    System.out.println("map[" + n + "]: " + map.get(n));
//                    System.out.println("map.higherEntry("+n+")=" + map.higherEntry(n));
                    if (map.higherEntry(n) == null) continue;
                    else {
                        int idx = map.higherEntry(n).getValue();
                        if (idx > map.get(n) && idx < i){
                        ans = false;
                        break;
                        }
                    }
                }
                map.put(n, i);
            }

            sb.append(ans ? "Yes\n" : "No\n");

        }

        System.out.println(sb);

    }
}
