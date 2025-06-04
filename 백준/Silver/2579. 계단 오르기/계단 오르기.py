# 2579. 계단 오르기
# 1s, 128MB

# 한 번에 1 또는 2 계단씩 오를 수 있음
# 연속 3 계단은 X

import sys
input = sys.stdin.readline
N = int(input())
st = [int(input()) for _ in range(N)]

ans = 0

if N == 1:
    print(st[0])

else:
    # 각 계단별로 1 or 2 계단 올라가는 경우
    # 1 계단: max (이전에 1인 경우 vs 이전에 2인 경우)
    # 2 계단: max (두 칸전 경우들)

    dp = [[0] * 2 for _ in range(N)]
    dp[0] = [st[0], 0]
    dp[1] = [st[0] + st[1], st[1]]
    # dp[1] = list(map(lambda x: x + st[1], dp[0]))

    for i in range(2, N):

        # 연속 3 계단 불가: 이전에 2인 경우만 선택  
        dp[i][0] = dp[i-1][1] + st[i]
        dp[i][1] = max(dp[i-2]) + st[i]

    print(max(dp[-1]))