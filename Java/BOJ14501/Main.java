package Algorithm.Algorithm25.Java.BOJ14501;

/*
14501. 퇴사 (2s, 512MB)
T[i] 상담일수 (5)
P[i] 상담비용 (1000)
N 일간 상담의 최대 수익 = ?

dp!
..
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static class Consult{
        int T, P;
        Consult(int T, int P){
            this.T = T;
            this.P = P;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());    // 15
        Consult[] consults = new Consult[N+1];

        for (int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            consults[i] = new Consult(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int ans = 0;
        int[] dp = new int[N+1];
        for (int i=1; i<=N; i++){                   // 현재 i를 기준으로
            for (int j = 1; j <= i; j++){           // 이전 값들 중에
                if (j + consults[j].T -1 == i) {    // idx + Ti 다음 값이 현재 i 값인 경우
                    // 현재 dp vs (dp[해당 idx] + 현재 P)
                    dp[i] = Math.max(dp[i], (dp[i - consults[j].T] + consults[j].P));
                }
            }
            // 현재까지의 최댓값을 계속 저장
            ans = dp[i] = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}
