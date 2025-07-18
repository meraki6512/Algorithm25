// package Algorithm.Algorithm25.Java.BOJ1965;

import java.util.*;

/*
1965. 상자넣기 (2s, 128MB)
https://www.acmicpc.net/problem/1965
 */

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();   // 1000

        int[] boxes = new int[n];
        for (int i=0; i<n; i++){
            boxes[i] = sc.nextInt();
        }

        // Sol
        // 현재의 오른쪽에 위치하는 박스들 중에서 가장 작은 값에 넣음
        // -> 예외 있음 ex) 1 3 4 5 6 2 면 1을 2가 아닌 3에 넣어야 유리
        // -> dp 써서 최대 박스 수 계산
        // dp: 부분 수열의 최대 길이
        // 1 6 2 5 7 3 5 6

        int ans = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i=0; i<n; i++){            // 현재 박스
            for (int j=0; j<i; j++){        // 의 왼쪽에 있는 박스들 중에서
                if (boxes[j] < boxes[i]){   // 더 작은 크기의 박스가 있다면
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}